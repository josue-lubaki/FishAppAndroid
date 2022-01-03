package ca.josue.fishapp.di

import android.app.Application
import androidx.room.Room
import ca.josue.fishapp.data.data_source.local.FishDatabase
import ca.josue.fishapp.data.repository.MyOrderRepositoryImpl
import ca.josue.fishapp.data.repository.OrderItemRepositoryImpl
import ca.josue.fishapp.data.repository.ProductResponseRepositoryImpl
import ca.josue.fishapp.data.repository.UserRepositoryImpl
import ca.josue.fishapp.domain.repository.MyOrderRepository
import ca.josue.fishapp.domain.repository.OrderItemRepository
import ca.josue.fishapp.domain.repository.ProductRoomRepository
import ca.josue.fishapp.domain.repository.UserRepository
import ca.josue.fishapp.domain.viewModel.MyOrderViewModel
import ca.josue.fishapp.domain.viewModel.OrderItemViewModel
import ca.josue.fishapp.domain.viewModel.ProductRoomViewModel
import ca.josue.fishapp.ui.activity.MainActivity
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
    fun provideContext(context : MainActivity) : MainActivity {
        return context
    }

    @Provides
    @Singleton
    fun provideFishDatabase(app : Application) : FishDatabase {
        return Room.databaseBuilder(
            app,
            FishDatabase::class.java,
            FishDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db : FishDatabase) : UserRepository {
        return UserRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun provideProductResponseRepository(db : FishDatabase) : ProductRoomRepository {
        return ProductResponseRepositoryImpl(db.productDao)
    }

    @Provides
    @Singleton
    fun provideProductResponseVIewModel(repo : ProductRoomRepository) : ProductRoomViewModel {
        return ProductRoomViewModel(repo)
    }

    @Provides
    @Singleton
    fun provideMyOrderRepository(db : FishDatabase) : MyOrderRepository {
        return MyOrderRepositoryImpl(db.myOrderDao)
    }

    @Provides
    @Singleton
    fun provideMyOrderViewModel(repo : MyOrderRepository) : MyOrderViewModel {
        return MyOrderViewModel(repo)
    }

    @Provides
    @Singleton
    fun provideOrderItemRepository(db : FishDatabase) : OrderItemRepository{
        return OrderItemRepositoryImpl(db.orderItemDao)
    }

    @Provides
    @Singleton
    fun provideOrderItemViewModel(repo : OrderItemRepository) : OrderItemViewModel {
        return OrderItemViewModel(repo)
    }



}