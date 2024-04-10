package com.architectureteam.multitenat.ms.exception

class NoTenantPresentException(val error: String = "No Tenant present"): Throwable()