package com.frankfuenmayor.intellij.riveplugin

import com.intellij.lang.Language

object RiveLanguage : Language("Rive") {
    private fun readResolve(): Any = RiveLanguage
}