package com.frankfuenmayor.intellij.riveplugin

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.util.Key
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import java.beans.PropertyChangeListener
import javax.swing.JComponent
import javax.swing.JPanel


class RiveFileEditor(private val file: VirtualFile) : FileEditor {

    private val filePreview = RiveFilePreviewer(file)

    override fun <T : Any?> getUserData(p0: Key<T>): T? {
        return null
    }

    override fun <T : Any?> putUserData(p0: Key<T>, p1: T?) {

    }

    override fun dispose() {

    }

    override fun getComponent(): JComponent {
        return filePreview
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return filePreview
    }


    override fun getName(): String = "Rive Preview"

    override fun setState(p0: FileEditorState) {

    }

    override fun isModified(): Boolean {
        return false
    }

    override fun isValid(): Boolean {
        return true
    }

    override fun addPropertyChangeListener(p0: PropertyChangeListener) {
        println(p0)
    }

    override fun removePropertyChangeListener(p0: PropertyChangeListener) {
        println(p0)
    }

    override fun getFile(): VirtualFile = file

}
