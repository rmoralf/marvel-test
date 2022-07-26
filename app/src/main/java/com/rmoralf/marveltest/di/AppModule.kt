package com.rmoralf.marveltest.di

import android.content.Context
import androidx.room.Room
import com.rmoralf.marveltest.core.Constants
import com.rmoralf.marveltest.core.Constants.DbConstants.DB_NAME
import com.rmoralf.marveltest.core.Constants.Network.API_ENDPOINT
import com.rmoralf.marveltest.core.Constants.Network.CACHE_SIZE
import com.rmoralf.marveltest.core.MarvelUtils
import com.rmoralf.marveltest.data.database.CharacterDatabase
import com.rmoralf.marveltest.data.database.dao.CharacterDao
import com.rmoralf.marveltest.data.network.remote.CharactersService
import com.rmoralf.marveltest.data.repository.CharacterPagingSource
import com.rmoralf.marveltest.data.repository.CharactersRepositoryImpl
import com.rmoralf.marveltest.data.repository.DatabaseRepositoryImpl
import com.rmoralf.marveltest.domain.repository.CharactersRepository
import com.rmoralf.marveltest.domain.repository.DatabaseRepository
import com.rmoralf.marveltest.domain.usecases.database.*
import com.rmoralf.marveltest.domain.usecases.search.GetCharacterDetails
import com.rmoralf.marveltest.domain.usecases.search.SearchCharacters
import com.rmoralf.marveltest.domain.usecases.search.SearchUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideCharacterRepository(
        charactersService: CharactersService
    ): CharactersRepository = CharactersRepositoryImpl(charactersService)

    @Provides
    fun provideCharactersService(restClient: Retrofit): CharactersService =
        restClient.create()

    @Provides
    fun providesSearchUseCases(
        charactersRepository: CharactersRepository
    ) = SearchUseCases(
        searchCharacters = SearchCharacters(charactersRepository),
        getCharacterDetails = GetCharacterDetails(charactersRepository)
    )

    @Provides
    fun providePagingSource(
        charactersService: CharactersService,
        query: String
    ) = CharacterPagingSource(charactersService, query)


    //Retrofit2
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    fun provideMarvelInterceptor() = Interceptor {
        val original = it.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(Constants.Network.QUERY_APIKEY, MarvelUtils.API_KEY)
            .addQueryParameter(Constants.Network.QUERY_TS, MarvelUtils.TS)
            .addQueryParameter(Constants.Network.QUERY_HASH, MarvelUtils.marvelHash())
            .build()

        it.proceed(original.newBuilder().url(url).build())
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        marvelInterceptor: Interceptor,
        @ApplicationContext context: Context
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(marvelInterceptor)
        .cache(Cache(context.cacheDir, CACHE_SIZE))
        .build()

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    //Room
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CharacterDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun provideCharacterDao(db: CharacterDatabase) = db.getCharacterDao()

    @Provides
    fun provideRoomRepository(characterDao: CharacterDao): DatabaseRepository =
        DatabaseRepositoryImpl(characterDao)

    @Provides
    fun provideUseCases(
        databaseRepository: DatabaseRepository
    ) = DatabaseUseCases(
        favCharacter = FavCharacter(databaseRepository),
        unfavCharacter = UnfavCharacter(databaseRepository),
        getAllFavedCharacters = GetAllFavedCharacters(databaseRepository),
        checkIfCharacterIsFaved = CheckIfCharacterIsFaved(databaseRepository)
    )
}