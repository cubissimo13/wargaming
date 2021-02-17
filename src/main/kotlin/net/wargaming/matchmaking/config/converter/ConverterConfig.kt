package net.wargaming.matchmaking.config.converter

import net.wargaming.matchmaking.converter.UserConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConverterConfig {
    @Bean
    fun userConverter() = UserConverter()
}