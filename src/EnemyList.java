
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
        ArrayList<String> EnemyAbilities = new ArrayList<String>();
        
        try{
            FileReader fr = new FileReader("./Resources/EnemyList.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            while ((line = inputStream.readLine())!= null){
                EnemyAbilities.clear();
                
                for (String atribute: line.split(" ")){
                    EnemyAbilities.add(atribute);
                }
                this.enemyList.add(new Enemy(EnemyAbilities.get(0).replace(" ","_"), Integer.valueOf(EnemyAbilities.get(1)), Integer.valueOf(EnemyAbilities.get(2)), Integer.valueOf(EnemyAbilities.get(3)), Integer.valueOf(EnemyAbilities.get(4)), Integer.valueOf(EnemyAbilities.get(5)), Integer.valueOf(EnemyAbilities.get(6)), itemList.GetItemByName(EnemyAbilities.get(7).replace(" ","_")), itemList.GetItemByName(EnemyAbilities.get(8).replace(" ","_")), itemList.GetItemByName(EnemyAbilities.get(9).replace(" ","_")), itemList.GetItemByName(EnemyAbilities.get(10).replace(" ","_"))));
            }
            inputStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Error reading from file");
        }
    }
}
