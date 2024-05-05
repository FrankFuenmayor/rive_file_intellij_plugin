package com.frankfuenmayor.intellij.riveplugin

import io.javalin.Javalin
import io.javalin.http.Context
import java.io.FileInputStream
import java.nio.file.Files
import java.util.*
import kotlin.random.Random

const val riveFilePlaceholder = "{{{RIVE_FILE}}}"

@org.intellij.lang.annotations.Language(value = "html")
const val viewerHtml = """<html lang="en">
<head> 
    <style>
        body {
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            width: 100vw;
        }
    </style> 
    <title>Preview</title>
</head>
<body>
<canvas id="canvas"></canvas>
<script src="https://unpkg.com/@rive-app/canvas@2.7.0"></script>
<script>
    const r = new rive.Rive({
      src: "$riveFilePlaceholder",
      canvas: document.getElementById("canvas"),
      autoplay: true,
      onLoad: () => {r.resizeDrawingSurfaceToCanvas();},
    });
</script>
</body>
</html>
"""

object RiveFileServer {

    private val port by lazy { Random.nextInt(54000, 55000) }
    private val server by lazy { Javalin.create() }
    private var started = false
    private var securePrefix = UUID.randomUUID().toString()
    val isStarted: Boolean get() = started
    fun start() {
        if (started) return
        server
            .getFileHandler()
            .getViewerPageHandler()
            .start(port)
        started = true
    }

    fun getUrl(path: String): String = "http://127.0.0.1:$port/$securePrefix$path"

    private fun Javalin.getFileHandler() = get("/-/*") {
        val filePath = it.path().removePrefix("/-/$securePrefix")
        it.result(FileInputStream(filePath))
    }

    private fun Javalin.getViewerPageHandler(): Javalin {
        return get("/$securePrefix/*") { ctx: Context ->
            ctx.header("Content-Type", "text/html")
            viewerHtml
                .replace(riveFilePlaceholder, "/-" + ctx.path())
                .run(ctx::result)
        }
    }

}