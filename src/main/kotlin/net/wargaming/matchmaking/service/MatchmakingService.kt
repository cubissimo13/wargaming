package net.wargaming.matchmaking.service

import net.wargaming.matchmaking.entity.User

/**
 * Интерфейс сервиса создания команды
 */
interface MatchmakingService {
    fun addUserInQueue(user: User)
}