package com.sushicode.bolao.domain.entities

data class CupMatch(
    val teamHome: String,
    val teamGuest: String,
    val teamHomeScore: Int?,
    val teamGuestScore: Int?,
    val date: String,
    val venue: String,
    val group: String,
    val round: String,
) {
    override fun toString(): String {
        val scoreHome = if (teamHomeScore == null) "_" else teamHomeScore
        val scoreGuest = if (teamGuestScore == null) "_" else teamGuestScore

        return "$teamHome $scoreHome X $scoreGuest $teamGuestScore"
    }
}