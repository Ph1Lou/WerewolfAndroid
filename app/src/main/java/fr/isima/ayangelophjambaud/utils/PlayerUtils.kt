package fr.isima.ayangelophjambaud.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64
import android.util.TypedValue
import kotlin.math.roundToInt

class PlayerUtils {

    companion object {

        private fun dpToPx(context: Context, dp: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context.resources.displayMetrics
            ).roundToInt()
        }

        private fun getResizedBitmap(bm: Bitmap, size: Int): Bitmap {
            val width = bm.width
            val height = bm.height
            val scaleWidth = size.toFloat() / width
            val scaleHeight = size.toFloat() / height
            val matrix = Matrix()
            matrix.postScale(scaleWidth, scaleHeight)
            return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
        }

        fun getHead(context: Context, head: String): Bitmap {
            val decodedString: ByteArray = Base64.decode(head, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            return getResizedBitmap(decodedByte, dpToPx(context, 20))
        }
    }


}