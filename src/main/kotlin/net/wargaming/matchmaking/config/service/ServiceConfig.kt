package net.wargaming.matchmaking.config.service

import net.wargaming.matchmaking.service.MatchmakingServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig {
    @Bean
    fun matchmakingService() = MatchmakingServiceImpl()
}