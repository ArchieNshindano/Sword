package com.example.Sword

import android.content.Context
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import com.archie.Sword.repositories.database.DaoFunctionsForTheme
import com.archie.Sword.repositories.database.DaoFunctionsForVerses
import com.archie.Sword.repositories.database.LocalDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class VerseDataBaseTest{

    // get reference to the LanguageDatabase and LanguageDao class
    private lateinit var db: LocalDatabase
    private lateinit var dao: DaoFunctionsForVerses
    private val context = ApplicationProvider.getApplicationContext<Context>()

    // Override function setUp() and annotate it with @Before
    // this function will be called at first when this test class is called

    @get:Rule
    val helper = MigrationTestHelper(

        InstrumentationRegistry.getInstrumentation(),
        LocalDatabase::class.java,
        listOf(),
        FrameworkSQLiteOpenHelperFactory()
    )
    @Before
    fun setUp() {
        // get context -- since this is an instrumental test it requires
        // context from the running application

        // initialize the db and dao variable
        db = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java).build()
        dao = db.daoFunctionsForVerses()
    }

    // Override function closeDb() and annotate it with @After
    // this function will be called at last when this test class is called
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    // create a test function and annotate it with @Test
    // here we are first adding an item to the db and then checking if that item
    // is present in the db -- if the item is present then our test cases pass
    @Test
    @Throws(Exception::class)
    fun writeAndReadVerse() {

//        val verse = Verse(
//            bookName = "John",
//            chapterAndVerseNumber = "1:1",
//            verse = "jjkbjbkbkje",
//            bookPosition = 1,
//            date = 33,
//            themeName = "trust",
//            photoFilePath = "hbhbdb",
//            id = 0,
//        )


        //dao.addVerse(verse)



    }

}