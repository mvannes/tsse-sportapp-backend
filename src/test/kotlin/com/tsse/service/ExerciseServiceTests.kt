package com.tsse.service

import com.tsse.domain.Category
import com.tsse.domain.Exercise
import com.tsse.domain.ExerciseAlreadyExistsException
import com.tsse.domain.ExerciseNotFoundException
import com.tsse.repository.ExerciseRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

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
        MockitoAnnotations.initMocks(this::class)
        exerciseService = ExerciseServiceImpl(exerciseRepository)
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
        val actual = exerciseService.getAllExercises()

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
        given(exerciseRepository.findByName(expected.name)).willReturn(null)
        given(exerciseRepository.save(expected)).willReturn(expected)

        // Actual function call.
        val actual = exerciseService.createExercise(expected)

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(exerciseRepository, times(1)).findByName(expected.name)
        verify(exerciseRepository, times(1)).save(expected)
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test(expected = ExerciseAlreadyExistsException::class)
    fun testCreateExercise_ThrowsAlreadyExistsException() {
        // Mock object.
        val duplicateExercise = Exercise("Name", "Description.")

        // Mockito expectations.
        given(exerciseRepository.findByName(duplicateExercise.name)).willReturn(duplicateExercise)

        // Actual call will throw the expected exception.
        exerciseService.createExercise(duplicateExercise)
    }

    @Test
    fun testGetExerciseById_ReturnsExercise() {
        // Mock objects.
        val expected = Exercise("Name", "Description.")
        val id = expected.id

        // Mockito expectations.
        given(exerciseRepository.findOne(id)).willReturn(expected)

        // Actual function call and result.
        val actual = exerciseService.getExerciseById(id)

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(exerciseRepository, times(1)).findOne(id)
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test(expected = ExerciseNotFoundException::class)
    fun testGetExerciseById_ThrowsNotFoundException() {
        // Mock identifier.
        val id = 1L

        // Mockito expectations.
        given(exerciseRepository.findOne(id)).willReturn(null)

        // Actual call will throw the expected exception.
        exerciseService.getExerciseById(id)
    }

    @Test
    fun testDeleteAllExercises() {
        // Mockito expectations, Unit function will return nothing when called.
        willDoNothing().given(exerciseRepository).deleteAll()

        // Actual function call.
        exerciseService.deleteAllExercises()

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
        exerciseService.deleteExercise(id)

        // Assertions.
        verify(exerciseRepository, times(1)).delete(id)
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test
    fun testUpdateExercise_ReturnsUpdatedExercise() {
        // Mock objects.
        val expected = Exercise("Name", "Description.")
        val id = expected.id

        // Mockito expectations, repository will be accessed twice.
        given(exerciseRepository.findOne(id)).willReturn(expected)
        given(exerciseRepository.save(expected)).willReturn(expected)

        // Actual function call returns updated exercise.
        val actual = exerciseService.updateExercise(expected)

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(exerciseRepository, times(1)).findOne(id)
        verify(exerciseRepository, times(1)).save(expected)
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test(expected = ExerciseNotFoundException::class)
    fun testUpdateExercise_ThrowsExerciseNotFoundException() {
        // Mock objects.
        val mockExercise = Exercise("Name", "Description.")
        val id = mockExercise.id

        // Mockito expectations, findOne() will throw an exception when called.
        given(exerciseRepository.findOne(id)).willThrow(ExerciseNotFoundException::class.java)

        // Actual function call will throw exception.
        exerciseService.updateExercise(mockExercise)
    }

    @Test
    fun testGetExerciseByName_ReturnsExercise() {
        // Mock objects.
        val expected = Exercise("Name", "Description.")
        val name = expected.name

        // Mockito expectations.
        given(exerciseRepository.findByName(name)).willReturn(expected)

        // Actual function call
        val actual = exerciseService.getExerciseByName(name)

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(exerciseRepository, times(1)).findByName(name)
        verifyNoMoreInteractions(exerciseRepository)
    }

    @Test(expected = ExerciseNotFoundException::class)
    fun testGetExerciseByName_ThrowsExerciseNotFoundException() {
        // Mock name.
        val name = "Name"

        // Mockito expectations.
        given(exerciseRepository.findByName(name)).willReturn(null)

        // Actual call throws expectations.
        exerciseService.getExerciseByName(name)
    }

    @Test
    fun testGetExercisesByCategory_ReturnsListOfExercises() {
        // Mock expected list of exercises.
        val expected = arrayListOf(
                Exercise("Name 1", "Description 1."),
                Exercise("Name 2", "Description 2."))

        // Mockito expectations.
        given(exerciseRepository.findByCategory(Category.DEFAULT)).willReturn(expected)

        // Actual call retrieves exercises.
        val actual = exerciseService.getExercisesByCategory(Category.DEFAULT)

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(exerciseRepository, times(1)).findByCategory(Category.DEFAULT)
        verifyNoMoreInteractions(exerciseRepository)
    }

}