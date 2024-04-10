package com.architectureteam.multitenat.controller

import com.architectureteam.multitenat.ms.controller.dto.UserRequest
import com.architectureteam.multitenat.ms.controller.dto.UserResponse
import com.architectureteam.multitenat.ms.model.User
import com.architectureteam.multitenat.model.UserBuilder
import com.architectureteam.multitenat.ms.controller.UserController
import com.architectureteam.multitenat.ms.service.UserService
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.text.SimpleDateFormat

class UserControllerTest : StringSpec() {
    init {
        val userService = mockk<UserService>()
        val mockMvc = MockMvcBuilders
            .standaloneSetup(UserController(userService))
            .setMessageConverters(jackson2HttpMessageConverter())
            .build()
        lateinit var userRequest: UserRequest
        lateinit var user: User
        lateinit var expectedResult: UserResponse

        "Return 200 HTTP status code for a well-formed data user" {
            //GIVEN
            userRequest = UserRequest("Victoria","1118")

            //WHEN
            user = UserBuilder().build()
            every {
                userService.create(User(id = 0, documentNumber = userRequest.documentNumber!!, name = userRequest.name!!))
            } returns user

            expectedResult = UserResponse.from(user)

            //THEN
            mockMvc.post("/api/v1/users") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper().writeValueAsString(userRequest)
            }.andExpect {
                    status { isCreated }
                    content { json(objectMapper().writeValueAsString(expectedResult)) }
            }
        }
    }
}

private fun jackson2HttpMessageConverter() = MappingJackson2HttpMessageConverter(objectMapper())

private fun objectMapper() = Jackson2ObjectMapperBuilder()
    .serializationInclusion(JsonInclude.Include.NON_NULL)
    .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
    .dateFormat(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"))
    .build<ObjectMapper>()
