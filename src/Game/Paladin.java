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
public class Paladin extends Ally{
    private Boolean attackedLastTurn = false;
    
    //Creating a new Paladin
    public Paladin(Item leftHand, Item rightHand) {
        super(100, 0, 35, 2, 0, 10, leftHand, rightHand, new DamageItem("Smite", 6, 3, DamageTypes.DamageType.Holy, 0), new DefensiveItem("Healing Touch", BuffTypes.Buffs.Heal, 6, 1, 0), "Paladin");
        calculateAbilities();
    }

    //Loading a paladin from save
    public Paladin(int baseHealth, int baseDodge, int baseDamageReduction, int baseDamageModifier, int baseSpeed, int baseCritChance, int health, int dodge, int damageReduction, int damageModifier, int speed, int critChance, int currentHealth, Item leftHand, Item rightHand, Item classDamage, Item classDefensive, int level, int damageReductionBoost, int damageBoost, int passiveBuff) {
        super(baseHealth, baseDodge, baseDamageReduction, baseDamageModifier, baseSpeed, baseCritChance, health, dodge, damageReduction, damageModifier, speed, critChance, currentHealth, leftHand, rightHand, classDamage, classDefensive, level, damageReductionBoost, damageBoost, passiveBuff, "Paladin");
    }
    
    
    //the code for this player classes passive, you get bonus damage for switching between damage and buff abilities
    @Override
    public void Passive(Item item){
        if (!attackedLastTurn){
            if (item.isDamageItem()){
                this.setPassiveBuff(10);
                attackedLastTurn = true;
            }
            else{
                this.setPassiveBuff(0);
            }
        }
        else{
            if (!item.isDamageItem()){
                this.setPassiveBuff(10);
                attackedLastTurn = false;
            }
            else{
                this.setPassiveBuff(0);
            }
        }
    }
    
    //reset passive between battles
    @Override
    public void ResetPassive(){
        attackedLastTurn = false;
        this.setPassiveBuff(0);
    }

    @Override
    public Boolean getAttackedLastTurn() {
        return attackedLastTurn;
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
        if (attackedLastTurn == 0){
            this.attackedLastTurn = false;
        }
        else{
            this.attackedLastTurn = true;
        }
    }
}
