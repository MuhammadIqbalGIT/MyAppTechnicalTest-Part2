package com.example.myapptechnicaltest_part2.di

import com.example.core.domain.repository.IChuckNorrisRepository
import com.example.core.domain.usecase.ChuckNorrisInteractor
import com.example.core.domain.usecase.ChuckNorrisUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideChuckNorrisUseCase(repository: IChuckNorrisRepository): ChuckNorrisUseCase {
        return ChuckNorrisInteractor(repository)
    }
}