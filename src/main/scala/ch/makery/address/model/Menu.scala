package ch.makery.address.model

import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scala.util.control._


// Class stores class to create menu items; designed for potential modularity when new buttons are added for new menu items
class Item (name: String, price: Double) {
  // public attributes to provide other classes with attribute values
  var itemName = new StringProperty(name)
  var unitPrice = ObjectProperty[Double](price)
  var quantity = ObjectProperty[Int](1)
}

// singleton object for other classes to access the methods to manage menu items
object ItemCart{
  // Stores items in cart; each cart can have an unknown maximum number of items therefore ObservableBuffer used
  val cart = new ObservableBuffer[Item]()
  val loop = new Breaks

  // adds item to cart when invoked
  def addItem(name: String, price: Double): Unit ={
    if (cart.isEmpty){
      cart += new Item(name, price)
    }
    else if (cart.nonEmpty){
      loop.breakable{
        for (i <- 0 until cart.length){
          if (cart(i).itemName.value == name){
            cart(i).quantity.value = cart(i).quantity.value + 1
            loop.break()    // break loop when done to reduce resource usage
          }
        }
        cart += new Item(name, price)
      }
    }
  }

  // deletes quantity specified for selected item from the cart
  def deleteItem(name: String, quantity: Int): Unit ={
    loop.breakable {
      for (i <- 0 until cart.length) {
        if (cart(i).itemName.value == name) {
          cart(i).quantity.value = cart(i).quantity.value - quantity
          Payment.totalCart()
          if (cart(i).quantity.value <= 0) {
            cart.remove(i)
            Payment.totalCart()
          }
          loop.break() // break loop when done to reduce resource usage
        }
      }
    }
  }
}