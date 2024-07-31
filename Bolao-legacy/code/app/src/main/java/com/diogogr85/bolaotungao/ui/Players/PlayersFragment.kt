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

package com.diogogr85.bolaotungao.ui.Players

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.models.Player
import com.diogogr85.bolaotungao.data.source.local.provideSharedPreferences
import com.diogogr85.bolaotungao.di.DatabaseInjector
import com.diogogr85.bolaotungao.di.injectBetsRepository
import com.diogogr85.bolaotungao.di.injectPlayersRepository
import com.diogogr85.bolaotungao.ui.BetsDetails.BetsDetailsActivity
import com.diogogr85.bolaotungao.ui.BetsDetails.INTENT_EXTRAS_PLAYER
import com.diogogr85.bolaotungao.ui.base.BaseFragment
import com.diogogr85.bolaotungao.ui.Players.adapters.PlayersAdapter


class PlayersFragment: BaseFragment<PlayersPresenter, PlayersView>(), PlayersView {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mProgressView: View

    companion object {
        fun newInstance(): PlayersFragment {
            return PlayersFragment()
        }
    }

    override fun requestLayout(): Int {
        return R.layout.fragment_players
    }

    override fun getViewToAttach(): PlayersView {
        return this
    }

    override fun createPresenter(): PlayersPresenter {
        return PlayersPresenter(injectPlayersRepository(),
                injectBetsRepository(),
                provideSharedPreferences())
    }

    override fun setupViews(layout: View) {
        mProgressView = layout.findViewById(R.id.progress_view)

        mRecyclerView = layout.findViewById(R.id.players_list)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = GridLayoutManager(activity!!.applicationContext, 3)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getBets(!isInstanceStateSaved)
    }

    /*******************/
    /**** Callbacks ****/
    /*******************/
    override fun showProgress(show: Boolean) {
        mProgressView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onPlayersSuccess(players: ArrayList<Player>) {
        if (isAdded) {
            mRecyclerView.adapter = PlayersAdapter(activity!!.applicationContext, players,
                    { player ->
                        val intent = Intent(activity!!.applicationContext,
                                BetsDetailsActivity::class.java)
                        intent.putExtra(INTENT_EXTRAS_PLAYER, player)

                        startActivity(intent)
                        Log.d("ON-ITEM-CLICK-LINSTENER", player.mName)
                    })
        }
    }

    override fun onError() {
    }

    override fun showMessage(message: String) {
        Snackbar.make(mRecyclerView, message,
                Snackbar.LENGTH_LONG).show()
    }
}