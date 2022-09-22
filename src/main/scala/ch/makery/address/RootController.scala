package ch.makery.address

import ch.makery.address.model.ItemCart
import scalafxml.core.macros.sfxml

// controller class to control the menu bar throughout the application
@sfxml
class RootController {
  def logOut(): Unit ={
    MainDriver.showView("/ch/makery/address/view/Welcome.fxml")
    ItemCart.cart.clear()
  }

  def returnMain(): Unit ={
    MainDriver.showView("/ch/makery/address/view/PointOfSaleMain.fxml")
  }

  def deletePage(): Unit ={
    MainDriver.showView("/ch/makery/address/view/DeleteItem.fxml")
  }

  def paymentPage(): Unit ={
    MainDriver.showView("/ch/makery/address/view/PaymentPage.fxml")
  }
}
