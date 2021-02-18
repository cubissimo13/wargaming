package net.wargaming.matchmaking.api.v1.endpoint

import io.swagger.annotations.ApiOperation
import net.wargaming.matchmaking.api.v1.dto.UserDto
import net.wargaming.matchmaking.converter.Converter
import net.wargaming.matchmaking.entity.User
import net.wargaming.matchmaking.service.MatchmakingService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * REST-контроллер для поиска группы
 */
@RestController
@RequestMapping("/v1/matchmaking", produces = [MediaType.APPLICATION_JSON_VALUE])
class MatchmakingController(private val userConverter: Converter<UserDto, User>,
                            private val matchmakingService: MatchmakingService) {

    @PostMapping("/users")
    @ApiOperation(value = "Добавление пользователя в очередь")
    fun getMobileNumberInfo(@RequestBody userDto: UserDto) {
        matchmakingService.addUserInQueue(userConverter.convert(userDto))
    }
}