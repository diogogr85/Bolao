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
import com.diogogr85.bolaotungao.data.models.Group
import com.diogogr85.bolaotungao.data.models.Team
import com.diogogr85.bolaotungao.data.source.remote.network.BaseNetworkCall
import com.diogogr85.bolaotungao.data.source.remote.network.NetworkManager
import com.diogogr85.bolaotungao.data.source.remote.GroupsApi
import com.diogogr85.bolaotungao.data.source.remote.TableApi
import com.diogogr85.bolaotungao.data.source.repositories.models.TableResponse
import com.diogogr85.bolaotungao.utils.*
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.HashMap

class GroupsRepository: BaseNetworkCall() {

    private val mGroupsClient=
            NetworkManager<GroupsApi>(GroupsApi::class.java).mService
    private val mTableClient =
            NetworkManager<TableApi>(TableApi::class.java).mService

    companion object {
        fun getRepository(): GroupsRepository {
            return GroupsRepository()
        }
    }

    fun getGroups(onSuccess: (List<Group>) -> Unit,
                  onError: () -> Unit) {

        doSubscribe(
                mGroupsClient.getGroups(assembleQuery(API_CONTENT_SHEET_ID, SHEET_GROUPS_NAME))
                        .subscribeOn(Schedulers.io())
                        .flatMap { responseList ->
                            Log.d("GROUPS", "GET::GROUPS")

                            val map = HashMap<String, Group>()
                            if (!responseList.groups.isEmpty()) {
                                for (group in responseList.groups) {
                                    val teamList = ArrayList<Team>()
                                    teamList.add(Team(group.mTeam1))
                                    teamList.add(Team(group.mTeam2))
                                    teamList.add(Team(group.mTeam3))
                                    teamList.add(Team(group.mTeam4))

                                    map.put(group.mGroupName, Group(group.mGroupName, teamList))
                                }
                            }
                            Observable.combineLatest<TableResponse, HashMap<String, Group>,
                                                        Pair<TableResponse, HashMap<String, Group>>> (
                                    mTableClient.getTable(
                                            assembleQuery(API_CONTENT_SHEET_ID, SHEET_TABLE_NAME)),
                                    Observable.just(map),
                                    BiFunction{t, g -> Pair(t, g)}
                            )
                        }
                        .flatMap { pair ->
                            Log.d("GROUPS", "GET::TABLE")

                            val table = pair.first.mTable
                            val groupMap = pair.second

                            table.map {
                                val team = groupMap[it.mGrupo]?.findTeam(it.mTeam)

                                team?.mPoints = it.mPoints
                                team?.mMatchesPlayed = it.mMatchesPlayed
                                team?.mWon = it.mWon
                                team?.mDraw = it.mDraw
                                team?.mLoss = it.mLoss
                                team?.mGoalsFor = it.mGoalsFor
                                team?.mGoalsAgainst = it.mGoalsAgainst
                                team?.mDifference= it.mDifference
                            }

                            val groupsList = groupMap.values.toList()

//                            groupsList.sortedWith(Comparator { o1, o2 ->
//                                o1.groupName.compareTo(o2.groupName)
//                            })
                            Collections.sort(groupsList, kotlin.Comparator { o1, o2 ->
                                o1.groupName.compareTo(o2.groupName)
                            })
//                            groupsList.sortedBy { it.groupName }

                            for (group in groupsList) {
//                                group.mTeamList.sortedWith(Comparator({o1, o2 ->
//                                    o1.mPoints.compareTo(o2.mPoints)
//                                }))

                                Collections.sort(group.mTeamList, { o1, o2 ->
                                            var comparatorValue = o2.mPoints.compareTo(o1.mPoints)

                                            if (comparatorValue == 0) {
                                                comparatorValue =
                                                        o2.mDifference.compareTo(o1.mDifference)

                                                if (comparatorValue == 0) {
                                                    comparatorValue =
                                                            o2.mGoalsFor.compareTo(o1.mGoalsFor)
                                                }
                                            }


                                            comparatorValue
                                        })
                            }

                            Observable.just(groupsList)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ groupsList ->
                            Log.d("GROUPS", Gson().toJson(groupsList))
                            onSuccess(groupsList)
                        }, { e ->
                            e.printStackTrace()
                            onError()
                        }, { Log.d("GROUPS", "complete")}
                        )
        )
    }

    override fun assembleQuery(sheetId: String, sheetName: String): HashMap<String, String> {
        val queryMap = HashMap<String, String>()
        queryMap.put(SHEET_ID_ATTRIBUTE, sheetId)
        queryMap.put(SHEET_NAME_ATTRIBUTE, sheetName)

        return queryMap
    }

//    private fun assembleGroupsQuery(): HashMap<String, String> {
//        val queryMap = HashMap<String, String>()
//        queryMap.put(SHEET_ID_ATTRIBUTE, API_CONTENT_SHEET_ID)
//        queryMap.put(SHEET_NAME_ATTRIBUTE, SHEET_GROUPS_NAME)
//
//        return queryMap
//    }
//
//    private fun assembleTableQuery(): HashMap<String, String> {
//        val queryMap = HashMap<String, String>()
//        queryMap.put(SHEET_ID_ATTRIBUTE, API_CONTENT_SHEET_ID)
//        queryMap.put(SHEET_NAME_ATTRIBUTE, SHEET_TABLE_NAME)
//
//        return queryMap
//    }
}