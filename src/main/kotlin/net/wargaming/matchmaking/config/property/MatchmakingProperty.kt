package net.wargaming.matchmaking.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "matchmaking")
data class MatchmakingProperty(
        val groupCapacity: Int
)