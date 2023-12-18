import com.lowagie.text.BadElementException
import com.lowagie.text.Image
import org.w3c.dom.Element
import org.xhtmlrenderer.extend.ReplacedElement
import org.xhtmlrenderer.extend.ReplacedElementFactory
import org.xhtmlrenderer.extend.UserAgentCallback
import org.xhtmlrenderer.layout.LayoutContext
import org.xhtmlrenderer.pdf.ITextFSImage
import org.xhtmlrenderer.pdf.ITextImageElement
import org.xhtmlrenderer.render.BlockBox
import org.xhtmlrenderer.simple.extend.FormSubmissionListener
import java.io.IOException

class ImageReplacedElementFactory(val superFactory: ReplacedElementFactory) : ReplacedElementFactory {

    var images = mutableMapOf(Pair("", ByteArray(2)))

    override fun createReplacedElement(c: LayoutContext?, box: BlockBox?, uac: UserAgentCallback?, cssWidth: Int, cssHeight: Int): ReplacedElement? {
        val element = box?.getElement()
        val nodeName = element?.getNodeName()
        val id = element?.getAttribute("id")
        if (nodeName == "div" && images[id] != null) {
            try {
                val image = Image.getInstance(images[id])
                val fsImage = ITextFSImage(image)
                if ((cssWidth != -1) || (cssHeight != -1)) {
                    fsImage.scale(cssWidth, cssHeight)
                }
                return ITextImageElement(fsImage)
            } catch (e: IOException) {
            } catch (e: BadElementException) {
            }
        }
        return superFactory.createReplacedElement(c, box, uac, cssWidth, cssHeight)
    }

    override fun reset() {
        superFactory.reset()
    }

    override fun remove(e: Element?) {
        superFactory.remove(e)
    }

    override fun setFormSubmissionListener(listener: FormSubmissionListener?) {
        superFactory.setFormSubmissionListener(listener)
    }
}
