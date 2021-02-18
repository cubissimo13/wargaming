package net.wargaming.matchmaking.mock

import net.wargaming.matchmaking.entity.User
import net.wargaming.matchmaking.service.MatchmakingService
import java.time.LocalDateTime
import kotlin.random.Random

/**
 * Реализация запуска и остановки тестового сервиса добавления пользователей
 */
class SimulationImpl(private val matchmakingService: MatchmakingService) : Simulation {
    private var switch = false

    override fun runSimulation() {
        if (!switch) {
            switch = true
            var userNameCounter = 1
            while (switch) {
                matchmakingService.addUserInQueue(User(
                        name = userNameCounter.toString(),
                        skill = Random.nextDouble(1.0, 10.0),
                        latency = Random.nextDouble(1.0, 10.0),
                        createdAt = LocalDateTime.now()))
                userNameCounter++
                Thread.sleep(100L)
            }
        }
    }

    override fun stopSimulation() {
        switch = false
    }
}