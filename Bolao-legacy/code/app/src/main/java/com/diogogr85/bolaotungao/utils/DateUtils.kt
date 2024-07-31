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

package com.diogogr85.bolaotungao.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {
        private val brazilLocale = Locale("pt", "PT");

        private val INPUT_FORMAT_STR = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        val ISO_8601_FORMAT = SimpleDateFormat(INPUT_FORMAT_STR);

        private val DATE_FORMAT_BR_STR = "dd/MM";
        val DATE_FORMAT_BR = SimpleDateFormat(DATE_FORMAT_BR_STR);

        private val DATE_FORMAT_BR_STR_COMPLETE = "dd/MM/yyyy";
        private val DATE_FORMAT_BR_COMPLETE = SimpleDateFormat(DATE_FORMAT_BR_STR_COMPLETE);

        private val TIME_FORMAT_BR_STR = "HH:mm";
        private val TIME_FORMAT_BR = SimpleDateFormat(TIME_FORMAT_BR_STR);

        private val DAY_OF_WEEK_FORMAT_STR = "EE";
        val DAY_OF_WEEK_FORMAT = SimpleDateFormat(DAY_OF_WEEK_FORMAT_STR, brazilLocale);

        private val DATE_RANGE_FIRST_ROUND_START = "14/06/2018"
        private val DATE_RANGE_SECOND_ROUND_START = "19/06/2018"
        private val DATE_RANGE_THIRD_ROUND_START = "25/06/2018"
        private val DATE_RANGE_8A_ROUND_START = "30/06/2018"
        private val DATE_RANGE_4A_ROUND_START = "06/07/2018"
        private val DATE_RANGE_SEMIFINALS_ROUND_START = "10/07/2018"
        private val DATE_RANGE_THIRD_PLACE_ROUND_START = "14/07/2018"
        private val DATE_RANGE_FINAL_ROUND_START = "15/07/2018"

        private fun getLocalTimeZone(): String {
            //calculate local time zone
            val calendar = Calendar.getInstance();
            val tz = calendar.getTimeZone();
            val mGMTOffset = tz.getRawOffset();

            return "GMT" + mGMTOffset;
        }

        private fun getCurrentDate(): String {
            ISO_8601_FORMAT.setTimeZone(TimeZone.getTimeZone(getLocalTimeZone()));
            return ISO_8601_FORMAT.format(Calendar.getInstance().getTime());
        }

        fun getCompleteDate(dateStr: String): Date {
            return DATE_FORMAT_BR_COMPLETE.parse(dateStr)
        }

        fun getTime(timeStr: String): String {
            return timeStr.replace("-", ":")
        }

        fun calculateCurrentMatchesPage(): Int {
            val currentDate = Calendar.getInstance().time

            var page = 0
            with(currentDate) {
                if (before(getCompleteDate(DATE_RANGE_SECOND_ROUND_START))) {
                    page = 0

                } else if (before(getCompleteDate(DATE_RANGE_THIRD_ROUND_START))) {
                    page = 1

                } else if (before(getCompleteDate(DATE_RANGE_8A_ROUND_START))) {
                    page = 2

                } else if (before(getCompleteDate(DATE_RANGE_4A_ROUND_START))) {
                    page = 3

                } else if (before(getCompleteDate(DATE_RANGE_SEMIFINALS_ROUND_START))) {
                    page = 4

                } else if (before(getCompleteDate(DATE_RANGE_THIRD_PLACE_ROUND_START))) {
                    page = 5

                } else if (before(getCompleteDate(DATE_RANGE_FINAL_ROUND_START))) {
                    page = 6

                } else {
                    page = 7
                }
            }

            return page
        }

    }
}