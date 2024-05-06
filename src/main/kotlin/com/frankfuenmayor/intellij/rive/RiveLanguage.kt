package com.frankfuenmayor.intellij.rive

import com.intellij.lang.Language

object RiveLanguage : Language("Rive") {
    private fun readResolve(): Any = RiveLanguage
}