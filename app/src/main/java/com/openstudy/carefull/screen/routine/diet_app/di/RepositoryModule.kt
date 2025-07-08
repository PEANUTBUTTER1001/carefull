package com.openstudy.carefull.screen.routine.diet_app.di

import com.openstudy.carefull.screen.routine.diet_data.repository.DietRepositoryImpl
import com.openstudy.carefull.screen.routine.diet_shared.repository.DietRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // 이 모듈의 규칙은 앱 전체 생명주기 동안 유효합니다.
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDietRepository(
        dietRepositoryImpl: DietRepositoryImpl
    ): DietRepository
}