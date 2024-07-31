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

package com.diogogr85.bolaotungao.data.loaders

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.Nullable
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.diogogr85.bolaotungao.R
import com.google.firebase.storage.StorageReference


fun ImageView.loadImage(context: Context, imagePath: Drawable) {
    val options = RequestOptions()
    options.centerInside()
    options.diskCacheStrategy(DiskCacheStrategy.ALL)

    Glide.with(context)
            .load(imagePath)
            .apply(options)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    e?.printStackTrace()
                    Log.d("GLIDE-EXCEPTION", e?.message)
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    Log.d("GLIDE-EXCEPTION", "Load: ok")
                    return false
                }

            })
            .into(this)
}

fun ImageView.loadImage(context: Context, storageReference: StorageReference, progressView: View?) {
    GlideApp.with(context)
            .load(storageReference)
            .error(R.drawable.ic_boi)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    e?.printStackTrace()
                    Log.d("GLIDE-EXCEPTION", e?.message)
                    progressView?.visibility = View.GONE
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    progressView?.visibility = View.GONE
                    Log.d("GLIDE-EXCEPTION", "Load: ok")
                    return false
                }

            })
            .fitCenter()
            .into(this)
}

fun ImageView.loadImageCircle(context: Context, storageReference: StorageReference, progressView: View?) {
    GlideApp.with(context)
            .load(storageReference)
            .error(R.drawable.ic_boi)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    e?.printStackTrace()
                    Log.d("GLIDE-EXCEPTION", e?.message)
                    progressView?.visibility = View.GONE
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    progressView?.visibility = View.GONE
                    Log.d("GLIDE-EXCEPTION", "Load: ok")
                    return false
                }

            })
            .circleCrop()
            .into(this)
}

fun ImageView.loadImageCircle(context: Context, imagePath: Drawable) {
    val options = RequestOptions()
    options.circleCrop()
    options.diskCacheStrategy(DiskCacheStrategy.ALL)

    Glide.with(context)
            .load(imagePath)
            .apply(options)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    e?.printStackTrace()
                    Log.d("GLIDE-EXCEPTION", e?.message)
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    Log.d("GLIDE-EXCEPTION", "Load: ok")
                    return false
                }

            })
            .into(this)
}
