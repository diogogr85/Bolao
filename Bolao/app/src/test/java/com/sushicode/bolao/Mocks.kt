package com.sushicode.bolao

import com.sushicode.bolao.data.models.MatchResponse
import com.sushicode.bolao.domain.entities.Match
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

const val response = "[\n" +
        "  {\n" +
        "    \"team_home\": \"team_home NaN\",\n" +
        "    \"team_guest\": \"team_guest NaN\",\n" +
        "    \"team_home_score\": 41,\n" +
        "    \"team_guest_score\": 68,\n" +
        "    \"date\": \"date NaN\",\n" +
        "    \"venue\": \"venue NaN\",\n" +
        "    \"group\": \"group NaN\",\n" +
        "    \"round\": \"round NaN\",\n" +
        "    \"id\": null\n" +
        "  },\n" +
        "  {\n" +
        "    \"team_home\": \"team_home NaN\",\n" +
        "    \"team_guest\": \"team_guest NaN\",\n" +
        "    \"team_home_score\": 21,\n" +
        "    \"team_guest_score\": 86,\n" +
        "    \"date\": \"date NaN\",\n" +
        "    \"venue\": \"venue NaN\",\n" +
        "    \"group\": \"group NaN\",\n" +
        "    \"round\": \"round NaN\",\n" +
        "    \"id\": null\n" +
        "  },\n" +
        "  {\n" +
        "    \"team_home\": \"team_home NaN\",\n" +
        "    \"team_guest\": \"team_guest NaN\",\n" +
        "    \"team_home_score\": 3,\n" +
        "    \"team_guest_score\": 74,\n" +
        "    \"date\": \"date NaN\",\n" +
        "    \"venue\": \"venue NaN\",\n" +
        "    \"group\": \"group NaN\",\n" +
        "    \"round\": \"round NaN\",\n" +
        "    \"id\": null\n" +
        "  }\n" +
        "]"

val mockMatchResponse = MatchResponse(
    teamHome = "team_home",
    teamGuest = "team_guest",
    teamHomeScore = 4,
    teamGuestScore = 6,
    date = "date NaN",
    venue = "venue",
    round = "round",
    group = "group"
)

val mockMatch = Match(
    teamHome = "team_home",
    teamGuest = "team_guest",
    teamHomeScore = 4,
    teamGuestScore = 6,
    date = "date NaN",
    venue = "venue",
    round = "round",
    group = "group"
)

fun mockEngine(mockResponse: String = response, statusCode: HttpStatusCode = OK) = MockEngine {
    respond(
        content = ByteReadChannel(mockResponse),
        status = statusCode,
        headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
    )
}