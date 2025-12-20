package com.sarang.torang.di.dialogsbox_di

import com.sarang.torang.dialogsbox.usecase.DeleteReviewUseCase
import com.sarang.torang.repository.feed.FeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DeleteReviewUseCaseImpl {
    @Provides
    fun providesDeleteReviewUseCase(
        feedRepository: FeedRepository
    ): DeleteReviewUseCase {
        return object : DeleteReviewUseCase {
            override suspend fun invoke(reviewId : Int) {
                feedRepository.deleteById(reviewId)
            }
        }
    }
}