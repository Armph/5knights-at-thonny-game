package stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import logic.GameLogic;

public class Menu {
	private Scene menuScene;
		
	
	public Menu() throws Exception {
		// TODO Auto-generated method stub
		GameLogic.gameStart();
		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene menuScene = new Scene(root);
		setMenuScene(menuScene);
			
	}
	public Scene getMenuScene() {
		return menuScene;
	}

	public void setMenuScene(Scene menuScene) {
		this.menuScene = menuScene;
	}


}
