package net.wargaming.matchmaking.converter

/**
* Интерфейс конвертера из одной сущности в другую
*/
interface Converter<S,R> {
    fun convert(source: S): R
}