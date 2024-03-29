package com.tsse.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tsse.domain.model.User
import com.tsse.service.UserService
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.data.rest.webmvc.ResourceNotFoundException
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

        val user = User("x@y.z", "Password", true, Date(), "displayName", "firstName", "lastName", "status", "USER")

        BDDMockito.given(service.create(user)).willReturn(user)
        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect { MockMvcResultMatchers.content().equals(user) }
    }

    @Test
    @Ignore
    fun testSaveUserEmptyUsername_returnsHttpStatusIsBadRequest() {

        val user = User("", "Password", true, Date(), "displayname", "firstname", "lastname", "status", "USER")
        println(user.birthdate)

        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect { MockMvcResultMatchers.jsonPath("message", Matchers.equalTo("Object sent is not valid: [Username cannot be empty!]")) }
    }

    @Test
    @Ignore
    fun testSaveUserEmptyPassword_returnsHttpStatusIsBadRequest() {

        val user = User("x@y.z", "", true, Date(), "displayName", "firstName", "lastName", "status", "USER")

        mockMvc.perform(
                MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect { MockMvcResultMatchers.jsonPath("message", Matchers.equalTo("Object sent is not valid: [Password cannot be empty!]")) }
    }

    @Test
    fun testGetUserById_returnsHttpStatusOk() {

        val user = User("x@y.z", "Password", true, Date(), "displayName", "firstName", "lastName", "status", "USER")
        val id = 1L
        BDDMockito.given(service.findOne(id)).willReturn(user)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI + "{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { MockMvcResultMatchers.content().equals(user) }


    }

    @Test
    fun testGetUserById_returnsHttpStatusIsNotFound() {

        val id = 1L
        BDDMockito.given(service.findOne(id)).willThrow(ResourceNotFoundException::class.java)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI + "{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @Ignore
    fun testGetAllUsers_returnsHttpStatusOk() {

        val results = arrayListOf(User("x@y.z", "Password", true, Date(), "displayName", "firstName", "lastName", "status", "USER"),
                User("x@y.z", "Password", true, Date(), "displayName", "firstName", "lastName", "status", "USER"))


        BDDMockito.given(service.findAll()).willReturn(results)

        mockMvc.perform(
                MockMvcRequestBuilders.get(URI))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect { MockMvcResultMatchers.content().equals(results) }

    }

    @Test
    @Ignore
    fun testUpdateUser_returnsHttpStatusOk() {
        val user = User("x@y.z", "Password", true, Date(), "displayName", "firstName", "lastName", "status", "USER")

        BDDMockito.given(service.update(user, 1L))

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect { MockMvcResultMatchers.content().equals(user) }

    }


    @Test
    @Ignore
    fun testUpdateUserInvalidForm_returnsHttpStatusBadRequest() {

        val user = User("x@y.z", "", true, Date(), "displayName", "firstName", "lastName", "status", "USER")

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect { MockMvcResultMatchers.jsonPath("message", Matchers.equalTo("Object sent is not valid: [Username cannot be empty!]")) }

    }

    @Test
    @Ignore
    fun testUpdateUserNonExistingUser_returnsHttpStatusNotFound() {

        val user = User("x@y.z", "Password", true, Date(), "displayName", "firstName", "lastName", "status", "USER")

        BDDMockito.given(service.update(user, 1L)).willThrow(ResourceNotFoundException::class.java)

        mockMvc.perform(
                MockMvcRequestBuilders.put(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isNotFound)

    }

    @Test
    fun testDeleteUser_returnsHttpStatusNoContent() {

        val id = 1L

        BDDMockito.willDoNothing().given(service).delete(id)

        mockMvc.perform(
                MockMvcRequestBuilders.delete(URI + "{id}", id))
                .andExpect(MockMvcResultMatchers.status().isNoContent)

    }

    private fun asJsonString(obj: Any): String = ObjectMapper().writeValueAsString(obj)

}
