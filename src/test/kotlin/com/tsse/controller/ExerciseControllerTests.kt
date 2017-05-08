package com.tsse.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.ExerciseNotFoundException
import com.tsse.domain.model.Exercise
import com.tsse.service.ExerciseService
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * Exercise controller integration tests.
 *
 * Setup with MockMvc's standalone setup for controllers.
 *
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@RunWith(SpringRunner::class)
class ExerciseControllerTests {

    @Mock lateinit var exerciseService: ExerciseService

    @InjectMocks lateinit var exerciseController: ExerciseController

    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(ExerciseControllerTests::class)
        mockMvc = MockMvcBuilders
                .standaloneSetup(exerciseController)
                .build()
    }

    @Test
    fun testGetAllExercises_ReturnsHttpStatusOk() {
        given(exerciseService.getAllExercises()).willReturn(arrayListOf(
                Exercise("Name 1", "Description 1."),
                Exercise("Name 2", "Description 2.")))

        mockMvc.perform(
                get("/api/exercises"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize<Exercise>(2)))
                .andExpect(jsonPath("$[0].name", equalTo("Name 1")))
                .andExpect(jsonPath("$[0].description", equalTo("Description 1.")))
                .andExpect(jsonPath("$[1].name", equalTo("Name 2")))
                .andExpect(jsonPath("$[1].description", equalTo("Description 2.")))

        verify(exerciseService, times(1)).getAllExercises()
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testGetExerciseById_ReturnsHttpStatusOk() {
        given(exerciseService.getExerciseById(1L)).willReturn(Exercise("Name", "Description."))

        mockMvc.perform(
                get("/api/exercises/{id}", 1L))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", equalTo("Name")))
                .andExpect(jsonPath("$.description", equalTo("Description.")))

        verify(exerciseService, times(1)).getExerciseById(1L)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testGetExerciseById_ReturnsHttpStatusNotFound() {
        given(exerciseService.getExerciseById(1L)).willThrow(ExerciseNotFoundException::class.java)

        mockMvc.perform(get("/api/exercises/{id}", 1L)).andExpect(status().isNotFound)

        verify(exerciseService, times(1)).getExerciseById(1L)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testCreateExercise_ReturnsHttpStatusIsCreated() {
        val exercise = Exercise("Name", "Description.")

        given(exerciseService.createExercise(exercise)).willReturn(exercise)

        mockMvc.perform(
                post("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(exercise)))
                .andExpect(status().isCreated)

        verify(exerciseService, times(1)).createExercise(exercise)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testUpdateExercise_ReturnsHttpStatusOk() {
        val exercise = Exercise("Name", "Description.")

        given(exerciseService.updateExercise(exercise)).willReturn(exercise)

        mockMvc.perform(
                put("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(exercise)))
                .andExpect(status().isOk)

        verify(exerciseService, times(1)).updateExercise(exercise)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testUpdateExercise_ReturnsHttpStatusNotFound() {
        val exercise = Exercise("Name", "Description.")

        given(exerciseService.updateExercise(exercise)).willThrow(ExerciseNotFoundException::class.java)

        mockMvc.perform(
                put("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(exercise)))
                .andExpect(status().isNotFound)

        verify(exerciseService, times(1)).updateExercise(exercise)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testDeleteAllExercises_ReturnsHttpStatusNoContent() {
        willDoNothing().given(exerciseService).deleteAllExercises()

        mockMvc.perform(
                delete("/api/exercises"))
                .andExpect(status().isNoContent)

        verify(exerciseService, times(1)).deleteAllExercises()
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testDeleteExerciseById_ReturnsHttpStatusNoContent() {
        willDoNothing().given(exerciseService).deleteExercise(1L)

        mockMvc.perform(
                delete("/api/exercises/{id}", 1L))
                .andExpect(status().isNoContent)

        verify(exerciseService, times(1)).deleteExercise(1L)
        verifyNoMoreInteractions(exerciseService)
    }

    private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)

}