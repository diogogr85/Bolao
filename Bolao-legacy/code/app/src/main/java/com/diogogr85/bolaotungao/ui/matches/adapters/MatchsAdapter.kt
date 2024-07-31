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

package com.diogogr85.bolaotungao.ui.matches.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.models.Match
import com.diogogr85.bolaotungao.data.models.Player
import com.diogogr85.bolaotungao.ui.matches.viewholders.MatchDateViewHolder
import com.diogogr85.bolaotungao.ui.matches.viewholders.MatchCardViewHolder
import com.diogogr85.bolaotungao.ui.matches.viewholders.MatchGenericViewHolder
import com.diogogr85.bolaotungao.utils.flagResIdParser

class MatchsAdapter(private val mMatchesMap: HashMap<String, ArrayList<Match>>,
                    var mRoundType: String,
                    private val onItemClick: (mMatch: Match) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DATE_VIEW_TYPE = 0
    private val CARD_VIEW_TYPE = 1

    private var mList: ArrayList<Match>

    init {
        mList = mMatchesMap.get(mRoundType)!!
    }

    override fun getItemViewType(position: Int): Int {
        if (mList.get(position).mTeam1.isDateType()) {
            return DATE_VIEW_TYPE

        } else {
            return CARD_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View
        val holder: RecyclerView.ViewHolder

        when(viewType) {
            DATE_VIEW_TYPE -> {
                itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_match_date,
                        parent, false)
                holder = MatchDateViewHolder(itemView)
            }
            CARD_VIEW_TYPE -> {
                itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_match_card,
                        parent, false)
                holder = MatchCardViewHolder(itemView, { position ->
                    onItemClick(mList[position])
                })
            }
            else -> {
                itemView = LinearLayout(parent.context)
                holder = MatchGenericViewHolder.newInstance(itemView)
            }
        }

        return holder
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val match = mList.get(position)

        if (holder is MatchDateViewHolder) {
            holder.mMatchDate.setText(match.mDate)

        } else if (holder is MatchCardViewHolder) {
            holder.mTeam1Flag.setBackgroundResource(
                    flagResIdParser(match.mTeam1.mName))
            holder.mTeam1Name.setText(match.mTeam1.mName)
            holder.mTeam1Score.setText(match.showTeam1Score())

            holder.mTeam2Flag.setBackgroundResource(
                    flagResIdParser(match.mTeam2.mName))
            holder.mTeam2Name.setText(match.mTeam2.mName)
            holder.mTeam2Score.setText(match.showTeam2Score())

            holder.mMatchTime.setText(match.mTime)
            holder.mVenue.setText(match.mVenue)
        }
    }

    fun onPageClick(roundType: String) {
        mRoundType = roundType
        mList = mMatchesMap.get(mRoundType)!!

        notifyDataSetChanged()
    }


}