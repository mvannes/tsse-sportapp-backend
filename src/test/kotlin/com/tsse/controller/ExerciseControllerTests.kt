package com.tsse.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.ResourceNotFoundException
import com.tsse.domain.model.Exercise
import com.tsse.service.impl.ExerciseService
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
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
        given(exerciseService.findAll()).willReturn(arrayListOf(
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

        verify(exerciseService, times(1)).findAll()
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testGetExerciseById_ReturnsHttpStatusOk() {
        given(exerciseService.findOne(1L)).willReturn(Exercise("Name", "Description."))

        mockMvc.perform(
                get("/api/exercises/{id}", 1L))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", equalTo("Name")))
                .andExpect(jsonPath("$.description", equalTo("Description.")))

        verify(exerciseService, times(1)).findOne(1L)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testGetExerciseById_ReturnsHttpStatusNotFound() {
        given(exerciseService.findOne(1L)).willThrow(ResourceNotFoundException::class.java)

        mockMvc.perform(get("/api/exercises/{id}", 1L)).andExpect(status().isNotFound)

        verify(exerciseService, times(1)).findOne(1L)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testCreateExercise_ReturnsHttpStatusIsCreated() {
        val exercise = Exercise("Name", "Description.")

        given(exerciseService.create(exercise)).willReturn(exercise)

        mockMvc.perform(
                post("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(exercise)))
                .andExpect(status().isCreated)

        verify(exerciseService, times(1)).create(exercise)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    @Ignore
    fun testUpdateExercise_ReturnsHttpStatusOk() {
        val exercise = Exercise("Name", "Description.")

        given(exerciseService.update(exercise, 1L))

        mockMvc.perform(
                put("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(exercise)))
                .andExpect(status().isOk)

        verify(exerciseService, times(1)).update(exercise, 1L)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    @Ignore
    fun testUpdateExercise_ReturnsHttpStatusNotFound() {
        val exercise = Exercise("Name", "Description.")

        given(exerciseService.update(exercise, 1L)).willThrow(ResourceNotFoundException::class.java)

        mockMvc.perform(
                put("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(exercise)))
                .andExpect(status().isNotFound)

        verify(exerciseService, times(1)).update(exercise, 1L)
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testDeleteAllExercises_ReturnsHttpStatusNoContent() {
        willDoNothing().given(exerciseService).deleteAll()

        mockMvc.perform(
                delete("/api/exercises"))
                .andExpect(status().isNoContent)

        verify(exerciseService, times(1)).deleteAll()
        verifyNoMoreInteractions(exerciseService)
    }

    @Test
    fun testDeleteExerciseById_ReturnsHttpStatusNoContent() {
        willDoNothing().given(exerciseService).delete(1L)

        mockMvc.perform(
                delete("/api/exercises/{id}", 1L))
                .andExpect(status().isNoContent)

        verify(exerciseService, times(1)).delete(1L)
        verifyNoMoreInteractions(exerciseService)
    }

    private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)

}