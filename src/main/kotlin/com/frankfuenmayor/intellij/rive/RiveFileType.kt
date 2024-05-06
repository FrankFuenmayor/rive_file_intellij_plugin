package com.frankfuenmayor.intellij.rive

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class RiveFileType private constructor() : LanguageFileType(RiveLanguage) {
    override fun getName(): String {
        return "Rive File"
    }

    override fun getDescription(): String {
        return "Rive animation file"
    }

    override fun getDefaultExtension(): String {
        return "riv"
    }

    override fun getIcon(): Icon {
        return RiveFileIcons.RIVE_FILE
    }

    companion object {
        val INSTANCE: RiveFileType = RiveFileType()
    }
}