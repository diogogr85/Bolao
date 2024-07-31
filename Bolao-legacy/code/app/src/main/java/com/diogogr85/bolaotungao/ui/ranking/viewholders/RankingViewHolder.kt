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

package com.diogogr85.bolaotungao.ui.ranking.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.diogogr85.bolaotungao.R

class RankingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val mMedalShapeImageView: ImageView
    val mMedalIconImageView: ImageView
    val mFlashlightImageView: ImageView
    val mPositionTextView: TextView

    val mPlayerNameTextView: TextView
    val mPointsTextView: TextView
    val mGamesScoredTextView: TextView
    val mHalfGamesScoredTextView: TextView
    val mWinnersScoredTextView: TextView

    init {
        mMedalShapeImageView = itemView.findViewById(R.id.item_ranking_medal_shape)
        mMedalIconImageView = itemView.findViewById(R.id.item_ranking_medal_icon)
        mFlashlightImageView = itemView.findViewById(R.id.item_ranking_flashlight_icon)
        mPositionTextView = itemView.findViewById(R.id.item_ranking_position_textview)

        mPlayerNameTextView = itemView.findViewById(R.id.item_ranking_player_name_textview)
        mPointsTextView = itemView.findViewById(R.id.item_ranking_points_textview)
        mGamesScoredTextView = itemView.findViewById(R.id.item_ranking_games_scored_textview)
        mHalfGamesScoredTextView = itemView.findViewById(R.id.item_ranking_half_games_scored_textview)
        mWinnersScoredTextView = itemView.findViewById(R.id.item_ranking_winners_scored_textview)
    }

    companion object {
        fun newInstance(itemView: View): RankingViewHolder {
            return RankingViewHolder(itemView)
        }
    }
}