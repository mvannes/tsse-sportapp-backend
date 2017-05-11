package com.tsse.controller


import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.Workout
import com.tsse.service.WorkoutService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.ArrayList

/**
 * MockMVC and Mockito based integration tests.
 *
 * @author Floris van Lent
 * @version 1.0.0
 */
@RunWith(SpringRunner::class)
class WorkoutControllerTests {

    @Mock lateinit var workoutService: WorkoutService

    @InjectMocks lateinit var workoutController: WorkoutController

    lateinit var mockMvc: MockMvc

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

        given(workoutService.saveWorkout(workout)).willReturn(workout)
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/workouts")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(workout)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(workout) }
    }

    private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)

}