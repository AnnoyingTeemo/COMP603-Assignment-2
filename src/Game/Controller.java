/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Owner
 */
public class Controller implements ActionListener{
    public View view;

    public Controller(View view) {
        this.view = view;
        this.view.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        
        System.out.println(command);
        
        if (command.equals("Start Game")){
            view.UnloadStartMenu();
            view.LoadClassSelect();
        }
    }
}
