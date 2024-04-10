package com.architectureteam.multitenat.ms.controller

import com.architectureteam.multitenat.ms.controller.dto.UserRequest
import com.architectureteam.multitenat.ms.controller.dto.UserResponse
import com.architectureteam.multitenat.ms.model.User
import com.architectureteam.multitenat.ms.service.UserService
import com.architectureteam.multitenat.ms.toolkit.tenant.annotation.SetUpTenant
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
@Validated
@RequestMapping("/api")
class UserController(val userService: UserService) {

    @PostMapping(
        value = ["/v1/users"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @SetUpTenant
    fun create(
        @RequestBody @Valid @NotNull(message = "The request body is required") userRequest: UserRequest?
    ) =ResponseEntity
        .status(HttpStatus.CREATED)
        .body(userService.create(checkNotNull(userRequest).toDomain()).returnResponse())

    fun UserRequest.toDomain() = User(id = 0, documentNumber = this.documentNumber!!, name = this.name!!)

    private fun User.returnResponse() = UserResponse.from(this)
}
