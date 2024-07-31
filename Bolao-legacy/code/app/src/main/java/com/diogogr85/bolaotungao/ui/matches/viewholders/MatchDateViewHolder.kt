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
import android.widget.TextView
import com.diogogr85.bolaotungao.R

class MatchDateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val mMatchDate: TextView

    init {
        mMatchDate = itemView.findViewById(R.id.item_match_date_textview)
    }

    companion object {
        fun newInstance(itemView: View): MatchDateViewHolder {
            return MatchDateViewHolder(itemView)
        }
    }

}