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

package com.diogogr85.bolaotungao.ui.main.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.diogogr85.bolaotungao.ui.Players.PlayersFragment
import com.diogogr85.bolaotungao.ui.groups.GroupsFragment
import com.diogogr85.bolaotungao.ui.matches.MatchesFragment
import com.diogogr85.bolaotungao.ui.ranking.RankingFragment

class MainPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val TAB_SIZE = 4

    companion object {
        val TAB_GROUPS = 0
        val TAB_MATCHES = 1
        val TAB_RANKING = 2
        val TAB_BETS = 3

        val TAB_GROUPS_TEXT = "Grupos"
        val TAB_MATCHES_TEXT = "Jogos"
        val TAB_RANKING_TEXT = "Ranking"
        val TAB_BETS_TEXT = "Apostadores"

    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            TAB_GROUPS -> return GroupsFragment.newInstance()  //Groups and Classification fragment
            TAB_MATCHES -> return MatchesFragment.newInstance() //Matches fragment
            TAB_RANKING -> return RankingFragment.newInstance() //Tungoes' ranking fragment
            TAB_BETS -> return PlayersFragment.newInstance()    //Player fragment
            else -> return Fragment()
        }
    }

    override fun getCount(): Int {
        return TAB_SIZE
    }

    fun getTitle(tabPosition: Int): String {
        when(tabPosition) {
            TAB_GROUPS -> return TAB_GROUPS_TEXT
            TAB_MATCHES -> return TAB_MATCHES_TEXT
            TAB_RANKING -> return TAB_RANKING_TEXT
            TAB_BETS -> return TAB_BETS_TEXT
            else -> return ""
        }
    }
}