package com.frankfuenmayor.intellij.riveplugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class RivePluginInitProjectActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        RiveFileServer.start()
    }
}