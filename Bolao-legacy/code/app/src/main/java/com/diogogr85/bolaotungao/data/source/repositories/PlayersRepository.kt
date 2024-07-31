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
import com.diogogr85.bolaotungao.data.models.Player
import com.diogogr85.bolaotungao.data.source.remote.PlayersApi
import com.diogogr85.bolaotungao.data.source.remote.network.BaseNetworkCall
import com.diogogr85.bolaotungao.data.source.remote.network.NetworkManager
import com.diogogr85.bolaotungao.utils.API_CONTENT_SHEET_ID
import com.diogogr85.bolaotungao.utils.SHEET_ID_ATTRIBUTE
import com.diogogr85.bolaotungao.utils.SHEET_NAME_ATTRIBUTE
import com.diogogr85.bolaotungao.utils.SHEET_PLAYERS_NAME
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlayersRepository: BaseNetworkCall() {

    private val mPlayersClient = NetworkManager<PlayersApi>(PlayersApi::class.java).mService

    companion object {
        fun getRepository(): PlayersRepository {
            return PlayersRepository()
        }
    }

    fun getPlayers(onSuccess: (ArrayList<Player>) -> Unit,
                   onError: (throwable: Throwable) -> Unit) {
        doSubscribe(
                mPlayersClient.getPlayers(assembleQuery(API_CONTENT_SHEET_ID, SHEET_PLAYERS_NAME))
                        .subscribeOn(Schedulers.io())
                        .flatMap { PlayersResponse ->
                            val players = ArrayList<Player>()
                            for (rawPlayers in PlayersResponse.mPlayers) {
                                players.add(Player(rawPlayers.mName,
                                        rawPlayers.mTableUrl,
                                        rawPlayers.mProfileImage,
                                        rawPlayers.mBetsId))
                            }

                            Observable.just(players)
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({players -> onSuccess(players)},
                                {throwable -> onError(throwable)},
                                { Log.d("GET::PLAYERS", "onComplete")})
        )
    }

    override fun assembleQuery(sheetId: String, sheetName: String): HashMap<String, String> {
        val queryMap = HashMap<String, String>()
        queryMap.put(SHEET_ID_ATTRIBUTE, sheetId)
        queryMap.put(SHEET_NAME_ATTRIBUTE, sheetName)

        return queryMap
    }

}