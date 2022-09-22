package ch.makery.address

import ch.makery.address.model.{Item, ItemCart, Payment}
import scalafx.scene.control.{Alert, Button, Label, TableColumn, TableView}
import scalafxml.core.macros.sfxml
import scalafx.Includes._

@sfxml
class PointOfSaleController (private val cartTable: TableView[Item], private val itemName: TableColumn[Item, String], private val unitPrice: TableColumn[Item, Double],
                             private val totalQuantity: TableColumn[Item, Int], private val cartTotal: Label, private val discount: Button,
                             private val payCart: Button, private val delete: Button,
                             private val nasiLemak: Button, private val carbonaraSpag: Button, private val chickenDon: Button,
                             private val chocIce: Button, private val vanillaGel: Button, private val waffles: Button,
                             private val coffee: Button, private val coke: Button, private val hundredPlus: Button) {
  // track if dicount has been applied or not since discount can only be applied once
  private var discountClicked: Boolean = false
  updateTotal()

  // linking all cart items data to cartTable
  cartTable.items = ItemCart.cart

  // displaying all data from cart items
  itemName.cellValueFactory = {_.value.itemName}
  unitPrice.cellValueFactory = {_.value.unitPrice}
  totalQuantity.cellValueFactory = {_.value.quantity}

  // updates the total of the cart in GUI with data from Payment backend
  def updateTotal(): Unit ={
    cartTotal.text = Payment.totalPrice.toString
  }

  // =============================================================================================================

  // button handlers for menu items
  nasiLemak.onAction = handle {
    ItemCart.addItem("Nasi Lemak", 7.50)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }

  carbonaraSpag.onAction = handle {
    ItemCart.addItem("Carbonara Spaghetti", 12.50)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }

  chickenDon.onAction = handle {
    ItemCart.addItem("Chicken Donburi", 8.90)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }

  chocIce.onAction = handle {
    ItemCart.addItem("Chocolate Ice Cream", 5.50)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }

  vanillaGel.onAction = handle {
    ItemCart.addItem("Vanilla Gelato", 7.90)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }

  waffles.onAction = handle {
    ItemCart.addItem("Waffles", 6.50)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }

  coffee.onAction = handle {
    ItemCart.addItem("Coffee", 3.50)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }

  coke.onAction = handle {
    ItemCart.addItem("Coca-Cola", 2.90)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }

  hundredPlus.onAction = handle {
    ItemCart.addItem("100 Plus", 2.90)
    Payment.totalCart()
    updateTotal()
    discountClicked = false
  }
  // =============================================================================================================
  // button handlers for different functionality buttons
  // apply discount when discount button clicked
  discount.onAction = handle{
    // if discount already applied, return dialog saying already discounted;
    // total bill changes when items are added or deleted from cart & discount can be applied to new bill
    if (discountClicked){
      val discountApplied = new Alert(Alert.AlertType.Error){
        initOwner(MainDriver.stage)
        title       = "Discount Applied"
        headerText  = "5% Discount Applied"
        contentText = "Discount can only be applied once per bill!"
      }.showAndWait()
    }
    else{
      if (ItemCart.cart.isEmpty){
        val emptyCart = new Alert(Alert.AlertType.Error){
          initOwner(MainDriver.stage)
          title       = "Empty Cart"
          headerText  = "Cart is empty!"
          contentText = "Please enter items into the cart!"
        }.showAndWait()
      }
      else{
        Payment.applyDiscount()
        updateTotal()
        discountClicked = true
      }
    }
  }

  // show delete item page when delete button clicked
  delete.onAction = handle{
    if (ItemCart.cart.isEmpty){
      val emptyCart = new Alert(Alert.AlertType.Error){
        initOwner(MainDriver.stage)
        title       = "Empty Cart"
        headerText  = "No items in cart!"
      }.showAndWait()
    }
    else {
      MainDriver.showView("/ch/makery/address/view/DeleteItem.fxml")
    }
  }

  // show payment page when pay button clicked
  payCart.onAction = handle {
    if (ItemCart.cart.isEmpty){
      val emptyCart = new Alert(Alert.AlertType.Error){
        initOwner(MainDriver.stage)
        title       = "Empty Cart"
        headerText  = "Cart is empty!"
        contentText = "Please enter items into the cart!"
      }.showAndWait()
    }
    else{
      MainDriver.showView("/ch/makery/address/view/PaymentPage.fxml")
    }
  }
}
