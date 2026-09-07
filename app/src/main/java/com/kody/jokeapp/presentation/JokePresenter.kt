package com.kody.jokeapp.presentation

import com.kody.jokeapp.data.JokeCallback
import com.kody.jokeapp.data.JokeRemoteDateSource
import com.kody.jokeapp.model.Joke
import com.kody.jokeapp.view.JokeFragment

class JokePresenter(
    private val view: JokeFragment,
    private val dataSource: JokeRemoteDateSource = JokeRemoteDateSource()
): JokeCallback {

    fun findBy(categoryName: String){
        view.showProgress()
        dataSource.findBy(categoryName, this)
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