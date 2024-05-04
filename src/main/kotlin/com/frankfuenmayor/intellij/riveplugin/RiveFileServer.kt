package com.frankfuenmayor.intellij.riveplugin

import io.javalin.Javalin
import io.javalin.http.Context
import java.io.FileInputStream
import java.util.*
import kotlin.random.Random

@org.intellij.lang.annotations.Language(value = "html")
const val viewerHtml = """<html lang="en">
<head>
    <title>Rive Hello World</title>
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
</head>
<body>
<canvas id="canvas"></canvas>
<script src="https://unpkg.com/@rive-app/canvas@2.7.0"></script>
<script>
    const r = new rive.Rive({
      src: "{{rivFile}}",
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

    fun getUrl(path: String): String = "http://127.0.0.1:${port}${path}"

    private fun Javalin.getFileHandler() = get("/${securePrefix}/*") {
        it.result(FileInputStream(it.path().removePrefix("/${securePrefix}")))
    }

    private fun Javalin.getViewerPageHandler() = get("/*") { ctx: Context ->
        ctx.header("Content-Type", "text/html")
        viewerHtml
            .replace("{{rivFile}}", "/${securePrefix}" + ctx.path())
            .also(::println)
            .run(ctx::result)
    }

}