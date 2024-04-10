package com.architectureteam.multitenat.ms.toolkit.db

interface TenantFacade {
    fun getCurrentTenant(): String
}