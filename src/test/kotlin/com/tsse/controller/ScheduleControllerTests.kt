package com.tsse.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.model.Schedule
import com.tsse.service.ScheduleService
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * Tests related to the ScheduleController.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */

@RunWith(SpringRunner::class)
class ScheduleControllerTests {

    @Mock lateinit var service: ScheduleService

    @InjectMocks lateinit var scheduleController: ScheduleController

    lateinit var mockMvc: MockMvc

    val URI = "/api/schedule/"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(ScheduleControllerTests::class)
        mockMvc = MockMvcBuilders
                .standaloneSetup(scheduleController)
                .build()
    }

    @Test
    @Ignore
    fun testSaveSchedule_returnsHttpStatusIsCreated() {

        val schedule = Schedule("Name", "Description", ArrayList(), 1)

        given(service.create(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(schedule) }
    }

    @Test
    @Ignore
    fun testSaveScheduleEmptyName_returnsHttpStatusIsBadRequest() {

        val schedule = Schedule("", "Description", ArrayList(), 1)

        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", equalTo("Object sent is not valid: [Schedule name cannot be empty!]")) }
    }

    @Test
    @Ignore
    fun testSaveScheduleEmptyDescription_returnsHttpStatusIsCreated() {

        val schedule = Schedule("Name", "", ArrayList(), 1)

        given(service.create(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(schedule) }
    }

    @Test
    @Ignore
    fun testSaveScheduleEmptyAmountOfTrainings_returnsHttpStatusIsBadRequest() {

        val schedule = Schedule("Name", "Description", ArrayList(), 0)

        given(service.create(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", equalTo("Object sent is not valid: [Schedule needs at least one training per week!]")) }
    }

    @Test
    @Ignore
    fun testGetScheduleById_returnsHttpStatusOk() {

        val schedule = Schedule("Name", "Description", ArrayList(), 0)
        val id = 1L
        given(service.findOne(id)).willReturn(schedule)

        mockMvc.perform(
                get(URI + "{id}", id))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { content().equals(schedule) }


    }

    @Test
    fun testGetScheduleById_returnsHttpStatusIsNotFound() {

        val id = 1L
        given(service.findOne(id)).willThrow(ResourceNotFoundException::class.java)

        mockMvc.perform(
                get(URI + "{id}", id))
                .andExpect(status().isNotFound)
    }

    @Test
    @Ignore
    fun testGetAllSchedules_returnsHttpStatusOk() {

        val results = arrayListOf(Schedule("Name", "Description", ArrayList(), 0),
                Schedule("Name2", "Description2", ArrayList(), 0))


        given(service.findAll()).willReturn(results)

        mockMvc.perform(
                get(URI))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { content().equals(results) }

    }

    @Test
    @Ignore
    fun testUpdateSchedule_returnsHttpStatusOk() {
        val schedule = Schedule("Name", "Description", ArrayList(), 1)

        given(service.update(schedule, 1L))

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isOk)
                .andExpect { content().equals(schedule) }

    }

    @Test
    @Ignore
    fun testUpdateScheduleInvalidForm_returnsHttpStatusBadRequest() {

        val schedule = Schedule("", "Description", ArrayList(), 1)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", equalTo("Object sent is not valid: [Schedule name cannot be empty!]")) }

    }

    @Test
    fun testUpdateScheduleNonExistingSchedule_returnsHttpStatusNotFound() {

        val schedule = Schedule("Name", "Description", ArrayList(), 1)

        given(service.update(schedule, 1L)).willThrow(ResourceNotFoundException::class.java)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(schedule)))
                .andExpect(status().isNotFound)

    }

    @Test
    @Ignore
    fun testDeleteSchedule_returnsHttpStatusNoContent() {

        val id = 1L

        willDoNothing().given(service).delete(id)

        mockMvc.perform(
                delete(URI + "{id}", id))
                .andExpect(status().isNoContent)
                .andExpect(status().isNoContent)
    }

    private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)

}
