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

package com.diogogr85.bolaotungao.ui.BetsDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.diogogr85.bolaotungao.R
import com.diogogr85.bolaotungao.data.loaders.loadImage
import com.diogogr85.bolaotungao.data.models.Player
import com.diogogr85.bolaotungao.utils.FIREBASE_BASE_URL
import com.github.chrisbanes.photoview.PhotoView
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

val INTENT_EXTRAS_PLAYER = "intent_extras_player"

class BetsDetailsActivity: AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var mPlayerNameTextView: TextView
    lateinit var mTableBetsImageView: PhotoView
    lateinit var mProgressView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bets_details)

        initViews()
        setupViews()
    }

    private fun initViews() {
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        mProgressView = findViewById(R.id.progress_view)

        mPlayerNameTextView = findViewById(R.id.bets_details_player_textview)
        mTableBetsImageView = findViewById(R.id.bets_details_photo_view)
    }

    @SuppressLint("RestrictedApi")
    private fun setupViews() {
        val title = mToolbar.findViewById<TextView>(R.id.toolbar_title)
        title.text = getString(R.string.bets_details_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val player = intent.extras[INTENT_EXTRAS_PLAYER] as Player
        mPlayerNameTextView.text = player.mName ?: "Boi Tungão"

        val storageReference = FirebaseStorage.getInstance()
                .getReferenceFromUrl(FIREBASE_BASE_URL)
                .child(player.mBetTableUrl)

        mProgressView.visibility = View.VISIBLE
        mTableBetsImageView.loadImage(applicationContext, storageReference, mProgressView)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}