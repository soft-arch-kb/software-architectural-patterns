package com.architectureteam.multitenat.ms.toolkit.db

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import javax.sql.DataSource

class TenantRoutingDB(val tenantFacade: TenantFacade): AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any? {
        return tenantFacade.getCurrentTenant()
    }

    @SuppressWarnings
    fun setDBSources(targetDataSources: Map<String, DataSource>) {
        super.setTargetDataSources(targetDataSources.mapKeys { it.key } as Map<Any, Any>)
    }
}