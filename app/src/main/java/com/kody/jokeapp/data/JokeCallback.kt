package com.kody.jokeapp.data

import com.kody.jokeapp.model.Joke

interface JokeCallback {

    fun onSucess(response: Joke)

    fun onError(response: String)

    fun onCoplete()
}
