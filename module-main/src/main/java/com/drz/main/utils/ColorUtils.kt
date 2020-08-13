package com.drz.main.utils

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * 应用模块:
 *
 *
 * 类描述:
 *
 *
 *
 * @author darryrzhoong
 * @since 2020-02-26
 */
object ColorUtils {
    fun getColor(context: Context?, colorId: Int): Int {
        return ContextCompat.getColor(context!!, colorId)
    }
}