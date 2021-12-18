package com.pablobarriosdevs.easytask.di

import android.app.Application
import androidx.room.Room
import com.pablobarriosdevs.easytask.data.local_data_source.TaskDataBase
import com.pablobarriosdevs.easytask.data.repository.TaskRepositoryImpl
import com.pablobarriosdevs.easytask.domain.repository.TaskRepository
import com.pablobarriosdevs.easytask.domain.use_cases.*
import com.pablobarriosdevs.easytask.domain.use_cases.wrapper.UseCasesWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDataBase(app: Application): TaskDataBase {
        return Room.databaseBuilder(
            app,
            TaskDataBase::class.java,
            TaskDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: TaskDataBase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: TaskRepository): UseCasesWrapper {
        return UseCasesWrapper(
        insertTaskUseCase= InsertTaskUseCase(repository = repository),
        insertSubTaskUseCase= InsertSubTaskUseCase(repository = repository),
        getAllTasksByTargetDateUseCase= GetAllTasksByTodayDateUseCase(repository = repository),
        getSingleTaskWithSubTaskUseCase= GetSingleTaskWithSubTaskUseCase(repository = repository),
        deleteTaskUseCase= DeleteTaskUseCase(repository = repository),
        searchTaskUseCase= SearchTaskUseCase(repository = repository),
        deleteSubTaskUseCase= DeleteSubTaskUseCase(repository = repository),
        rowCalendarUseCase= RowCalendarUseCase(),
        )
    }
}









