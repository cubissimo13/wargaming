package net.wargaming.matchmaking.config.service

import net.wargaming.matchmaking.config.property.MatchmakingProperty
import net.wargaming.matchmaking.service.MatchmakingServiceImpl
import net.wargaming.matchmaking.mock.SimulationImpl
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(MatchmakingProperty::class)
class ServiceConfig(private val matchmakingProperty: MatchmakingProperty) {
    @Bean
    fun matchmakingService() = MatchmakingServiceImpl(matchmakingProperty)

    @Bean
    fun simulation() = SimulationImpl(matchmakingService())
}