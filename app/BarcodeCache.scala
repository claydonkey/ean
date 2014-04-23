package com.github.claydonkey.barcodes

import akka.actor.Actor
import scala.concurrent._
import scala.Some
import scala.util.Try
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
import org.krysalis.barcode4j.impl.upcean.EAN13Bean


class BarcodeCache extends Actor {
  var imageCache = {  Map[Long, Future[Array[Byte]]]()    }

  override def receive: Actor.Receive = {
    case RenderImage(ean) => {
      val futureImage = imageCache.get(ean) match {
        case Some(futureImage) => futureImage
        case None => {
          val futureImage = future {
            ean13BarCode(ean, "image/png")
          }
          imageCache += (ean -> futureImage)
          futureImage
        }
      }
      val client = sender
      //   futureImage.onComplete{client.tell(_,client)}
      futureImage.onComplete {
        client ! RenderResult(_)
      }

    }

  }
  def ean13BarCode (ean: Long, mimeType: String): Array[Byte] = {

    import java.io.ByteArrayOutputStream
    import java.awt.image.BufferedImage

    val output = new ByteArrayOutputStream
    val canvas = new BitmapCanvasProvider (output, mimeType, Barcodes.imageResolution, BufferedImage.TYPE_BYTE_BINARY, false, 0)
    val barCode = new EAN13Bean
    barCode.generateBarcode (canvas, String valueOf ean)
    canvas.finish ()
    output.toByteArray
  }

}



case class RenderImage(ean: Long)

case class RenderResult(image: Try[Array[Byte]])