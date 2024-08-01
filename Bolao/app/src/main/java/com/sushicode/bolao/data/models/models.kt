package com.sushicode.bolao.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class Result<out T> {
    class Success<out T>(val result: T): Result<T>()
    class Error(val exception: Exception) : Result<Nothing>()
}

@Serializable
data class MatchResponse(
    @SerialName("id")
    val matchId: String? = null,
    @SerialName("team_home")
    val teamHome: String,
    @SerialName("team_guest")
    val teamGuest: String,
    @SerialName("team_home_score")
    val teamHomeScore: Int? = null,
    @SerialName("team_guest_score")
    val teamGuestScore: Int? = null,
    @SerialName("date")
    val date: String,
    @SerialName("venue")
    val venue: String,
    @SerialName("group")
    val group: String,
    @SerialName("round")
    val round: String
)
