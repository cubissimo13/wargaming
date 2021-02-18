package net.wargaming.matchmaking.entity

import java.time.LocalDateTime

/**
 * Сущность для информации о пользователе
 */
data class User(
        val name: String,
        val skill: Double,
        val latency: Double,
        val createdAt: LocalDateTime)