package com.frankfuenmayor.intellij.rive

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.projectView.ProjectViewNode
import com.intellij.ide.projectView.ProjectViewNodeDecorator

class RiveFileProjectViewNodeDecorator : ProjectViewNodeDecorator {
    override fun decorate(node: ProjectViewNode<*>, data: PresentationData) {

        val virtualFile = node.virtualFile ?: return
        if (virtualFile.isDirectory) return

        data.locationString = " (${virtualFile.humanFileSize})"
    }
}