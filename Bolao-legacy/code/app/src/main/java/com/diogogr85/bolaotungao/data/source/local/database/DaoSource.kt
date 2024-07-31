/*
 *
 *  Copyright (c) 2018 Diogo Ribeiro
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.diogogr85.bolaotungao.data.source.local.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.diogogr85.bolaotungao.data.models.Bets
import com.diogogr85.bolaotungao.data.models.BetsDb
import com.diogogr85.bolaotungao.data.models.PlayerMatchBet
import io.reactivex.Flowable

@Dao
interface BetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchBet(matchBet: PlayerMatchBet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bets: BetsDb)

    @Query("SELECT * FROM player_bets")
    fun getBets(): Flowable<Array<BetsDb>>

    @Query("SELECT p.id AS mId , p.player AS mPlayerName, p.date AS mMatchDate, " +
            "p.time AS mMatchTime, m.team1_score AS mTeam1Score, m.team2_score AS mTeam2Score " +
            "FROM player_bets as p, player_match_bet as m WHERE p.match_bet_id LIKE m.player_match_id " +
            "AND m.team1 LIKE :team1 AND m.team2 LIKE :team2 AND p.date LIKE :matchDate AND p.time LIKE :matchTime")
    fun getBetsByMatch(team1: String, team2: String,
                       matchDate: String, matchTime: String): Flowable<Array<Bets>>

//    id,match_bet_id,player,date,time,player_match_id,team1,team2,team1_score,team2_score
//    @SerializedName("id")
//    val mId: String,
//    @SerializedName("player")
//    val mPlayerName: String,
//    @SerializedName("date")
//    val mMatchDate: String,
//    @SerializedName("time")
//    val mMatchTime: String,
//    @SerializedName("team1_score")
//    var mTeam1Score: String,
//    @SerializedName("team2_score")
//    var mTeam2Score: String



    @Query("SELECT * FROM player_bets WHERE player LIKE :playerName")
    fun getBetsByName(playerName: String): Flowable<Array<BetsDb>>
}