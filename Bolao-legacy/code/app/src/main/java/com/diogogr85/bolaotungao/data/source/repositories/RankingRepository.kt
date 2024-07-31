/*
 * Copyright (c) 2018 Diogo Ribeiro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.diogogr85.bolaotungao.data.source.repositories

import android.util.Log
import com.diogogr85.bolaotungao.data.models.Ranking
import com.diogogr85.bolaotungao.data.source.remote.network.BaseNetworkCall
import com.diogogr85.bolaotungao.data.source.remote.network.NetworkManager
import com.diogogr85.bolaotungao.data.source.remote.RankingApi
import com.diogogr85.bolaotungao.utils.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RankingRepository: BaseNetworkCall() {

    private val mRankingClient = NetworkManager<RankingApi>(RankingApi::class.java).mService

    companion object {
        fun getRepository(): RankingRepository {
            return RankingRepository()
        }
    }

    fun getRanking(onSuccess: (ArrayList<Ranking>) -> Unit,
                   onError: (trowable: Throwable) -> Unit) {

        doSubscribe(
                mRankingClient.getRanking(assembleQuery(API_RANKING_SHEET_ID, SHEET_RANKING_NAME))
                        .subscribeOn(Schedulers.io())
                        .flatMap {rankingResponse ->
                            val ranking = ArrayList<Ranking>()

                            for (rawRanking in rankingResponse.mRanking) {
                                ranking.add(rawRanking.parseToRanking())
                            }

                            ranking.sortWith(Comparator {o1, o2->
                                var comparatorValue = o2.mPoints.compareTo(o1.mPoints)
                                if (comparatorValue == 0) {
                                    comparatorValue = o2.mGamesScored.compareTo(o1.mGamesScored)

                                    if (comparatorValue == 0) {
                                        comparatorValue =
                                                o2.mHalfGamesScored.compareTo(o1.mHalfGamesScored)

                                        if (comparatorValue == 0) {
                                            comparatorValue = o2.mWinners.compareTo(o1.mWinners)
                                        }
                                    }
                                }

                                comparatorValue
                            })

                            Observable.just(ranking)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ranking -> onSuccess(ranking)},
                                {throwable -> onError(throwable)},
                                { Log.d("GET::RANKING", "onComplete")})
        )

    }

    override fun assembleQuery(sheetId: String, sheetName: String): HashMap<String, String> {
        val queryMap = HashMap<String, String>()
        queryMap.put(SHEET_ID_ATTRIBUTE, sheetId)
        queryMap.put(SHEET_NAME_ATTRIBUTE, sheetName)

        return queryMap
    }
}