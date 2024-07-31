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

package com.diogogr85.bolaotungao.ui.MatchBets.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.models.Bets
import com.diogogr85.bolaotungao.ui.MatchBets.viewholder.MatchBetsViewHolder
import com.diogogr85.bolaotungao.utils.EMPTY_TEXT

class MatchBetsAdapter(val mMatchBets: Array<Bets>): RecyclerView.Adapter<MatchBetsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchBetsViewHolder {
        return MatchBetsViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_match_bets, parent, false))
    }

    override fun getItemCount(): Int {
        return mMatchBets.size
    }

    override fun onBindViewHolder(holder: MatchBetsViewHolder, position: Int) {
        val bets = mMatchBets[position]

        if (bets != null) {
            holder.mMatchBetPlayer.text = bets.mPlayerName
            holder.mMatchBetScores.text =
                    bets.mTeam1Score + " vs " + bets.mTeam2Score
        } else {
            holder.mMatchBetPlayer.text = EMPTY_TEXT
            holder.mMatchBetScores.text = EMPTY_TEXT
        }
    }


}