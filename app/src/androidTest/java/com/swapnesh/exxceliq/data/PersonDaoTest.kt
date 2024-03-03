package com.swapnesh.exxceliq.data


import androidx.test.ext.junit.runners.AndroidJUnit4
import com.swapnesh.exxceliq.data.local.database.daos.PersonDao
import com.swapnesh.exxceliq.util.getValue
import com.swapnesh.exxceliq.util.testA
import com.swapnesh.exxceliq.util.testB


import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PersonDaoTest : DbTest() {
    private lateinit var personDao: PersonDao
    private val setA = testA.copy()
    private val setB = testB.copy()



    @Before fun createDb() {
        personDao = db.getUserList()

        // Insert legoSets in non-alphabetical order to test that results are sorted by name
        runBlocking {
            personDao.insert(setA)
            personDao.insert(setB)
        }
    }

    @Test fun testGetSets() {
        val list = getValue(personDao.getListPerson())

        assertThat(list.size, equalTo(2))

        // Ensure legoSet list is sorted by name
        assertThat(list[0], equalTo(setA))
        assertThat(list[1], equalTo(setB))
    }

}
