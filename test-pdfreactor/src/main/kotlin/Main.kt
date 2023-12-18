import com.realobjects.pdfreactor.Configuration
import com.realobjects.pdfreactor.PDFreactor
import java.io.File
import java.time.Instant

fun main(args: Array<String>) {
    val avg = timedRepeat({ createPdf("../resources/clean-table-cleaned-up.html", "./out-pdf-reactor.pdf") }, 10)
    println("Took ${avg / 1000.0F}s (mean)")
}

fun timedRepeat(fn: () -> Unit, times: Int): Long {
    var total = 0L
    (0..<times).forEach {
        println("Run $it...")
        val start = Instant.now()
        fn()
        val end = Instant.now()
        total += (end.toEpochMilli() - start.toEpochMilli())
    }
    return total / times
}

fun createPdf(html: String, pdf: String) {
    val config = Configuration()
    config.licenseKey = """
        xxxx
    """.trimIndent()
    config.baseUrl = "https://members.caisgroup.com/"
    config.setProcessingPreferences(Configuration.ProcessingPreferences.SAVE_MEMORY_IMAGES)
    config.setDocument(File(html).readBytes())
    config.setLogLevel(Configuration.LogLevel.DEBUG)
    config.setFontCachePath("/tmp/pdfreactor")
    val pdfReactor = run {
        System.setProperty("java.awt.headless", "true")
        PDFreactor()
    }
    val result = pdfReactor.convert(config)
    val file = File(pdf)
    file.writeBytes(result.document)
}
