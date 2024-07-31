/*
 * Copyright (c) 2018 Diogo Ribeiro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.diogogr85.bolaotungao.data.source.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.diogogr85.bolaotungao.data.models.BetsDb
import com.diogogr85.bolaotungao.data.models.PlayerMatchBet

@Database(entities = arrayOf(BetsDb::class, PlayerMatchBet::class),
        version = 1, exportSchema = false)
@TypeConverters(BetsConverters::class)
abstract class BetsDatabase: RoomDatabase() {

    abstract fun betsDao(): BetsDao

    companion object {

        @Volatile private var sInstance: BetsDatabase? = null

        fun getInstance(context: Context): BetsDatabase =
                sInstance
                        ?: synchronized(this) {
                    sInstance
                            ?: buildDatabase(context)
                            .also {
                                sInstance = it
                            }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        BetsDatabase::class.java,
                        "bolao.db")
                        .build()

    }

}