package com.architectureteam.multitenat.ms.toolkit.db

import com.mysql.cj.jdbc.MysqlDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.annotation.Priority
import javax.sql.DataSource

@Component
@Priority(Ordered.HIGHEST_PRECEDENCE)
class DBConfig() {
    val datasourcesMap: MutableMap<String, DataSource> = mutableMapOf()

    @Bean
    @Primary
    fun defaultDataSource(tenantFacade: TenantFacade): DataSource {
        datasourcesMap.put("tenant-1",createDataSource("db_users"))
        datasourcesMap.put("tenant-2",createDataSource("tenant2_db_users"))

        val customDataSource = TenantRoutingDB(tenantFacade)
        customDataSource.setDBSources (datasourcesMap)

        return customDataSource
    }

    private fun createDataSource(databaseName: String): MysqlDataSource {
        val dataSource = MysqlDataSource()
        dataSource.setServerName("localhost")
        dataSource.setPort(3306)
        dataSource.setDatabaseName(databaseName)
        dataSource.setUser("root")
        dataSource.setPassword("root")
        return dataSource
    }
}