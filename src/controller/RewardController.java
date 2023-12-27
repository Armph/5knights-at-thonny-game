package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import entity.base.Items;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameLogic;

public class RewardController implements Initializable {
	private boolean hasPicked;
	int item1, item2, item3;
	private final String HEALPOTION = "HealPotion.png";
	private final String BUFFATKPOTION = "BuffATKPotion.png";
	private final String BUFFSHIELDPOTION = "BuffShieldPotion.png";
	private final String DEBUFFPOTION = "deBuffPotion.png";
	private final String REVIVEPOTION = "RevivePotion.png";
	private String URL;

	@FXML
	private Text rewardMoneyText, topicText;

	@FXML
	private ImageView itemImage1, itemImage2, itemImage3, back2menuButton, continueButton;
	
	@FXML
	public void chooseSound() {
		Thread thread = new Thread(() -> {
			String path = getClass().getResource("/chooseSound.mp3").getPath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setVolume(0.1);
			mediaPlayer.play();
		});
		thread.start();
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
	
	@FXML
	public void winSound() {
		Thread thread = new Thread(() -> {
		String path = getClass().getResource("/winSound.mp3").getPath();
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.1);
		mediaPlayer.play();
		});
		thread.start();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		winSound();
		draw_all();
		int reward = updateMoneytext();
		GameLogic.getPlayer().reward(reward);
		hasPicked = false;
		updateTopicText(null);
		GameLogic.next_level();
		blackLight.setInput(lighting);
		back2menuButton.setVisible(false);
		continueButton.setVisible(false);
	}

	private ColorAdjust blackLight = new ColorAdjust(0, 0, -0.5, 0);
	private Lighting lighting = new Lighting();

	public int updateMoneytext() {
		int reward = 100 + GameLogic.getLevel() * 20;
		rewardMoneyText.setText(Integer.toString(reward) + "$");
		return reward;
	}

	public void randomItem() {
		item1 = ThreadLocalRandom.current().nextInt(0, 5);
		item2 = (item1 + 1) % 5;
		item3 = (item1 + 2) % 5;
	}

	public void draw_all() {
		randomItem();
		draw(itemImage1, item1);
		draw(itemImage2, item2);
		draw(itemImage3, item3);
	}

	public void draw(ImageView im, int i) {
		switch (i) {
		case 0:
			URL = BUFFATKPOTION;
			break;
		case 1:
			URL = BUFFSHIELDPOTION;
			break;
		case 2:
			URL = DEBUFFPOTION;
			break;
		case 3:
			URL = HEALPOTION;
			break;
		case 4:
			URL = REVIVEPOTION;
			break;
		}
		im.setImage(new Image(ClassLoader.getSystemResource(URL).toString()));
		im.setPreserveRatio(false);
	}

	public void add_black_effect() {
		itemImage1.setEffect(blackLight);
		itemImage2.setEffect(blackLight);
		itemImage3.setEffect(blackLight);
	}

	public void image1Handler() {
		if (hasPicked)
			return;
		chooseSound();
		add_black_effect();
		itemImage1.setEffect(null);
		hasPicked = true;
		back2menuButton.setVisible(true);
		continueButton.setVisible(true);
		Items item = GameLogic.getPlayer().getItemsDeck().get(item1);
		item.setCount(item.getCount() + 1);
		updateTopicText("You Got " + item.getName());
	}

	public void image2Handler() {
		if (hasPicked)
			return;
		chooseSound();
		add_black_effect();
		itemImage2.setEffect(null);
		hasPicked = true;
		back2menuButton.setVisible(true);
		continueButton.setVisible(true);
		Items item = GameLogic.getPlayer().getItemsDeck().get(item2);
		item.setCount(item.getCount() + 1);
		updateTopicText("You Got " + item.getName());
	}

	public void image3Handler() {
		if (hasPicked)
			return;
		chooseSound();
		add_black_effect();
		itemImage3.setEffect(null);
		hasPicked = true;
		back2menuButton.setVisible(true);
		continueButton.setVisible(true);
		Items item = GameLogic.getPlayer().getItemsDeck().get(item3);
		item.setCount(item.getCount() + 1);
		updateTopicText("You Got " + item.getName());
	}

	public void updateTopicText(String text) {
		if (!hasPicked) {
			topicText.setText("You win get your reward. (Pick one)");
		} else {
			topicText.setText(text);
		}
	}

	public void back2menuButtonHandler() throws Exception {
		selectSound();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Menu.fxml"));

		Stage window = (Stage) back2menuButton.getScene().getWindow();
		window.setScene(new Scene(root));
	}

	public void continueButtonHandler() throws Exception {
		if (!hasPicked)
			return;

		selectSound();
		GameLogic.resetPlayerUnit();

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Battle.fxml"));

		Stage window = (Stage) continueButton.getScene().getWindow();
		window.setScene(new Scene(root));
	}

}
