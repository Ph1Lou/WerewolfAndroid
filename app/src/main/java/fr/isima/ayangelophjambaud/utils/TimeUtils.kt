package fr.isima.ayangelophjambaud.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TimeUtils {
    companion object{
        fun getDuration(duration: Long): String {
            val hh: Long = TimeUnit.SECONDS.toHours(duration)
            val mm: Long = TimeUnit.SECONDS.toMinutes(duration) % 60
            val ss: Long = TimeUnit.SECONDS.toSeconds(duration) % 60
            return String.format("%02d:%02d:%02d", hh, mm, ss)
        }

        fun getDate(date: Date): String {
            val pattern = "HH:mm dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.FRANCE)
            return simpleDateFormat.format(date)
        }
    }
}