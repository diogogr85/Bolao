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
package com.diogogr85.bolaotungao.ui.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.ui.license.LicenseActivity
import com.diogogr85.bolaotungao.ui.rules.RulesActivity

abstract class BaseActivity<P: BasePresenter<V>, V: BaseView>: AppCompatActivity() {

    protected lateinit var mPresenter: P
    protected lateinit var mView: V

    protected lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(requestLayout())

        mToolbar = findViewById(R.id.toolbar)
        mToolbar.findViewById<TextView>(R.id.toolbar_title).text = getString(R.string.toolbar_name)
        setSupportActionBar(mToolbar)

        initPresenter()
        onCreate()
        setView(getView())
        attachPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        detachPresenter()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        MenuInflater(applicationContext).inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_rules -> {
                startActivity(Intent(applicationContext, RulesActivity::class.java))
                return true
            }
            R.id.menu_license -> {
                startActivity(Intent(applicationContext, LicenseActivity::class.java))
                return true
            } else -> return super.onOptionsItemSelected(item)
        }
    }

    /**************************/
    /**** Config functions ****/
    /**************************/
    abstract fun requestLayout(): Int

    abstract fun getView(): V

    abstract fun createPresenter(): P

    abstract fun onCreate()

    /*************************/
    /**** Setup functions ****/
    /*************************/
    protected fun setToolbarTitle(titleRes: Int) {
        mToolbar.findViewById<TextView>(R.id.toolbar_title).text = getString(titleRes)
    }

    private fun initPresenter() {
        mPresenter = createPresenter()
    }

    private fun setView(view: V) {
        mView = view
    }

    private fun attachPresenter() {
        mPresenter.bindView(mView)
    }

    private fun detachPresenter() {
        mPresenter.unbindView()
    }

}