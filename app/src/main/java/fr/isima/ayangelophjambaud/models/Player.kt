package fr.isima.ayangelophjambaud.models

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Player(val head: String, val uuid: UUID, val name: String)