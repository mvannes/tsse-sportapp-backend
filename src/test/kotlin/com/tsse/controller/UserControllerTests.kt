package com.tsse.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.UserNotFoundException
import com.tsse.domain.model.User
import com.tsse.service.UserService
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

/**
 *
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@RunWith(SpringRunner::class)
class UserControllerTests {

    @Mock lateinit var service: UserService

    @InjectMocks lateinit var userController: UserController

    lateinit var mockMvc: MockMvc

    val URI = "/api/users/"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(UserControllerTests::class)
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build()
    }

    @Test
    fun testSaveUsers_returnsHttpStatusIsCreated() {

        val user = User("Name", "Password", true, Date())

        BDDMockito.given(service.saveUser(user)).willReturn(user)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect { MockMvcResultMatchers.content().equals(user) }
    }

    @Test
    fun testSaveUserEmptyUsername_returnsHttpStatusIsBadRequest() {

        val user = User("", "Password", true, Date())
        println(user.getBirthdate())

        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect { MockMvcResultMatchers.jsonPath("message", Matchers.equalTo("Object sent is not valid: [Username cannot be empty!]")) }
    }

    @Test
    fun testSaveUserEmptyPassword_returnsHttpStatusIsBadRequest() {

        val user = User("Name", "", true, Date())

        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect { MockMvcResultMatchers.jsonPath("message", Matchers.equalTo("Object sent is not valid: [Password cannot be empty!]")) }
    }

    @Test
    fun testGetUserById_returnsHttpStatusOk() {

        val user = User("Name", "Password", true, Date())
        val id = 1L
        BDDMockito.given(service.getUser(id)).willReturn(user)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI + "{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { MockMvcResultMatchers.content().equals(user) }


    }

    @Test
    fun testGetUserById_returnsHttpStatusIsNotFound() {

        val id = 1L
        BDDMockito.given(service.getUser(id)).willThrow(UserNotFoundException::class.java)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI + "{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun testGetAllUsers_returnsHttpStatusOk() {

        val results = arrayListOf(User("Name1", "Password1", true, Date()),
                User("Name2", "Password2", true, Date()))


        BDDMockito.given(service.getAllUsers()).willReturn(results)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { MockMvcResultMatchers.content().equals(results) }

    }

    @Test
    fun testUpdateUser_returnsHttpStatusOk() {
        val user = User("Name", "Password", true, Date())

        BDDMockito.given(service.updateUser(user)).willReturn(user)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect { MockMvcResultMatchers.content().equals(user) }

    }


    @Test
    fun testUpdateUserInvalidForm_returnsHttpStatusBadRequest() {

        val user = User("", "Password", true, Date())

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect { MockMvcResultMatchers.jsonPath("message", Matchers.equalTo("Object sent is not valid: [Username cannot be empty!]")) }

    }

    @Test
    fun testUpdateUserNonExistingSchedule_returnsHttpStatusNotFound() {

        val user = User("Name", "Password", true, Date())

        BDDMockito.given(service.updateUser(user)).willThrow(UserNotFoundException::class.java)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound)

    }

    @Test
    fun testDeleteUser_returnsHttpStatusNoContent() {

        val id = 1L

        BDDMockito.willDoNothing().given(service).deleteUser(id)

        mockMvc.perform(
                MockMvcRequestBuilders.delete(URI + "{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNoContent)

    }

    private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)


}
