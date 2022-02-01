package fr.isima.ayangelophjambaud.utils

import fr.isima.ayangelophjambaud.R

class ColorUtil {
    companion object {
        fun getTextColor(id: Int): Int{
            return when (id) {
                1 -> R.color.textColor1
                2 -> R.color.textColor2
                3 -> R.color.textColor3
                4 -> R.color.textColor4
                5 -> R.color.textColor5
                6 -> R.color.textColor6
                7 -> R.color.textColor7
                else -> R.color.colorPrimary
            }
        }

        fun getBackGroundColor(id: Int): Int {
            return when (id) {
                1 -> R.color.textBackgroundColor1
                2 -> R.color.textBackgroundColor2
                3 -> R.color.textBackgroundColor3
                4 -> R.color.textBackgroundColor4
                5 -> R.color.textBackgroundColor5
                6 -> R.color.textBackgroundColor6
                7 -> R.color.textBackgroundColor7
                else -> R.color.colorPrimary
            }
        }
    }

}