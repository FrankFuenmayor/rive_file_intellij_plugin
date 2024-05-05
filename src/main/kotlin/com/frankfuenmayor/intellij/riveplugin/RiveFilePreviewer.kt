package com.frankfuenmayor.intellij.riveplugin

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.Colors
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.JBCefBrowserBase
import java.awt.BorderLayout
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.swing.JLabel
import javax.swing.JPanel

class RiveFilePreviewer(file: VirtualFile) : JPanel() {

    companion object {
        private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    }

    init {
        val browser = JBCefBrowser()
        browser.setProperty(JBCefBrowserBase.Properties.NO_CONTEXT_MENU, true);

        layout = BorderLayout(10, 10)
        add(browser.component, BorderLayout.CENTER)
        executor.submit {
            while (!RiveFileServer.isStarted) Thread.sleep(1000)
            browser.loadURL(RiveFileServer.getUrl(file.path))
        }
    }
}