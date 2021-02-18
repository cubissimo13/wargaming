package net.wargaming.matchmaking.mock

/**
 * Интерфейс запуска и остановки тестового сервиса добавления пользователей
 */
interface Simulation {
    fun runSimulation()
    fun stopSimulation()
}