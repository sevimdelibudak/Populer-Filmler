package com.example.popularmovies.ui.home

import android.media.session.MediaSession.Token
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.model.MovieItem
import com.example.popularmovies.network.ApiClient
import com.example.popularmovies.util.Constants
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(){
    val movieList: MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    init {
        getMovieList()

    }
    fun getMovieList(){
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieList(token = Constants.BEARER_TOKEN)

                if(response.isSuccessful){
                    movieList.postValue(response.body()?.movieItems)
                }
                else{
                    if(response.message().isNullOrEmpty()){
                        errorMessage.value = "Bilinmeyen bir hata oluştu"
                    }else{
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception){
                errorMessage.value = e.message
            }
            finally {
                isLoading.value = false
            }
        }
    }
}