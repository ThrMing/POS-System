package ch.makery.address

import scalafx.application.JFXApp
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.{scene => jfxscene}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._

object MainDriver extends JFXApp{
  // attributes & functions to execute RootLayer containing navigation menu;
  //    - private ensures no other classes can change root layer by accident & removing navigation bar
  private val rootResource = getClass.getResource("/ch/makery/address/view/RootLayout.fxml")
  private val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load()
  private val sceneRoot = loader.getRoot[jfxscene.layout.BorderPane]
  stage = new PrimaryStage{
    title = "Restaurant POS System"
    scene = new Scene(){
      root = sceneRoot
    }
  }

  // function used to call specific views based on URL input
  def showView(viewURL: String): Unit ={
    val resource = getClass.getResource(viewURL)
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load()
    val roots = loader.getRoot[jfxscene.layout.AnchorPane]
    this.sceneRoot.center = roots
  }

  // display Welcome page when MainDriver executed
  showView("/ch/makery/address/view/Welcome.fxml")
}
