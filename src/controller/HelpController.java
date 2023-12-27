package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class HelpController implements Initializable {

	@FXML
	private ImageView back2menu;
	
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
		// TODO Auto-generated method stub

	}
	

	public void back2menuHandler() throws Exception {
		selectSound();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Menu.fxml"));

		Stage window = (Stage) back2menu.getScene().getWindow();
		window.setScene(new Scene(root));
	}

}
