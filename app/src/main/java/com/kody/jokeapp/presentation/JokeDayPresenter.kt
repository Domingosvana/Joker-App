package com.kody.jokeapp.presentation

import com.kody.jokeapp.data.JokeCallback
import com.kody.jokeapp.data.JokeDayRemoteDateSource
import com.kody.jokeapp.data.JokeRemoteDateSource
import com.kody.jokeapp.model.Joke
import com.kody.jokeapp.view.JokeDayFragment
import com.kody.jokeapp.view.JokeFragment

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDateSource = JokeDayRemoteDateSource()
): JokeCallback {

    fun findRandom(){
        view.showProgress()
        dataSource.findRandom( this)
    }



    override fun onSucess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onCoplete() {
        view.hideProgress()

    }
}