package com.aumarbello.carowners.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aumarbello.carowners.models.Filter
import com.aumarbello.carowners.service.CarOwnersService
import com.aumarbello.carowners.utils.CSVParser
import com.aumarbello.carowners.utils.TestObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import java.io.File

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class OwnersRepositoryTest {
    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    private lateinit var service: CarOwnersService
    private lateinit var parser: CSVParser

    private lateinit var repository: CarOwnersRepository

    @Before
    fun setUp() {
        service = mock(CarOwnersService::class.java)
        parser = mock(CSVParser::class.java)

        repository = CarOwnersRepository(service, parser)
    }

    @Test
    fun `when file exists then dont download`() = runBlocking {
        val file = mock(File::class.java)

        `when`(file.exists()).thenReturn(true)
        `when`(parser.owners).thenReturn(emptyList())

        val filter = Filter("male", listOf("Blue", "Red"), listOf("Nigeria"))
        repository.filterOwners(filter, file)

        verify(parser).parseFile(file)
        verify(parser).owners

        verifyZeroInteractions(service)

        Unit
    }

    @Test
    fun `when no element matches filter return  empty data set`() = runBlocking {
        val file = mock(File::class.java)

        `when`(file.exists()).thenReturn(true)
        `when`(parser.owners).thenReturn(TestObjects.owners)

        val filter = Filter(
            "Male",
            listOf("Green", "Blue"),
            listOf("Nigeria", "Canada")
        )
        val response = repository.filterOwners(filter, file)
        assertThat(response.size, `is`(0))
    }

    @Test
    fun `when filter is valid return appropriate data set`() = runBlocking {
        val file = mock(File::class.java)

        `when`(file.exists()).thenReturn(true)
        `when`(parser.owners).thenReturn(TestObjects.owners)

        val filter = Filter(
            "Female",
            listOf("Green", "Blue"),
            listOf("Canada")
        )
        val response = repository.filterOwners(filter, file)
        assertThat(response.size, `is`(4))

        response.forEach {
            assertThat(it.gender, `is`("Female"))
            assertThat(it.country, `is`("Canada"))
            assertTrue(listOf("Green", "Blue").contains(it.carColor))
        }
    }

    @Test
    fun `when filter contains multiple countries return appropriate data set`() = runBlocking {
        val file = mock(File::class.java)

        `when`(file.exists()).thenReturn(true)
        `when`(parser.owners).thenReturn(TestObjects.owners)

        val filter = Filter(
            "female",
            listOf("Green", "Blue"),
            listOf("Canada", "USA")
        )
        val response = repository.filterOwners(filter, file)
        assertThat(response.size, `is`(5))

        response.forEach {
            assertThat(it.gender, `is`("Female"))
            assertTrue(listOf("Canada", "USA").contains(it.country))
            assertTrue(listOf("Green", "Blue").contains(it.carColor))
        }
    }

    @Test
    fun `when filter contains empty options perform partial filter`() = runBlocking {
        val file = mock(File::class.java)

        `when`(file.exists()).thenReturn(true)
        `when`(parser.owners).thenReturn(TestObjects.owners)

        val filter = Filter(
            "female",
            listOf(),
            listOf()
        )
        val response = repository.filterOwners(filter, file)
        assertThat(response.size, `is`(7))

        response.forEach {
            assertThat(it.gender, `is`("Female"))
        }
    }
}