package com.architectureteam.multitenat.ms.controller.dto

import javax.validation.constraints.NotNull

data class UserRequest(
    @field:NotNull(message = "The field name is required")
    val name: String?,

    @field:NotNull(message = "The field documentNumber is required")
    val documentNumber: String?,

)