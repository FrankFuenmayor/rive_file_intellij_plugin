package com.frankfuenmayor.intellij.riveplugin

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.swing.JPanel

class RiveFilePreviewer(file: VirtualFile) : JPanel() {

    val browser = JBCefBrowser()

    companion object {
        private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    }

    init {
        layout = BorderLayout()
        add(browser.component, BorderLayout.CENTER)
        executor.submit {
            while (!RiveFileServer.isStarted) Thread.sleep(1000)
            browser.loadURL(RiveFileServer.getUrl(file.path))
        }
    }
}