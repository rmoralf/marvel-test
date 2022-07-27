package com.rmoralf.marveltest.data.database

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.rmoralf.marveltest.data.database.dao.CharacterDao
import com.rmoralf.marveltest.data.database.entities.CharacterEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class CharacterDatabaseTest {

    private lateinit var characterDatabase: CharacterDatabase
    private lateinit var characterDao: CharacterDao

    private val testCharacterEntity = CharacterEntity(
        id = 1,
        name = "Hulk",
        thumbnail_path = "thumbnail_path",
        thumbnail_ext = "thumbnail_ext"
    )

    @Before
    fun setUp() {
        characterDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            CharacterDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        characterDao = characterDatabase.getCharacterDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        characterDatabase.close()
    }


    @Test
    fun `write character in database`() = runBlocking {
        val character = testCharacterEntity

        characterDao.insert(character)

        assertEquals(1, characterDao.getAllFavedCharacters().size)
    }

    @Test
    fun `write and read all character from database`() = runBlocking {
        val character = testCharacterEntity

        characterDao.insert(character)

        val allFavedCharacters = characterDao.getAllFavedCharacters()
        assertTrue { allFavedCharacters.contains(character) }
    }

    @Test
    fun `write and read single character from database`() = runBlocking {
        val character = testCharacterEntity

        characterDao.insert(character)

        val allFavedCharacters = characterDao.getByIds(intArrayOf(testCharacterEntity.id))
        assertTrue { allFavedCharacters.contains(character) }
    }

    @Test
    fun `remove character from database`() = runBlocking {
        val character = testCharacterEntity
        characterDao.insert(character)
        assertEquals(1, characterDao.getAllFavedCharacters().size)
        characterDao.delete(character)
        assertEquals(0, characterDao.getAllFavedCharacters().size)
    }
}
