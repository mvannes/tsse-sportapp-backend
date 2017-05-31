package com.tsse.service

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

/**
 * Unit tests for WorkoutService.
 *
 * @author Floris van Lent
 * @version 1.0.1
 */
@RunWith(SpringRunner::class)
class WorkoutServiceTests {

//    @Mock lateinit var workoutRepository: WorkoutRepository
//
//    lateinit var workoutService: WorkoutService
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(WorkoutServiceTests::class)
//        workoutService = WorkoutService(workoutRepository)
//    }
//
//    @Test
//    fun testGetAllWorkouts_ReturnsListOfWorkouts() {
//        // Expected objects.
//        val e1 = Exercise("Name 1", "Description 1.")
//        val e2 = Exercise("Name 2", "Description 2.")
//        val exercises: ArrayList<Exercise> = arrayListOf(e1, e2)
//
//        val expected = arrayListOf(
//                Workout("Name 1", "Description 1.", exercises),
//                Workout("Name 2", "Description 2.", exercises))
//
//        // Mockito expectations.
//        given(workoutRepository.findAll()).willReturn(expected)
//
//        // Actual function call.
//        val actual = workoutService.getAllWorkouts()
//
//        // Assertions.
//        assertNotNull(actual)
//        assertEquals(expected, actual)
//
//        verify(workoutRepository, times(1)).findAll()
//        verifyNoMoreInteractions(workoutRepository)
//    }
//
//    @Test
//    fun testCreateWorkout_ReturnsNewlyCreatedWorkout() {
//        // Expected object.
//        val expected = Workout("Name", "Description.", ArrayList())
//
//        // Mockito expectations.
//        given(workoutRepository.findOne(expected.id)).willReturn(null)
//        given(workoutRepository.save(expected)).willReturn(expected)
//
//        // Actual function call.
//        val actual = workoutService.saveWorkout(expected)
//
//        // Assertions.
//        assertNotNull(actual)
//        assertEquals(expected, actual)
//
//        verify(workoutRepository, times(1)).save(expected)
//        verifyNoMoreInteractions(workoutRepository)
//    }
//
//    @Test
//    fun testGetWorkoutById_ReturnsWorkout() {
//        // Mock objects.
//        val expected = Workout("Name", "Description.", ArrayList())
//        val id = expected.id
//
//        // Mockito expectations.
//        given(workoutRepository.findOne(id)).willReturn(expected)
//
//        // Actual function call and result.
//        val actual = workoutService.getWorkout(id)
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
//        workoutService.getWorkout(id)
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
//        val expected = Workout("Name", "Description.", ArrayList())
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
//        val mockWorkout = Workout("Name", "Description.", ArrayList())
//        val id = mockWorkout.id
//
//        // Mockito expectations, findOne() will throw an exception when called.
//        given(workoutRepository.findOne(id)).willThrow(WorkoutNotFoundException::class.java)
//
//        // Actual function call will throw exception.
//        workoutService.updateWorkout(mockWorkout)
//    }

}