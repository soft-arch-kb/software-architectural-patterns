package com.architectureteam.multitenat.ms.config

import com.architectureteam.multitenat.ms.toolkit.db.TenantFacade
import com.architectureteam.multitenat.ms.toolkit.tenant.TenantThreadLocal
import org.springframework.stereotype.Component

@Component
class TenantSupplie : TenantFacade {
    override fun getCurrentTenant(): String {
        return TenantThreadLocal().getName()
    }
}