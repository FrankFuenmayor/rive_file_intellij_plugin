package com.frankfuenmayor.intellij.rive

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class RiveFileEditorProvider : FileEditorProvider, DumbAware {
    override fun accept(p0: Project, p1: VirtualFile): Boolean {
        return p1.extension == "riv"
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        return RiveFileEditor(file)
    }

    override fun getEditorTypeId(): String {
        return "RivePreview"
    }

    override fun getPolicy(): FileEditorPolicy {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR
    }

}