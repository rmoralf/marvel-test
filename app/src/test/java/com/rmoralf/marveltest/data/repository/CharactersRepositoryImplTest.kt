package com.rmoralf.marveltest.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.rmoralf.marveltest.data.network.remote.CharactersService
import com.rmoralf.marveltest.domain.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyInt
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: CharactersService
    private lateinit var client: OkHttpClient

    private lateinit var charactersRepositoryImpl: CharactersRepositoryImpl

    private val apiCharacterDataWrapper = CharacterDataWrapper(
        code = 123,
        status = "status",
        copyright = "copyright",
        attributionText = "attributionText",
        attributionHTML = "attributionHTML",
        data = CharacterDataContainer(
            offset = 10,
            limit = 20,
            total = 30,
            count = 40,
            results = listOf(
                Character(
                    id = 123,
                    name = "name",
                    description = "description",
                    resourceURI = "resourceURI",
                    thumbnail = Image("path", "extension"),
                    urls = listOf(Url("type", "url"))
                )
            ),
        ),
        etag = "etag"
    )

    @Before
    fun setUp() {
        //Mock server
        mockWebServer = MockWebServer()
        mockWebServer.start()
        client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersService::class.java)

        //Create repository with mocked server
        charactersRepositoryImpl = CharactersRepositoryImpl(service)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `service_getCharacterDetails and check response success returned`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(apiCharacterDataWrapper))
        mockWebServer.enqueue(response)

        runBlocking {
            val actual = service.getCharacterDetails(anyInt()).toDomain()
            val expected = apiCharacterDataWrapper

            assertThat(actual).isEqualTo(expected)
        }
    }

    @Test
    fun `charactersRepositoryImpl_getCharacterDetails returns a domain Character`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(apiCharacterDataWrapper))
        mockWebServer.enqueue(response)

        runBlocking {
            charactersRepositoryImpl.getCharacterDetails(anyInt()).test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(Response.Loading)
                val emission2 = awaitItem()
                assertThat(emission2).isEqualTo(Response.Success(apiCharacterDataWrapper.data.results.first()))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `charactersRepositoryImpl_getCharacterDetails catches HTTP 400 BAD REQUEST exception if there is an error`() {
        val errorMsg = "HTTP 400 Client Error"

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
        mockWebServer.enqueue(response)

        runBlocking {
            charactersRepositoryImpl.getCharacterDetails(anyInt()).test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(Response.Loading)
                val emission2 = awaitItem()
                assertThat(emission2).isInstanceOf(Response.Error::class.java)
                assertThat(emission2).isEqualTo(Response.Error(errorMsg))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `charactersRepositoryImpl_getCharacterDetails catches HTTP 500 INTERNAL ERROR exception if there is an error`() {
        val errorMsg = "HTTP 500 Server Error"

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
        mockWebServer.enqueue(response)

        runBlocking {
            charactersRepositoryImpl.getCharacterDetails(anyInt()).test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(Response.Loading)
                val emission2 = awaitItem()
                assertThat(emission2).isInstanceOf(Response.Error::class.java)
                assertThat(emission2).isEqualTo(Response.Error(errorMsg))
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}