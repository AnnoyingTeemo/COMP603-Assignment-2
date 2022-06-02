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
        
        if (e.getSource() == view.saveNameInput){
            if (db.getSaveNameList().contains(command)){
                view.loadSaveButton.setEnabled(true);
            }
            else{
                view.loadSaveButton.setEnabled(false);
            }
        }
        
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
        
        for (JButton button: view.itemButtonList){
            if (command.equals(button.getText()) && view.gameLoaded){
                view.ResetOutputText();
                ApplyMove(db.GetItemByName(command), game.player, game.enemy, true);
                if (game.enemy.Alive()){
                    ApplyMove(game.enemy.PickMove(), game.player, game.enemy, false);
                }
                else{
                    game.RoomsPassedIncrease();
                    game.PickEnemy(db.getEnemyList());
                    view.addOutputText("You face a " + game.getEnemy().getName() + ":");
                }
                if (game.player.Alive()){
                    view.RefreshGame();
                }
                else{
                    view.UnloadGame();
                    view.GameOverScreen();
                }
            }
        }
        
        for (JButton button: view.weaponButtonList){
            if (command.equals(button.getText()) && !view.gameLoaded){
                if (item1 == null){
                    item1 = db.GetItemByName(button.getText());
                    button.setEnabled(false);
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
    
    //once a move has been picked this will appply its effects, e.g. attempting damage or buffing self
    public void ApplyMove(Item move, Ally player, Enemy enemy, Boolean playerTurn) {
        String output = "";
        
        //Player
        if (playerTurn){
            player.Passive(move);
            
            output = player.getClassString() + " used: " + move.getItemName() + "\n";
            view.addOutputText(output);
            
            if (move.isDamageItem()){
                int hitChance = (int)(Math.random() * 100) + 1;
                if (hitChance > enemy.getDodge()){
                    int max = move.getBaseDamageMaxRoll();
                    int min = move.getBaseDamageMinRoll();
                    int range = max - min + 1;

                    int damage = (int)(Math.random() * range) + min + player.getDamageModifier() + player.getPassiveBuff();
                    if (damage < 0){
                        damage = 0;
                    }
                    int critChance = (int)(Math.random() * 100) + 1;
                    if (critChance > player.getCritChance()){
                        damage = damage - (damage * enemy.getDamageReduction()/100);
                        enemy.TakeDamage(damage);
                    }
                    else{
                        damage = damage * 2;
                        enemy.TakeDamage(damage);
                        output = "Critted\n";
                        view.addOutputText(output);
                        GameLog.Log("Critted"); 
                    }
                    output = "Dealt " + damage + " damage";
                    view.addOutputText(output);
                    GameLog.Log("Dealt " + damage + " damage");
                }
                else{
                    output = "Attack Missed";
                    view.addOutputText(output);
                    GameLog.Log("Attack Missed");
                }
            }
            else{
                
                
                int max = move.getBaseBuffMaxRoll();
                int min = move.getBaseBuffMinRoll();
                int range = max - min + 1;

                int buff = (int)(Math.random() * range) + min + player.getPassiveBuff();
                if (buff < 0){
                    buff = 0;
                }
                if (move.getBuffType() == BuffTypes.Buffs.DamageBuff){
                    player.BuffDamage(buff);
                    output = "Damage Buffed by: +" + buff;
                    view.addOutputText(output);
                    GameLog.Log("Damage Buffed by: +" + buff);
                }
                else if (move.getBuffType() == BuffTypes.Buffs.DamageReduction){
                    player.BuffDefense(buff);
                    output = "Damage Reduction Buffed by: +" + buff;
                    view.addOutputText(output);
                    GameLog.Log("Damage Reduction Buffed by: +" + buff);
                }
                else{
                    player.Heal(buff);
                    output = "Healed: " + buff;
                    view.addOutputText(output);
                    GameLog.Log("Healed: " + buff);
                }   
            }
        }
        //Enemy
        else{
            output = enemy.getName()+ " used: " + move.getItemName() + "\n";
            view.addOutputText(output);
            
            if (move.isDamageItem()){
                int hitChance = (int)(Math.random() * 100) + 1;
                if (hitChance > player.getDodge()){
                    int max = move.getBaseDamageMaxRoll();
                    int min = move.getBaseDamageMinRoll();
                    int range = max - min + 1;

                    int damage = (int)(Math.random() * range) + min + enemy.getDamageModifier();

                    int critChance = (int)(Math.random() * 100) + 1;
                    if (critChance > enemy.getCritChance()){
                        damage = damage - (damage * player.getDamageReduction()/100);
                        player.TakeDamage(damage);
                    }
                    else{
                        player.TakeDamage(damage);
                        output = "Critted";
                        view.addOutputText(output);
                        GameLog.Log("Critted");
                        damage = damage * 2;
                    }
                    output = "Dealt " + damage + " damage";
                    view.addOutputText(output);    
                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Dealt " + damage + " damage");
                }
                else{
                    output = "Attack Missed";
                    view.addOutputText(output);
                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Attack Missed");
                }

            }
            else{
                int max = move.getBaseBuffMaxRoll();
                int min = move.getBaseBuffMinRoll();
                int range = max - min + 1;

                int buff = (int)(Math.random() * range) + min;

                if (move.getBuffType() == BuffTypes.Buffs.DamageBuff){
                    enemy.BuffDamage(buff);
                    output = "Damage Buffed by: +" + buff;
                    view.addOutputText(output);
                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Damage Buffed by: +" + buff);
                    
                }
                else if (move.getBuffType() == BuffTypes.Buffs.DamageReduction){
                    enemy.BuffDefense(buff);
                    output = "Damage Reduction Buffed by: +" + buff;
                    view.addOutputText(output);
                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Damage Reduction Buffed by: +" + buff);
                    
                }
                else{
                    enemy.Heal(buff);
                    output = "Healed: " + buff;
                    view.addOutputText(output);
                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Healed: " + buff);
                }
            }
        }
    }
}
