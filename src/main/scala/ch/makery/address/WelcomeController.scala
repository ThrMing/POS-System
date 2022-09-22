package ch.makery.address

import scalafx.scene.control.Button
import scalafx.Includes._
import scalafxml.core.macros.sfxml

@sfxml
class WelcomeController(private val signIn: Button){
  signIn.onAction = handle {
    MainDriver.showView("/ch/makery/address/view/PointOfSaleMain.fxml")
  }
}
