
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
public class DisplayMoves {
    private ArrayList<Item> itemList;

    //Itemlist holds all the items that a player has
    public DisplayMoves(Ally player) {
        this.itemList = new ArrayList<Item>();
        
        this.itemList.add(player.getClassDamage());
        this.itemList.add(player.getClassDefensive());
        this.itemList.add(player.getLeftHand());
        this.itemList.add(player.getRightHand());
    }

    //Hide old text with new lines and display player health and enemy health
    public String DisplayScreen(Ally player, Enemy enemy){
        String output = "";
        
        for (int i = 0; i < 50; i++){
            System.out.print("\n");
        }
        
        output += "Healh: " + player.getCurrentHealth() + "/" + player.getHealth() + "\n";
        output += enemy.getName() + " Healh: " + enemy.getCurrentHealth() + "/" + enemy.getHealth() + "\n";
        return output;
    }
    
    //display all available moves in a nice looking format
    public String Moves(){ 
        int numberOfSpaces;
        int spaceBetweenItems = 20;
        String output = "";
        
        
    
        output += this.itemList.get(0).getItemName();
        
        numberOfSpaces = spaceBetweenItems - this.itemList.get(0).getItemName().length();
        
        for (int i = 0; i < numberOfSpaces; i++){
            output += " ";
        }
        
        output += this.itemList.get(1).getItemName();
        
        output += "\n" + this.itemList.get(2).getItemName();
        
        numberOfSpaces = spaceBetweenItems - this.itemList.get(2).getItemName().length();
        
        for (int i = 0; i < numberOfSpaces; i++){
            output += " ";
        }
        
        output += this.itemList.get(3).getItemName();
        
        output += "\n";
        
        return output;
    }
    
    //Check if the spesified string is a move that the player has
    public boolean CheckContains(String move){
        if (this.itemList.get(0).getItemName().equals(move) || this.itemList.get(1).getItemName().equals(move) || this.itemList.get(2).getItemName().equals(move) || this.itemList.get(3).getItemName().equals(move)){
            return true;
        }
        else {
            return false;
        }
    }
    //Return an item to be used
    public Item useItem(String move){
        for (Item item : this.itemList){
            if (item.getItemName().equals(move)){
                return item;
            }
        }
        
        return this.itemList.get(0);
    }
}
