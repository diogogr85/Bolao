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

package com.diogogr85.bolaotungao.ui.matches

import android.content.SharedPreferences
import com.diogogr85.bolaotungao.data.source.local.SHARED_PREFS_BOLAO_DATABASE_FETCHED
import com.diogogr85.bolaotungao.data.source.repositories.MatchesRepository
import com.diogogr85.bolaotungao.ui.base.BasePresenter

class MatchesPresenter(private val mMatchesRepository: MatchesRepository,
                       private val mPrefs: SharedPreferences): BasePresenter<MatchesView>() {

    fun getMatches(forceUpdate: Boolean) {
        if (forceUpdate) {
            getView()?.showProgress(true)

            mMatchesRepository.getMatches(
                    { listSuccess ->
                        getView()?.showProgress(false)
                        getView()?.onMatchesSuccess(listSuccess)
                    },
                    {
                        getView()?.showProgress(false)
                        getView()?.onError()
                    }
            )
        }
    }

    fun isMatchBetsFetched(): Boolean {
        return mPrefs.getBoolean(SHARED_PREFS_BOLAO_DATABASE_FETCHED, false)
    }

}