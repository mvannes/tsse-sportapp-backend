package com.tsse.service

import com.tsse.domain.*
import com.tsse.repository.WorkoutRepository
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
 * Unit tests for WorkoutService.
 *
 * @author Floris van Lent
 * @version 1.0.0
 */
@RunWith(SpringRunner::class)
class WorkoutServiceTests {

    @Mock lateinit var workoutRepository: WorkoutRepository

    lateinit var workoutService: WorkoutService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(WorkoutServiceTests::class)
        workoutService = WorkoutServiceImpl(workoutRepository)
    }

    @Test
    fun testGetAllWorkouts_ReturnsListOfWorkouts() {
        // Expected objects.
        val e1 = Exercise("Name 1", "Description 1.")
        val e2 = Exercise("Name 2", "Description 2.")
        val exercises: ArrayList<Exercise> = arrayListOf(e1, e2)

        val expected = arrayListOf(
                Workout("Name 1", "Description 1.", exercises),
                Workout("Name 2", "Description 2.", exercises))

        // Mockito expectations.
        given(workoutRepository.findAll()).willReturn(expected)

        // Actual function call.
        val actual = workoutService.getAllWorkouts()

        // Assertions.
        assertNotNull(actual)
        assertEquals(expected, actual)

        verify(workoutRepository, times(1)).findAll()
        verifyNoMoreInteractions(workoutRepository)
    }
//
//    @Test
//    fun testCreateWorkout_ReturnsNewlyCreatedWorkout() {
//        // Expected object.
//        val expected = Workout("Name", "Description.")
//
//        // Mockito expectations.
//        given(workoutRepository.findByName(expected.name)).willReturn(null)
//        given(workoutRepository.save(expected)).willReturn(expected)
//
//        // Actual function call.
//        val actual = workoutService.createWorkout(expected)
//
//        // Assertions.
//        assertNotNull(actual)
//        assertEquals(expected, actual)
//
//        verify(workoutRepository, times(1)).findByName(expected.name)
//        verify(workoutRepository, times(1)).save(expected)
//        verifyNoMoreInteractions(workoutRepository)
//    }
//
//    @Test(expected = WorkoutAlreadyExistsException::class)
//    fun testCreateWorkout_ThrowsAlreadyExistsException() {
//        // Mock object.
//        val duplicateWorkout = Workout("Name", "Description.")
//
//        // Mockito expectations.
//        given(workoutRepository.findByName(duplicateWorkout.name)).willReturn(duplicateWorkout)
//
//        // Actual call will throw the expected exception.
//        workoutService.createWorkout(duplicateWorkout)
//    }
//
//    @Test
//    fun testGetWorkoutById_ReturnsWorkout() {
//        // Mock objects.
//        val expected = Workout("Name", "Description.")
//        val id = expected.id
//
//        // Mockito expectations.
//        given(workoutRepository.findOne(id)).willReturn(expected)
//
//        // Actual function call and result.
//        val actual = workoutService.getWorkoutById(id)
//
//        // Assertions.
//        assertNotNull(actual)
//        assertEquals(expected, actual)
//
//        verify(workoutRepository, times(1)).findOne(id)
//        verifyNoMoreInteractions(workoutRepository)
//    }
//
//    @Test(expected = WorkoutNotFoundException::class)
//    fun testGetWorkoutById_ThrowsNotFoundException() {
//        // Mock identifier.
//        val id = 1L
//
//        // Mockito expectations.
//        given(workoutRepository.findOne(id)).willReturn(null)
//
//        // Actual call will throw the expected exception.
//        workoutService.getWorkoutById(id)
//    }
//
//    @Test
//    fun testDeleteAllWorkouts() {
//        // Mockito expectations, Unit function will return nothing when called.
//        willDoNothing().given(workoutRepository).deleteAll()
//
//        // Actual function call.
//        workoutService.deleteAllWorkouts()
//
//        // Assertions.
//        verify(workoutRepository, times(1)).deleteAll()
//        verifyNoMoreInteractions(workoutRepository)
//    }
//
//    @Test
//    fun testDeleteWorkoutById() {
//        // Mock objects.
//        val id = 1L
//
//        // Mockito expectations, Unit function will return nothing when called.
//        willDoNothing().given(workoutRepository).delete(id)
//
//        // Actual function call.
//        workoutService.deleteWorkout(id)
//
//        // Assertions.
//        verify(workoutRepository, times(1)).delete(id)
//        verifyNoMoreInteractions(workoutRepository)
//    }
//
//    @Test
//    fun testUpdateWorkout_ReturnsUpdatedWorkout() {
//        // Mock objects.
//        val expected = Workout("Name", "Description.")
//        val id = expected.id
//
//        // Mockito expectations, repository will be accessed twice.
//        given(workoutRepository.findOne(id)).willReturn(expected)
//        given(workoutRepository.save(expected)).willReturn(expected)
//
//        // Actual function call returns updated workout.
//        val actual = workoutService.updateWorkout(expected)
//
//        // Assertions.
//        assertNotNull(actual)
//        assertEquals(expected, actual)
//
//        verify(workoutRepository, times(1)).findOne(id)
//        verify(workoutRepository, times(1)).save(expected)
//        verifyNoMoreInteractions(workoutRepository)
//    }
//
//    @Test(expected = WorkoutNotFoundException::class)
//    fun testUpdateWorkout_ThrowsWorkoutNotFoundException() {
//        // Mock objects.
//        val mockWorkout = Workout("Name", "Description.")
//        val id = mockWorkout.id
//
//        // Mockito expectations, findOne() will throw an exception when called.
//        given(workoutRepository.findOne(id)).willThrow(WorkoutNotFoundException::class.java)
//
//        // Actual function call will throw exception.
//        workoutService.updateWorkout(mockWorkout)
//    }
//
//    @Test
//    fun testGetWorkoutByName_ReturnsWorkout() {
//        // Mock objects.
//        val expected = Workout("Name", "Description.")
//        val name = expected.name
//
//        // Mockito expectations.
//        given(workoutRepository.findByName(name)).willReturn(expected)
//
//        // Actual function call
//        val actual = workoutService.getWorkoutByName(name)
//
//        // Assertions.
//        assertNotNull(actual)
//        assertEquals(expected, actual)
//
//        verify(workoutRepository, times(1)).findByName(name)
//        verifyNoMoreInteractions(workoutRepository)
//    }
//
//    @Test(expected = WorkoutNotFoundException::class)
//    fun testGetWorkoutByName_ThrowsWorkoutNotFoundException() {
//        // Mock name.
//        val name = "Name"
//
//        // Mockito expectations.
//        given(workoutRepository.findByName(name)).willReturn(null)
//
//        // Actual call throws expectations.
//        workoutService.getWorkoutByName(name)
//    }
//
//    @Test
//    fun testGetWorkoutsByCategory_ReturnsListOfWorkouts() {
//        // Mock expected list of workouts.
//        val expected = arrayListOf(
//                Workout("Name 1", "Description 1."),
//                Workout("Name 2", "Description 2."))
//
//        // Mockito expectations.
//        given(workoutRepository.findByCategory(Category.DEFAULT)).willReturn(expected)
//
//        // Actual call retrieves workouts.
//        val actual = workoutService.getWorkoutsByCategory(Category.DEFAULT)
//
//        // Assertions.
//        assertNotNull(actual)
//        assertEquals(expected, actual)
//
//        verify(workoutRepository, times(1)).findByCategory(Category.DEFAULT)
//        verifyNoMoreInteractions(workoutRepository)
//    }

}