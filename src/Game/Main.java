package Game;


import java.util.Scanner;
import java.lang.Math;
import java.util.HashMap;
import java.util.HashSet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class Main {
    //runs the encounter, switches between whos turn it is
    public static Ally Encounter(Ally player, Enemy enemy){
        boolean enounterRunning = true;
        DisplayMoves displayMoves = new DisplayMoves(player);
        
        Scanner scan = new Scanner(System.in);
        GameLog.Log(enemy.getName() + " appears");
        
        GameLog.Log("\nType anything to continue");
        scan.next();
        GameLog.Log(displayMoves.DisplayScreen(player, enemy));
        
        while (enounterRunning) {
            GameLog.Log(displayMoves.DisplayScreen(player, enemy));
            GameLog.Log("Pick a move or type Exit to save and exit");
            GameLog.Log(displayMoves.Moves());
            
            String move = "";
            
            while (true) {
                move = scan.nextLine();

                if (displayMoves.CheckContains(move)){
                    break;
                }
                else if(move.contains("Exit")){
                    SaveLoadPlayer saveLoadPlayer = new SaveLoadPlayer();
                    SaveLoadEnemy saveLoadEnemy = new SaveLoadEnemy();
                    
                    saveLoadPlayer.Save(player);
                    saveLoadEnemy.Save(enemy);
                    
                    GameLog.Log("Exit");
                    
                    System.exit(0);
                }
            }
            GameLog.Log(move);
            
            Item usedItem = displayMoves.useItem(move);
            //System.out.println(enemy.getName() + "used: " + usedItem.getItemName());
            ApplyMove(usedItem, player, enemy, true);
            
            
            if (!enemy.Alive()){
                enounterRunning = false;
//                player.Heal(Math.floorDiv(enemy.getHealth(), 4));
                break;
            }
            
            ApplyMove(enemy.PickMove(), player, enemy, false);
            
            if (!player.Alive()){
                enounterRunning = false;
                GameLog.Log("You Lose");
                break;
            }
        }
        
        return player;
    }
    
    //once a move has been picked this will appply its effects, e.g. attempting damage or buffing self
    public static void ApplyMove(Item move, Ally player, Enemy enemy, Boolean playerTurn) {
        DisplayMoves displayMoves = new DisplayMoves(player);
        Scanner scan = new Scanner(System.in);
        
        //Player
        if (playerTurn){
            player.Passive(move);
            
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
                        GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                    }
                    else{
                        damage = damage * 2;
                        enemy.TakeDamage(damage);
                        GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                        GameLog.Log("Critted");
                        
                    }


                    GameLog.Log("Dealt " + damage + " damage");
                    
//                  System.out.println("Enemy Health: " + enemy.getCurrentHealth() + "\n");


                }
                else{
                    GameLog.Log(displayMoves.DisplayScreen(player, enemy));
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
                    GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                    GameLog.Log("Damage Buffed by: +" + buff);
                }
                else if (move.getBuffType() == BuffTypes.Buffs.DamageReduction){
                    player.BuffDefense(buff);
                    GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                    GameLog.Log("Damage Reduction Buffed by: +" + buff);

                }
                else{
                    player.Heal(buff);
                    GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                    GameLog.Log("Healed: " + buff);
                }
                
            }
            
            GameLog.Log("\nType anything to continue");
            scan.next();
        }
        //Enemy
        else{
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
                        GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                    }
                    else{
                        player.TakeDamage(damage);
                        GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                        GameLog.Log("Critted");
                        damage = damage * 2;
                    }


                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Dealt " + damage + " damage");
//                    System.out.println("Enemy Health: " + enemy.getCurrentHealth() + "\n");


                }
                else{
                    GameLog.Log(displayMoves.DisplayScreen(player, enemy));
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
                    GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Damage Buffed by: +" + buff);
                    
                }
                else if (move.getBuffType() == BuffTypes.Buffs.DamageReduction){
                    enemy.BuffDefense(buff);
                    GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Damage Reduction Buffed by: +" + buff);
                    
                }
                else{
                    enemy.Heal(buff);
                    GameLog.Log(displayMoves.DisplayScreen(player, enemy));
                    GameLog.Log(enemy.getName() + " used: " + move.getItemName() + " Healed: " + buff);
                    enemy.Heal(buff);
                }
            }
            GameLog.Log("\nType anything to continue");
            scan.next();
        }
    }
    
    //runs the game
    public static void main(String[] args) {
        GameLog.LogCount();
        SaveLoadPlayer saveLoadPlayer = new SaveLoadPlayer();
        SaveLoadEnemy saveLoadEnemy = new SaveLoadEnemy();
        ItemList itemList = new ItemList();
        Ally player;
        
        Scanner scan = new Scanner(System.in);
        
        String loadSave = "";
        HashSet<String> randomItemList = itemList.ReturnRandomList(6);
        
        //checks if there is a previous save and if so asks if wants to use that
        if (saveLoadPlayer.CheckSave()){
            GameLog.Log("Save detected, would you like to load or create a new character?");
            GameLog.Log("Please type Load or New");
            
            while (true) {
                loadSave = scan.nextLine();

                if (loadSave.contains("Load")){
                    player = saveLoadPlayer.Load();
                    GameLog.Log("Loaded Save");
                    break;
                }
                else if (loadSave.contains("New")){                    
                    GameLog.Log("Choose a class");
                    GameLog.Log("Paladin Monk Sorcerer or Barbarian");
                    GameLog.Log("Paladin gets buffs from alternating between attacks and buffs");
                    GameLog.Log("Monk gets buffed by using the same ability each round and then gets a big burst buff when they switch abilities");
                    GameLog.Log("Sorcerer alternates from a random buff to a random debuff");
                    GameLog.Log("Barbarian gets buffed from the more %missing health they have");
                    while (true) {                
                        String playerClass = scan.nextLine();
                        if (playerClass.contains("Paladin")){
                            Item item1 = pickItem(randomItemList);
                            Item item2 = pickItem(randomItemList);
                            player = new Paladin(item1, item2);
                            break;
                        }
                        else if (playerClass.contains("Monk")){
                            Item item1 = pickItem(randomItemList);
                            Item item2 = pickItem(randomItemList);
                            player = new Monk(item1, item2);
                            break;
                        }
                        else if (playerClass.contains("Sorcerer")){
                            Item item1 = pickItem(randomItemList);
                            Item item2 = pickItem(randomItemList);
                            player = new Sorcerer(item1, item2);
                            break;
                        }
                        else if (playerClass.contains("Barbarian")){
                            Item item1 = pickItem(randomItemList);
                            Item item2 = pickItem(randomItemList);
                            player = new Barbarian(item1, item2);
                            break;
                        }
                    }
                    break;
                }
            }
            saveLoadPlayer.ClearSave();
            
        }//creates a new playing using the class that was chosen by the player
        else{            
            GameLog.Log("Choose a class");
            GameLog.Log("Paladin Monk Sorcerer or Barbarian");
            GameLog.Log("Paladin gets buffs from alternating between attacks and buffs");
            GameLog.Log("Monk gets buffed by using the same ability each round and then gets a big burst buff when they switch abilities");
            GameLog.Log("Sorcerer alternates from a random buff to a random debuff");
            GameLog.Log("Barbarian gets buffed from the more %missing health they have");
            while (true) {                
                String playerClass = scan.nextLine();
                if (playerClass.contains("Paladin")){
                    Item item1 = pickItem(randomItemList);
                    Item item2 = pickItem(randomItemList);
                    player = new Paladin(item1, item2);
                    break;
                }
                else if (playerClass.contains("Monk")){
                    Item item1 = pickItem(randomItemList);
                    Item item2 = pickItem(randomItemList);
                    player = new Monk(item1, item2);
                    break;
                }
                else if (playerClass.contains("Sorcerer")){
                    Item item1 = pickItem(randomItemList);
                    Item item2 = pickItem(randomItemList);
                    player = new Sorcerer(item1, item2);
                    break;
                }
                else if (playerClass.contains("Barbarian")){
                    Item item1 = pickItem(randomItemList);
                    Item item2 = pickItem(randomItemList);
                    player = new Barbarian(item1, item2);
                    break;
                }
            }
        }
        
        
//        Ally player = saveLoadPlayer.Load();
        EnemyList enemyList = new EnemyList();
        //outputs some basic info on the player and then starts a battle
        GameLog.Log("Health: " + player.getHealth() + "/" + player.getCurrentHealth());
        GameLog.Log("Crit Chance: " + player.getCritChance());
        GameLog.Log("Damage Mod: " + player.getDamageModifier());
        
        GameLog.Log("Left Weapon Name: " + player.getLeftHand().getItemName());
        GameLog.Log("Right Weapon Name: " + player.getRightHand().getItemName());
        GameLog.Log("Class Attack: " + player.getClassDamage().getItemName());
        GameLog.Log("Class Defensive: " + player.getClassDefensive().getItemName());
        
        int roomsPassed = 0;
        
        //checks if there is an enemy save and loads that if there is, unless there is an enemy save but no player save in which case closes the game and fixes the problem by deleting the enemy save
        while(true){
        if (saveLoadEnemy.checkSave()){
            if (loadSave.contains("Load")){
                player = Encounter(player, saveLoadEnemy.Load());
                saveLoadEnemy.ClearSave();
            }
            else if (loadSave.contains("New")){
                saveLoadEnemy.ClearSave();
                player = Encounter(player, new Enemy(enemyList.GetEnemy(RandomEnemy(enemyList.GetLength()))));
            }
            else{
                GameLog.Log("Trying to load enemy while there is no player save");
                saveLoadEnemy.ClearSave();
                System.exit(0);
            }
        }
        else{
            player = Encounter(player, new Enemy(enemyList.GetEnemy(RandomEnemy(enemyList.GetLength()))));
        }
        player.resetAbilities();
        if (!player.Alive()){
            break;
        }
        else{
            roomsPassed += 1;
        }
        }
        
        GameLog.Log("You Survived " + roomsPassed + " rooms!!!");
    }
    
    //picks a random enemy
    public static int RandomEnemy(int enemyListLength){
        int max = enemyListLength -1;
        int min = 0;
        int range = max - min + 1;

        int enemy = (int)(Math.random() * range) + min;
        
        return enemy;
    }
    
    //takes a hashset of items and offers them to the player, the player can pick 2 items to use
    public static Item pickItem(HashSet<String> randomItemList){
        ItemList itemList = new ItemList();
        
        Scanner scan = new Scanner(System.in);
        GameLog.Log("Pick An Item");
        for (String item: randomItemList){
            String stats = "";
            if (itemList.GetItemByName(item).isDamageItem()){
                stats = item + ": Max " + (itemList.GetItemByName(item)).getDamageMaxRoll() + " " + (itemList.GetItemByName(item)).getDamageType() + " damage, Minimum " +  (itemList.GetItemByName(item)).getDamageMinRoll() + " " + (itemList.GetItemByName(item)).getDamageType() + " damage";
            }
            else{
                stats = item + ": Max " + (itemList.GetItemByName(item)).getBuffMaxRoll() + " " + (itemList.GetItemByName(item)).getBuffType() + ", minimum " +  (itemList.GetItemByName(item)).getBuffMinRoll() + " " + (itemList.GetItemByName(item)).getBuffType();
            }
            GameLog.Log(stats);
        }
        String input;
        while (true) {
            input = scan.nextLine();
            if (randomItemList.contains(input)){
                randomItemList.remove(input);
                break;
            }
        }
        Item item = itemList.GetItemByName(input);
        return item;
    }
}
