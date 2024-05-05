package com.frankfuenmayor.intellij.riveplugin

import com.intellij.openapi.vfs.VirtualFile
import java.io.File

val VirtualFile.humanFileSize
    get(): String {
        val sizeInKB = File(path).length() / 1024.0
        val sizeInMb = sizeInKB / 1024.0
        val useKB = sizeInMb.toLong() == 0L

        return String.format(
            "%.2f %s",
            if (useKB) sizeInKB else sizeInMb,
            if (useKB) "KB" else "MB"
        )
    }