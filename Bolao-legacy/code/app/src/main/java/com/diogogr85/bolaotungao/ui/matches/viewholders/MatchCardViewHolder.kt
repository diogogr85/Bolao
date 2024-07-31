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

package com.diogogr85.bolaotungao.ui.matches.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.diogogr85.bolaotungao.R

class MatchCardViewHolder(itemView: View,
                          itemClick: (position: Int) -> Unit): RecyclerView.ViewHolder(itemView) {

    private val mTeam1Container: View
    private val mTeam2Container: View
    private val mMatchInfoContainer: View
    val mVenue: TextView

    val mTeam1Flag: ImageView
    val mTeam1Name: TextView
    val mTeam1Score: TextView
    val mTeam2Flag: ImageView
    val mTeam2Name: TextView
    val mTeam2Score: TextView
    val mMatchTime: TextView

    val mItemContainer: View

    init {
        mItemContainer = itemView.findViewById(R.id.item_match_card)
        mItemContainer.setOnClickListener {
            itemClick(adapterPosition)
        }

        mTeam1Container = itemView.findViewById(R.id.item_match_card_team_1_container)
        mTeam2Container = itemView.findViewById(R.id.item_match_card_team_2_container)
        mMatchInfoContainer = itemView.findViewById(R.id.item_match_card_info_container)
        mVenue = itemView.findViewById(R.id.item_match_card_venue_textview)

        mTeam1Flag = mTeam1Container.findViewById(R.id.item_match_team_flag_imageview)
        mTeam1Name = mTeam1Container.findViewById(R.id.item_match_team_name_textview)
        mTeam1Score = mMatchInfoContainer.findViewById(R.id.item_match_info_score_1_textview)
        mTeam2Flag = mTeam2Container.findViewById(R.id.item_match_team_flag_imageview)
        mTeam2Name = mTeam2Container.findViewById(R.id.item_match_team_name_textview)
        mTeam2Score = mMatchInfoContainer.findViewById(R.id.item_match_info_score_2_textview)

        mMatchTime = mMatchInfoContainer.findViewById(R.id.item_match_info_time_textview)
    }

    companion object {
        fun newInstance(itemView: View, itemClick: (position: Int) -> Unit): MatchCardViewHolder {
            return MatchCardViewHolder(itemView, itemClick)
        }
    }

}