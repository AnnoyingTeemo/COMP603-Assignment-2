package Game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class EnemyList {
    ArrayList<Enemy> enemyList;

    //Holds a list of enemies
    public EnemyList() {
        this.enemyList = new ArrayList<Enemy>();
    }
    
    public Enemy GetEnemy(int position){
        return this.enemyList.get(position);
    }
    
    public int GetLength(){
        return this.enemyList.size();
    }
    
    //Iterates through file and adds each line as a new enemy to the enemy list
    public ArrayList<Enemy> LoadEnemies(Database db){
        enemyList.add(new Enemy("Wolf", 25, 10, 15, 2, 10, 15, db.GetItemByName("Claws"), db.GetItemByName("Howl"), db.GetItemByName("Regenerate"), db.GetItemByName("Growl")));
        enemyList.add(new Enemy("Bandit", 35, 15, 50, 1, 25, 15, db.GetItemByName("Greatsword"), db.GetItemByName("Dagger"),db.GetItemByName("Rally"), db.GetItemByName("Rage")));
        enemyList.add(new Enemy("Kobold", 23, 20, 0, 3, 25, 20, db.GetItemByName("Spear"), db.GetItemByName("Kobold Regeneration"), db.GetItemByName("Rally"), db.GetItemByName("Focus")));
        enemyList.add(new Enemy("Ooze", 30, 0, 50, 1, 0, 10, db.GetItemByName("Slam"), db.GetItemByName("Slime Toss"), db.GetItemByName("Regenerate"), db.GetItemByName("Absorb")));
        enemyList.add(new Enemy("Zombie", 29, 5, 25, 3, 0, 10, db.GetItemByName("Claws"), db.GetItemByName("Howl"), db.GetItemByName("Regenerate"), db.GetItemByName("Slam")));
        enemyList.add(new Enemy("Dragon", 50, 5, 50, 4, 0, 2, db.GetItemByName("Fire Breath"), db.GetItemByName("Claws"), db.GetItemByName("Claws"), db.GetItemByName("Claws")));
        
        return this.enemyList;
    }
}
