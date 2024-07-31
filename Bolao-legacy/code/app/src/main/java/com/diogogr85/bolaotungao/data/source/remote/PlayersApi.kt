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

package com.diogogr85.bolaotungao.data.source.remote

import com.diogogr85.bolaotungao.data.source.repositories.models.PlayersResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface PlayersApi {

    @GET("exec")
    fun getPlayers(@QueryMap queryMap: HashMap<String, String>): Observable<PlayersResponse>

}