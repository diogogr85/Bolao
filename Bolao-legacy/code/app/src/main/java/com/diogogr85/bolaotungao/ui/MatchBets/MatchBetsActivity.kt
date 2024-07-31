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

package com.diogogr85.bolaotungao.ui.MatchBets

import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.models.Bets
import com.diogogr85.bolaotungao.data.models.Match
import com.diogogr85.bolaotungao.di.injectBetsRepository
import com.diogogr85.bolaotungao.ui.MatchBets.adapter.MatchBetsAdapter
import com.diogogr85.bolaotungao.ui.base.BaseActivity
import com.diogogr85.bolaotungao.utils.flagResIdParser
import kotlinx.android.synthetic.main.activity_match_bets.*
import kotlinx.android.synthetic.main.item_match_card.*
import kotlinx.android.synthetic.main.item_match_info.*
import kotlinx.android.synthetic.main.item_match_team.view.*

class MatchBetsActivity: BaseActivity<MatchBetsPresenter, MatchBetsView>(), MatchBetsView {

    private val mMatch by lazy {
        intent.getParcelableExtra<Match>("match")
    }

    override fun requestLayout(): Int {
        return R.layout.activity_match_bets
    }

    override fun getView(): MatchBetsView {
        return this
    }

    override fun createPresenter(): MatchBetsPresenter {
        return MatchBetsPresenter(injectBetsRepository())
    }

    override fun onCreate() {
        mToolbar.findViewById<ImageView>(R.id.toolbar_icon).visibility = View.GONE

        setToolbarTitle(R.string.match_bets_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        bindViews()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }



    private fun bindViews() {
        matchBetsList.layoutManager = LinearLayoutManager(applicationContext)
        matchBetsList.setHasFixedSize(true)

        with(item_match_card_team_1_container) {
            item_match_team_flag_imageview.setBackgroundResource(flagResIdParser(mMatch.mTeam1.mName))
            item_match_team_name_textview.setText(mMatch.mTeam1.mName)
        }

        with(item_match_card_team_2_container) {
            item_match_team_flag_imageview.setBackgroundResource(flagResIdParser(mMatch.mTeam2.mName))
            item_match_team_name_textview.setText(mMatch.mTeam2.mName)
        }

        with(item_match_card_info_container) {
            item_match_info_score_1_textview.setText(mMatch.showTeam1Score())
            item_match_info_score_2_textview.setText(mMatch.showTeam2Score())
            item_match_info_time_textview.setText(mMatch.mTime)
        }

        item_match_card_venue_textview.setText(mMatch.mVenue)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getMatchBets(mMatch.mTeam1.mName, mMatch.mTeam2.mName,
                mMatch.mDate, mMatch.mTime)
    }

    /*******************/
    /**** Callbacks ****/
    /*******************/
    override fun showProgress(show: Boolean) {

    }

    override fun onSuccess(matchBets: Array<Bets>) {
        matchBetsList.adapter = MatchBetsAdapter(matchBets)
    }

    override fun onError() {
        Snackbar.make(findViewById(android.R.id.content),
                "Não foi possível mostrar os palpites para este jogo",
                Snackbar.LENGTH_LONG).show()
    }

}