package com.github.claydonkey.barcodes
import play.api.{ Application, Logger, Plugin }
import play.api.libs.concurrent.Akka
import play.api.Play.current
import akka.actor.Props

class BarcodesPlugin(val app: Application) extends Plugin {

  override def onStart() {
    Logger.info("Initializing cache")
    Barcodes.barcodeCache = Akka.system.actorOf(Props[BarcodeCache])
  }
  override def onStop() {
    Logger.info("Stopping application")
  }
  override def enabled = true
}
