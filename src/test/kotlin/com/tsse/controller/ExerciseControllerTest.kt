package com.tsse.controller

import com.tsse.repository.ExerciseRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

/**
 * Created by boydhogerheijde on 09/03/2017.
 */
@RunWith(SpringRunner::class)
class ExerciseControllerTest {

    @Autowired lateinit var mockMvc: MockMvc

    @MockBean lateinit var repository: ExerciseRepository

    @Test
    fun testSaveExercise() {


    }
}