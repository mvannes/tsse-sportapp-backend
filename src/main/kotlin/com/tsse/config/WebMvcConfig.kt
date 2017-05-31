package com.tsse.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.*

/**
 * Created by boydhogerheijde on 31/05/2017.
 */
@Configuration
@EnableWebMvc
class WebMvcConfig : WebMvcConfigurerAdapter() {

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val jsonConverterFound: Optional<HttpMessageConverter<*>> = converters
                .stream()
                .filter { converter -> converter is MappingJackson2HttpMessageConverter }
                .findFirst()

        if (jsonConverterFound.isPresent) {
            val jsonConverter: AbstractJackson2HttpMessageConverter = jsonConverterFound.get() as AbstractJackson2HttpMessageConverter
            jsonConverter.objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
            jsonConverter.objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        }
    }
}
