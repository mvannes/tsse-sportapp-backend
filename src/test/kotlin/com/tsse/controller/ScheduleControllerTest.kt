package com.tsse.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.Schedule
import com.tsse.service.ScheduleService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

/**
 * Tests related to the ScheduleController.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */

@RunWith(SpringRunner::class)
class ScheduleControllerTest {

    @Mock lateinit var service: ScheduleService

    @InjectMocks lateinit var scheduleController: ScheduleController

    lateinit var mockMvc: MockMvc

    val apiUri = "/api/schedule/"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this::class)
        mockMvc = MockMvcBuilders
                .standaloneSetup(scheduleController)
                .build()
    }

    @Test
    fun testSaveSchedule_returnsHttpStatusIsCreated() {

        val schedule = Schedule("Name", "Description", ArrayList(), 1)

        given(service.saveSchedule(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(apiUri)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(schedule) }
    }

    @Test
    fun testSaveScheduleEmptyName_returnsHttpStatusIsBadRequest() {
        val schedule = Schedule("", "Description", ArrayList(), 1)

        mockMvc.perform(
                MockMvcRequestBuilders.post(apiUri)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", equals("Object sent is not valid: [Schedule name cannot be empty!]")) }
    }

    @Test
    fun testSaveScheduleEmptyDescription_returnsHttpStatusIsCreated() {
        val schedule = Schedule("Name", "", ArrayList(), 1)

        given(service.saveSchedule(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(apiUri)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isCreated)
                .andExpect { content().equals(schedule) }
    }

    @Test
    fun testSaveScheduleEmptyAmountOfTrainings_returnsHttpStatusIsBadRequest() {
        val schedule = Schedule("Name", "Description", ArrayList(), 0)

        given(service.saveSchedule(schedule)).willReturn(schedule)
        mockMvc.perform(
                MockMvcRequestBuilders.post(apiUri)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(schedule)))
                .andExpect(status().isBadRequest)
                .andExpect { jsonPath("message", equals("Object sent is not valid: [Schedule needs at least one training per week!]")) }
    }

    
}


private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)