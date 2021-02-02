package com.seloger.android.estateviewer.ui.gallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.seloger.android.estateviewer.MainCoroutineRule
import com.seloger.android.estateviewer.data.DataSource
import com.seloger.android.estateviewer.data.entity.Estate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GalleryViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test repository response made of an empty list of estates`() = runBlockingTest {
        val repository: DataSource = mock()
        whenever(repository.getEstates())
            .thenReturn(MutableLiveData(arrayListOf()))
        val expected = 0
        val model = GalleryViewModel(repository)

        val estates = model.estates.value

        assertNotNull(estates)
        assertEquals(expected, estates!!.size)
    }

    @Test
    fun `test repository response made of one item of estate`() = runBlockingTest {
        val repository: DataSource = mock()
        val item = Estate(1, 8, "IDF", 200.0, "", 4000000.0, "", "", 10)
        whenever(repository.getEstates())
            .thenReturn(
                MutableLiveData(
                    arrayListOf(
                        item
                    )
                )
            )
        val expected = 1
        val model = GalleryViewModel(repository)

        val estates = model.estates.value

        assertNotNull(estates)
        assertEquals(expected, estates!!.size)
    }

    @Test
    fun `test repository response made of multiple estate`() = runBlockingTest {
        val repository: DataSource = mock()
        val expected = 40
        val response = arrayListOf<Estate>()
        for (i in 1..expected) {
            response += Estate(i, 8, "IDF", 200.0, "", 4000000.0, "", "", 10)
        }
        whenever(repository.getEstates())
            .thenReturn(
                MutableLiveData(
                    response
                )
            )

        val model = GalleryViewModel(repository)

        val estates = model.estates.value

        assertNotNull(estates)
        assertEquals(expected, estates!!.size)
    }
}