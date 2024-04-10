package com.architectureteam.multitenat.ms.persistance

import com.architectureteam.multitenat.ms.exception.InsertErrorDBException
import com.architectureteam.multitenat.ms.model.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository

@Repository
class UserDao(
    private val defaultJdbcTemplate: NamedParameterJdbcTemplate
) {
    companion object {
        const val NO_RECORD_UPDATED = 0
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    private val queryInsert = """
            INSERT INTO user(
                document_number,
                name
            )
            VALUES(
                :document_number,
                :name
            )
        """.trimIndent()

    fun save(user: User) : User {
        try {
            val keyHolder: KeyHolder = GeneratedKeyHolder()
            val recordsInserted = defaultJdbcTemplate.update(queryInsert, getSaveParameterSource(user), keyHolder)
            logger.info("Inserted $recordsInserted records successfully")
            return user.copy(id = keyHolder.key!!.toLong())
        } catch (e: Exception) {
            logger.error("Error inserting record", e)
        }
        
        throw InsertErrorDBException(
            "Record not inserted in DB with : ${user.name} , ${user.documentNumber}")
    }

    private fun getSaveParameterSource(user: User) = MapSqlParameterSource(
        mapOf(
            "document_number" to user.documentNumber,
            "name" to user.name
        )
    )
}