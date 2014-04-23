package com.github.claydonkey.barcodes

import play.api.mvc.Action
import akka.pattern.ask
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
import akka.actor.ActorRef
import scala.concurrent.Future
import scala.concurrent.duration._
import org.krysalis.barcode4j.impl.upcean.EAN13Bean
import scala.util.Try
import akka.util.Timeout

/*
NON CACHING
 */
/*
object Barcodes extends Controller {
  val imageResolution = 144

  def barcode(ean: Long) = Action {
    val MimeType = "image/png"
    Try(ean13BarCode(ean, MimeType)) match {
      case Success(imageData) => Ok(imageData).as(MimeType)
      case Failure(e) => BadRequest("Couldn't generate bar code. Error: " + e.getMessage)
    }
  }
   */

object Barcodes  {
  var barcodeCache: ActorRef = _
  val mimeType = "image/png"
  val imageResolution = 144

  def renderImage(ean: Long): Future[Try[Array[Byte]]] = {
    implicit val timeout = Timeout(20.seconds)
    barcodeCache ? RenderImage(ean) map {
      case RenderResult(result) => result
    }
  }


  def ean13BarCode(ean: Long, mimeType: String): Array[Byte] = {

    import java.io.ByteArrayOutputStream
    import java.awt.image.BufferedImage

    val output = new ByteArrayOutputStream
    val canvas = new BitmapCanvasProvider(output, mimeType, imageResolution, BufferedImage.TYPE_BYTE_BINARY, false, 0)
    val barCode = new EAN13Bean
    barCode.generateBarcode(canvas, String valueOf ean)
    canvas.finish()
    output.toByteArray
  }


}