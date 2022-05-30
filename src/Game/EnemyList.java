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
        
        LoadEnemies();
        }
    
    public Enemy GetEnemy(int position){
        return this.enemyList.get(position);
    }
    
    public int GetLength(){
        return this.enemyList.size();
    }
    
    //Iterates through file and adds each line as a new enemy to the enemy list
    public void LoadEnemies(){
        ItemList itemList = new ItemList();
        
        enemyList.add(new Enemy("Wolf", 25, 10, 15, 2, 10, 15, itemList.GetItemByName("Claws"), itemList.GetItemByName("Howl"), itemList.GetItemByName("Regenerate"), itemList.GetItemByName("Growl")));
        enemyList.add(new Enemy("Bandit", 35, 15, 50, 1, 25, 15, itemList.GetItemByName("Greatsword"), itemList.GetItemByName("Dagger"),itemList.GetItemByName("Rally"), itemList.GetItemByName("Rage")));
        enemyList.add(new Enemy("Kobold", 23, 20, 0, 3, 25, 20, itemList.GetItemByName("Spear"), itemList.GetItemByName("Kobold Regeneration"), itemList.GetItemByName("Rally"), itemList.GetItemByName("Focus")));
        enemyList.add(new Enemy("Ooze", 30, 0, 50, 1, 0, 10, itemList.GetItemByName("Slam"), itemList.GetItemByName("Slime Toss"), itemList.GetItemByName("Regenerate"), itemList.GetItemByName("Absorb")));
        enemyList.add(new Enemy("Zombie", 29, 5, 25, 3, 0, 10, itemList.GetItemByName("Claws"), itemList.GetItemByName("Howl"), itemList.GetItemByName("Regenerate"), itemList.GetItemByName("Slam")));
        enemyList.add(new Enemy("Dragon", 50, 5, 50, 4, 0, 2, itemList.GetItemByName("Fire Breath"), itemList.GetItemByName("Claws"), itemList.GetItemByName("Claws"), itemList.GetItemByName("Claws")));
//        ArrayList<String> EnemyAbilities = new ArrayList<String>();
        
//        try{
//            FileReader fr = new FileReader("./Resources/EnemyList.txt");
//            BufferedReader inputStream = new BufferedReader(fr);
//            String line = null;
//            while ((line = inputStream.readLine())!= null){
//                EnemyAbilities.clear();
//                
//                for (String atribute: line.split(" ")){
//                    EnemyAbilities.add(atribute);
//                }
//                this.enemyList.add(new Enemy(EnemyAbilities.get(0).replace(" ","_"), Integer.valueOf(EnemyAbilities.get(1)), Integer.valueOf(EnemyAbilities.get(2)), Integer.valueOf(EnemyAbilities.get(3)), Integer.valueOf(EnemyAbilities.get(4)), Integer.valueOf(EnemyAbilities.get(5)), Integer.valueOf(EnemyAbilities.get(6)), itemList.GetItemByName(EnemyAbilities.get(7).replace(" ","_")), itemList.GetItemByName(EnemyAbilities.get(8).replace(" ","_")), itemList.GetItemByName(EnemyAbilities.get(9).replace(" ","_")), itemList.GetItemByName(EnemyAbilities.get(10).replace(" ","_"))));
//            }
//            inputStream.close();
//        }
//        catch(FileNotFoundException e){
//            System.out.println("File not found");
//        }
//        catch(IOException e){
//            System.out.println("Error reading from file");
//        }
    }
    
    public ArrayList<Enemy> getList(){
        return this.enemyList;
    }
}
