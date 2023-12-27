package application;

import javafx.application.Application;
import javafx.stage.Stage;
import stage.Menu;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		arg0.setScene(menu.getMenuScene());
		arg0.setTitle("5 Knights At Thonny");
		arg0.setResizable(false);
		arg0.show();
	}

}
