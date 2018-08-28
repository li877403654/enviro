package ext

import java.text.NumberFormat
import java.util.*
import java.util.Locale.FRANCE



/**
 * Created by zzl.
 * Date: 2018-03-06
 */

@Suppress("NOTHING_TO_INLINE")
inline fun Any?.toDouble(defValue: Int): Double = this.toDouble(defValue.toDouble())

fun Any?.toDouble(defValue: Double): Double {
    if(this == null) {
        return defValue;
    }
    if(this is Double) {
        return this;
    }
    if(this is Int) {
        return this.toDouble()
    }
    if(this is Long) {
        return this.toDouble()
    }
    val sValue = this.toString()
    try {
        return java.lang.Double.parseDouble(sValue);
    }
    catch (e: Exception) {
        if(sValue.indexOf(',') != -1) {
            try {
                return java.lang.Double.parseDouble(sValue.replace(",", ""));
            }
            catch (e2: Exception) {
                return defValue;
            }
        }
        return defValue
    }
}

