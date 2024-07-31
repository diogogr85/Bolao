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

package com.diogogr85.bolaotungao.ui.groups

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.models.Group
import com.diogogr85.bolaotungao.ui.base.BaseFragment
import com.diogogr85.bolaotungao.ui.groups.adapters.GroupsAdapter
import android.support.design.widget.Snackbar
import com.diogogr85.bolaotungao.di.injectGroupsRepository


class GroupsFragment: BaseFragment<GroupsPresenter, GroupsView>(), GroupsView {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mProgressView: View

    companion object {
        fun newInstance(): GroupsFragment {
            return GroupsFragment()
        }
    }

    override fun requestLayout(): Int {
        return R.layout.fragment_groups
    }

    override fun getViewToAttach(): GroupsView {
        return this
    }

    override fun createPresenter(): GroupsPresenter {
        return GroupsPresenter(injectGroupsRepository())
    }

    override fun setupViews(layout: View) {
        mProgressView = layout.findViewById(R.id.progress_view)

        mRecyclerView = layout.findViewById(R.id.groups_list)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getGroups(!isInstanceStateSaved)
    }

    /*******************/
    /**** Callbacks ****/
    /*******************/
    override fun showProgress(show: Boolean) {
        mProgressView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onGetGroups(list: List<Group>) {
        mRecyclerView.adapter = GroupsAdapter(activity!!.applicationContext, list)
    }

    override fun onError() {
        Snackbar.make(mRecyclerView, "Verifique sua conexão e tente novamente.",
                Snackbar.LENGTH_SHORT).show()
    }
}