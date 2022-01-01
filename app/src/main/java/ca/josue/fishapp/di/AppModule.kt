package ca.josue.fishapp.di

import android.app.Application
import androidx.room.Room
import ca.josue.fishapp.data.local.FishDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideFishDatabase(app : Application) : FishDatabase {
//        return Room.databaseBuilder(
//            app,
//            FishDatabase::class.java,
//            FishDatabase.DATABASE_NAME
//        ).build()
//    }


}