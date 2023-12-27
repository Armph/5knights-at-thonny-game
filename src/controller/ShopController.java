package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import entity.base.Items;
import entity.base.Unit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameLogic;

public class ShopController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		updateMoneyText();
		updateBufferText();
		updateDPSText();
		updateHealerText();
		updateMageText();
		updateTankText();
		updateBufferButton();
		updateDPSButton();
		updateHealerButton();
		updateMageButton();
		updateTankButton();
	}

	@FXML
	private Text moneyText;
	
	@FXML
	private Text tankText;
	
	@FXML
	private Text DPSText;
	
	@FXML
	private Text mageText;
	
	@FXML
	private Text healerText;
	
	@FXML
	private Text bufferText;

	@FXML
	private ImageView treasureButton;

	@FXML
	private Label tankButton, DPSButton, mageButton, healerButton, bufferButton;
	
	@FXML
	private ImageView returnButton;
	
	@FXML
	public void purchaseSound() {
		Thread thread = new Thread(() -> {
		String path = getClass().getResource("/PurchaseSound.mp3").getPath();
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(0.3);
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
	
	public void updateMoneyText() {
		String cash = Integer.toString(GameLogic.getPlayer().getMoney());
		moneyText.setText(cash + "$");
	}
	
	public void updateTankText() {
		Unit tank = GameLogic.getPlayer().getUnitsDeck().get(1);
		String text = "TankLevel: "+Integer.toString(tank.getLevel()+1);
		tankText.setText(text);
	}
	
	public void updateDPSText() {
		Unit DPS = GameLogic.getPlayer().getUnitsDeck().get(0);
		String text = "DPSLevel: "+Integer.toString(DPS.getLevel()+1);
		DPSText.setText(text);
	}
	
	public void updateMageText() {
		Unit mage = GameLogic.getPlayer().getUnitsDeck().get(3);
		String text = "MageLevel: "+Integer.toString(mage.getLevel()+1);
		mageText.setText(text);
	}
	
	public void updateHealerText() {
		Unit healer = GameLogic.getPlayer().getUnitsDeck().get(2);
		String text = "HealerLevel: "+Integer.toString(healer.getLevel()+1);
		healerText.setText(text);
	}
	
	public void updateBufferText() {
		Unit buffer = GameLogic.getPlayer().getUnitsDeck().get(4);
		String text = "BuffLevel: "+Integer.toString(buffer.getLevel()+1);
		bufferText.setText(text);
	}
	
	public void updateTankButton() {
		Unit tank = GameLogic.getPlayer().getUnitsDeck().get(1);
		String level = Integer.toString(tank.getLevel() * 10);
		tankButton.setText(level + "$");
	}

	
	public void updateDPSButton() {
		Unit DPS = GameLogic.getPlayer().getUnitsDeck().get(0);
		String level = Integer.toString(DPS.getLevel() * 10);
		DPSButton.setText(level + "$");
	}

	
	public void updateMageButton() {
		Unit mage = GameLogic.getPlayer().getUnitsDeck().get(3);
		String level = Integer.toString(mage.getLevel() * 10);
		mageButton.setText(level + "$");
	}

	
	public void updateHealerButton() {
		Unit healer = GameLogic.getPlayer().getUnitsDeck().get(2);
		String level = Integer.toString(healer.getLevel() * 10);
		healerButton.setText(level + "$");
	}

	
	public void updateBufferButton() {
		Unit buffer = GameLogic.getPlayer().getUnitsDeck().get(4);
		String level = Integer.toString(buffer.getLevel() * 10);
		bufferButton.setText(level + "$");
	}

	
	public void alertNotEnoughMoney() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Not enough money!");
		alert.setHeaderText(null);
		alert.setContentText("You have not enough money!!\nPlease play game to get more money.");
		alert.showAndWait();
	}
	
	public void alertTreasure(Items item) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Congratualtions!!");
		alert.setContentText("You got "+item.getName()+".");
		alert.showAndWait();
	}

	
	public void treasureHandler() {
		int index = ThreadLocalRandom.current().nextInt(0, 5);
		Items it = GameLogic.getPlayer().getItemsDeck().get(index);
		it.setCount(it.getCount() + 1);
		if(50 > GameLogic.getPlayer().getMoney()) {
			alertNotEnoughMoney();
			return;
		}
		purchaseSound();
		GameLogic.getPlayer().pay(50);
		updateMoneyText();
		alertTreasure(it);

	}

	
	public void tankLevelUpHandler() {
		String[] tmp = tankButton.getText().split(" ");
		int cost = Integer.parseInt(tmp[0].substring(0, tmp[0].length()-1));
		if (cost > GameLogic.getPlayer().getMoney()) {
			alertNotEnoughMoney();
			return;
		}
		purchaseSound();
		GameLogic.getPlayer().pay(cost);
		Unit tank = GameLogic.getPlayer().getUnitsDeck().get(1);
		tank.levelUp();
		updateMoneyText();
		updateTankButton();
		updateTankText();
	}

	
	public void DPSLevelUpHandler() {
		String[] tmp = DPSButton.getText().split(" ");
		int cost = Integer.parseInt(tmp[0].substring(0, tmp[0].length()-1));
		if (cost > GameLogic.getPlayer().getMoney()) {
			alertNotEnoughMoney();
			return;
		}
		purchaseSound();
		GameLogic.getPlayer().pay(cost);
		Unit DPS = GameLogic.getPlayer().getUnitsDeck().get(0);
		DPS.levelUp();
		updateMoneyText();
		updateDPSButton();
		updateDPSText();
	}

	
	public void mageLevelUpHandler() {
		String[] tmp = mageButton.getText().split(" ");
		int cost = Integer.parseInt(tmp[0].substring(0, tmp[0].length()-1));
		if (cost > GameLogic.getPlayer().getMoney()) {
			alertNotEnoughMoney();
			return;
		}
		purchaseSound();
		GameLogic.getPlayer().pay(cost);
		Unit mage = GameLogic.getPlayer().getUnitsDeck().get(3);
		mage.levelUp();
		updateMoneyText();
		updateMageButton();
		updateMageText();
	}

	
	public void healerLevelUpHandler() {
		String[] tmp = healerButton.getText().split(" ");
		int cost = Integer.parseInt(tmp[0].substring(0, tmp[0].length()-1));
		if (cost > GameLogic.getPlayer().getMoney()) {
			alertNotEnoughMoney();
			return;
		}
		purchaseSound();
		GameLogic.getPlayer().pay(cost);
		Unit healer = GameLogic.getPlayer().getUnitsDeck().get(2);
		healer.levelUp();
		updateMoneyText();
		updateHealerText();
		updateHealerButton();
	}

	
	public void bufferLevelUpHandler() {
		String[] tmp = bufferButton.getText().split(" ");
		int cost = Integer.parseInt(tmp[0].substring(0, tmp[0].length()-1));
		if (cost > GameLogic.getPlayer().getMoney()) {
			alertNotEnoughMoney();
			return;
		}
		purchaseSound();
		GameLogic.getPlayer().pay(cost);
		Unit buffer = GameLogic.getPlayer().getUnitsDeck().get(4);
		buffer.levelUp();
		updateMoneyText();
		updateBufferButton();
		updateBufferText();
	}
	
	public void returnButtonHandler() throws Exception {
		selectSound();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Menu.fxml"));
		
		Stage window = (Stage) returnButton.getScene().getWindow();
		window.setScene(new Scene(root));	
	}

}
