package controller;

import java.awt.Color;
import java.awt.Menu;
import java.beans.beancontext.BeanContextServiceProviderBeanInfo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import entity.base.Unit;
import entity.unit.*;
import logic.GameLogic;

public class BattleController implements Initializable {
	private ArrayList<Integer> toAdd = new ArrayList<>();
	private ArrayList<Unit> PlayerField = new ArrayList<Unit>(Arrays.asList(null, null, null));
	private ArrayList<Unit> EnemyField;
	private final String BG = "ab.png";
	private final String DPSURL = "DPS.png";
	private final String TANKURL = "FordRanger.png";
	private final String HEALURL = "Heal.png";
	private final String MAGEURL = "Mage.png";
	private final String BUFFURL = "Buff.png";
	private final String EDPSURL = "DPSEnemy.png";
	private final String ETANKURL = "TankEnemy.png";
	private final String EHEALURL = "HealEnemy.png";
	private final String EMAGEURL = "MageEnemy.png";
	private final String EBUFFURL = "BuffEnemy.png";
	private final String HEALPOTION = "HealPotion.png";
	private final String BUFFATKPOTION = "BuffATKPotion.png";
	private final String BUFFSHIELDPOTION = "BuffShieldPotion.png";
	private final String DEBUFFPOTION = "deBuffPotion.png";
	private final String REVIVEPOTION = "RevivePotion.png";
	private boolean isStart = false;
	private ColorAdjust brightLight = new ColorAdjust(0, 0, 0.5, 0);
	private ColorAdjust blackLight = new ColorAdjust(0, 0, 0, 0);
	private Lighting lighting = new Lighting();

	@FXML
	private BorderPane battleMenu;

	@FXML
	private Label curStage;

	@FXML
	private ImageView hole1;

	@FXML
	private ImageView hole2;

	@FXML
	private ImageView hole3;

	@FXML
	private ImageView slot1;

	@FXML
	private ImageView slot2;

	@FXML
	private ImageView slot3;

	@FXML
	private ImageView slot4;

	@FXML
	private ImageView slot5;

	@FXML
	private ImageView slot6;

	@FXML
	private Pane sloat_pane1;

	@FXML
	private Pane sloat_pane2;

	@FXML
	private Pane sloat_pane3;

	@FXML
	private Pane sloat_pane4;

	@FXML
	private Pane sloat_pane5;

	@FXML
	private Pane sloat_pane6;

	@FXML
	private ImageView bar1;

	@FXML
	private ImageView bar2;

	@FXML
	private ImageView bar3;

	@FXML
	private ImageView bar4;

	@FXML
	private ImageView bar5;

	@FXML
	private Pane pane1;

	@FXML
	private Pane pane2;

	@FXML
	private Pane pane3;

	@FXML
	private Pane pane4;

	@FXML
	private Pane pane5;

	@FXML
	private ImageView startButton;

	@FXML
	private ImageView endButton;

	@FXML
	private ImageView back2menu;

	@FXML
	private ImageView level_bar;

	@FXML
	private Label level;

	@FXML
	private ProgressBar atk;

	@FXML
	private ProgressBar hp;

	@FXML
	private ProgressBar phyShield;

	@FXML
	private ProgressBar magicSheild;

	@FXML
	private Label count1;

	@FXML
	private Label count2;

	@FXML
	private Label count3;

	@FXML
	private Label count4;

	@FXML
	private Label count5;

	@FXML
	private Label atkLabel;

	@FXML
	private Label hpLabel;

	@FXML
	private Label phyLabel;

	@FXML
	private Label magicLabel;

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
	public void endTurnSound() {
		Thread thread = new Thread(() -> {
			String path = getClass().getResource("/EndTurnSound.mp3").getPath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setVolume(0.3);
			mediaPlayer.play();
		});
		thread.start();
	}
	
	@FXML
	public void readySound() {
		Thread thread = new Thread(() -> {
			String path = getClass().getResource("/readySound.mp3").getPath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setVolume(0.2);
			mediaPlayer.play();
		});
		thread.start();
	}

	@FXML
	public void hitSound() {
		Thread thread = new Thread(() -> {
			String path = getClass().getResource("/HitSound.mp3").getPath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setVolume(0.2);
			mediaPlayer.play();
		});
		thread.start();
	}

	@FXML
	public void healSound() {
		Thread thread = new Thread(() -> {
			String path = getClass().getResource("/HealSound.mp3").getPath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setVolume(0.3);
			mediaPlayer.play();
		});
		thread.start();
	}

	@FXML
	public void buffSound() {
		Thread thread = new Thread(() -> {
			String path = getClass().getResource("/BuffSound.mp3").getPath();
			Media media = new Media(new File(path).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
		});
		thread.start();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		curStage.setText("Stage: " + GameLogic.getLevel());
		endButton.setDisable(true);
		endButton.setVisible(false);
		atk.setStyle("-fx-accent: #FF4500;");
		hp.setStyle("-fx-accent: #7CFC00;");
		phyShield.setStyle("-fx-accent: #FFFF00;");
		magicSheild.setStyle("-fx-accent: #87CEFA;");
		level_bar.setVisible(false);
		level.setVisible(false);
		atk.setVisible(false);
		hp.setVisible(false);
		phyShield.setVisible(false);
		magicSheild.setVisible(false);
		atkLabel.setVisible(false);
		hpLabel.setVisible(false);
		phyLabel.setVisible(false);
		magicLabel.setVisible(false);
		brightLight.setInput(lighting);
		blackLight.setInput(lighting);
		EnemyField = GameLogic.getEnemyField();
		reset_pane_effect();
		count1.setText(null);
		count2.setText(null);
		count3.setText(null);
		count4.setText(null);
		count5.setText(null);
		draw_enemy();
	}

	public void draw_enemy() {
		for (int i = 0; i < EnemyField.size(); i++) {
			switch (i) {
			case 0:
				switch (check_Unit(EnemyField.get(i))) {
				case 0:
					draw(slot4, EDPSURL);
					break;
				case 1:
					draw(slot4, ETANKURL);
					break;
				case 2:
					draw(slot4, EHEALURL);
					break;
				case 3:
					draw(slot4, EMAGEURL);
					break;
				case 4:
					draw(slot4, EBUFFURL);
					break;
				}
			case 1:
				switch (check_Unit(EnemyField.get(i))) {
				case 0:
					draw(slot5, EDPSURL);
					break;
				case 1:
					draw(slot5, ETANKURL);
					break;
				case 2:
					draw(slot5, EHEALURL);
					break;
				case 3:
					draw(slot5, EMAGEURL);
					break;
				case 4:
					draw(slot5, EBUFFURL);
					break;
				}
			case 2:
				switch (check_Unit(EnemyField.get(i))) {
				case 0:
					draw(slot6, EDPSURL);
					break;
				case 1:
					draw(slot6, ETANKURL);
					break;
				case 2:
					draw(slot6, EHEALURL);
					break;
				case 3:
					draw(slot6, EMAGEURL);
					break;
				case 4:
					draw(slot6, EBUFFURL);
					break;
				}
			}
		}
	}

	public int check_Unit(Unit unit) {
		if (unit instanceof DPS)
			return 0;
		if (unit instanceof Tank)
			return 1;
		if (unit instanceof Heal)
			return 2;
		if (unit instanceof Mage)
			return 3;
		return 4;
	}

	public void draw(ImageView im, String URL) {
		im.setImage(new Image(ClassLoader.getSystemResource(URL).toString()));
		im.setPreserveRatio(false);
	}

	public void show_slot1() {
		show_status(0);
	}

	public void show_slot2() {
		show_status(1);
	}

	public void show_slot3() {
		show_status(2);
	}

	public void show_slot4() {
		show_status(3);
	}

	public void show_slot5() {
		show_status(4);
	}

	public void show_slot6() {
		show_status(5);
	}

	public void show_status(int i) {
		Unit player;
		if (i <= 2) {
			player = PlayerField.get(i);
		} else {
			i -= 3;
			player = EnemyField.get(i);
		}
		atk.setProgress(0);
		hp.setProgress(0);
		phyShield.setProgress(0);
		magicSheild.setProgress(0);
		if (player != null) {
			int attack = player.getAttack();
			int maxHealth = player.getMaxHealth();
			int health = player.getHealth();
			int maxShiledphy = player.getMaxShieldPhy();
			int sheildPhy = player.getShieldPhy();
			if (player instanceof Tank) {
				int maxShildmagic = ((Tank) player).getMaxShieldMagic();
				int shildMagic = ((Tank) player).getShieldMagic();
				magicLabel.setText("MagicShield: " + shildMagic + "/" + maxShildmagic);
				magicSheild.setProgress((float) shildMagic / maxShildmagic);
			} else {
				magicLabel.setText("MagicShield: 0");
				magicSheild.setProgress(0);
			}
			atk.setProgress((float) attack / maxHealth);
			hp.setProgress((float) health / maxHealth);
			phyShield.setProgress((float) sheildPhy / maxShiledphy);

			level.setText("Level: " + player.getLevel());
			level.setVisible(true);
			level_bar.setVisible(true);

			atkLabel.setText("Attack: " + attack);
			hpLabel.setText("Health: " + health + "/" + maxHealth);
			phyLabel.setText("PhyShield: " + sheildPhy + "/" + maxShiledphy);

			atkLabel.setVisible(true);
			hpLabel.setVisible(true);
			phyLabel.setVisible(true);
			magicLabel.setVisible(true);

			atk.setVisible(true);
			hp.setVisible(true);
			phyShield.setVisible(true);
			magicSheild.setVisible(true);
		}
		return;

	}

	public void hide_status() {
		level.setVisible(false);
		level_bar.setVisible(false);
		atk.setVisible(false);
		hp.setVisible(false);
		phyShield.setVisible(false);
		magicSheild.setVisible(false);
		atkLabel.setVisible(false);
		hpLabel.setVisible(false);
		phyLabel.setVisible(false);
		magicLabel.setVisible(false);
	}

	public void toSlot1() {
		if (isStart && GameLogic.getPlayerField().get(0) == null)
			return;
		reset_hole_effect();
		hole1.setEffect(brightLight);
		reset_pane_effect();
		if (!isStart) {
			slot1.setImage(null);
			reset_pane(PlayerField.get(0));
			PlayerField.set(0, null);
			toAdd.clear();
			toAdd.add(0);
			return;
		}
		if (GameLogic.getPlayerField().get(0).isAlive()) {
			if (!toAdd.isEmpty() && toAdd.get(0) == 0) {
				if (GameLogic.getPlayerField().get(0) instanceof Heal) {
					GameLogic.healAll(0);
					healSound();
					slot1.setDisable(true);
					hole1.setEffect(null);
					toAdd.clear();
					return;
				}
				if (GameLogic.getPlayerField().get(0) instanceof Buff) {
					GameLogic.buffAll(0);
					buffSound();
					slot1.setDisable(true);
					hole1.setEffect(null);
					toAdd.clear();
					return;
				}
			}
			if (toAdd.isEmpty() || toAdd.get(0) < 3) {
				toAdd.clear();
				toAdd.add(0);
				return;
			}
			if (toAdd.get(0) == 7 && GameLogic.getPlayerField().get(0).isAlive()) {
				toAdd.clear();
				reset_hole_effect();
				return;
			}
			GameLogic.usePotion(toAdd.get(0) - 3, 0);
			toAdd.clear();
			show_status(0);
			reset_hole_effect();
			update_status();
			game_end();
		} else if (!toAdd.isEmpty() && toAdd.get(0) == 7) {
			GameLogic.usePotion(4, 0);
			slot1.setEffect(null);
			toAdd.clear();
			show_status(0);
			reset_hole_effect();
			update_status();
		}
	}

	public void toSlot2() {
		if (isStart && GameLogic.getPlayerField().get(1) == null)
			return;
		reset_hole_effect();
		hole2.setEffect(brightLight);
		reset_pane_effect();

		if (!isStart) {
			slot2.setImage(null);
			reset_pane(PlayerField.get(1));
			PlayerField.set(1, null);
			toAdd.clear();
			toAdd.add(1);
			return;
		}
		if (GameLogic.getPlayerField().get(1).isAlive()) {
			if (!toAdd.isEmpty() && toAdd.get(0) == 1) {
				if (GameLogic.getPlayerField().get(1) instanceof Heal) {
					GameLogic.healAll(1);
					healSound();
					slot2.setDisable(true);
					hole2.setEffect(null);
					toAdd.clear();
					return;
				}
				if (GameLogic.getPlayerField().get(1) instanceof Buff) {
					GameLogic.buffAll(1);
					buffSound();
					slot2.setDisable(true);
					hole2.setEffect(null);
					toAdd.clear();
					return;
				}
			}
			if (toAdd.isEmpty() || toAdd.get(0) < 3) {
				toAdd.clear();
				toAdd.add(1);
				return;
			}
			if (toAdd.get(0) == 7 && GameLogic.getPlayerField().get(1).isAlive()) {
				toAdd.clear();
				reset_hole_effect();
				return;
			}
			GameLogic.usePotion(toAdd.get(0) - 3, 1);
			toAdd.clear();
			show_status(1);
			reset_hole_effect();
			update_status();
			game_end();
		} else if (!toAdd.isEmpty() && toAdd.get(0) == 7) {
			GameLogic.usePotion(4, 1);
			slot2.setEffect(null);
			toAdd.clear();
			show_status(1);
			reset_hole_effect();
			update_status();
		}
	}

	public void toSlot3() {
		if (isStart && GameLogic.getPlayerField().get(2) == null)
			return;
		reset_hole_effect();
		hole3.setEffect(brightLight);
		reset_pane_effect();

		if (!isStart) {
			slot3.setImage(null);
			reset_pane(PlayerField.get(2));
			PlayerField.set(2, null);
			toAdd.clear();
			toAdd.add(2);
			return;
		}
		if (GameLogic.getPlayerField().get(2).isAlive()) {
			if (!toAdd.isEmpty() && toAdd.get(0) == 2) {
				if (GameLogic.getPlayerField().get(2) instanceof Heal) {
					GameLogic.healAll(2);
					healSound();
					slot3.setDisable(true);
					hole3.setEffect(null);
					toAdd.clear();
					return;
				}
				if (GameLogic.getPlayerField().get(2) instanceof Buff) {
					GameLogic.buffAll(2);
					buffSound();
					slot3.setDisable(true);
					hole3.setEffect(null);
					toAdd.clear();
					return;
				}
			}
			if (toAdd.isEmpty() || toAdd.get(0) < 3) {
				toAdd.clear();
				toAdd.add(2);
				return;
			}
			if (toAdd.get(0) == 7 && GameLogic.getPlayerField().get(2).isAlive()) {
				toAdd.clear();
				reset_hole_effect();
				return;
			}
			GameLogic.usePotion(toAdd.get(0) - 3, 2);
			toAdd.clear();
			show_status(2);
			reset_hole_effect();
			update_status();
			game_end();
		} else if (!toAdd.isEmpty() && toAdd.get(0) == 7) {
			GameLogic.usePotion(4, 2);
			slot3.setEffect(null);
			toAdd.clear();
			show_status(2);
			reset_hole_effect();
			update_status();
		}
	}

	public void reset_hole_effect() {
		hole1.setEffect(null);
		hole2.setEffect(null);
		hole3.setEffect(null);
	}

	public void reset_pane_effect() {
		pane1.setEffect(null);
		pane2.setEffect(null);
		pane3.setEffect(null);
		pane4.setEffect(null);
		pane5.setEffect(null);
		if (isStart)
			update_status();
	}

	public void addBar1() {
		if (!isStart && !toAdd.isEmpty()) {
			// String TankURL = ClassLoader.getSystemResource(TANKURL).toString();
			pane1.setDisable(true);
			switch (toAdd.get(0)) {
			case 0:
				draw(slot1, DPSURL);
				// slot1.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(0, GameLogic.getPlayer().getUnitsDeck().get(0));
				return;
			case 1:
				draw(slot2, DPSURL);
				// slot2.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(1, GameLogic.getPlayer().getUnitsDeck().get(0));
				return;
			case 2:
				draw(slot3, DPSURL);
				// slot3.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(2, GameLogic.getPlayer().getUnitsDeck().get(0));
				return;
			}
		}
		if (!isStart)
			return;
		if (!toAdd.isEmpty() && toAdd.get(0) == 3) {
			toAdd.clear();
			reset_pane_effect();
			return;
		}
		toAdd.clear();
		toAdd.add(3);
		reset_pane_effect();
		reset_hole_effect();
		pane1.setEffect(brightLight);
	}

	public void addBar2() {
		if (!isStart && !toAdd.isEmpty()) {
			// String TankURL = ClassLoader.getSystemResource(TANKURL).toString();
			pane2.setDisable(true);
			switch (toAdd.get(0)) {
			case 0:
				draw(slot1, TANKURL);
				// slot1.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(0, GameLogic.getPlayer().getUnitsDeck().get(1));
				return;
			case 1:
				draw(slot2, TANKURL);
				// slot2.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(1, GameLogic.getPlayer().getUnitsDeck().get(1));
				return;
			case 2:
				draw(slot3, TANKURL);
				// slot3.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(2, GameLogic.getPlayer().getUnitsDeck().get(1));
				return;
			}
			return;
		}
		if (!isStart)
			return;
		if (!toAdd.isEmpty() && toAdd.get(0) == 4) {
			toAdd.clear();
			reset_pane_effect();
			return;
		}
		toAdd.clear();
		toAdd.add(4);
		reset_pane_effect();
		reset_hole_effect();
		pane2.setEffect(brightLight);
	}

	public void addBar3() {
		if (!isStart && !toAdd.isEmpty()) {
			// String TankURL = ClassLoader.getSystemResource(TANKURL).toString();
			pane3.setDisable(true);
			switch (toAdd.get(0)) {
			case 0:
				draw(slot1, HEALURL);
				// slot1.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(0, GameLogic.getPlayer().getUnitsDeck().get(2));
				return;
			case 1:
				draw(slot2, HEALURL);
				// slot2.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(1, GameLogic.getPlayer().getUnitsDeck().get(2));
				return;
			case 2:
				draw(slot3, HEALURL);
				// slot3.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(2, GameLogic.getPlayer().getUnitsDeck().get(2));
				return;
			}
			return;
		}
		if (!isStart)
			return;
		if (!toAdd.isEmpty() && toAdd.get(0) == 5) {
			toAdd.clear();
			reset_pane_effect();
			return;
		}
		toAdd.clear();
		toAdd.add(5);
		reset_pane_effect();
		reset_hole_effect();
		pane3.setEffect(brightLight);
	}

	public void addBar4() {
		if (!isStart && !toAdd.isEmpty()) {
			// String TankURL = ClassLoader.getSystemResource(TANKURL).toString();
			pane4.setDisable(true);
			switch (toAdd.get(0)) {
			case 0:
				draw(slot1, MAGEURL);
				// slot1.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(0, GameLogic.getPlayer().getUnitsDeck().get(3));
				return;
			case 1:
				draw(slot2, MAGEURL);
				// slot2.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(1, GameLogic.getPlayer().getUnitsDeck().get(3));
				return;
			case 2:
				draw(slot3, MAGEURL);
				// slot3.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(2, GameLogic.getPlayer().getUnitsDeck().get(3));
				return;
			}
			return;
		}
		if (!isStart)
			return;
		if (!toAdd.isEmpty() && toAdd.get(0) == 6) {
			toAdd.clear();
			reset_pane_effect();
			return;
		}
		toAdd.clear();
		toAdd.add(6);
		reset_pane_effect();
		reset_hole_effect();
		pane4.setEffect(brightLight);
	}

	public void addBar5() {
		if (!isStart && !toAdd.isEmpty()) {
			// String TankURL = ClassLoader.getSystemResource(TANKURL).toString();
			pane5.setDisable(true);
			switch (toAdd.get(0)) {
			case 0:
				draw(slot1, BUFFURL);
				// slot1.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(0, GameLogic.getPlayer().getUnitsDeck().get(4));
				return;
			case 1:
				draw(slot2, BUFFURL);
				// slot2.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(1, GameLogic.getPlayer().getUnitsDeck().get(4));
				return;
			case 2:
				draw(slot3, BUFFURL);
				// slot3.setImage(new Image(TankURL));
				toAdd.clear();
				reset_hole_effect();
				PlayerField.set(2, GameLogic.getPlayer().getUnitsDeck().get(4));
				return;
			}
			return;
		}
		if (!isStart)
			return;
		if (!toAdd.isEmpty() && toAdd.get(0) == 7) {
			toAdd.clear();
			reset_pane_effect();
			return;
		}
		toAdd.clear();
		toAdd.add(7);
		reset_pane_effect();
		reset_hole_effect();
		pane5.setEffect(brightLight);
	}

	public void reset_pane(Unit unit) {
		if (unit instanceof DPS)
			pane1.setDisable(false);
		if (unit instanceof Tank)
			pane2.setDisable(false);
		if (unit instanceof Heal)
			pane3.setDisable(false);
		if (unit instanceof Mage)
			pane4.setDisable(false);
		if (unit instanceof Buff)
			pane5.setDisable(false);
	}

	public void start_battle() {
		int count = 0;
		for (int i = 0; i < PlayerField.size(); i++) {
			if (PlayerField.get(i) == null) {
				count++;
			}
		}
		if (count == 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("No Unit found");
			alert.setContentText("Please select at least one Unit.");
			alert.showAndWait();
			return;
		}
		readySound();
		back2menu.setVisible(false);
		back2menu.setDisable(true);
		isStart = true;
		endButton.setDisable(false);
		endButton.setVisible(true);
		draw_items();
		startButton.setDisable(true);
		startButton.setVisible(false);
		pane1.setDisable(false);
		pane2.setDisable(false);
		pane3.setDisable(false);
		pane4.setDisable(false);
		pane5.setDisable(false);
		GameLogic.updatePlayerField(PlayerField);
		PlayerField = GameLogic.getPlayerField();
		pane1.setDisable(false);
		pane2.setDisable(false);
		pane3.setDisable(false);
		pane4.setDisable(false);
		pane5.setDisable(false);
		hole1.setEffect(null);
		hole2.setEffect(null);
		hole3.setEffect(null);
		count1.setText("" + GameLogic.getPlayer().getItemsDeck().get(0).getCount());
		count2.setText("" + GameLogic.getPlayer().getItemsDeck().get(1).getCount());
		count3.setText("" + GameLogic.getPlayer().getItemsDeck().get(2).getCount());
		count4.setText("" + GameLogic.getPlayer().getItemsDeck().get(3).getCount());
		count5.setText("" + GameLogic.getPlayer().getItemsDeck().get(4).getCount());
		update_status();
	}

	public void update_status() {
		for (int i = 0; i < GameLogic.getPlayerField().size(); i++) {
			if (GameLogic.getPlayerField().get(i) != null && !GameLogic.getPlayerField().get(i).isAlive()) {
				switch (i) {
				case 0:
					// slot1.setDisable(true);
					slot1.setEffect(blackLight);
					continue;
				case 1:
					// slot2.setDisable(true);
					slot2.setEffect(blackLight);
					continue;
				case 2:
					// slot3.setDisable(true);
					slot3.setEffect(blackLight);
					continue;
				}
			}
		}
		for (int i = 0; i < GameLogic.getEnemyField().size(); i++) {
			if (GameLogic.getEnemyField().get(i) != null && !GameLogic.getEnemyField().get(i).isAlive()) {
				switch (i) {
				case 0:
					slot4.setDisable(true);
					slot4.setEffect(blackLight);
					continue;
				case 1:
					slot5.setDisable(true);
					slot5.setEffect(blackLight);
					continue;
				case 2:
					slot6.setDisable(true);
					slot6.setEffect(blackLight);
					continue;
				}
			}
		}
		count1.setText("" + GameLogic.getPlayer().getItemsDeck().get(0).getCount());
		count2.setText("" + GameLogic.getPlayer().getItemsDeck().get(1).getCount());
		count3.setText("" + GameLogic.getPlayer().getItemsDeck().get(2).getCount());
		count4.setText("" + GameLogic.getPlayer().getItemsDeck().get(3).getCount());
		count5.setText("" + GameLogic.getPlayer().getItemsDeck().get(4).getCount());
		for (int i = 0; i < GameLogic.getPlayer().getItemsDeck().size(); i++) {
			if (GameLogic.getPlayer().getItemsDeck().get(i) != null
					&& GameLogic.getPlayer().getItemsDeck().get(i).getCount() == 0) {
				switch (i) {
				case 0:
					pane1.setDisable(true);
					pane1.setEffect(blackLight);
					continue;
				case 1:
					pane2.setDisable(true);
					pane2.setEffect(blackLight);
					continue;
				case 2:
					pane3.setDisable(true);
					pane3.setEffect(blackLight);
					continue;
				case 3:
					pane4.setDisable(true);
					pane4.setEffect(blackLight);
					continue;
				case 4:
					pane5.setDisable(true);
					pane5.setEffect(blackLight);
					continue;
				}
			}
		}
	}

	public void draw_items() {
		draw(bar1, BUFFATKPOTION);
		draw(bar2, BUFFSHIELDPOTION);
		draw(bar3, DEBUFFPOTION);
		draw(bar4, HEALPOTION);
		draw(bar5, REVIVEPOTION);
	}

	public void atkE1() {
		if (!isStart || toAdd.isEmpty())
			return;
		switch (toAdd.get(0)) {
		case 0:
			GameLogic.calculatePlayerAttack(0, 0);
			hitSound();
			slot1.setDisable(true);
			hole1.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(3);
			game_end();
			return;
		case 1:
			GameLogic.calculatePlayerAttack(1, 0);
			hitSound();
			slot2.setDisable(true);
			hole2.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(3);
			game_end();
			return;
		case 2:
			GameLogic.calculatePlayerAttack(2, 0);
			hitSound();
			slot3.setDisable(true);
			hole3.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(3);
			game_end();
			return;
		case 7:
			return;
		default:
			GameLogic.usePotion(toAdd.get(0) - 3, 3);
			update_status();
			reset_pane_effect();
			toAdd.clear();
			show_status(3);
			game_end();
		}

		toAdd.clear();
	}

	public void atkE2() {
		if (!isStart || toAdd.isEmpty())
			return;
		switch (toAdd.get(0)) {
		case 0:
			GameLogic.calculatePlayerAttack(0, 1);
			hitSound();
			slot1.setDisable(true);
			hole1.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(4);
			game_end();
			return;
		case 1:
			GameLogic.calculatePlayerAttack(1, 1);
			hitSound();
			slot2.setDisable(true);
			hole2.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(4);
			game_end();
			return;
		case 2:
			GameLogic.calculatePlayerAttack(2, 1);
			hitSound();
			slot3.setDisable(true);
			hole3.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(4);
			game_end();
			return;
		case 7:
			return;
		default:
			GameLogic.usePotion(toAdd.get(0) - 3, 4);
			update_status();
			reset_pane_effect();
			toAdd.clear();
			show_status(4);
			game_end();
		}
		toAdd.clear();
	}

	public void atkE3() {
		if (!isStart || toAdd.isEmpty())
			return;
		switch (toAdd.get(0)) {
		case 0:
			GameLogic.calculatePlayerAttack(0, 2);
			hitSound();
			slot1.setDisable(true);
			hole1.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(5);
			game_end();
			return;
		case 1:
			GameLogic.calculatePlayerAttack(1, 2);
			hitSound();
			slot2.setDisable(true);
			hole2.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(5);
			game_end();
			return;
		case 2:
			GameLogic.calculatePlayerAttack(2, 2);
			hitSound();
			slot3.setDisable(true);
			hole3.setEffect(null);
			toAdd.clear();
			update_status();
			show_status(5);
			game_end();
			return;
		case 7:
			return;
		default:
			GameLogic.usePotion(toAdd.get(0) - 3, 5);
			update_status();
			reset_pane_effect();
			toAdd.clear();
			show_status(5);
			game_end();
		}

		toAdd.clear();
	}

	public void enemyAttck() {
		endTurnSound();
		slot1.setDisable(false);
		slot2.setDisable(false);
		slot3.setDisable(false);
		GameLogic.enemyAttack();
		reset_pane_effect();
		reset_hole_effect();
		toAdd.clear();
		update_status();
		game_end();
	}

	public void game_end() {
		if (!GameLogic.isGameEnd())
			return;
		try {
			if (GameLogic.isPlayerWin()) {
				Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Reward.fxml"));
				Stage window = (Stage) back2menu.getScene().getWindow();
				window.setScene(new Scene(root));
			} else if (GameLogic.isPlayerLose()) {
				Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Lose.fxml"));

				Stage window = (Stage) back2menu.getScene().getWindow();
				window.setScene(new Scene(root));
			}
		} catch (Exception e) {
			System.out.println("error on opening fxml");
		}

	}

	public void back2Menu() throws Exception {
		if (isStart)
			return;
		selectSound();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("stage/Menu.fxml"));

		Stage window = (Stage) back2menu.getScene().getWindow();
		window.setScene(new Scene(root));
		System.out.println("Go Back 2 Menu");
	}

}
