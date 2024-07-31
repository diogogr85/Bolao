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

package com.diogogr85.bolaotungao.ui.Players.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.diogogr85.bolaotungao.R



class PlayerViewHolder(itemView: View,
                       itemClick: (position: Int) -> Unit): RecyclerView.ViewHolder(itemView) {

    val mItem: View
    val mProfileImageView: ImageView
    val mNameTextView: TextView

    companion object {
        fun newInstance(itemView: View, itemClick: (position: Int) -> Unit): PlayerViewHolder {
            return PlayerViewHolder(itemView, itemClick)
        }
    }

    init {
        mItem = itemView.findViewById(R.id.item_player)
        mItem.setOnClickListener {
            itemClick(adapterPosition)
        }

        mProfileImageView = itemView.findViewById(R.id.item_player_profile_imageview)
        mNameTextView = itemView.findViewById(R.id.item_player_name)

    }

}