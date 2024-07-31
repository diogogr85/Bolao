/*
 *
 *  * Copyright (c) 2018 Diogo Ribeiro
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.diogogr85.bolaotungao.data.source.repositories

import android.util.Log
import com.diogogr85.bolaotungao.appContext
import com.diogogr85.bolaotungao.data.models.Bets
import com.diogogr85.bolaotungao.data.models.BetsDb
import com.diogogr85.bolaotungao.data.models.PlayerMatchBet
import com.diogogr85.bolaotungao.data.source.local.database.BetsDao
import com.diogogr85.bolaotungao.data.source.remote.BetsApi
import com.diogogr85.bolaotungao.data.source.remote.network.BaseNetworkCall
import com.diogogr85.bolaotungao.data.source.remote.network.NetworkManager
import com.diogogr85.bolaotungao.di.DatabaseInjector
import com.diogogr85.bolaotungao.utils.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class BetsRepository(val mPlayerBetsDao: BetsDao): BaseNetworkCall() {

    private val mBetsClient = NetworkManager(BetsApi::class.java).mService

    companion object {
        fun getRepository(): BetsRepository{
            return BetsRepository(DatabaseInjector.provideBetsDatabase(appContext))
        }
    }

    fun fetchBets(matchBets: ArrayList<Pair<String, String>>,
                  onSuccess: (playerAdded: String) -> Unit,
                  onError: (throwable: Throwable) -> Unit,
                  onComplete: (isCompleted: Boolean) -> Unit) {

        val queryMapList = ArrayList<HashMap<String, String>>()
        for (playerMatchBets in matchBets) {
            val map = assembleQuery(API_CONTENT_SHEET_ID, playerMatchBets.second)
            map.put(PLAYER_NAME_ATTRIBUTE, playerMatchBets.first)
            queryMapList.add(map)
        }

        val dataSize = queryMapList.size - 1
        var index = 0
        doSubscribe(
            Observable.fromIterable(queryMapList).flatMap { params ->
                Observable.just(
                        mBetsClient.getBets(params)
                                .subscribeOn(Schedulers.io())
                                .flatMap { matchesResponse ->
                                    for (match in matchesResponse.matches) {
                                        val matchBet = PlayerMatchBet(mTeam1 = match.mTeam1,
                                                mTeam1Score = match.mTime1Score,
                                                mTeam2 = match.mTeam2,
                                                mTeam2Score = match.mTime2Score)
                                        mPlayerBetsDao.insertMatchBet(matchBet)

                                        val bets = BetsDb(mPlayerName = params[PLAYER_NAME_ATTRIBUTE]!!,
                                                mMatchDate = match.mDate.getDate(),
                                                mMatchTime = DateUtils.getTime(match.mTime),
                                                mMatchBetId = matchBet.mId)
                                        mPlayerBetsDao.insert(bets)
                                    }
                                    Observable.just(params[SHEET_NAME_ATTRIBUTE]!!)
                                }
                                .subscribe({ playerAdded -> onSuccess(playerAdded) },
                                        {throwable -> onError(throwable)},
                                        {
                                            if (index >= dataSize) {
                                                onComplete(true)
                                                Log.d("GET::PLAYERS-MATCHES", "onComplete")
                                            } else {
                                                index++
                                                Log.d("GET::PLAYERS-MATCHES", index.toString())
                                            }
                                        })
                )

            }.subscribe()
        )

    }

    fun getMatchBets(team1: String, team2: String, date: String, time: String,
                     onSuccess: (Array<Bets>) -> Unit,
                     onError: (throwable: Throwable) -> Unit) {

        mPlayerBetsDao.getBetsByMatch(team1, team2, date, time)
                .subscribeOn(Schedulers.io())
                .flatMap { bets ->
                    bets.sortWith(kotlin.Comparator { o1, o2 ->
                        o1.mPlayerName.compareTo(o2.mPlayerName)
                    })

                    Flowable.just(bets)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({bets -> onSuccess(bets)},
                        {throwable ->  onError(throwable)},
                        {Log.d("GET::DATABASE-BETS", "onComplete")})
    }

    override fun assembleQuery(sheetId: String, sheetName: String): HashMap<String, String> {
        val queryMap = HashMap<String, String>()
        queryMap.put(SHEET_ID_ATTRIBUTE, sheetId)
        queryMap.put(SHEET_NAME_ATTRIBUTE, sheetName)

        return queryMap
    }
}