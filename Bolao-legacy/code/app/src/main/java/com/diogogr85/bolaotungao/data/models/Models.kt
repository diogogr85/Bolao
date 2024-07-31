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

package com.diogogr85.bolaotungao.data.models

import android.arch.persistence.room.*
import android.os.Parcelable
import android.support.annotation.NonNull
import com.diogogr85.bolaotungao.utils.EMPTY_TEXT
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.UUID

data class Group(
        val groupName: String,
        val mTeamList: List<Team>) {

    fun findTeam(teamName: String): Team? {
        for (team in mTeamList) {
            if (teamName == team.mName) {
                return team
            }
        }

        return null
    }
}

@Parcelize
data class Match(
        @NonNull val mTeam1: Team,
        @NonNull val mTeam2: Team,
        var mTeam1Score: Int,
        var mTeam2Score: Int,
        val mWinner: String,
        val mDate: String,
        val mTime: String,
        val mVenue: String,
        val mGroup: String,
        val mRound: String): Parcelable {


    fun showTeam1Score(): String {
        return if (mTeam1Score >= 0) mTeam1Score.toString() else EMPTY_TEXT
    }

    fun showTeam2Score(): String {
        return if (mTeam2Score >= 0) mTeam2Score.toString() else EMPTY_TEXT
    }
}

data class Ranking(
        val mPlayerName: String,
        val mPoints: Int,
        val mGamesScored: Int,
        val mHalfGamesScored: Int,
        val mWinners: Int)

@Parcelize
@Entity
data class Team(
        @PrimaryKey
        @ColumnInfo(name = "name")
        val mName: String): Parcelable {

    @ColumnInfo(name = "points")
    var mPoints: Int = 0
    @ColumnInfo(name = "matches_played")
    var mMatchesPlayed: Int = 0
    @ColumnInfo(name = "won")
    var mWon: Int = 0
    @ColumnInfo(name = "draw")
    var mDraw: Int = 0
    @ColumnInfo(name = "loss")
    var mLoss: Int = 0
    @ColumnInfo(name = "goals_for")
    var mGoalsFor: Int = 0
    @ColumnInfo(name = "goals_against")
    var mGoalsAgainst: Int = 0
    @ColumnInfo(name = "difference")
    var mDifference: Int = 0

    fun isDateType(): Boolean {
        return mName.isEmpty()
    }
}

@Parcelize
data class Player(
        val mName: String,
        val mBetTableUrl: String,
        val mProfileImage: String,
        val mBetsId: String): Parcelable

@Entity(tableName = "player_match_bet")
data class PlayerMatchBet(
        @PrimaryKey
        @ColumnInfo(name = "player_match_id")
        val mId: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "team1")
        @NonNull val mTeam1: String,
        @ColumnInfo(name = "team2")
        @NonNull val mTeam2: String,
        @ColumnInfo(name = "team1_score")
        var mTeam1Score: String,
        @ColumnInfo(name = "team2_score")
        var mTeam2Score: String)

@Entity(tableName = "player_bets",
        foreignKeys = arrayOf(ForeignKey(entity = PlayerMatchBet::class,
                parentColumns = arrayOf("player_match_id"),
                childColumns = arrayOf("match_bet_id"),
                onDelete = ForeignKey.CASCADE)))
data class BetsDb(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val mId: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "match_bet_id")
        val mMatchBetId: String,
        @ColumnInfo(name = "player")
        val mPlayerName: String,
        @ColumnInfo(name = "date")
        val mMatchDate: String,
        @ColumnInfo(name = "time")
        val mMatchTime: String)

data class Bets(
        @SerializedName("id")
        val mId: String,
        @SerializedName("player")
        val mPlayerName: String,
        @SerializedName("date")
        val mMatchDate: String,
        @SerializedName("time")
        val mMatchTime: String,
        @SerializedName("team1_score")
        var mTeam1Score: String,
        @SerializedName("team2_score")
        var mTeam2Score: String)
