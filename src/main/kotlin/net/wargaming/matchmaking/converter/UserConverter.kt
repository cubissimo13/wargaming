package net.wargaming.matchmaking.converter

import net.wargaming.matchmaking.api.v1.dto.UserDto
import net.wargaming.matchmaking.entity.User

/**
 * Реализация интерфейса конвертора UserDto в User
 */
class UserConverter : Converter<UserDto, User> {
    override fun convert(source: UserDto): User {
        return User(
                name = source.name,
                skill = source.skill,
                latency = source.latency)
    }
}