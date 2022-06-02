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
public class Sorcerer extends Ally{
    boolean magicSurge = true;
    
    //creates a new sorcerer
    public Sorcerer(Item leftHand, Item rightHand){
        super(70, 10, 15, 5, 0, 20, leftHand, rightHand, new DamageItem("Fireball", 10, 1, DamageTypes.DamageType.Fire, 0), new DefensiveItem("Shield Spell", BuffTypes.Buffs.DamageReduction, 6, 1, 0), "Sorcerer");
        this.calculateAbilities();
    }
    
    //creates a new sorcerer from a save
    public Sorcerer(int baseHealth, int baseDodge, int baseDamageReduction, int baseDamageModifier, int baseSpeed, int baseCritChance, int health, int dodge, int damageReduction, int damageModifier, int speed, int critChance, int currentHealth, Item leftHand, Item rightHand, Item classDamage, Item classDefensive, int level, int damageReductionBoost, int damageBoost, int passiveBuff) {
        super(baseHealth, baseDodge, baseDamageReduction, baseDamageModifier, baseSpeed, baseCritChance, health, dodge, damageReduction, damageModifier, speed, critChance, currentHealth, leftHand, rightHand, classDamage, classDefensive, level, damageReductionBoost, damageBoost, passiveBuff, "Sorcerer");
    }
    
    //The passive for this class, switches between adding more to roll or removing roll
    @Override
    public void Passive(Item item){
        int max = 15;
        int min = 1;
        int range = max - min + 1;

        int buff = (int)(Math.random() * range) + min;
        
        if (magicSurge){
            this.setPassiveBuff(buff);
            magicSurge = false;
        }
        else{
            this.setPassiveBuff(buff * -1);
            magicSurge = true;
        }
    }
    
    //reset passive info
    @Override
    public void ResetPassive(){
        magicSurge = true;
    }

    public boolean isMagicSurge() {
        return magicSurge;
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
    public void loadPassive(int attackedLastTurn, int repeatedUseCount, Item lastUsedItem, int magicSurge){
        if (magicSurge == 0){
            this.magicSurge = false;
        }
        else{
            this.magicSurge = true;
        }
    }
}
