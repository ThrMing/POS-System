package ch.makery.address.model

import ch.makery.address.MainDriver
import ch.makery.address.model.ItemCart.cart
import scalafx.scene.control.Alert


// This singleton object stores the functions for calculating total from the cart and discount to apply
object Payment {
  // public attribute used by other classes to display the total price of the cart
  var totalPrice: Double = 0.0

  // calculates the cart total
  def totalCart(): Unit ={
    if (cart.isEmpty){
      totalPrice = 0.00
    }
    else{
      // hold temporary value to prevent errors in total
      var tempTotal: Double = 0.0
      for (i <- 0 until cart.length){
        tempTotal = tempTotal + (cart(i).unitPrice.value * cart(i).quantity.value)
      }
      // change totalPrice value to tempTotal when calculations are complete & error-free
      totalPrice = BigDecimal(tempTotal).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
    }
  }

  // applies a 5% member discount on the total bill
  def applyDiscount(): Unit ={
    totalPrice = BigDecimal(0.95 * totalPrice).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }

  // function checks if payment is successful or not
  def checkPayment(amtReceived: Double): Boolean ={
    if (amtReceived == totalPrice){
      // invoke success dialogue
      val alert = new Alert(Alert.AlertType.Information){
        initOwner(MainDriver.stage)
        title       = "Payment Successful"
        headerText  = "Click OK to return to the main page"
      }.showAndWait()
      return true
    }
    else{
      if (amtReceived < totalPrice){
        // invoke less than dialogue
        val alert = new Alert(Alert.AlertType.Error){
          initOwner(MainDriver.stage)
          title       = "Payment Error"
          headerText  = "Entered amount is less than total!"
        }.showAndWait()
        return false
      }
      if (amtReceived > totalPrice){
        // invoke return change message displaying amount to return
        val alert = new Alert(Alert.AlertType.Information){
          initOwner(MainDriver.stage)
          title       = "Payment Successful"
          headerText  = "Return change: RM" + BigDecimal(amtReceived - totalPrice).setScale(2, BigDecimal.RoundingMode.HALF_UP)
        }.showAndWait()
      }
      return true
    }
  }
}
