package com.github.claydonkey.barcodes

import play.api.mvc.{ Action, Controller }
import util.{ Failure, Success }
import play.api.libs.concurrent.Execution.Implicits._
import com.github.claydonkey._

object BarcodesController extends Controller {

  def barcode(ean: Long) =  Action.async {
   
      Barcodes.renderImage(ean) map
        {
          case Success(image) => Ok(image).as(Barcodes.mimeType)
          case Failure(e) =>
            BadRequest("Couldn't Generate Barcode Error: " + e.getMessage)
        }
        
  }
}

