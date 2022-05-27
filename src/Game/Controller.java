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
    public Game game;

    public Controller(View view, Game game) {
        this.view = view;
        this.game = game;
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
        if (command.equals("Barbarian")){
            view.UnloadClassSelect();
            view.LoadWeaponSelect();
//            game.SetPlayerClass(new Barbarian());
        }
    }
}
