package com.architectureteam.multitenat.ms.config

import com.architectureteam.multitenat.ms.toolkit.tenant.annotation.ApikeyHeaderInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig(private val myInterceptor: ApikeyHeaderInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(myInterceptor)
    }
}