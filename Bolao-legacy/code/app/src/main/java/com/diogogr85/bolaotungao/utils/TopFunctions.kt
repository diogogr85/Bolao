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

package com.diogogr85.bolaotungao.utils

import android.text.TextUtils
import java.util.zip.DataFormatException

fun String.getDate(): String {
    try {
        if (!TextUtils.isEmpty(this)) {
            val date = DateUtils.ISO_8601_FORMAT.parse(this)
            return DateUtils.DAY_OF_WEEK_FORMAT.format(date).capitalize() + ", " + DateUtils.DATE_FORMAT_BR.format(date)
        } else {
            return ""
        }

    } catch (e: DataFormatException) {
        return ""
    }
}