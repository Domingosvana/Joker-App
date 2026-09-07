package com.kody.jokeapp.data

import com.kody.jokeapp.model.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeDayRemoteDateSource {

    fun findRandom(callback: JokeCallback){
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findRandom(
                apiKey = HTTPClient.API_KEY
            )
            .enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful) {
                        val joke = response.body()
                        callback.onSucess(joke ?: throw RuntimeException("Piada não encontrada"))
                    } else {
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro desconhecido")
                    }
                    callback.onCoplete()

                }


                override fun onFailure(
                    call: Call<Joke?>,
                    t: Throwable
                ) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onCoplete()
                }

            })
                }
}