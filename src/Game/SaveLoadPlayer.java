package Game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class SaveLoadPlayer {
    
    private Ally player;
    
    //load a player from a file
    public Ally Load(){
        ArrayList<String> playerAtributes = new ArrayList<String>();
        
        try{
            FileReader fr = new FileReader("./Resources/PlayerSave.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line = null;
            while ((line = inputStream.readLine())!= null){
                for (String atribute: line.split(" ")){
                    playerAtributes.add(atribute);
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
        if(playerAtributes.get(0).equals("Paladin")){
            player = new Paladin(Integer.valueOf(playerAtributes.get(1)), Integer.valueOf(playerAtributes.get(2)), Integer.valueOf(playerAtributes.get(3)), Integer.valueOf(playerAtributes.get(4)), Integer.valueOf(playerAtributes.get(5)), Integer.valueOf(playerAtributes.get(6)), Integer.valueOf(playerAtributes.get(7)), Integer.valueOf(playerAtributes.get(8)), Integer.valueOf(playerAtributes.get(9)), Integer.valueOf(playerAtributes.get(10)), Integer.valueOf(playerAtributes.get(11)), Integer.valueOf(playerAtributes.get(12)), Integer.valueOf(playerAtributes.get(13)), itemList.GetItemByName(playerAtributes.get(14).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(15).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(16).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(17).replace("_", " ")), Integer.valueOf(playerAtributes.get(18)), Integer.valueOf(playerAtributes.get(19)), Integer.valueOf(playerAtributes.get(20)), Integer.valueOf(playerAtributes.get(21)));
        }
        else if(playerAtributes.get(0).equals("Monk")){     
            player = new Monk(Integer.valueOf(playerAtributes.get(1)), Integer.valueOf(playerAtributes.get(2)), Integer.valueOf(playerAtributes.get(3)), Integer.valueOf(playerAtributes.get(4)), Integer.valueOf(playerAtributes.get(5)), Integer.valueOf(playerAtributes.get(6)), Integer.valueOf(playerAtributes.get(7)), Integer.valueOf(playerAtributes.get(8)), Integer.valueOf(playerAtributes.get(9)), Integer.valueOf(playerAtributes.get(10)), Integer.valueOf(playerAtributes.get(11)), Integer.valueOf(playerAtributes.get(12)), Integer.valueOf(playerAtributes.get(13)), itemList.GetItemByName(playerAtributes.get(14).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(15).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(16).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(17).replace("_", " ")), Integer.valueOf(playerAtributes.get(18)), Integer.valueOf(playerAtributes.get(19)), Integer.valueOf(playerAtributes.get(20)), Integer.valueOf(playerAtributes.get(21)));
        }
        else if (playerAtributes.get(0).equals("Sorcerer")){
            player = new Sorcerer(Integer.valueOf(playerAtributes.get(1)), Integer.valueOf(playerAtributes.get(2)), Integer.valueOf(playerAtributes.get(3)), Integer.valueOf(playerAtributes.get(4)), Integer.valueOf(playerAtributes.get(5)), Integer.valueOf(playerAtributes.get(6)), Integer.valueOf(playerAtributes.get(7)), Integer.valueOf(playerAtributes.get(8)), Integer.valueOf(playerAtributes.get(9)), Integer.valueOf(playerAtributes.get(10)), Integer.valueOf(playerAtributes.get(11)), Integer.valueOf(playerAtributes.get(12)), Integer.valueOf(playerAtributes.get(13)), itemList.GetItemByName(playerAtributes.get(14).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(15).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(16).replace("_", " ")), itemList.GetItemByName(playerAtributes.get(17).replace("_", " ")), Integer.valueOf(playerAtributes.get(18)), Integer.valueOf(playerAtributes.get(19)), Integer.valueOf(playerAtributes.get(20)), Integer.valueOf(playerAtributes.get(21)));
        }
        player.Load();
        
        return player;
    }
    
    //save a player to a file
    public void Save(Ally player){
        player.Save();
        
        String output = "";
        
        output += player.getClassString()+ " ";
        output += player.getBaseHealth()+ " ";
        output += player.getBaseDodge()+ " ";
        output += player.getBaseDamageReduction()+ " ";
        output += player.getBaseDamageModifier()+ " ";
        output += player.getBaseSpeed()+ " ";
        output += player.getBaseCritChance()+ " ";
        output += player.getHealth()+ " ";
        output += player.getDodge()+ " ";
        output += player.getDamageReduction()+ " ";
        output += player.getDamageModifier()+ " ";
        output += player.getSpeed()+ " ";
        output += player.getCritChance()+ " ";
        output += player.getCurrentHealth()+ " ";
        
        output += player.getLeftHand().getItemName().replace(" ", "_")+ " ";
        output += player.getRightHand().getItemName().replace(" ", "_")+ " ";
        output += player.getClassDamage().getItemName().replace(" ", "_")+ " ";
        output += player.getClassDefensive().getItemName().replace(" ", "_")+ " ";
        
        output += player.getLevel()+ " ";
        output += player.getDamageReductionBoost()+ " ";
        output += player.getDamageBoost()+ " ";
        output += player.getPassiveBuff()+ " ";
        
        
        PrintWriter pw = null;
        
        try {
            pw = new PrintWriter(new FileOutputStream("./Resources/PlayerSave.txt"));
            pw.print(output);
            pw.close();
        }
        catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
        }
    }
    
    //check if a save exists for a player
    public boolean CheckSave(){
        String string = "";
        
        try{
            FileReader fr = new FileReader("./Resources/PlayerSave.txt");
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
    
    //delete old saves
    public void ClearSave(){
        PrintWriter pw = null;
        
        try {
            pw = new PrintWriter(new FileOutputStream("./Resources/PlayerSave.txt"));
            pw.print("");
            pw.close();
        }
        catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
        }
        
        pw = null;
        
        try {
            pw = new PrintWriter(new FileOutputStream("./Resources/ClassSave.txt"));
            pw.print("");
            pw.close();
        }
        catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
        }
    }
}
