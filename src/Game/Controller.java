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
    public ItemList itemList;

    private Item item1;
    private Item item2;
    
    private String playerClass = "";
    
    public Controller(View view, Game game) {
        this.view = view;
        this.game = game;
        this.view.addActionListener(this);
        this.itemList = new ItemList();
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
        
        
        if (command.equals(view.weapon1.getText())){
            if (item1 == null){
                item1 = itemList.GetItemByName(view.weapon1.getText());
                view.weapon1.setVisible(false);
            }
            else{
                item2 = itemList.GetItemByName(view.weapon1.getText());
                game.SetPlayerClass(selectClass());
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
