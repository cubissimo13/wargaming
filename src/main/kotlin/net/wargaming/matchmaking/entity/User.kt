package net.wargaming.matchmaking.entity

/**
 * Сущность для информации о пользователе
 */
data class User(
        val name: String,
        val skill: Double,
        val latency: Double)