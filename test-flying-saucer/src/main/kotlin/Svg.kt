import org.apache.batik.transcoder.SVGAbstractTranscoder
import org.apache.batik.transcoder.TranscoderInput
import org.apache.batik.transcoder.TranscoderOutput
import org.apache.batik.transcoder.image.PNGTranscoder
import java.io.ByteArrayOutputStream
import java.io.StringReader

class Svg {

    fun createSvg(): ByteArray {
        val t = PNGTranscoder()
        t.addTranscodingHint(SVGAbstractTranscoder.KEY_WIDTH, 800.0F)
        t.addTranscodingHint(SVGAbstractTranscoder.KEY_HEIGHT, 800.0F)
        colors.entries.forEach { svgString = svgString.replace("var(${it.key})", "${it.value.first},${it.value.second},${it.value.third}") }
        val input = TranscoderInput(StringReader(svgString))
        val ostream = ByteArrayOutputStream(1024 * 1024)
        val output = TranscoderOutput(ostream)
        t.transcode(input, output)
        // resolution? presumably this is in px and the document sets the dpi at the top level
        ostream.flush()
        ostream.close()
        return ostream.toByteArray()
    }

    // map colours since svg renderer doesn't know about styles in doc
    private val colors = mapOf(
        "--colors-datavis-1" to Triple(0, 64, 123),
        "--colors-datavis-2" to Triple(140, 173, 233),
        "--colors-datavis-3" to Triple(50, 113, 116),
        "--colors-datavis-4" to Triple(150, 219, 200),
        "--colors-datavis-5" to Triple(255, 193, 112),
        "--colors-datavis-6" to Triple(202, 97, 57),
        "--colors-datavis-7" to Triple(101, 22, 44),
        "--colors-datavis-8" to Triple(192, 187, 247),
        "--colors-datavis-9" to Triple(89, 92, 92),
        "--colors-datavis-10" to Triple(42, 146, 186),
        "--colors-datavis-11" to Triple(134, 211, 242),
        "--colors-datavis-12" to Triple(108, 143, 112),
        "--colors-datavis-13" to Triple(209, 224, 165),
        "--colors-datavis-14" to Triple(243, 215, 188),
        "--colors-datavis-15" to Triple(168, 134, 77),
        "--colors-datavis-16" to Triple(170, 105, 122),
        "--colors-datavis-17" to Triple(145, 125, 163),
        "--colors-datavis-18" to Triple(205, 209, 216),
        "--colors-datavis-19" to Triple(57, 88, 161),
        "--colors-datavis-20" to Triple(192, 224, 219),
        "--colors-datavis-21" to Triple(244, 240, 219),
        "--colors-datavis-22" to Triple(196, 119, 116),
    )

    // TODO we should be able to pull this out of the html DOM somehow?
    private var svgString = """
<svg data-testid="pie-chart" style="width: 100%; height: 348px;" xmlns="http://www.w3.org/2000/svg">
  <path d="M 382.5 16 A 158 158 0 0 1 530.2300417418281 117.96577146995142 L 382.5 174 Z" fill="rgb(var(--colors-datavis-2))" id="0" fill-opacity="1" data-testid="pie-chart-slice-0" stroke="white" stroke-width="1">
  </path>
  <path d="M 530.2300417418281 117.96577146995142 A 158 158 0 0 1 499.3837095465037 280.31179822883513 L 382.5 174 Z" fill="rgb(var(--colors-datavis-3))" id="1" fill-opacity="1" data-testid="pie-chart-slice-1" stroke="white" stroke-width="1">
  </path>
  <path d="M 499.3837095465037 280.31179822883513 A 158 158 0 0 1 354.51853634053623 329.5025327493868 L 382.5 174 Z" fill="rgb(var(--colors-datavis-4))" id="2" fill-opacity="1" data-testid="pie-chart-slice-2" stroke="white" stroke-width="1">
  </path>
  <path d="M 354.51853634053623 329.5025327493868 A 158 158 0 0 1 284.0822915116428 297.6040236234271 L 382.5 174 Z" fill="rgb(var(--colors-datavis-7))" id="3" fill-opacity="1" data-testid="pie-chart-slice-3" stroke="white" stroke-width="1">
  </path>
  <path d="M 284.0822915116428 297.6040236234271 A 158 158 0 0 1 241.82514303698466 245.9345856903004 L 382.5 174 Z" fill="rgb(var(--colors-datavis-6))" id="4" fill-opacity="1" data-testid="pie-chart-slice-4" stroke="white" stroke-width="1">
  </path>
  <path d="M 241.82514303698466 245.9345856903004 A 158 158 0 0 1 225.57015449499613 192.35820224792235 L 382.5 174 Z" fill="rgb(var(--colors-datavis-8))" id="5" fill-opacity="1" data-testid="pie-chart-slice-5" stroke="white" stroke-width="1">
  </path>
  <path d="M 225.57015449499613 192.35820224792235 A 158 158 0 0 1 226.98820349402513 146.07006717723334 L 382.5 174 Z" fill="rgb(var(--colors-datavis-10))" id="6" fill-opacity="1" data-testid="pie-chart-slice-6" stroke="white" stroke-width="1">
  </path>
  <path d="M 226.98820349402513 146.07006717723334 A 158 158 0 0 1 239.47995022647825 106.84893624983025 L 382.5 174 Z" fill="rgb(var(--colors-datavis-9))" id="7" fill-opacity="1" data-testid="pie-chart-slice-7" stroke="white" stroke-width="1">
  </path>
  <path d="M 239.47995022647825 106.84893624983025 A 158 158 0 0 1 260.59064671043734 73.49074878141512 L 382.5 174 Z" fill="rgb(var(--colors-datavis-5))" id="8" fill-opacity="1" data-testid="pie-chart-slice-8" stroke="white" stroke-width="1">
  </path>
  <path d="M 260.59064671043734 73.49074878141512 A 158 158 0 0 1 283.8804834101151 50.55693236322496 L 382.5 174 Z" fill="rgb(var(--colors-datavis-11))" id="9" fill-opacity="1" data-testid="pie-chart-slice-9" stroke="white" stroke-width="1">
  </path>
  <path d="M 283.8804834101151 50.55693236322496 A 158 158 0 0 1 305.62895658191604 35.96072050385422 L 382.5 174 Z" fill="rgb(var(--colors-datavis-13))" id="10" fill-opacity="1" data-testid="pie-chart-slice-10" stroke="white" stroke-width="1">
  </path>
  <path d="M 305.62895658191604 35.96072050385422 A 158 158 0 0 1 327.6927738832692 25.810364851688945 L 382.5 174 Z" fill="rgb(var(--colors-datavis-16))" id="11" fill-opacity="1" data-testid="pie-chart-slice-11" stroke="white" stroke-width="1">
  </path>
  <path d="M 327.6927738832692 25.810364851688945 A 158 158 0 0 1 342.26540944017074 21.208711889444118 L 382.5 174 Z" fill="rgb(var(--colors-datavis-15))" id="12" fill-opacity="1" data-testid="pie-chart-slice-12" stroke="white" stroke-width="1">
  </path>
  <path d="M 342.26540944017074 21.208711889444118 A 158 158 0 0 1 354.8743431681179 18.433862667330175 L 382.5 174 Z" fill="rgb(var(--colors-datavis-12))" id="13" fill-opacity="1" data-testid="pie-chart-slice-13" stroke="white" stroke-width="1">
  </path>
  <path d="M 354.8743431681179 18.433862667330175 A 158 158 0 0 1 363.6219148459619 17.131845485079765 L 382.5 174 Z" fill="rgb(var(--colors-datavis-18))" id="14" fill-opacity="1" data-testid="pie-chart-slice-14" stroke="white" stroke-width="1">
  </path>
  <path d="M 363.6219148459619 17.131845485079765 A 158 158 0 0 1 370.4423075921195 16.46076027288649 L 382.5 174 Z" fill="rgb(var(--colors-datavis-14))" id="15" fill-opacity="1" data-testid="pie-chart-slice-15" stroke="white" stroke-width="1">
  </path>
  <path d="M 370.4423075921195 16.46076027288649 A 158 158 0 0 1 374.2680640940686 16.214591196648854 L 382.5 174 Z" fill="rgb(var(--colors-datavis-2))" id="16" fill-opacity="1" data-testid="pie-chart-slice-16" stroke="white" stroke-width="1">
  </path>
  <path d="M 374.2680640940686 16.214591196648854 A 158 158 0 0 1 376.9506449226309 16.09748368621456 L 382.5 174 Z" fill="rgb(var(--colors-datavis-1))" id="17" fill-opacity="1" data-testid="pie-chart-slice-17" stroke="white" stroke-width="1">
  </path>
  <path d="M 376.9506449226309 16.09748368621456 A 158 158 0 0 1 379.3130235599475 16.0321451016994 L 382.5 174 Z" fill="rgb(var(--colors-datavis-21))" id="18" fill-opacity="1" data-testid="pie-chart-slice-18" stroke="white" stroke-width="1">
  </path>
  <path d="M 379.3130235599475 16.0321451016994 A 158 158 0 0 1 380.5196125265378 16.01241167276794 L 382.5 174 Z" fill="rgb(var(--colors-datavis-17))" id="19" fill-opacity="1" data-testid="pie-chart-slice-19" stroke="white" stroke-width="1">
  </path>
  <path d="M 380.5196125265378 16.01241167276794 A 158 158 0 0 1 381.6213669143459 16.002443044517946 L 382.5 174 Z" fill="rgb(var(--colors-datavis-22))" id="20" fill-opacity="1" data-testid="pie-chart-slice-20" stroke="white" stroke-width="1">
  </path>
  <path d="M 381.6213669143459 16.002443044517946 A 158 158 0 0 1 382.40167897965443 16.00003059184803 L 382.5 174 Z" fill="rgb(var(--colors-datavis-19))" id="21" fill-opacity="1" data-testid="pie-chart-slice-21" stroke="white" stroke-width="1">
  </path>
  <path d="M 382.40167897965443 16.00003059184803 A 158 158 0 0 1 382.46874187033103 16.000003091995836 L 382.5 174 Z" fill="rgb(var(--colors-datavis-3))" id="22" fill-opacity="1" data-testid="pie-chart-slice-22" stroke="white" stroke-width="1">
  </path>
  <path d="M 382.46874187033103 16.000003091995836 A 158 158 0 0 1 382.5000000000001 16 L 382.5 174 Z" fill="rgb(var(--colors-datavis-20))" id="23" fill-opacity="1" data-testid="pie-chart-slice-23" stroke="white" stroke-width="1">
  </path>
</svg>
    """.trimIndent()
}
