package com.architectureteam.multitenat.ms.service

import com.architectureteam.multitenat.ms.model.User

interface UserService {
    fun create(user: User): User
}
