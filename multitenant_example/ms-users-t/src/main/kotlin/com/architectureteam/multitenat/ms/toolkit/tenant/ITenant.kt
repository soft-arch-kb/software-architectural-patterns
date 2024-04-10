package com.architectureteam.multitenat.ms.toolkit.tenant

interface ITenant {
    fun getName():String
    fun setTenant(tenantName: String)
    fun clear()
}