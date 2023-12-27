package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameLogic;

public class MenuController implements Initializable {

	private final String NEWGAMEURL = "NEW GAME.png";
	private final String CONTINUNEURL = "CONTINUE.png";
	private final String SHOPURL = "SHOP.png";
	private final String HELPURL = "HELP.png";
	private final String EXITURL = "EXIT.png";

	private final String NEWGAMESLEURL = "NEW GAME slect.png";
	private final String CONTINUNESLEURL = "CONTINUE slect.png";
	private final String SHOPSLEURL = "SHOP select.png";
	private final String HELPSLEURL = "HELP select.png";
	private final String EXITUSLERL = "EXIT select.png";
	
	private static boolean isStart = false;

	private ColorAdjust blackLight = new ColorAdjust(0, 0, -0.5, 0);
	private Lighting lighting = new Lighting();

	@FXML
	private ImageView newGameButton, continueButton, shopButton, helpButton, exitGameButton;
	
	@FXML
	String path = getClass().getResource("/BGSound.mp3").getPath();
	Media media = new Media(new File(path).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(media);
	

	@FXML
	public void bgSound() {
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setVolume(0.7);
		mediaPlayer.play();
	}
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (!isStart) {
			Thread thread = new Thread(() -> {
				bgSound();
			});
			thread.start();
			isStart = true;
		}
		mediaPlayer.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mediaPlayer.seek(Duration.ZERO);
				mediaPlayer.play();
			}
		});
		blackLight.setInput(lighting);
		updateContinueButton();
	}

	public void mouseEnter1() {
		mouseEnter(0);
	}

	public void mouseEnter2() {
		mouseEnter(1);
	}

	public void mouseEnter3() {
		mouseEnter(2);
	}

	public void mouseEnter4() {
		mouseEnter(3);
	}

	public void mouseEnter5() {
		mouseEnter(4);
	}

	public void draw(ImageView im, String URL) {
		im.setImage(new Image(ClassLoader.getSystemResource(URL).toString()));
		im.setPreserveRatio(false);
	}

	public void mouseEnter(int i) {
		switch (i) {
		case 0:
			draw(newGameButton, NEWGAMESLEURL);
			return;
		case 1:
			draw(continueButton, CONTINUNESLEURL);
			return;
		case 2:
			draw(shopButton, SHOPSLEURL);
			return;
		case 3:
			draw(helpButton, HELPSLEURL);
			return;
		case 4:
			draw(exitGameButton, EXITUSLERL);
			return;
		}
	}

	public void mouseExit() {
		draw(newGameButton, NEWGAMEURL);
		draw(continueButton, CONTINUNEURL);
		draw(shopButton, SHOPURL);
		draw(helpButton, HELPURL);
		draw(exitGameButton, EXITURL);
	}

	public void shopButtonHandler() throws Exception {
		selectSound();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Shop.fxml"));

		Stage window = (Stage) shopButton.getScene().getWindow();
		window.setScene(new Scene(root));
	}

	public void continueButtonHandler() throws Exception {
		selectSound();
		GameLogic.resetPlayerUnit();
		GameLogic.reset_gameEnd();
		GameLogic.createEnemy(GameLogic.getLevel());
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Battle.fxml"));
		Stage window = (Stage) shopButton.getScene().getWindow();
		window.setScene(new Scene(root));
	}

	public void updateContinueButton() {
		if (GameLogic.getLevel() == 1) {
			continueButton.setEffect(blackLight);
			continueButton.setDisable(true);
		} else {
			continueButton.setDisable(false);
		}
	}

	public void newGameButtonHandler() throws Exception {
		selectSound();
		if (GameLogic.getLevel() != 1) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Reset Level?");
			alert.setHeaderText("This will reset Stage level and Enemy level to be starter!");
			alert.setContentText(
					"Your Unit level and your Items will not lose");
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					GameLogic.resetEnemy();
					GameLogic.resetPlayerUnit();
					Parent root;
					try {
						root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Battle.fxml"));
						Stage window = (Stage) shopButton.getScene().getWindow();
						window.setScene(new Scene(root));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					return;
				}
			});
		} else {
			GameLogic.resetPlayerUnit();
			GameLogic.reset_gameEnd();
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Battle.fxml"));
			Stage window = (Stage) shopButton.getScene().getWindow();
			window.setScene(new Scene(root));
		}

	}

	public void helpButtonHandler() throws Exception {
		selectSound();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Help.fxml"));

		Stage window = (Stage) helpButton.getScene().getWindow();
		window.setScene(new Scene(root));
	}

	public void exitButtonHandler() {
		selectSound();
		Stage window = (Stage) shopButton.getScene().getWindow();
		window.close();
	}

}
