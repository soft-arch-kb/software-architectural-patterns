package com.architectureteam.multitenat.ms.controller.dto

import com.architectureteam.multitenat.ms.model.User
data class UserResponse(
    val id: Long,
    val documentNumber: String,
    val name: String
) {
    companion object {
        fun from(user: User): UserResponse =
            with(user) {
                UserResponse(
                    id = id,
                    documentNumber = documentNumber,
                    name = name
                )
            }
    }

}
