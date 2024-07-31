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

package com.diogogr85.bolaotungao.ui.ranking.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.models.Ranking
import com.diogogr85.bolaotungao.ui.ranking.viewholders.RankingViewHolder

class RankingAdapter(private val mContext: Context, private val mRanking: ArrayList<Ranking>):
        RecyclerView.Adapter<RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ranking, parent, false)

        return RankingViewHolder.newInstance(itemLayout)
    }

    override fun getItemCount(): Int {
        return mRanking.size
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val ranking = mRanking[position]

        with(holder) {
            if (position in 0..2) {
                mMedalShapeImageView.visibility = View.VISIBLE
                mMedalIconImageView.visibility = View.VISIBLE
                mFlashlightImageView.visibility = View.GONE
                mPositionTextView.visibility = View.GONE
            } else if (position == (itemCount - 2)) {
                mMedalShapeImageView.visibility = View.VISIBLE
                mMedalIconImageView.visibility = View.GONE
                mFlashlightImageView.visibility = View.GONE
                mPositionTextView.visibility = View.VISIBLE
            } else if (position == (itemCount - 1)) {
                mMedalShapeImageView.visibility = View.VISIBLE
                mMedalIconImageView.visibility = View.GONE
                mFlashlightImageView.visibility = View.VISIBLE
                mPositionTextView.visibility = View.GONE
            } else {
                mMedalShapeImageView.visibility = View.GONE
                mMedalIconImageView.visibility = View.GONE
                mFlashlightImageView.visibility = View.GONE
                mPositionTextView.visibility = View.VISIBLE
            }

            if (position == 0) {
                mMedalShapeImageView.setColorFilter(
                        ContextCompat.getColor(mContext, R.color.colorPrimaryDark),
                        PorterDuff.Mode.SRC_IN)
                mMedalIconImageView.setColorFilter(
                        ContextCompat.getColor(mContext, R.color.colorGolden), PorterDuff.Mode.SRC_IN)

                mPositionTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryDark))
                mPlayerNameTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryDark))
                holder.mPointsTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryDark))
                holder.mGamesScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryDark))
                holder.mHalfGamesScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryDark))
                holder.mWinnersScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryDark))

            } else if (position == 1) {
                mMedalShapeImageView.setColorFilter(
                        ContextCompat.getColor(mContext, R.color.colorSilverDark),
                        PorterDuff.Mode.SRC_IN)
                mMedalIconImageView.setColorFilter(
                        ContextCompat.getColor(mContext, R.color.colorSilver), PorterDuff.Mode.SRC_IN)

                mPositionTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorSilverDark))
                mPlayerNameTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorSilverDark))
                mPointsTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorSilverDark))
                mGamesScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorSilverDark))
                mHalfGamesScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorSilverDark))
                mWinnersScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorSilverDark))

            } else if (position == 2) {
                mMedalShapeImageView.setColorFilter(
                        ContextCompat.getColor(mContext, R.color.colorCopperDark),
                        PorterDuff.Mode.SRC_IN)
                mMedalIconImageView.setColorFilter(
                        ContextCompat.getColor(mContext, R.color.colorCopper), PorterDuff.Mode.SRC_IN)

                mPositionTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorCopperDark))
                mPlayerNameTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorCopperDark))
                mPointsTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorCopperDark))
                mGamesScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorCopperDark))
                mHalfGamesScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorCopperDark))
                mWinnersScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorCopperDark))

            } else if (position in (itemCount - 2)..(itemCount - 1)) {
                mMedalShapeImageView.setColorFilter(
                        ContextCompat.getColor(mContext, android.R.color.holo_red_dark),
                        PorterDuff.Mode.SRC_IN)

                mPositionTextView.setTextColor(Color.RED)
                mPlayerNameTextView.setTextColor(Color.RED)
                mPointsTextView.setTextColor(Color.RED)
                mGamesScoredTextView.setTextColor(Color.RED)
                mHalfGamesScoredTextView.setTextColor(Color.RED)
                mWinnersScoredTextView.setTextColor(Color.RED)

            } else {
                mPositionTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryText))
                mPlayerNameTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryText))
                mPointsTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryText))
                mGamesScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryText))
                mHalfGamesScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryText))
                mWinnersScoredTextView.setTextColor(ContextCompat.getColor(mContext,
                        R.color.colorPrimaryText))
            }

            mPositionTextView.text = (position + 1).toString()

            with(ranking) {
                mPlayerNameTextView.text = mPlayerName
                mPointsTextView.text = mPoints.toString()
                mGamesScoredTextView.text = mGamesScored.toString()
                mHalfGamesScoredTextView.text = mHalfGamesScored.toString()
                mWinnersScoredTextView.text = mWinners.toString()
            }
        }

    }
}