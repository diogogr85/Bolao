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

package com.diogogr85.bolaotungao.ui.MatchBets

import android.util.Log
import com.diogogr85.bolaotungao.data.source.repositories.BetsRepository
import com.diogogr85.bolaotungao.ui.base.BasePresenter
import com.diogogr85.bolaotungao.ui.matches.MatchesView
import com.google.gson.Gson

class MatchBetsPresenter(private val mBetsRepository: BetsRepository): BasePresenter<MatchBetsView>() {

    fun getMatchBets(team1: String, team2: String, date: String, time: String) {
            getView()?.showProgress(true)

            mBetsRepository.getMatchBets(team1, team2, date, time,
                    { arrayOfBets ->
                        getView()?.showProgress(false)
                        getView()?.onSuccess(arrayOfBets)
                        Log.d("ROOM-SUCCESS", Gson().toJson(arrayOfBets))
                    },
                    {
                        getView()?.showProgress(false)
                        getView()?.onError()
                    })
    }

}