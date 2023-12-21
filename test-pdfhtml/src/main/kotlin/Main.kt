import com.itextpdf.html2pdf.HtmlConverter
import com.itextpdf.licensing.base.LicenseKey
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.Instant

fun main(args: Array<String>) {
    val avg = timedRepeat({ createPdf("../resources/clean-table-cleaned-up-1500.html", "./out-pdfhtml.pdf") }, 10)
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
    FileInputStream(
        "/Users/tblench/Downloads/091175c5a0103e3910ff35e12d33732ebebe5bd00d792aa1279d9efbe51ceb28.json",
    ).use { license -> LicenseKey.loadLicenseFile(license) }
    HtmlConverter.convertToPdf(FileInputStream(html), FileOutputStream(pdf))
}
