package com.kody.jokeapp.presentation


import android.graphics.Color
import android.os.Looper
import androidx.core.os.postDelayed
import com.kody.jokeapp.data.CategoryRemoteDataSource
import com.kody.jokeapp.data.ListCategoryCallback
import com.kody.jokeapp.model.Category
import com.kody.jokeapp.view.CategoryItem
import com.kody.jokeapp.view.HomeFragment
import java.util.logging.Handler


class HomePresenter(private val view: HomeFragment,
    //passando dataSource como parametro quer dizer que estou pedir obrigatoriamente um objeto
    private val dataSource: CategoryRemoteDataSource

    //desta forma estou passando objeto por padrao caso ninguem precisa mais instaciar ele no HomeFraGMENT
    //private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
    ): ListCategoryCallback {

    fun findAllCategories(){
        view.showProgress()
        dataSource.findAllCategories(this)

    }

      override fun onError(response: String){
            view.showFailure(response)
        }

    override fun onSucess(response: List<String>){
       /* val categories = mutableListOf<CategoryItem>()
            for (category in response){
            categories.add(CategoryItem(category))
        }*/

        val start = 40 // H - matiz
        val end = 190 // H - matiz
        val diff = (end - start) / response.size


        val categories = response.mapIndexed { index, s ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f,
            )
            Category(s,  Color.HSVToColor(hsv).toLong())
        }


        view.showCategories(categories)

    }

    override fun onCoplete(){
        view.hideProgress()
    }


    //fake simulador um request http


}