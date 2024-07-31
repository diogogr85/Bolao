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

package com.diogogr85.bolaotungao.data.source.remote.network

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseNetworkCall {

    private val mCompositeDisposable = CompositeDisposable()

    fun clear() {
        mCompositeDisposable.clear()
    }

    protected fun doSubscribe(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    abstract fun assembleQuery(sheetId: String, sheetName: String): HashMap<String, String>
}