package fr.isima.ayangelophjambaud.models

data class PrettyEvent(val players : List<Player>,
                       val timer: Int,
                       val color: Int,
                       val text: String)