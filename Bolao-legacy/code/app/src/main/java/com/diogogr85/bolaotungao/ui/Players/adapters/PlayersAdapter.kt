/*
 *
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
 *
 */

package com.diogogr85.bolaotungao.ui.Players.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.loaders.loadImage
import com.diogogr85.bolaotungao.data.loaders.loadImageCircle
import com.diogogr85.bolaotungao.data.models.Player
import com.diogogr85.bolaotungao.ui.Players.viewholders.PlayerViewHolder
import com.diogogr85.bolaotungao.utils.FIREBASE_BASE_URL
import com.google.firebase.storage.FirebaseStorage


class PlayersAdapter(private val mContext: Context,
                     private val mPlayers: ArrayList<Player>,
                     private val onItemClick: (mPlayer: Player) -> Unit):
        RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_player, parent, false)

        return PlayerViewHolder.newInstance(itemLayout, { position ->
            onItemClick(mPlayers[position])
        })
    }

    override fun getItemCount(): Int {
        return mPlayers.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = mPlayers[position]

        holder.mNameTextView.text = player.mName

        if (!TextUtils.isEmpty(player?.mProfileImage) &&
                player?.mProfileImage?.contains("profile")) {
            val storageReference = FirebaseStorage.getInstance()
                    .getReferenceFromUrl(FIREBASE_BASE_URL)
                    .child(player.mProfileImage)
            holder.mProfileImageView.loadImageCircle(mContext, storageReference, null)

        } else {
            holder.mProfileImageView.loadImageCircle(mContext,
                    ContextCompat.getDrawable(mContext, R.drawable.ic_boi)!!)
        }
    }

}