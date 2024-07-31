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

package com.diogogr85.bolaotungao.data.source.repositories.models

import com.diogogr85.bolaotungao.data.models.Match
import com.diogogr85.bolaotungao.data.models.Ranking
import com.google.gson.annotations.SerializedName

data class GroupRaw(
        @SerializedName("Grupo")
        val mGroupName: String,
        @SerializedName("time1")
        val mTeam1: String,
        @SerializedName("flag1")
        val mFlag1: String,
        @SerializedName("time2")
        val mTeam2: String,
        @SerializedName("flag2")
        val mFlag2: String,
        @SerializedName("time3")
        val mTeam3: String,
        @SerializedName("flag3")
        val mFlag3: String,
        @SerializedName("time4")
        val mTeam4: String,
        @SerializedName("flag4")
        val mFlag4: String)

data class GroupsResponse(
        @SerializedName("records")
        val groups: List<GroupRaw>)

data class MatchRaw(
        @SerializedName("time1")
        val mTeam1: String,
        @SerializedName("time2")
        val mTeam2: String,
        @SerializedName("placar1")
        val mTime1Score: String,
        @SerializedName("placar2")
        val mTime2Score: String,
        @SerializedName("data")
        val mDate: String,
        @SerializedName("hora")
        val mTime: String,
        @SerializedName("local")
        val mVenue: String,
        @SerializedName("grupo")
        val mGroup: String,
        @SerializedName("fase")
        val mRound: String)

data class MatchesResponse(
        @SerializedName("records")
        val matches: List<MatchRaw>)

data class RawRanking(@SerializedName("APOSTADOR")
                      val mPlayerName: String,
                      @SerializedName("PONTOS")
                      val mPoints: String,
                      @SerializedName("PE")
                      val mGameScore: String,
                      @SerializedName("PSE")
                      val mHalfGameScore: String,
                      @SerializedName("V")
                      val mWinners: String) {

    fun parseToRanking(): Ranking {
        return Ranking(mPlayerName,
                mPoints.toIntOrNull() ?: 0,
                mGameScore.toIntOrNull() ?: 0,
                mHalfGameScore.toIntOrNull() ?: 0,
                mWinners.toIntOrNull() ?: 0)
    }

}

data class RankingResponse(
        @SerializedName("records")
        val mRanking: ArrayList<RawRanking>)

data class TableRaw(
        @SerializedName("grupo")
        val mGrupo: String,
        @SerializedName("time")
        val mTeam: String,
        @SerializedName("pontos")
        val mPoints: Int,
        @SerializedName("jogos")
        val mMatchesPlayed: Int,
        @SerializedName("vitorias")
        val mWon: Int,
        @SerializedName("empates")
        val mDraw: Int,
        @SerializedName("derrotas")
        val mLoss: Int,
        @SerializedName("gols_pro")
        val mGoalsFor: Int,
        @SerializedName("gols_contra")
        val mGoalsAgainst: Int,
        @SerializedName("saldo")
        val mDifference: Int)

data class TableResponse(
        @SerializedName("records")
        val mTable: ArrayList<TableRaw>)

data class PlayersRaw(
        @SerializedName("apostador")
        val mName: String,
        @SerializedName("tabela")
        val mTableUrl: String,
        @SerializedName("profile")
        val mProfileImage: String,
        @SerializedName("apostas")
        val mBetsId: String)

data class PlayersResponse(
        @SerializedName("records")
        val mPlayers: ArrayList<PlayersRaw>
)