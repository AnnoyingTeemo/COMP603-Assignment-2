package Game;


import java.util.ArrayList;
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
public class ItemList {
    private ArrayList<Item> itemList;
    private int unuseableWeapons = 20;

    public ItemList() {
        this.itemList = new ArrayList<Item>();
        
        SetupList();
    }
    //Adds all items to the list of items
    public void SetupList(){
        this.itemList.add(new DamageItem("Nothing", 0, 0, DamageTypes.DamageType.Water, 0));
        this.itemList.add(new DamageItem("Smite", 6, 3, DamageTypes.DamageType.Holy, 0));
        this.itemList.add(new DefensiveItem("Healing Touch", BuffTypes.Buffs.Heal, 6, 1, 0));
        this.itemList.add(new DamageItem("Punch", 4, 3, DamageTypes.DamageType.Blunt, 0));
        this.itemList.add(new DefensiveItem("Prepare Body", BuffTypes.Buffs.DamageReduction, 6, 1, 0));
        this.itemList.add(new DamageItem("Fireball", 10, 1, DamageTypes.DamageType.Fire, 0));
        this.itemList.add(new DefensiveItem("Shield Spell", BuffTypes.Buffs.DamageReduction, 6, 1, 0));
        this.itemList.add(new DamageItem("Rage", 8, 6, DamageTypes.DamageType.Lightning, 0));
        this.itemList.add(new DamageItem("Reckless Attack", 12, 1, DamageTypes.DamageType.Blunt, 0));
        
        this.itemList.add(new DamageItem("Howl", 4, 1, DamageTypes.DamageType.Sound, 0));
        this.itemList.add(new DefensiveItem("Regenerate", BuffTypes.Buffs.Heal, 10, 1, 0));
        this.itemList.add(new DefensiveItem("Growl", BuffTypes.Buffs.DamageBuff, 10, 1, 0));
        this.itemList.add(new DefensiveItem("Kobold Regeneration", BuffTypes.Buffs.Heal, 10, 0, 0));
        this.itemList.add(new DefensiveItem("Rally", BuffTypes.Buffs.DamageReduction, 10, 1, 0));
        this.itemList.add(new DefensiveItem("Focus", BuffTypes.Buffs.DamageBuff, 4, 1, 0));
        this.itemList.add(new DamageItem("Slam", 10, 1, DamageTypes.DamageType.Blunt, 0));
        this.itemList.add(new DamageItem("Slime Toss", 6, 2, DamageTypes.DamageType.Holy, 0));
        this.itemList.add(new DefensiveItem("Absorb", BuffTypes.Buffs.Heal, 6, 3, 0));
        this.itemList.add(new DefensiveItem("Regenerate", BuffTypes.Buffs.Heal, 10, 0, 0));
        this.itemList.add(new DamageItem("Fire Breath", 12, 1, DamageTypes.DamageType.Fire, 0));
                
        this.itemList.add(new DefensiveItem("Shield", BuffTypes.Buffs.DamageReduction, 10, 1, 1));
        this.itemList.add(new DefensiveItem("Healing Potion", BuffTypes.Buffs.Heal, 6, 1, 1));
        this.itemList.add(new DefensiveItem("Strength Potion", BuffTypes.Buffs.DamageBuff, 6, 1, 1));
        this.itemList.add(new DamageItem("Sword", 8, 1, DamageTypes.DamageType.Slash, 1));
        this.itemList.add(new DamageItem("Spear", 10, 1, DamageTypes.DamageType.Poke, 1));
        this.itemList.add(new DamageItem("Claws", 4, 1, DamageTypes.DamageType.Slash, 1));
        this.itemList.add(new DamageItem("Fire Sword", 8, 1, DamageTypes.DamageType.Fire, 1));
        
    }
    
    public Item GetItem(int position){
        return this.itemList.get(position);
    }
    
    public String PrintItem(int position){
        String tempString = "";
        Item tempItem = this.itemList.get(position);
        tempString += "Item Name: " + tempItem.getItemName();
        return tempString;
    }
    
    public Item GetItemByName(String name){
        for(Item item : this.itemList){
            if (item.getItemName().equals(name)){
                return item;
            }
        }
        
        return this.itemList.get(0);
    }
    //Returns a random hashset of items to be used in item selection by the player
    public ArrayList<String> ReturnRandomList(int numberOfItems){
        ArrayList<String> itemArrayList = new ArrayList<String>();
        
        if (numberOfItems > this.itemList.size() - unuseableWeapons){
            numberOfItems = this.itemList.size() - 1;
        }
        
        while (itemArrayList.size() < numberOfItems){
            int max = this.itemList.size() -1 ;
            int min = unuseableWeapons;
            int range = max - min + 1;

            Item tryItem = this.itemList.get((int)(Math.random() * range) + min);

            if (!itemArrayList.contains(tryItem.getItemName())){
                itemArrayList.add(tryItem.getItemName());
            }
        }
        
        return itemArrayList;
    }
    
    public ArrayList<Item> getList(){
        return this.itemList;
    }
}
