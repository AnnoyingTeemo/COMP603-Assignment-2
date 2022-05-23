package Game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SaveLoadEnemy {
    private Enemy enemy;
    
    //load an enemy from a file
    public Enemy Load(){
        ArrayList<String> enemyAtributes = new ArrayList<String>();
        
        try{
            FileReader fr = new FileReader("./Resources/EnemySave.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            while ((line = inputStream.readLine())!= null){
                for (String atribute: line.split(" ")){
                    enemyAtributes.add(atribute);
                }
            }
            inputStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Error reading from file");
        }
        ItemList itemList = new ItemList();
        
        enemy = new Enemy(enemyAtributes.get(0), Integer.valueOf(enemyAtributes.get(1)), Integer.valueOf(enemyAtributes.get(2)), Integer.valueOf(enemyAtributes.get(3)), Integer.valueOf(enemyAtributes.get(4)), Integer.valueOf(enemyAtributes.get(5)), Integer.valueOf(enemyAtributes.get(6)), Integer.valueOf(enemyAtributes.get(7)), itemList.GetItemByName(enemyAtributes.get(8).replace("_", " ")), itemList.GetItemByName(enemyAtributes.get(9).replace("_", " ")), itemList.GetItemByName(enemyAtributes.get(10).replace("_", " ")), itemList.GetItemByName(enemyAtributes.get(11).replace("_", " ")), Integer.valueOf(enemyAtributes.get(12)), Integer.valueOf(enemyAtributes.get(13)));
        
        return enemy;
    }
    
    //save an enemy to file
    public void Save(Enemy enemy){        
        String output = "";
        
        output += enemy.getName() + " ";
        output += enemy.getHealth() + " ";
        output += enemy.getDodge() + " ";
        output += enemy.getDamageReduction() + " ";
        output += enemy.getDamageModifier() + " ";
        output += enemy.getSpeed() + " ";
        output += enemy.getCritChance() + " ";
        output += enemy.getCurrentHealth() + " ";
        
        output += enemy.getItem1().getItemName().replace(" ", "_") + " ";
        output += enemy.getItem2().getItemName().replace(" ", "_") + " ";
        output += enemy.getItem3().getItemName().replace(" ", "_") + " ";
        output += enemy.getItem4().getItemName().replace(" ", "_") + " ";
        output += enemy.getDamageReductionBoost() + " ";
        output += enemy.getDamageBoost();
        
        
        PrintWriter pw = null;
        
        try {
            pw = new PrintWriter(new FileOutputStream("./Resources/EnemySave.txt"));
            pw.print(output);
            pw.close();
        }
        catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
        }
    }
    
    //check if an enemy save exists
    public boolean checkSave(){
        String string = "";
        
        try{
            FileReader fr = new FileReader("./Resources/EnemySave.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            while ((line = inputStream.readLine())!= null){
                string += line;
            }
            inputStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Error reading from file");
        }
        if (string.length() == 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    //clear old saves
    public void ClearSave(){
        PrintWriter pw = null;
        
        try {
            pw = new PrintWriter(new FileOutputStream("./Resources/EnemySave.txt"));
            pw.print("");
            pw.close();
        }
        catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
        }
    }
}
