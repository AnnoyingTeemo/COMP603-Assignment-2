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
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class View extends JFrame{

    private GameLog log;
    
    private JLayeredPane startScreen = new JLayeredPane();
    private JLabel title = new JLabel("Game Name");
    
    private JLabel backgroundImage = new JLabel();
    private JButton startButton = new JButton("Start Game");
    
    //Select class
    private JLayeredPane classSelect = new JLayeredPane();
    private JButton barbarian = new JButton("Barbarian");
    private JButton monk = new JButton("Monk");
    private JButton paladin = new JButton("Paladin");
    private JButton sorcerer = new JButton("Sorcerer");
    
    //Select weapons
    private JLayeredPane weaponsSelect = new JLayeredPane();
    private JButton weapon1 = new JButton("BUTTON 1");
    private JButton weapon2 = new JButton("BUTTON 2");
    private JButton weapon3 = new JButton("BUTTON 3");
    private JButton weapon4 = new JButton("BUTTON 4");
    private JButton weapon5 = new JButton("BUTTON 5");
    private JButton weapon6 = new JButton("BUTTON 6");
    
    public View() {
        this.log = new GameLog();
        this.log.LogCount();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        this.title.setFont(font);
        this.title.setSize(150, 150);
        this.title.setLocation(235, 0);
        
        this.startButton.setSize(100,30);
        this.startButton.setText("Start Game");
        this.startButton.setLocation(235, 150);
        
        this.backgroundImage.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../Images/beans.jpg")).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT)));
        this.backgroundImage.setSize(600, 600);
        
        this.startScreen.add(title);
        this.startScreen.add(startButton);
        this.startScreen.add(backgroundImage);
        
//        this.startScreen.setComponentZOrder(title, 1);
//        this.startScreen.setComponentZOrder(backgroundImage, 0);
        
        this.add(startScreen);
        this.setVisible(true);
    }
    
    public void UnloadStartMenu(){
        log.Log("Start Menu unloaded");
        this.startScreen.setVisible(false);
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
        log.Log("Weapon Select Unloaded");
    }
    
    public void addActionListener(ActionListener listener){
        this.startButton.addActionListener(listener);
        this.barbarian.addActionListener(listener);
        this.monk.addActionListener(listener);
        this.paladin.addActionListener(listener);
        this.sorcerer.addActionListener(listener);
    }
    
//    @Override
//    public void update(Observable o, Object arg){
//        
//    }
}
