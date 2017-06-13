package com.tsse.service

import com.tsse.domain.ResourceNotFoundException
import com.tsse.domain.model.Exercise
import com.tsse.repository.ExerciseRepository
import com.tsse.service.impl.ExerciseService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringRunner

/**
 * Unit tests for exercise service.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@RunWith(SpringRunner::class)
class ExerciseServiceTests {

    @Mock lateinit var exerciseRepository: ExerciseRepository

    lateinit var exerciseService: ExerciseService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(ExerciseServiceTests::class)
        exerciseService = ExerciseService(exerciseRepository)
    }

    @Test
    fun testGetAllExercises_ReturnsListOfExercises() {
        // Expected objects.
        val expected = arrayListOf(
                Exercise("Name 1", "Description 1."),
                Exercise("Name 2", "Description 2."))

        // Mockito expectations.
        given(exerciseRepository.findAll()).willReturn(expected)

        // Actual function call.
        val actual = exerciseService.findAll()

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(exerciseRepository, times(1)).findAll()
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test
    fun testCreateExercise_ReturnsNewlyCreatedExercise() {
        // Expected object.
        val expected = Exercise("Name", "Description.")

        // Mockito expectations.
        given(exerciseRepository.save(expected)).willReturn(expected)

        // Actual function call.
        val actual = exerciseService.create(expected)

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(exerciseRepository, times(1)).save(expected)
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test
    fun testGetExerciseById_ReturnsExercise() {
        // Mock objects.
        val expected = Exercise("Name", "Description.")
        val id = expected.id

        // Mockito expectations.
        given(exerciseRepository.findOne(id)).willReturn(expected)

        // Actual function call and result.
        val actual = exerciseService.findOne(id)

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(exerciseRepository, times(1)).findOne(id)
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test(expected = ResourceNotFoundException::class)
    fun testGetExerciseById_ThrowsNotFoundException() {
        // Mock identifier.
        val id = 1L

        // Mockito expectations.
        given(exerciseRepository.findOne(id)).willReturn(null)

        // Actual call will throw the expected exception.
        exerciseService.findOne(id)
    }

    @Test
    fun testDeleteAllExercises() {
        // Mockito expectations, Unit function will return nothing when called.
        willDoNothing().given(exerciseRepository).deleteAll()

        // Actual function call.
        exerciseService.deleteAll()

        // Assertions.
        verify(exerciseRepository, times(1)).deleteAll()
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test
    fun testDeleteExerciseById() {
        // Mock objects.
        val id = 1L

        // Mockito expectations, Unit function will return nothing when called.
        willDoNothing().given(exerciseRepository).delete(id)

        // Actual function call.
        exerciseService.delete(id)

        // Assertions.
        verify(exerciseRepository, times(1)).delete(id)
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test
    fun testUpdateExercise_ReturnsUpdatedExercise() {
        // Mock objects.
        val mockExercise = Exercise("Name", "Description.")
        val id = mockExercise.id

        // Mockito expectations, repository will be accessed twice.
        given(exerciseRepository.save(mockExercise)).willReturn(mockExercise)

        // Actual function call returns updated exercise.
        exerciseService.update(mockExercise, id)

        verify(exerciseRepository, times(1)).save(mockExercise)
        verifyNoMoreInteractions(exerciseRepository)
    }

}