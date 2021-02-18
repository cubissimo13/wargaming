package net.wargaming.matchmaking.service

import com.fasterxml.jackson.databind.ObjectMapper
import net.wargaming.matchmaking.config.property.MatchmakingProperty
import net.wargaming.matchmaking.entity.Group
import net.wargaming.matchmaking.entity.User
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.locks.ReentrantLock
import kotlin.math.round
import kotlin.math.roundToInt

/**
 * Реализация сервиса создания команды
 */
class MatchmakingServiceImpl(
        private val matchmakingProperty: MatchmakingProperty,
        private val groupMatrix: MutableMap<String, MutableMap<String, MutableList<User>>> = mutableMapOf()) : MatchmakingService {

    private var groupNumber = AtomicLong(1)
    private val objectMapper = ObjectMapper()
    private val lock = ReentrantLock()

    override fun addUserInQueue(user: User) {
        lock.tryLock()
        val userSkill = user.skill.roundToInt().toString()
        val userLatency = user.latency.roundToInt().toString()
        groupMatrix.putIfAbsent(userSkill, mutableMapOf())
        groupMatrix[userSkill]?.putIfAbsent(userLatency, mutableListOf())
        groupMatrix[userSkill]?.get(userLatency)?.add(user)
        if (groupMatrix[userSkill]?.get(userLatency)?.size == matchmakingProperty.groupCapacity) {
            makeGroup(groupNumber.getAndIncrement().toString(), ArrayList(groupMatrix[userSkill]?.get(userLatency)))
            groupMatrix[userSkill]?.set(userLatency, mutableListOf())
        }
        lock.unlock()
    }

    private fun makeGroup(name: String, members: List<User>) {
        var minSkill = 0.0
        var maxSkill = 0.0
        var totalSkill = 0.0
        var minLatency = 0.0
        var maxLatency = 0.0
        var totalLatency = 0.0
        var minTime = Duration.ZERO
        var maxTime = Duration.ZERO
        var totalTime = Duration.ZERO
        val membersName = mutableListOf<String>()
        for (member in members) {
            if (minSkill == 0.0 || member.skill < minSkill) {
                minSkill = member.skill
            }
            if (member.skill > maxSkill) {
                maxSkill = member.skill
            }
            if (minLatency == 0.0 || member.latency < minLatency) {
                minLatency = member.latency
            }
            if (member.latency > maxLatency) {
                maxLatency = member.latency
            }
            if (minTime == Duration.ZERO || minTime.seconds > (member.createdAt.until(LocalDateTime.now(), ChronoUnit.SECONDS))) {
                minTime = Duration.between(member.createdAt, LocalDateTime.now())
            }
            if (maxTime.seconds < (member.createdAt.until(LocalDateTime.now(), ChronoUnit.SECONDS))) {
                maxTime = Duration.between(member.createdAt, LocalDateTime.now())
            }
            totalSkill += member.skill
            totalLatency += member.latency
            totalTime += Duration.between(member.createdAt, LocalDateTime.now())
            membersName.add(member.name)
        }
        val avgSkill = totalSkill / matchmakingProperty.groupCapacity
        val avgLatency = totalLatency / matchmakingProperty.groupCapacity
        val avgTime = totalTime.seconds / matchmakingProperty.groupCapacity
        println(objectMapper.writeValueAsString(Group(
                groupName = name,
                minSkill = round(minSkill * 100) / 100,
                maxSkill = round(maxSkill * 100) / 100,
                avgSkill = round(avgSkill * 100) / 100,
                minLatency = round(minLatency * 100) / 100,
                maxLatency = round(maxLatency * 100) / 100,
                avgLatency = round(avgLatency * 100) / 100,
                minTime = minTime.seconds,
                maxTime = maxTime.seconds,
                avgTime = avgTime,
                membersName = membersName)))
    }
}
