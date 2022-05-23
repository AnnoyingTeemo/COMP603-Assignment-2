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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame{

    private JPanel userPanel = new JPanel();
    private JLabel uName = new JLabel("Username: ");
    
    public View() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.userPanel.add(uName);
        this.add(userPanel);
        this.setVisible(true);
        System.out.println("AMONG US");
    }
    
}
