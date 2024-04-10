package com.architectureteam.multitenat.ms.service

import com.architectureteam.multitenat.ms.model.User
import com.architectureteam.multitenat.ms.persistance.UserDao
import org.springframework.stereotype.Service

@Service
class UserUseCase(val userDao: UserDao) : UserService {
    override fun create(user: User): User {
        return userDao.save(user)
    }
}