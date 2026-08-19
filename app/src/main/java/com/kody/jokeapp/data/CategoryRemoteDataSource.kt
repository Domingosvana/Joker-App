package com.kody.jokeapp.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.kody.jokeapp.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {

    fun findAllCategories(callback: ListCategoryCallback){
        /*   Handler(Looper.getMainLooper()).postDelayed({
                val response =  arrayListOf(
                    ("Categoria 1" ),
                    ("Categoria 1"),
                    ("Categoria 1"),
                    ("Categoria 1"),
                    )
                Log.i("TAG", "findAllCategories: $response")

              callback.onSucess(response)

              callback.onCoplete()
            },400)

          */

        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories(
                HTTPClient.API_KEY)
            .enqueue(object : Callback<List<String>> {


                override fun onResponse(
                    call: Call<List<String>?>,
                    response: Response<List<String>?>
                ) {
                    if(response.isSuccessful){
                        val categories = response.body()
                        callback.onSucess(categories ?: emptyList())
                        callback.onCoplete()
                    }else{
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro desconhecido")
                    }
                    callback.onCoplete()




                }

                override fun onFailure(
                    call: Call<List<String>?>,
                    t: Throwable
                ) {
                     Log.e("TAG", "onFailure: ", t)
                    callback.onError(t.message ?: "Erro interno")
                    callback.onCoplete()


                }


            }


    }

}


