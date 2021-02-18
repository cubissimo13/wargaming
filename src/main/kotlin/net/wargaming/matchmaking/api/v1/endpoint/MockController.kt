package net.wargaming.matchmaking.api.v1.endpoint

import io.swagger.annotations.ApiOperation
import net.wargaming.matchmaking.mock.Simulation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

/**
 * REST-контроллер для запуска симуляции добавления пользователей в очередь
 */
@ApiIgnore
@RestController
@RequestMapping("/v1/mock/simulation", produces = [MediaType.APPLICATION_JSON_VALUE])
class MockController(private val simulation: Simulation) {

    @PostMapping("/run")
    @ApiOperation(value = "запуск добавления тестовых пользователей")
    fun runSimulation() {
        simulation.runSimulation()
    }

    @PostMapping("/stop")
    @ApiOperation(value = "запуск добавления тестовых пользователей")
    fun stopSimulation() {
        simulation.stopSimulation()
    }
}