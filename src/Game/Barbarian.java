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
public class Barbarian extends Ally{
    //Creating a new barbarian
    public Barbarian(Item leftHand, Item rightHand) {
        super(80, 10, 50, 4, 0, 15, leftHand, rightHand, new DamageItem("Rage", 8, 6, DamageTypes.DamageType.Lightning, 0), new DamageItem("Reckless Attack", 12, 1, DamageTypes.DamageType.Blunt, 0), "Barbarian");
        calculateAbilities();
    }

    //Creating a new barbarian from a save
    public Barbarian(int baseHealth, int baseDodge, int baseDamageReduction, int baseDamageModifier, int baseSpeed, int baseCritChance, int health, int dodge, int damageReduction, int damageModifier, int speed, int critChance, int currentHealth, Item leftHand, Item rightHand, Item classDamage, Item classDefensive, int level, int damageReductionBoost, int damageBoost, int passiveBuff) {
        super(baseHealth, baseDodge, baseDamageReduction, baseDamageModifier, baseSpeed, baseCritChance, health, dodge, damageReduction, damageModifier, speed, critChance, currentHealth, leftHand, rightHand, classDamage, classDefensive, level, damageReductionBoost, damageBoost, passiveBuff, "Barbarian");
    }
    
    
    //Handles the passive ability of each player
    @Override
    public void Passive(Item item){
        if(item.isDamageItem()){
            this.setPassiveBuff(10 - 10 * (this.getCurrentHealth()/this.getHealth()));
        }
        else{
            this.setPassiveBuff(0);
        }
    }
    
    //Reset the passive infomation at the start of each battle
    @Override
    public void ResetPassive(){
        this.setPassiveBuff(0);
    }
    
        @Override
    public Boolean getAttackedLastTurn() {
        return false;
    }
    
    @Override
    public int getRepeatedUseCount() {
        return 0;
    }
    
    @Override
    public Item getLastUsedItem() {
        return new DamageItem("Nothing", 0, 0, DamageTypes.DamageType.Water, 0);
    }
    
    @Override
    public boolean isMagicSurge() {
        return false;
    }
    
    @Override
    public void loadPassive(int attackedLastTurn, int repeatedUseCount, Item lastUsedItem, int magicSurge){
    
    }
}
