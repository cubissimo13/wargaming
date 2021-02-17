package net.wargaming.matchmaking.service

import net.wargaming.matchmaking.entity.User

/**
 * Реализация сервиса создания команды
 */
class MatchmakingServiceImpl : MatchmakingService {
    override fun addUserInQueue(user: User) {
        println("Добавлен пользователь ${user.name}")
    }
}