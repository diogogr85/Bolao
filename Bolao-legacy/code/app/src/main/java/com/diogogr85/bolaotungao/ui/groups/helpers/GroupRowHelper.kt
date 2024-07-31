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

package com.diogogr85.bolaotungao.ui.groups.helpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.diogogr85.bolaotungao.R

data class GroupRowHelper(val itemView: View) {

    val mFlagImageView: ImageView = itemView.findViewById(R.id.item_groups_team_flag_imageview)
    val mTeamNameTextView: TextView = itemView.findViewById(R.id.item_groups_team_name_textview)
    val mPointsTextView: TextView = itemView.findViewById(R.id.item_groups_points_textview)
    val mMatchesPlayed: TextView = itemView.findViewById(R.id.item_groups_matches_played_textview)
    val mWonsTextView: TextView = itemView.findViewById(R.id.item_groups_wons_textview)
    val mDrawsTextView: TextView = itemView.findViewById(R.id.item_groups_draws_textview)
    val mLossesTextView: TextView = itemView.findViewById(R.id.item_groups_losses_textview)
    val mGoalsForTextView: TextView = itemView.findViewById(R.id.item_groups_goal_for_textview)
    val mGoalsAgainstTextView: TextView = itemView.findViewById(R.id.item_groups_goals_against_textview)
    val mDifferenceTextView: TextView = itemView.findViewById(R.id.item_groups_difference_textview)

}