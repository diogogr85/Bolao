package com.sushicode.bolao.data.mappers

interface ResponseToEntityMapper<in Response, out Client> {
    fun map(response: Response): Client
}