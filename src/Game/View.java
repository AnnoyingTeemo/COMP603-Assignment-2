package Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View extends JFrame{

    private GameLog log;
    private ItemList itemList;
    private Database db;
    private Model model;
    private Game game;
    
    private JLayeredPane startScreen = new JLayeredPane();
    private JLabel title = new JLabel("FUNNY GAME NAME THE GAME");
    
    private JLabel backgroundImage = new JLabel();
    private JButton startButton = new JButton("Start Game");
    public JButton loadSaveButton = new JButton("Load Save");
    public JTextField saveNameInput = new JTextField("Test");
    
    //Select class
    private JLayeredPane classSelect = new JLayeredPane();
    private JButton barbarian = new JButton("Barbarian");
    private JButton monk = new JButton("Monk");
    private JButton paladin = new JButton("Paladin");
    private JButton sorcerer = new JButton("Sorcerer");
    
    //Select weapons
    private JLayeredPane weaponsSelect = new JLayeredPane();
    public JButton weapon1 = new JButton("BUTTON 1");
    public JButton weapon2 = new JButton("BUTTON 2");
    public JButton weapon3 = new JButton("BUTTON 3");
    public JButton weapon4 = new JButton("BUTTON 4");
    public JButton weapon5 = new JButton("BUTTON 5");
    public JButton weapon6 = new JButton("BUTTON 6");
    public ArrayList<JButton> weaponButtonList = new ArrayList<JButton>();
    
    private JLayeredPane gamePane = new JLayeredPane();
    private JLabel enemyName = new JLabel("Enemy Name");
    private JLabel playerName = new JLabel("Player Name");
    private JLabel enemyHealth = new JLabel("Enemy Health");
    private JLabel playerHealth = new JLabel("Player Health");
    private JButton Item1 = new JButton("Item 1");
    private JButton Item2 = new JButton("Item 2");
    private JButton Item3 = new JButton("Item 3");
    private JButton Item4 = new JButton("Item 4");
    public ArrayList<JButton> itemButtonList = new ArrayList<JButton>();
    private JLabel outputText = new JLabel();
    private String text = "";
    
    private JLabel gameOverText = new JLabel("");
    private JLayeredPane gameOverPane = new JLayeredPane();
    
    public boolean gameLoaded = false;
    
    public View(Model model) {
        this.model = model;
        this.db = model.getDb();
        this.game = model.getGame();
        
        this.log = new GameLog();
        this.log.LogCount();
        this.itemList = new ItemList();
        
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Font font = new Font("Comic Sans", Font.BOLD, 20);
        this.title.setFont(font);
        this.title.setSize(350, 150);
        this.title.setLocation(150, 0);
        
        this.startButton.setSize(100,30);
        this.startButton.setText("Start Game");
        this.startButton.setLocation(160, 150);
        
        this.loadSaveButton.setSize(100, 30);
        this.loadSaveButton.setText("Load Save");
        this.loadSaveButton.setLocation(310, 150);
        
        this.saveNameInput.setSize(250, 30);
        this.saveNameInput.setLocation(160, 200);
        this.saveNameInput.setEnabled(false);
        
        this.backgroundImage.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../Images/beans.jpg")).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT)));
        this.backgroundImage.setSize(600, 600);
        
        this.startScreen.add(title);
        this.startScreen.add(startButton);
        this.startScreen.add(loadSaveButton);
        this.startScreen.add(saveNameInput);
        this.startScreen.add(backgroundImage);

        this.add(startScreen);
        this.setVisible(true);
        
        this.weaponButtonList.add(weapon1);
        this.weaponButtonList.add(weapon2);
        this.weaponButtonList.add(weapon3);
        this.weaponButtonList.add(weapon4);
        this.weaponButtonList.add(weapon5);
        this.weaponButtonList.add(weapon6);
        
        this.itemButtonList.add(Item1);
        this.itemButtonList.add(Item2);
        this.itemButtonList.add(Item3);
        this.itemButtonList.add(Item4);
    }
    
    public void UnloadStartMenu(){
        log.Log("Start Menu unloaded");
        this.startScreen.setVisible(false);
        db.saveName = this.saveNameInput.getText();
    }
    
    public void LoadClassSelect(){
        log.Log("Character select loaded");
        
        this.barbarian.setSize(100, 30);
        this.monk.setSize(100, 30);
        this.paladin.setSize(100, 30);
        this.sorcerer.setSize(100, 30);
        
        this.barbarian.setLocation(50, 300);
        this.monk.setLocation(175, 300);
        this.paladin.setLocation(300,300);
        this.sorcerer.setLocation(425,300);
        
        this.classSelect.add(barbarian);
        this.classSelect.add(monk);
        this.classSelect.add(paladin);
        this.classSelect.add(sorcerer);
        
        this.add(classSelect);
    }
    
    public void UnloadClassSelect(){
        log.Log("Character Select Unloaded");
        this.classSelect.setVisible(false);
    }
    
    public void LoadWeaponSelect(){
        log.Log("Weapon Select loaded");
        
        this.weapon1.setSize(140, 30);
        this.weapon2.setSize(140, 30);
        this.weapon3.setSize(140, 30);
        this.weapon4.setSize(140, 30);
        this.weapon5.setSize(140, 30);
        this.weapon6.setSize(140, 30);
        
        this.weapon1.setLocation(25, 200);
        this.weapon2.setLocation(225, 200);
        this.weapon3.setLocation(425, 200);
        this.weapon4.setLocation(25, 300);
        this.weapon5.setLocation(225, 300);
        this.weapon6.setLocation(425, 300);
        
        
        db.setupItemLists();
        ArrayList<String> randomItems = db.ReturnRandomList(6);
        
        for (int i = 0; i < weaponButtonList.size(); i++){
            weaponButtonList.get(i).setText(randomItems.get(i));
        }
        
        this.weaponsSelect.add(weapon1);
        this.weaponsSelect.add(weapon2);
        this.weaponsSelect.add(weapon3);
        this.weaponsSelect.add(weapon4);
        this.weaponsSelect.add(weapon5);
        this.weaponsSelect.add(weapon6);
        this.add(weaponsSelect);
    }
    
    public void UnloadWeaponSelect(){
        log.Log("Weapon Select unloaded");
        this.weaponsSelect.setVisible(false);
    }
    
    public void LoadGame(){
        log.Log("Game Loaded");
        gameLoaded = true;
        
        this.enemyName.setSize(200, 200);
        this.enemyName.setText(game.getEnemy().getName());
        this.enemyName.setLocation(500, -50);
        this.enemyName.setFont(enemyName.getFont().deriveFont(20f));
        
        this.enemyHealth.setSize(200,200);
        int enemyCurrentHealthText = game.getEnemy().getCurrentHealth();
        int enemyHealthText = game.getEnemy().getHealth();
        this.enemyHealth.setText(String.valueOf(enemyCurrentHealthText) + "/" + String.valueOf(enemyHealthText));
        this.enemyHealth.setLocation(500, -25);
        this.enemyHealth.setFont(enemyName.getFont());
        
        this.playerName.setSize(200, 200);
        this.playerName.setText(game.getPlayer().getClassString());
        this.playerName.setLocation(25, 225);
        this.playerName.setFont(playerName.getFont().deriveFont(20f));
        
        this.playerHealth.setSize(200,200);
        int playerCurrentHealthText = game.getPlayer().getCurrentHealth();
        int playerHealthText = game.getPlayer().getHealth();
        this.playerHealth.setText(String.valueOf(playerCurrentHealthText) + "/" + String.valueOf(playerHealthText));
        this.playerHealth.setLocation(25, 250);
        this.playerHealth.setFont(playerName.getFont());
        
        for (JButton button : itemButtonList){
            button.setSize(140, 30);
            this.gamePane.add(button);
        }
        this.Item1.setLocation(150, 400);
        this.Item2.setLocation(150, 500);
        this.Item3.setLocation(300, 400);
        this.Item4.setLocation(300, 500);
        
        this.Item1.setText(game.getPlayer().getClassDamage().getItemName());
        this.Item2.setText(game.getPlayer().getClassDefensive().getItemName());
        this.Item3.setText(game.getPlayer().getLeftHand().getItemName());
        this.Item4.setText(game.getPlayer().getRightHand().getItemName());
        
        text = "You face a " + game.getEnemy().getName() + ":";
        this.outputText.setSize(400, 400);
        this.outputText.setLocation(200, 0);
        this.outputText.setFont(outputText.getFont().deriveFont(20f));
        this.outputText.setText(text);
        
        this.gamePane.add(enemyName);
        this.gamePane.add(enemyHealth);
        
        this.gamePane.add(playerName);
        this.gamePane.add(playerHealth);
        this.gamePane.add(outputText);
        
        this.add(gamePane);
        this.gamePane.setVisible(true);
    }
    
    public void UnloadGame(){
        log.Log("Game Unloaded");
        gameLoaded = false;
        this.gamePane.setVisible(false);
    }
    
    public void RefreshGame(){
        this.enemyName.setText(game.getEnemy().getName());
        int enemyCurrentHealthText = game.getEnemy().getCurrentHealth();
        int enemyHealthText = game.getEnemy().getHealth();
        this.enemyHealth.setText(String.valueOf(enemyCurrentHealthText) + "/" + String.valueOf(enemyHealthText));
        
        int playerCurrentHealthText = game.getPlayer().getCurrentHealth();
        int playerHealthText = game.getPlayer().getHealth();
        this.playerHealth.setText(String.valueOf(playerCurrentHealthText) + "/" + String.valueOf(playerHealthText));
        
        this.outputText.setText(text);
        
        this.gamePane.setVisible(true);
    }
    
    public void addActionListener(ActionListener listener){
        this.startButton.addActionListener(listener);
        this.loadSaveButton.addActionListener(listener);
        
        this.saveNameInput.addActionListener(listener);
        
        this.barbarian.addActionListener(listener);
        this.monk.addActionListener(listener);
        this.paladin.addActionListener(listener);
        this.sorcerer.addActionListener(listener);
        
        for (int i = 0; i < weaponButtonList.size(); i++){
            weaponButtonList.get(i).addActionListener(listener);
        }
        for (JButton button : itemButtonList){
            button.addActionListener(listener);
        }
    }
    
    public void GameOverScreen(){
        gameOverText.setSize(500, 200);
        gameOverText.setLocation(150, 100);
        gameOverText.setText("Game Over: " + game.getRoomsPassed() + " Monsters Defeated");
        this.gameOverText.setFont(gameOverText.getFont().deriveFont(20f));
        gameOverPane.add(gameOverText);
        this.add(gameOverPane);
        this.gameOverPane.setVisible(true);
    }

    public void addOutputText(String text){
        this.text += text + "<br>";
    }
    
    public void ResetOutputText(){
        this.text = "<html>";
    }
}
