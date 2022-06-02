package Game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class Monk extends Ally{
    int repeatedUseCount = 0;
    Item lastUsedItem = new DamageItem("Nothing", 0, 0, DamageTypes.DamageType.Water, 0);
    
    //Creating a new monk
    public Monk(Item leftHand, Item rightHand){
        super(40, 15, 0, 0, 0, 5, leftHand, rightHand, new DamageItem("Punch", 4, 3, DamageTypes.DamageType.Blunt, 0), new DefensiveItem("Prepare Body", BuffTypes.Buffs.DamageReduction, 6, 1, 0), "Monk");
        calculateAbilities();
    }
    
    //Creating a monk from save file
    public Monk(int baseHealth, int baseDodge, int baseDamageReduction, int baseDamageModifier, int baseSpeed, int baseCritChance, int health, int dodge, int damageReduction, int damageModifier, int speed, int critChance, int currentHealth, Item leftHand, Item rightHand, Item classDamage, Item classDefensive, int level, int damageReductionBoost, int damageBoost, int passiveBuff) {
        super(baseHealth, baseDodge, baseDamageReduction, baseDamageModifier, baseSpeed, baseCritChance, health, dodge, damageReduction, damageModifier, speed, critChance, currentHealth, leftHand, rightHand, classDamage, classDefensive, level, damageReductionBoost, damageBoost, passiveBuff, "Monk");
    }
    
    //The passive ability of the monk gets stronger the more it uses the same move
    @Override
    public void Passive(Item item){
        if (item.getItemName() == lastUsedItem.getItemName()){
            repeatedUseCount += 1;
            this.setPassiveBuff(repeatedUseCount);
        }
        else{
            
            if (repeatedUseCount > 0 && lastUsedItem.getItemName() != "Nothing"){
                lastUsedItem = new DamageItem("Nothing", 0, 0, DamageTypes.DamageType.Water, 0);
                this.setPassiveBuff(repeatedUseCount * 2);//need to then reset lastuseditem
            }
            else{
                lastUsedItem = item;
                this.setPassiveBuff(0);
                repeatedUseCount = 0;
            }
        }
    }
    
    //reset the passive between battles
    @Override
    public void ResetPassive(){
        repeatedUseCount = 0;
        lastUsedItem = new DamageItem("Nothing", 0, 0, DamageTypes.DamageType.Water, 0);
    }

    public int getRepeatedUseCount() {
        return repeatedUseCount;
    }

    public Item getLastUsedItem() {
        return lastUsedItem;
    }
    
    @Override
    public Boolean getAttackedLastTurn() {
        return false;
    }
    
    @Override
    public boolean isMagicSurge() {
        return false;
    }
    
    @Override
    public void loadPassive(int attackedLastTurn, int repeatedUseCount, Item lastUsedItem, int magicSurge){
        this.repeatedUseCount = repeatedUseCount;
        this.lastUsedItem = lastUsedItem;
    }
}
