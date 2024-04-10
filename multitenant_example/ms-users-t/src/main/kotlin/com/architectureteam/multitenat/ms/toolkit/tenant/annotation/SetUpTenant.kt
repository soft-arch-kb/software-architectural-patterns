package com.architectureteam.multitenat.ms.toolkit.tenant.annotation

import com.architectureteam.multitenat.ms.toolkit.tenant.TenantThreadLocal
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SetUpTenant

val map = mapOf(
    "apikey-1" to "tenant-1",
    "apikey-2" to "tenant-2",
)

fun setTenantByApikey(apikey: String) = map[apikey] ?: Exception("Apikey without tenant")

@Component
class ApikeyHeaderInterceptor : HandlerInterceptor {
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apikey = request.requireNonEmptyHeader("x-apikey")
        TenantThreadLocal().setTenant(setTenantByApikey(apikey).toString())
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: java.lang.Exception?
    ) {
        TenantThreadLocal().clear()
        super.afterCompletion(request, response, handler, ex)
    }

    fun HttpServletRequest.requireNonEmptyHeader(headerName: String) = getHeader(headerName)?.takeIf {
        it.isNotEmpty()
    } ?: throw IllegalArgumentException("Header $headerName was not provided or is empty")

}