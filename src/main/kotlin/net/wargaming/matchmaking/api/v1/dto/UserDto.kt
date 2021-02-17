package net.wargaming.matchmaking.api.v1.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(description = "Информация о пользователе")
data class UserDto(
        @ApiModelProperty(value = "Имя пользователя", example = "User")
        val name: String,

        @ApiModelProperty(value = "Уровень пользователя", example = "7.0")
        val skill: Double,

        @ApiModelProperty(value = "Качество связи", example = "1.0")
        val latency: Double)