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

package com.diogogr85.bolaotungao.ui.Players

import android.content.SharedPreferences
import android.util.Log
import com.diogogr85.bolaotungao.data.models.Player
import com.diogogr85.bolaotungao.data.source.local.SHARED_PREFS_BOLAO_DATABASE_FETCHED
import com.diogogr85.bolaotungao.data.source.local.put
import com.diogogr85.bolaotungao.data.source.repositories.BetsRepository
import com.diogogr85.bolaotungao.data.source.repositories.PlayersRepository
import com.diogogr85.bolaotungao.ui.base.BasePresenter


class PlayersPresenter(private val mPlayersRepository: PlayersRepository,
                       private val mPlayerBetsRepository: BetsRepository,
                       private val mPrefs: SharedPreferences): BasePresenter<PlayersView>() {

    fun getBets(forceUpdate: Boolean) {
        if (forceUpdate) {
            getView()?.showProgress(true)

            mPlayersRepository.getPlayers(
                    ::onSuccess,
                    ::onError
            )
        }
    }

    private fun onSuccess(listSuccess: ArrayList<Player>) {
        getView()?.showProgress(false)
        getView()?.onPlayersSuccess(listSuccess)

        if (!mPrefs.getBoolean(SHARED_PREFS_BOLAO_DATABASE_FETCHED, false)) {
            val matchBets = ArrayList<Pair<String, String>>()
            for (player in listSuccess) {
                matchBets.add(Pair(player.mName, player.mBetsId))
            }
            fetchPlayersBets(matchBets)
        }
    }

    private fun onError(throwable: Throwable) {
        getView()?.showProgress(false)
        getView()?.onError()
    }

    private fun fetchPlayersBets(matchBets: ArrayList<Pair<String, String>>) {
        mPlayerBetsRepository.fetchBets(matchBets,
                ::onDatabaseFetchedSuccess,
                ::error,
                ::onPlayersBetsFetchedComplete)
    }

    private fun onDatabaseFetchedSuccess(playerAdded: String) {
        Log.d("PLAYERS-MATCHES-ADDED", playerAdded)
    }

    private fun onPlayersBetsFetchedComplete(isCompleted: Boolean) {
        if (isCompleted) {
            mPrefs.put(SHARED_PREFS_BOLAO_DATABASE_FETCHED, true)
            getView()?.showMessage("Apostas computadas com sucesso.")
            Log.d("PLAYERS-ADDED-COMPLETE", isCompleted.toString())
        }
    }

}