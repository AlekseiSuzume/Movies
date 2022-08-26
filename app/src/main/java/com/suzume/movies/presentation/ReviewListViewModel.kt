package com.suzume.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suzume.movies.api.ApiFactory
import com.suzume.movies.data.pojo.reviewResponse.Review
import com.suzume.movies.data.pojo.reviewResponse.ReviewResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ReviewListViewModel(application: Application) : AndroidViewModel(application) {

    private companion object {
        const val POSITIVE_REVIEW = "Позитивный"
        const val NEGATIVE_REVIEW = "Негативный"
        const val NEUTRAL_REVIEW = "Нейтральный"
    }

    private val compositeDisposable = CompositeDisposable()
    private val apiService = ApiFactory.getApiService()

    private var allPage = 1
    private var positivePage = 1
    private var negativePage = 1
    private var neutralPage = 1

    private val _reviewAllList = MutableLiveData<List<Review>>()
    val reviewAllList: LiveData<List<Review>>
        get() = _reviewAllList

    private val _reviewPositiveList = MutableLiveData<List<Review>>()
    val reviewPositiveList: LiveData<List<Review>>
        get() = _reviewPositiveList

    private val _reviewNegativeList = MutableLiveData<List<Review>>()
    val reviewNegativeList: LiveData<List<Review>>
        get() = _reviewNegativeList

    private val _reviewNeutralList = MutableLiveData<List<Review>>()
    val reviewNeutralList: LiveData<List<Review>>
        get() = _reviewNeutralList

    private val _allReviewCount = MutableLiveData<Int>()
    val allReviewCount: LiveData<Int>
        get() = _allReviewCount

    private val _positiveReviewCount = MutableLiveData<Int>()
    val positiveReviewCount: LiveData<Int>
        get() = _positiveReviewCount

    private val _negativeReviewCount = MutableLiveData<Int>()
    val negativeReviewCount: LiveData<Int>
        get() = _negativeReviewCount

    private val _neutralReviewCount = MutableLiveData<Int>()
    val neutralReviewCount: LiveData<Int>
        get() = _neutralReviewCount

    fun refreshReviewAllList(movieId: Int) {
        val disposable = loadReviewAllResponse(movieId, allPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _reviewAllList.value = _reviewAllList.value?.plus(it.reviews) ?: it.reviews
                allPage++
                _allReviewCount.value = it.total
            }, {
                Log.d("ReviewListViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun refreshReviewPositiveList(movieId: Int) {
        val disposable = loadReviewTypedResponse(movieId, POSITIVE_REVIEW, positivePage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _reviewPositiveList.value =
                    _reviewPositiveList.value?.plus(it.reviews) ?: it.reviews
                positivePage++
                _positiveReviewCount.value = it.total
            }, {
                Log.d("ReviewListViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun refreshReviewNegativeList(movieId: Int) {
        val disposable = loadReviewTypedResponse(movieId, NEGATIVE_REVIEW, negativePage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _reviewNegativeList.value =
                    _reviewNegativeList.value?.plus(it.reviews) ?: it.reviews
                negativePage++
                _negativeReviewCount.value = it.total
            }, {
                Log.d("ReviewListViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun refreshReviewNeutralList(movieId: Int) {
        val disposable = loadReviewTypedResponse(movieId, NEUTRAL_REVIEW, neutralPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _reviewNeutralList.value = _reviewNeutralList.value?.plus(it.reviews) ?: it.reviews
                neutralPage++
                _neutralReviewCount.value = it.total
            }, {
                Log.d("ReviewListViewModel", it.toString())
            })
        compositeDisposable.add(disposable)
    }


    private fun loadReviewAllResponse(movieId: Int, page: Int): Single<ReviewResponse> {
        return apiService.loadMovieReviewAll(searchId = movieId, page = page)
    }

    private fun loadReviewTypedResponse(
        movieId: Int,
        type: String,
        page: Int,
    ): Single<ReviewResponse> {
        return apiService.loadMovieReviewTyped(searchId = movieId, searchType = type, page = page)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}