package com.example.popularmovies.ui.detail

import android.adservices.adid.AdId
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularmovies.model.MovieDetailResponse
import com.example.popularmovies.network.ApiClient
import com.example.popularmovies.util.Constants
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    val movieResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun getMovieDetail(movieId: Int){
        isLoading.value=true

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient()
                    .getMovieDetail(movieId = movieId.toString(), token = Constants.BEARER_TOKEN)

                if (response.isSuccessful) {
                    movieResponse.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "Bilinmeyen bir hata oluştu"
                    } else {
                        errorMessage.value = response.message()
                    }

                    }
                } catch (e: Exception){
                    errorMessage.value=e.message


                } finally {
                    isLoading.value = false
                }
        }
    }
}