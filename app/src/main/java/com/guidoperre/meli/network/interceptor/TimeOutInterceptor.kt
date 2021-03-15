package com.guidoperre.meli.network.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

/*
   Interceptor personalizado para poder catchear errores del tipo time out
   o unreached connection.
*/
class TimeOutInterceptor : Interceptor {

    private val timeout = 10

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response

        try {
            response = chain
                .withReadTimeout(timeout, TimeUnit.SECONDS)
                .withConnectTimeout(timeout, TimeUnit.SECONDS)
                .withWriteTimeout(timeout, TimeUnit.SECONDS)
                .proceed(chain.request())
        } catch(exception: SocketTimeoutException){
            val responseString = exception.message.toString()
            response = Response.Builder()
                    .code(408)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_2)
                    .body(
                            responseString.toByteArray()
                                    .toResponseBody("application/json".toMediaTypeOrNull())
                    )
                    .addHeader("content-type", "application/json")
                    .build()
        } catch (e: IOException) {
            val responseString = e.message.toString()
            response = Response.Builder()
                .code(404)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        }

        return response
    }
}