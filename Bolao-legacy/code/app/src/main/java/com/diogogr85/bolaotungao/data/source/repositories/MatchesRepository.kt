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
import com.diogogr85.bolaotungao.data.models.Match
import com.diogogr85.bolaotungao.data.models.Team
import com.diogogr85.bolaotungao.data.source.remote.network.BaseNetworkCall
import com.diogogr85.bolaotungao.data.source.remote.network.NetworkManager
import com.diogogr85.bolaotungao.data.source.remote.MatchesApi
import com.diogogr85.bolaotungao.utils.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MatchesRepository(): BaseNetworkCall() {

    private val matchesClient=
            NetworkManager<MatchesApi>(MatchesApi::class.java).mService

    companion object {
        fun getRepository(): MatchesRepository {
            return MatchesRepository()
        }
    }

    fun getMatches(onSuccess: (HashMap<String, ArrayList<Match>>) -> Unit,
                   onError: (trowable: Throwable) -> Unit) {
        doSubscribe(
                matchesClient.getMatches(assembleQuery(API_CONTENT_SHEET_ID, SHEET_MATCHES_NAME))
                        .subscribeOn(Schedulers.io())
                        .flatMap { matchesResponse ->
                            val matchesMap = HashMap<String, ArrayList<Match>>()

                            var currentRound = ""
                            var currentDate = ""
                            for (rawMatch in matchesResponse.matches) {

                                val team1 = Team(rawMatch.mTeam1)
                                val team2 = Team(rawMatch.mTeam2)

                                val team1Score = rawMatch.mTime1Score.toIntOrNull() ?: -1
                                val team2Score = rawMatch.mTime2Score.toIntOrNull() ?: -1

                                val winner = if (team1Score > team2Score) {
                                    team1.mName
                                } else if (team1Score < team2Score) {
                                    team2.mName
                                } else {
                                    GAME_RESULT_DRAW
                                }

                                val roundList = if (currentRound != rawMatch.mRound) {
                                    currentRound = rawMatch.mRound
                                    ArrayList<Match>()
                                } else {
                                    matchesMap[currentRound]!!
                                }

                                if (currentDate != rawMatch.mDate ||
                                        currentRound != rawMatch.mRound) {
                                    currentDate = rawMatch.mDate

                                    //Adding new date
                                    roundList.add(Match(mTeam1 = Team(""),
                                            mTeam2 = Team(""),
                                            mTeam1Score = team1Score, mTeam2Score = team2Score,
                                            mWinner = "",
                                            mDate = currentDate.getDate(),
                                            mTime = "", mVenue = "",
                                            mGroup = "", mRound = ""))

                                }
                                roundList.add(Match(mTeam1 = team1, mTeam2 = team2,
                                        mTeam1Score = team1Score, mTeam2Score = team2Score,
                                        mWinner = winner, mDate = currentDate.getDate(),
                                        mTime = DateUtils.getTime(rawMatch.mTime),
                                        mVenue = rawMatch.mVenue,
                                        mGroup = rawMatch.mGroup, mRound = currentRound))

                                if (!matchesMap.containsKey(currentRound)) {
                                    matchesMap.put(currentRound, roundList)
                                }
                            }

                            Observable.just(matchesMap)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({matchesMap -> onSuccess(matchesMap)},
                                {throwable -> onError(throwable)},
                                {Log.d("GET::MATCH", "onComplete")})
        )
    }

    override fun assembleQuery(sheetId: String, sheetName: String): HashMap<String, String> {
        val queryMap = HashMap<String, String>()
        queryMap.put(SHEET_ID_ATTRIBUTE, sheetId)
        queryMap.put(SHEET_NAME_ATTRIBUTE, sheetName)

        return queryMap
    }
}