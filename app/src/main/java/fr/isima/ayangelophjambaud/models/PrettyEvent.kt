package fr.isima.ayangelophjambaud.models

import kotlinx.serialization.Serializable

@Serializable
data class PrettyEvent(val players : List<Player>,
                       val timer: Int,
                       val color: Int,
                       val text: String)