package com.architectureteam.multitenat.model

import com.architectureteam.multitenat.ms.model.User

data class UserBuilder(
    val id: Long = 98765L,
    val documentNumber: String = "1118",
    val name: String = "Victoria",
) {
    fun build(): User =
        User(
            id = id,
            documentNumber = documentNumber,
            name = name
        )
}
