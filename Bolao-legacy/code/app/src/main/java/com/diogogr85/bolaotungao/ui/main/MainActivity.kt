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
package com.diogogr85.bolaotungao.ui.main

import android.graphics.PorterDuff
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.widget.TextView
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.ui.base.BaseActivity
import com.diogogr85.bolaotungao.ui.main.adapters.MainPageAdapter

class MainActivity : BaseActivity<MainPresenter, MainView>(), MainView {

    private val START_PAGE_POSITION = 0
    private val OFF_SCREEN_PAGE_LIMIT = 4

    lateinit var mViewPager: ViewPager
    lateinit var mTabLayout: TabLayout
    lateinit var mPageTitle: TextView

    lateinit var mAdapter: MainPageAdapter

    /***********************/
    /**** Setup methods ****/
    /***********************/
    override fun requestLayout(): Int {
        return R.layout.activity_main
    }

    override fun getView(): MainView {
        return this
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun onCreate() {
        mTabLayout = findViewById(R.id.main_tablayout)
        mViewPager = findViewById(R.id.main_viewpager)
        mPageTitle = findViewById(R.id.main_page_title)
        mAdapter = MainPageAdapter(supportFragmentManager)

        mViewPager.offscreenPageLimit = OFF_SCREEN_PAGE_LIMIT
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)
        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                customizeTab(tab, false)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                customizeTab(tab, true)
            }
        })

        mTabLayout.getTabAt(MainPageAdapter.TAB_GROUPS)!!.setIcon(R.drawable.ic_groups)
        mTabLayout.getTabAt(MainPageAdapter.TAB_MATCHES)!!.setIcon(R.drawable.ic_matches)
        mTabLayout.getTabAt(MainPageAdapter.TAB_RANKING)!!.setIcon(R.drawable.ic_ranking)
        mTabLayout.getTabAt(MainPageAdapter.TAB_BETS)!!.setIcon(R.drawable.ic_cow_black)

        //Initialize with first selected
        customizeTab(mTabLayout.getTabAt(START_PAGE_POSITION), true)
    }

    override fun onBackPressed() {
        if (mViewPager.currentItem != 0) {
            mViewPager.setCurrentItem(0, true)
        } else {
            super.onBackPressed()
        }
    }

    /***************/
    /**** Utils ****/
    /***************/
    private fun customizeTab(tab: TabLayout.Tab?, isSelected: Boolean) {
        mPageTitle.text = mAdapter.getTitle(tab!!.position)

        if (isSelected) {
            tab.icon?.setColorFilter(ContextCompat.getColor(applicationContext, android.R.color.white),
                    PorterDuff.Mode.SRC_IN)

        } else {
            tab.icon?.setColorFilter(ContextCompat.getColor(applicationContext, R.color.colorIcons),
                    PorterDuff.Mode.SRC_IN)
        }
    }

    /*******************/
    /**** Callbacks ****/
    /*******************/
    override fun showProgress(show: Boolean) {
        //Do nothing
    }
}
