package com.crypto.exam.manager

import android.content.Context
import java.io.InputStream

/**
 * @author Ricky
 * Asset manager
 */
class AssetManager(private val context: Context) {
    fun readAssetFile(fileName: String): InputStream {
        return context.assets.open(fileName)
    }
}