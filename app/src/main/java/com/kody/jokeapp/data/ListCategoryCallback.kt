package com.kody.jokeapp.data

interface ListCategoryCallback {

    fun onSucess(response: List<String>)

    fun onError(response: String)

    fun onCoplete()
}
