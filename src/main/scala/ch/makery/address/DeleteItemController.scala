package ch.makery.address

import ch.makery.address.model.{Item, ItemCart, Payment}
import scalafx.scene.control.{Alert, Button, Label, TableColumn, TableView, TextField}
import scalafx.Includes._
import scalafxml.core.macros.sfxml

@sfxml
class DeleteItemController(private val cartTable: TableView[Item], private val itemName: TableColumn[Item, String], private val unitPrice: TableColumn[Item, Double],
                           private val totalQuantity: TableColumn[Item, Int], private val selectedItemName: Label, private val confirmDel: Button,
                           private val returnMain: Button, private val delQuantity: TextField,
                           private val num1: Button, private val num2: Button, private val num3: Button,
                           private val num4: Button, private val num5: Button, private val num6: Button,
                           private val num7: Button, private val num8: Button, private val num9: Button, private val clearDelQuan: Button) {
  cartTable.items = ItemCart.cart

  // displaying all data from cart items
  itemName.cellValueFactory = {_.value.itemName}
  unitPrice.cellValueFactory = {_.value.unitPrice}
  totalQuantity.cellValueFactory = {_.value.quantity}

  def showCurrCart(item: Option[Item]): Unit ={
    item match {
      case Some(item) =>
        selectedItemName.text <== item.itemName

      case None =>
        selectedItemName.text.unbind()
        selectedItemName.text = ""
    }
  }
  cartTable.selectionModel().selectedItem.onChange((_, _, newValue) => showCurrCart(Option(newValue)))

// =============================================================================================================

  // numpad button handlers to input specified button values
  num1.onAction = handle {
    delQuantity.appendText("1")
  }

  num2.onAction = handle {
    delQuantity.appendText("2")
  }

  num3.onAction = handle {
    delQuantity.appendText("3")
  }

  num4.onAction = handle {
    delQuantity.appendText("4")
  }

  num5.onAction = handle {
    delQuantity.appendText("5")
  }

  num6.onAction = handle {
    delQuantity.appendText("6")
  }

  num7.onAction = handle {
    delQuantity.appendText("7")
  }

  num8.onAction = handle {
    delQuantity.appendText("8")
  }

  num9.onAction = handle{
    delQuantity.appendText("9")
  }

  // clear quantity value
  clearDelQuan.onAction = handle {
    delQuantity.clear()
  }

  // "Done" button handler
  returnMain.onAction = handle {
    MainDriver.showView("/ch/makery/address/view/PointOfSaleMain.fxml")
  }

  // "Confirm" button handler
  confirmDel.onAction = handle {
    val itemSelected = cartTable.selectionModel().selectedItem.value
    if (itemSelected != null){
      if (delQuantity.text.value == ""){
        val quantityError = new Alert(Alert.AlertType.Error){
          initOwner(MainDriver.stage)
          title       = "Quantity Error"
          headerText  = "No quantity entered!"
          contentText = "Please enter quantity to delete from cart!"
        }.showAndWait()
      }
      else{
        ItemCart.deleteItem(selectedItemName.text.value, delQuantity.getText.toInt)
        Payment.totalCart()
        val delSuccess = new Alert(Alert.AlertType.Information){
          initOwner(MainDriver.stage)
          title       = "Deletion Successful"
          headerText  = "Item successfully deleted"
        }.showAndWait()
      }
    }
    else{
      val noItem = new Alert(Alert.AlertType.Error){
        initOwner(MainDriver.stage)
        title       = "Deletion Error"
        headerText  = "No item selected"
        contentText = "Please select an item from the table!"
      }.showAndWait()
    }
  }
}
