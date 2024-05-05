package com.frankfuenmayor.intellij.riveplugin

import com.intellij.openapi.fileEditor.impl.EditorTabTitleProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

class RiveFileTabTitleProvider : EditorTabTitleProvider {
    override fun getEditorTabTitle(p0: Project, p1: VirtualFile): String {
        return p1.name + " (" + p1.humanFileSize + ")"
    }
}

