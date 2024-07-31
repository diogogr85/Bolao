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

package com.diogogr85.bolaotungao.ui.groups.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.models.Group
import com.diogogr85.bolaotungao.ui.groups.helpers.GroupRowHelper
import com.diogogr85.bolaotungao.ui.groups.viewholders.GroupsViewHolder
import com.diogogr85.bolaotungao.utils.flagResIdParser

class GroupsAdapter(
        private val mContext: Context,
        private val mGroups: List<Group>): RecyclerView.Adapter<GroupsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_groups_card, parent, false)

        return GroupsViewHolder.newInstance(itemLayout)
    }

    override fun getItemCount(): Int {
        return mGroups.size
    }

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        val group = mGroups.get(position)

        holder.mGroupTitle.setText("Grupo ${group.groupName}")

        holder.mTeamStatsContainer.removeAllViews()
        var index = 0
        while (index < group.mTeamList.size) {
            val team = group.mTeamList.get(index)

            val itemView: View = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_groups_row, holder.mTeamStatsContainer, false)
            itemView.setBackgroundColor(Color.TRANSPARENT)
            val row = GroupRowHelper(itemView)

            row.mFlagImageView.setBackgroundResource(flagResIdParser(team.mName))
            row.mTeamNameTextView.text = team.mName

            row.mPointsTextView.text = team.mPoints.toString()
            row.mMatchesPlayed.text = team.mMatchesPlayed.toString()
            row.mWonsTextView.text = team.mWon.toString()
            row.mDrawsTextView.text = team.mDraw.toString()
            row.mLossesTextView.text = team.mLoss.toString()

            row.mGoalsForTextView.text = team.mGoalsFor.toString()
            row.mGoalsAgainstTextView.text = team.mGoalsAgainst.toString()
            row.mDifferenceTextView.text = team.mDifference.toString()

            holder.mTeamStatsContainer.addView(row.itemView)
            index++
        }
    }

}