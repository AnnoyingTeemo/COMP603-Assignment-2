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
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class View extends JFrame{

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
    
    public View() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        this.title.setFont(font);
        this.title.setSize(150, 150);
        this.title.setLocation(200, 0);
        
        this.startButton.setSize(75,30);
        this.startButton.setText("START");
        this.startButton.setLocation(205, 150);
        
        this.backgroundImage.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../Images/beans.jpg")).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT)));
        this.backgroundImage.setSize(500, 500);
        
        this.startScreen.add(title);
        this.startScreen.add(startButton);
        this.startScreen.add(backgroundImage);
        
//        this.startScreen.setComponentZOrder(title, 1);
//        this.startScreen.setComponentZOrder(backgroundImage, 0);
        
        this.add(startScreen);
        this.setVisible(true);
        System.out.println("AMONG US");
    }
    
    public void UnloadStartMenu(){
        
    }
    
    public void LoadClassSelect(){
        this.barbarian.setSize(75, 30);
        this.monk.setSize(75, 30);
        this.paladin.setSize(75, 30);
        this.sorcerer.setSize(75, 30);
        
        this.barbarian.setLocation(100, 300);
    }
    
}
