package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import logic.GameLogic;

public class LoseController implements Initializable {
	
	private final String TRYAGAINURL = "TRY AGAIN.png";
	private final String SHOPURL = "SHOP.png";
	
	private final String TRYAGAINSLEURL = "TRY AGAIN select.png";
	private final String SHOPSLEURL = "SHOP select.png";

	@FXML
	private ImageView back2menu, TryAgain, Shop;
	
	@FXML
	public void selectSound() {
		Thread thread = new Thread(() -> {
		String path = getClass().getResource("/selectSound.mp3").getPath();
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.4);
		mediaPlayer.play();
		});
		thread.start();
	}
	
	@FXML
	public void loseSound() {
		Thread thread = new Thread(() -> {
		String path = getClass().getResource("/LoseSound.mp3").getPath();
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		});
		thread.start();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loseSound();
	}
	
	public void mouseEnter1() {
		mouseEnter(0);
	}

	public void mouseEnter2() {
		mouseEnter(1);
	}
	
	public void draw(ImageView im, String URL) {
		im.setImage(new Image(ClassLoader.getSystemResource(URL).toString()));
		im.setPreserveRatio(false);
	}

	public void mouseEnter(int i) {
		switch (i) {
		case 0:
			draw(TryAgain, TRYAGAINSLEURL);
			return;
		case 1:
			draw(Shop, SHOPSLEURL);
			return;
		}
	}
	
	public void mouseExit() {
		draw(TryAgain, TRYAGAINURL);
		draw(Shop, SHOPURL);
	}

	public void back2menu() throws Exception {
		selectSound();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Menu.fxml"));

		Stage window = (Stage) back2menu.getScene().getWindow();
		window.setScene(new Scene(root));
	}

	public void tryagain() throws Exception {
		selectSound();
		GameLogic.resetPlayerUnit();
		GameLogic.createEnemy(GameLogic.getLevel());
		GameLogic.reset_gameEnd();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Battle.fxml"));

		Stage window = (Stage) TryAgain.getScene().getWindow();
		window.setScene(new Scene(root));
	}

	public void go2shop() throws Exception {
		selectSound();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Shop.fxml"));

		Stage window = (Stage) Shop.getScene().getWindow();
		window.setScene(new Scene(root));
	}
}
