
import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.FileOutputStream
import java.time.Instant

fun main(args: Array<String>) {
    val avg = timedRepeat({ createPdf("../resources/clean-table-cleaned-up-1500.html", "./out-flying-saucer.pdf") }, 10)
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
    val os = FileOutputStream(pdf)
    val renderer = ITextRenderer()
    val iref = ImageReplacedElementFactory(renderer.sharedContext.replacedElementFactory)
    // the html declares a <div> with the id "pie-chart" - this is where we render the svg
    val chartId = "pie-chart"
    iref.images[chartId] = Svg().createSvg()
    renderer.sharedContext.setReplacedElementFactory(iref)
    renderer.setDocument(html)
    renderer.layout()
    renderer.createPDF(os)
    os.close()
}
