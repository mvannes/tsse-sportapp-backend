package com.tsse.controller


import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.ResourceNotFoundException
import com.tsse.domain.model.Workout
import com.tsse.service.WorkoutService
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

/**
 * MockMVC and Mockito based integration tests.
 *
 * @author Floris van Lent
 * @version 1.0.1
 */
@RunWith(SpringRunner::class)
class WorkoutControllerTests {

    @Mock lateinit var workoutService: WorkoutService

    @InjectMocks lateinit var workoutController: WorkoutController

    lateinit var mockMvc: MockMvc

    val URI = "/api/workouts/"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(WorkoutControllerTests::class)
        mockMvc = MockMvcBuilders
                .standaloneSetup(workoutController)
                .build()
    }

    @Test
    fun testSaveWorkout_returnsHttpStatusIsCreated() {

        val workout = Workout("Name", "Description", ArrayList())

        given(workoutService.create(workout)).willReturn(workout)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(workout)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(workout) }
    }


    @Test
    @Ignore
    fun testSaveWorkoutEmptyName_returnsHttpStatusBadRequest() {

        val workout = Workout("", "Description", ArrayList())

        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(workout)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", Matchers.equalTo("Object sent is not valid: [Workout name cannot be empty!]")) }
    }

    @Test
    fun testSaveWorkoutEmptyDescription_returnsHttpStatusIsCreated() {

        val workout = Workout("Name", "", ArrayList())

        given(workoutService.create(workout)).willReturn(workout)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(workout)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(workout) }
    }

    @Test
    fun testGetWorkoutById_returnsHttpStatusOk() {

        val workout = Workout("Name", "Description", ArrayList())
        val id = 1L
        given(workoutService.findOne(id)).willReturn(workout)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI + "{id}", id))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { content().equals(workout) }


    }

    @Test
    fun testGetWorkoutById_returnsHttpStatusIsNotFound() {

        val id = 1L
        given(workoutService.findOne(id)).willThrow(ResourceNotFoundException::class.java)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI + "{id}", id))
                .andExpect(status().isNotFound)
    }

    @Test
    fun testGetAllWorkouts_returnsHttpStatusOk() {

        val results = arrayListOf(Workout("Name", "Description", ArrayList()),
                Workout("Name 2", "Description 2", ArrayList()))


        given(workoutService.findAll()).willReturn(results)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { content().equals(results) }

    }

    @Test
    @Ignore
    fun testUpdateWorkout_returnsHttpStatusOk() {
        val workout = Workout("Name", "Description", ArrayList())

        given(workoutService.update(workout, 1L))

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(workout)))
                .andExpect(status().isOk)
                .andExpect { content().equals(workout) }

    }

    @Test
    @Ignore
    fun testUpdateWorkoutInvalidForm_returnsHttpStatusBadRequest() {

        val workout = Workout("", "Description", ArrayList())

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(workout)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", Matchers.equalTo("Object sent is not valid: [Workout name cannot be empty!]")) }

    }

    @Test
    @Ignore
    fun testUpdateWorkoutNonExistingWorkout_returnsHttpStatusNotFound() {

        val workout = Workout("Name", "Description", ArrayList())

        given(workoutService.update(workout, 1L)).willThrow(ResourceNotFoundException::class.java)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(workout)))
                .andExpect(status().isNotFound)

    }

    @Test
    fun testDeleteWorkout_returnsHttpStatusNoContent() {

        val id = 1L

        willDoNothing().given(workoutService).delete(id)

        mockMvc.perform(
                MockMvcRequestBuilders.delete(URI + "{id}", id))
                .andExpect(status().isNoContent)
    }

    private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)

}