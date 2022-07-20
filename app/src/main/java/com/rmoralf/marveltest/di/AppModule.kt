package com.rmoralf.marveltest.di

import android.content.Context
import com.rmoralf.marveltest.core.Constants
import com.rmoralf.marveltest.core.Constants.API_ENDPOINT
import com.rmoralf.marveltest.core.Constants.CACHE_SIZE
import com.rmoralf.marveltest.core.MarvelUtils
import com.rmoralf.marveltest.data.network.remote.CharactersService
import com.rmoralf.marveltest.data.repository.CharacterPagingSource
import com.rmoralf.marveltest.data.repository.CharactersRepositoryImpl
import com.rmoralf.marveltest.domain.repository.CharactersRepository
import com.rmoralf.marveltest.domain.usecases.GetAllCharacters
import com.rmoralf.marveltest.domain.usecases.GetCharacterDetails
import com.rmoralf.marveltest.domain.usecases.SearchUseCases
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
        getAllCharacters = GetAllCharacters(charactersRepository),
        getCharacterDetails = GetCharacterDetails(charactersRepository)
    )

    @Provides
    fun providePagingSource(
        charactersService: CharactersService
    ) = CharacterPagingSource(charactersService)


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
            .addQueryParameter(Constants.QUERY_APIKEY, MarvelUtils.API_KEY)
            .addQueryParameter(Constants.QUERY_TS, MarvelUtils.TS)
            .addQueryParameter(Constants.QUERY_HASH, MarvelUtils.marvelHash())
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
}