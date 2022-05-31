/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Owner
 */
public class Controller implements ActionListener{
    public View view;
    public Game game;
    private Database db;
    public ItemList itemList;

    private Item item1;
    private Item item2;
    
    private String playerClass = "";
    
    public Controller(View view, Game game, Database db) {
        this.view = view;
        this.game = game;
        this.view.addActionListener(this);
        this.itemList = new ItemList();
        this.db = db;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        
        if (command.equals("Start Game")){
            view.UnloadStartMenu();
            view.LoadClassSelect();
        }
        if (command.equals("Barbarian")){
            view.UnloadClassSelect();
            view.LoadWeaponSelect();
            playerClass = "Barbarian";
        }
        if (command.equals("Monk")){
            view.UnloadClassSelect();
            view.LoadWeaponSelect();
            playerClass = "Monk";
        }
        if (command.equals("Paladin")){
            view.UnloadClassSelect();
            view.LoadWeaponSelect();
            playerClass = "Paladin";
        }
        if (command.equals("Sorcerer")){
            view.UnloadClassSelect();
            view.LoadWeaponSelect();
            playerClass = "Sorcerer";
        }
        
        for (JButton button: view.weaponButtonList){
            if (command.equals(button.getText())){
                if (item1 == null){
                    item1 = db.GetItemByName(button.getText());
                    button.setVisible(false);
                }
                else{
                    item2 = db.GetItemByName(button.getText());
                    game.SetPlayerClass(selectClass());
                    view.UnloadWeaponSelect();
                    view.LoadGame();
                }
            }
        }
    }
    
    public Ally selectClass(){
        if (playerClass.equals("Barbarian")){
            return new Barbarian(item1, item2);
        }
        else if (playerClass.equals("Monk")){
            return new Monk(item1, item2);
        }
        else if (playerClass.equals("Paladin")){
            return new Paladin(item1, item2);
        }
        else if (playerClass.equals("Sorcerer")){
            return new Sorcerer(item1, item2);
        }
        else{
            return null;
        }
    }
}
