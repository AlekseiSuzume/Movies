package com.suzume.movies.presentation.reviewScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suzume.movies.domain.models.review.ReviewsListDomainModel
import com.suzume.movies.domain.usecases.review.LoadReviewsListUseCase
import com.suzume.movies.domain.usecases.review.LoadReviewsTypedUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReviewListViewModel @Inject constructor(
    private val loadReviewsListUseCase: LoadReviewsListUseCase,
    private val loadReviewsTypedUseCase: LoadReviewsTypedUseCase,
) : ViewModel() {

    private val _reviewAllList = MutableLiveData<ReviewsListDomainModel>()
    val reviewAllList: LiveData<ReviewsListDomainModel> = _reviewAllList

    private val _reviewPositiveList = MutableLiveData<ReviewsListDomainModel>()
    val reviewPositiveList: LiveData<ReviewsListDomainModel> = _reviewPositiveList

    private val _reviewNegativeList = MutableLiveData<ReviewsListDomainModel>()
    val reviewNegativeList: LiveData<ReviewsListDomainModel> = _reviewNegativeList

    private val _reviewNeutralList = MutableLiveData<ReviewsListDomainModel>()
    val reviewNeutralList: LiveData<ReviewsListDomainModel> = _reviewNeutralList

    private var allPage = 1
    private var positivePage = 1
    private var negativePage = 1
    private var neutralPage = 1

    fun refreshReviewAllList(movieId: Int) {
        viewModelScope.launch {
            val reviewsList = loadReviewsListUseCase(movieId, allPage)
            val oldReviews = _reviewAllList.value?.reviews ?: mutableListOf()
            _reviewAllList.value = _reviewAllList.value
                ?.copy(reviews = oldReviews.plus(reviewsList.reviews)) ?: reviewsList
            allPage++
        }
    }

    fun refreshReviewPositiveList(movieId: Int) {
        viewModelScope.launch {
            val reviewsList = loadReviewsTypedUseCase(movieId, POSITIVE_REVIEW, positivePage)
            val oldReviews = _reviewPositiveList.value?.reviews ?: mutableListOf()
            _reviewPositiveList.value = _reviewPositiveList.value
                ?.copy(reviews = oldReviews.plus(reviewsList.reviews)) ?: reviewsList
            positivePage++
        }
    }

    fun refreshReviewNegativeList(movieId: Int) {
        viewModelScope.launch {
            val reviewsList = loadReviewsTypedUseCase(movieId, NEGATIVE_REVIEW, negativePage)
            val oldReviews = _reviewNegativeList.value?.reviews ?: mutableListOf()
            _reviewNegativeList.value = _reviewNegativeList.value
                ?.copy(reviews = oldReviews.plus(reviewsList.reviews)) ?: reviewsList
            negativePage++
        }
    }

    fun refreshReviewNeutralList(movieId: Int) {
        viewModelScope.launch {
            val reviewsList = loadReviewsTypedUseCase(movieId, NEUTRAL_REVIEW, neutralPage)
            val oldReviews = _reviewNeutralList.value?.reviews ?: mutableListOf()
            _reviewNeutralList.value = _reviewNeutralList.value
                ?.copy(reviews = oldReviews.plus(reviewsList.reviews)) ?: reviewsList
            neutralPage++
        }
    }

    private companion object {
        const val POSITIVE_REVIEW = "Позитивный"
        const val NEGATIVE_REVIEW = "Негативный"
        const val NEUTRAL_REVIEW = "Нейтральный"
    }
}