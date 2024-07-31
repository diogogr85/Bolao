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

import com.diogogr85.bolaotungao.R

fun flagResIdParser(resName: String): Int {
    when(resName) {
        TEAM_NAME_EGI -> return R.drawable.ic_flag_egito
        TEAM_NAME_IRA -> return R.drawable.ic_flag_ira
        TEAM_NAME_AUS -> return R.drawable.ic_flag_australia
        TEAM_NAME_ARG -> return R.drawable.ic_flag_argentina
        TEAM_NAME_BRA -> return R.drawable.ic_flag_brasil
        TEAM_NAME_ALE -> return R.drawable.ic_flag_alemanha
        TEAM_NAME_BEL -> return R.drawable.ic_flag_belgica
        TEAM_NAME_COL -> return R.drawable.ic_flag_colombia
        TEAM_NAME_RUS -> return R.drawable.ic_flag_russia
        TEAM_NAME_MAR -> return R.drawable.ic_flag_marrocos
        TEAM_NAME_DIN -> return R.drawable.ic_flag_dinamarca
        TEAM_NAME_CRO -> return R.drawable.ic_flag_croacia
        TEAM_NAME_COS -> return R.drawable.ic_flag_costa
        TEAM_NAME_COR -> return R.drawable.ic_flag_coreia
        TEAM_NAME_ING -> return R.drawable.ic_flag_inglaterra
        TEAM_NAME_JAP -> return R.drawable.ic_flag_japao
        TEAM_NAME_ARA -> return R.drawable.ic_flag_arabia
        TEAM_NAME_POR -> return R.drawable.ic_flag_portugal
        TEAM_NAME_FRA -> return R.drawable.ic_flag_franca
        TEAM_NAME_ISL -> return R.drawable.ic_flag_islandia
        TEAM_NAME_SUI -> return R.drawable.ic_flag_suica
        TEAM_NAME_MEX -> return R.drawable.ic_flag_mexico
        TEAM_NAME_PAN -> return R.drawable.ic_flag_panama
        TEAM_NAME_POL -> return R.drawable.ic_flag_polonia
        TEAM_NAME_URU -> return R.drawable.ic_flag_uruguai
        TEAM_NAME_ESP -> return R.drawable.ic_flag_espanha
        TEAM_NAME_PER -> return R.drawable.ic_flag_peru
        TEAM_NAME_NIG -> return R.drawable.ic_flag_nigeria
        TEAM_NAME_SER -> return R.drawable.ic_flag_servia
        TEAM_NAME_SUE -> return R.drawable.ic_flag_suecia
        TEAM_NAME_TUN -> return R.drawable.ic_flag_tunisia
        TEAM_NAME_SEN -> return R.drawable.ic_flag_senegal
        else -> return R.drawable.ic_wcup_2018
    }
}

fun roundTypeValues(roundType: String): Int {
    when(roundType) {
        ROUND_TYPE_FIRST_ROUND -> return ROUND_TYPE_FIRST_ROUND_VALUE
        ROUND_TYPE_SECOND_ROUND -> return ROUND_TYPE_SECOND_ROUND_VALUE
        ROUND_TYPE_THIRD_ROUND -> return ROUND_TYPE_THIRD_ROUND_VALUE
        ROUND_TYPE_16TH_ROUND -> return ROUND_TYPE_16TH_ROUND_VALUE
        ROUND_TYPE_QUARTER_ROUND -> return ROUND_TYPE_QUARTER_ROUND_VALUE
        ROUND_TYPE_SEMIFINAL_ROUND -> return ROUND_TYPE_SEMIFINAL_ROUND_VALUE
        ROUND_TYPE_3RD_PLACE_ROUND -> return ROUND_TYPE_3RD_PLACE_ROUND_VALUE
        ROUND_TYPE_FINAL_ROUND -> return ROUND_TYPE_FINAL_ROUND_VALUE
        else -> return 0
    }
}
