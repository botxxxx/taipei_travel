package com.example.travel.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkService {

    @Singleton
    @Provides
    fun provideUnsplashService(): ApiService {
        return ApiService.create()
    }
}