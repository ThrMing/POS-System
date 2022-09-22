package ch.makery.address

import ch.makery.address.model.{ItemCart, Payment}
import scalafx.scene.control.{Alert, Button, Label, TextField}
import scalafx.Includes._
import scalafxml.core.macros.sfxml

@sfxml
class PaymentController (private val payConfirm: Button, private val amountReceived: TextField, private val cartTotal: Label,
                         private val num0: Button, private val num1: Button, private val num2: Button, private val num3: Button,
                         private val num4: Button, private val num5: Button, private val num6: Button, private val num7: Button,
                         private val num8: Button, private val num9: Button, private val period: Button, private val clearAmt: Button) {
  // display current cart total at the payment page
  cartTotal.text = Payment.totalPrice.toString

  // numpad button handlers to key in tapped button values
  num0.onAction = handle {
    amountReceived.appendText("0")
  }

  num1.onAction = handle {
    amountReceived.appendText("1")
  }

  num2.onAction = handle {
    amountReceived.appendText("2")
  }

  num3.onAction = handle {
    amountReceived.appendText("3")
  }

  num4.onAction = handle {
    amountReceived.appendText("4")
  }

  num5.onAction = handle {
    amountReceived.appendText("5")
  }

  num6.onAction = handle {
    amountReceived.appendText("6")
  }

  num7.onAction = handle {
    amountReceived.appendText("7")
  }

  num8.onAction = handle {
    amountReceived.appendText("8")
  }

  num9.onAction = handle {
    amountReceived.appendText("9")
  }

  period.onAction = handle {
    amountReceived.appendText(".")
  }

  clearAmt.onAction = handle {
    amountReceived.clear()
  }

  // "Confirm" button handler to execute payment
  payConfirm.onAction = handle {
    // check if payment amount was correct
    if (amountReceived.text.value != ""){
      val paymentResult = Payment.checkPayment(amountReceived.getText.toDouble)
      if (paymentResult){
        // clear cart for next order
        ItemCart.cart.removeRange(0, ItemCart.cart.length)
        Payment.totalPrice = 0.0
        // return user to POS main page
        MainDriver.showView("/ch/makery/address/view/PointOfSaleMain.fxml")
      }
    }
    else{
      val payError = new Alert(Alert.AlertType.Error){
        initOwner(MainDriver.stage)
        title       = "Payment Error"
        headerText  = "Enter the amount received!"
      }.showAndWait()
    }
  }
}
