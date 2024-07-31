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

package com.diogogr85.bolaotungao.ui.matches

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.appContext
import com.diogogr85.bolaotungao.data.models.Match
import com.diogogr85.bolaotungao.data.source.local.provideSharedPreferences
import com.diogogr85.bolaotungao.di.injectMatchesRepositories
import com.diogogr85.bolaotungao.ui.MatchBets.MatchBetsActivity
import com.diogogr85.bolaotungao.ui.base.BaseFragment
import com.diogogr85.bolaotungao.ui.matches.adapters.MatchsAdapter
import com.diogogr85.bolaotungao.utils.DateUtils
import com.diogogr85.bolaotungao.utils.roundTypeValues
import java.util.*
import kotlin.Comparator


class MatchesFragment: BaseFragment<MatchesPresenter, MatchesView>(), MatchesView {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mProgressView: View

    lateinit var mLeftButton: ImageView
    lateinit var mRightButton: ImageView
    lateinit var mRoundTextView: TextView

    var mCurrentPage: Int = 0
    val mPagesKeys = ArrayList<String>()

    companion object {
        fun newInstance(): MatchesFragment {
            return MatchesFragment()
        }
    }

    override fun requestLayout(): Int {
        return R.layout.fragment_matches
    }

    override fun getViewToAttach(): MatchesView {
        return this
    }

    override fun createPresenter(): MatchesPresenter {
        return MatchesPresenter(injectMatchesRepositories(), provideSharedPreferences())
    }

    override fun setupViews(layout: View) {
        mProgressView = layout.findViewById(R.id.progress_view)

        mRecyclerView = layout.findViewById(R.id.matches_list)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        mLeftButton = layout.findViewById(R.id.matches_round_left_button)
        mRightButton = layout.findViewById(R.id.matches_round_right_button)
        mRoundTextView = layout.findViewById(R.id.matches_round_textview)

        setListeners()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getMatches(!isInstanceStateSaved)
    }

    fun setListeners() {
        mLeftButton.setOnClickListener({
            val adapter = mRecyclerView.adapter as MatchsAdapter
            mCurrentPage--
            if (mCurrentPage < 0) mCurrentPage = 0

            adapter.onPageClick(mPagesKeys[mCurrentPage])
            setPage()
        })

        mRightButton.setOnClickListener({
            val adapter = mRecyclerView.adapter as MatchsAdapter
            mCurrentPage++
            if (mCurrentPage > mPagesKeys.count() - 1) mCurrentPage = mPagesKeys.count() - 1

            adapter.onPageClick(mPagesKeys[mCurrentPage])
            setPage()
        })
    }

    /*******************/
    /**** Callbacks ****/
    /*******************/
    override fun showProgress(show: Boolean) {
        mProgressView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onMatchesSuccess(matchesMap: HashMap<String, ArrayList<Match>>) {
        mPagesKeys.clear()
        mPagesKeys.addAll(matchesMap.keys)
        mPagesKeys.sortWith(Comparator { o1, o2 ->
            val o1Value = roundTypeValues(o1!!)
            val o2Value = roundTypeValues(o2!!)

            o1Value.compareTo(o2Value)
        })

        mCurrentPage = DateUtils.calculateCurrentMatchesPage()
        mRecyclerView.adapter = MatchsAdapter(matchesMap, mPagesKeys[mCurrentPage],
                { match ->
                    onBetsAllowed(match)
                })
        setPage()
    }

    fun onBetsAllowed(match: Match) {
        if (mPresenter.isMatchBetsFetched() && mCurrentPage <= 2) {
            val intent = Intent(appContext, MatchBetsActivity::class.java)
            intent.putExtra("match", match)

            startActivity(intent)
        }

    }

    override fun onError() {
        Snackbar.make(mRecyclerView, "Verifique sua conexão e tente novamente.",
                Snackbar.LENGTH_SHORT).show()
    }


    private fun setPage() {
        mRoundTextView.setText(mPagesKeys[mCurrentPage])
    }
}