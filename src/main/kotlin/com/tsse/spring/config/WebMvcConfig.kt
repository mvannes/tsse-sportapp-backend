package com.tsse.spring.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.stream.Collectors

/**
 * @author Boyd Hogerheijde
 */
@Configuration
@EnableWebMvc
class WebMvcConfig : WebMvcConfigurerAdapter() {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.apply {
            add(jsonMessageConverter())
            add(xmlMessageConverter())
        }
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        // Fetching all jackson message converter to extend them.
        val jacksonConverters = converters
                .stream()
                .filter { converter -> converter is AbstractJackson2HttpMessageConverter }
                .collect(Collectors.toList())

        // Enabling pretty printing and failure on unknown passed in properties for all jackson message converters.
        jacksonConverters.forEach { converter ->
            val jacksonConverter = converter as AbstractJackson2HttpMessageConverter
            jacksonConverter.objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
            jacksonConverter.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }
    }

    @Bean
    fun jsonMessageConverter(): MappingJackson2HttpMessageConverter {
        return MappingJackson2HttpMessageConverter()
    }

    @Bean
    fun xmlMessageConverter(): MappingJackson2XmlHttpMessageConverter {
        return MappingJackson2XmlHttpMessageConverter()
    }
}
