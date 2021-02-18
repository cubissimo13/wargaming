package net.wargaming.matchmaking.entity

/**
 * Сущность для информации о группе
 */
data class Group(
        val groupName: String,
        val membersName: List<String>,
        val minSkill: Double,
        val maxSkill: Double,
        val avgSkill: Double,
        val minLatency: Double,
        val maxLatency: Double,
        val avgLatency: Double,
        val minTime: Long,
        val maxTime: Long,
        val avgTime: Long)