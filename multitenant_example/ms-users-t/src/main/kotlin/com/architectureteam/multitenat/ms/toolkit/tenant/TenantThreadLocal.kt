package com.architectureteam.multitenat.ms.toolkit.tenant

import org.slf4j.MDC

class TenantThreadLocal() : ITenant {

    private val DEFAULT_TENANT = "tenant-1"
    companion object {
        const val KEY_TENANT_NAME ="X-TENANT-NAME"
        private val threadTenant = InheritableThreadLocal<Tenant>()
    }

    override fun getName() =  threadTenant.get()?.name ?: DEFAULT_TENANT

    override fun setTenant(tenantName: String) {
        val tenant = Tenant(tenantName)
        threadTenant.set(tenant)
        MDC.put(KEY_TENANT_NAME,tenant.name)
    }

    override fun clear() {
        MDC.remove(KEY_TENANT_NAME)
        threadTenant.remove()
    }
}