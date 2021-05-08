package com.bayuspace.restoranlivedatawithapi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bayuspace.restoranlivedatawithapi.data.CustomerReviewsItem
import com.bayuspace.restoranlivedatawithapi.data.Restaurant
import com.bayuspace.restoranlivedatawithapi.data.RestaurantResponse
import com.bayuspace.restoranlivedatawithapi.api.ApiConfig
import com.bayuspace.restoranlivedatawithapi.data.PostReviewResponseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> = _restaurant

    private var _listReview = MutableLiveData<List<CustomerReviewsItem>>()
    val listReview: LiveData<List<CustomerReviewsItem>> = _listReview

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    companion object {
        private const val TAG = "Main View Model"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    init {
        findRestaurant()
    }

    fun findRestaurant() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _restaurant.value = response.body()?.restaurant
                    _listReview.value = response.body()?.restaurant?.customerReviews
                } else {
                    Log.d(TAG, "onResponse: not successfully ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = t.localizedMessage
                Log.e(TAG, "onFailure: ${t.localizedMessage}")
            }

        })
    }

    fun postReview(review: String) {
        _isLoading.value = true
        val client =
            ApiConfig.getApiService().postReview(RESTAURANT_ID, "Dicoding Indonesia", review)
        client.enqueue(object : Callback<PostReviewResponseResponse> {
            override fun onResponse(
                call: Call<PostReviewResponseResponse>,
                response: Response<PostReviewResponseResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listReview.value = response.body()?.customerReviews
                } else Log.d(TAG, "onResponse: not successfully : ${response.body()?.message}")
            }

            override fun onFailure(call: Call<PostReviewResponseResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = t.localizedMessage
                Log.e(TAG, "onFailure: ${t.localizedMessage}")
            }
        })
    }
}