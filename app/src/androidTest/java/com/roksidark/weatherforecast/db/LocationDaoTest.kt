package com.roksidark.weatherforecast.db

//import com.google.common.truth.Truth.assertThat
import androidx.test.filters.SmallTest
import com.roksidark.weatherforecast.data.db.WeatherDatabase
import com.roksidark.weatherforecast.data.db.dao.LocationDao
import com.roksidark.weatherforecast.data.db.entity.Location
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItem
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class LocationDaoTest {

    @Inject
    @Named("test-db")
    lateinit var weatherDatabase: WeatherDatabase

    private lateinit var locationDao: LocationDao

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {

        hiltRule.inject()
        locationDao = weatherDatabase.locationDao()
    }

    @After
    fun teardown(){
        weatherDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun test_locationDao_insertIntoLocationList() = runBlocking{
        val location = Location("1", "Vilnius", "57.9", "21.56")

        locationDao.insert(location)

        val imageList = locationDao.getAll()
        imageList.collect {
            assertThat(it, hasItem(location))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_locationDao_deleteFromBookmarkList() = runBlocking {

        val location = Location("1", "Vilnius", "57.9", "21.56")
        locationDao.insert(location)
        locationDao.deleteAll()
        val imageList = locationDao.getAll()
        imageList.collect {
            assertThat(it, not(hasItem(location)))
        }
    }

    @Test
    @Throws(Exception::class)
    fun test_locationDao_i_getLocation() = runBlocking{
        val location = Location("1", "Vilnius", "57.9", "21.56")

        locationDao.insert(location)

        val item = locationDao.getLocationById(location.id)
        assertThat(item.id, equals("1"))
    }
}