package Util

/**
 * Created by Rizvan on 04.10.2016.
 */

fun parseColorToCssStyle(color: String): String {
        val parseColor = "#" + color.substring(2, color.length)
        return parseColor
}
