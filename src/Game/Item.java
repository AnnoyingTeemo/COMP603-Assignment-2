package Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public abstract class Item {
    
    
    private String itemName;
    
    private BuffTypes.Buffs buffType;
    private int buffMaxRoll;
    private int buffMinRoll;
    
    private DamageTypes.DamageType damageType;
    private int damageMaxRoll;
    private int damageMinRoll;

    private boolean damageItem;
    //Creating a defensive item
    public Item(String itemName, BuffTypes.Buffs buffType, int baseBuffMaxRoll, int baseBuffMinRoll) {
        this.itemName = itemName;
        this.buffType = buffType;
        this.buffMaxRoll = baseBuffMaxRoll;
        this.buffMinRoll = baseBuffMinRoll;
        this.damageItem = false;
    }

    //Creating a offensive item
    public Item(String itemName, int baseDamageMaxRoll, int baseDamageMinRoll, DamageTypes.DamageType damageType) {
        this.itemName = itemName;
        this.damageMaxRoll = baseDamageMaxRoll;
        this.damageMinRoll = baseDamageMinRoll;
        this.damageType = damageType;
        this.damageItem = true;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BuffTypes.Buffs getBuffType() {
        return buffType;
    }

    public void setBuffType(BuffTypes.Buffs buffType) {
        this.buffType = buffType;
    }

    public int getBaseBuffMaxRoll() {
        return buffMaxRoll;
    }

    public void setBaseBuffMaxRoll(int baseBuffMaxRoll) {
        this.buffMaxRoll = baseBuffMaxRoll;
    }

    public int getBaseBuffMinRoll() {
        return buffMinRoll;
    }

    public void setBaseBuffMinRoll(int baseBuffMinRoll) {
        this.buffMinRoll = baseBuffMinRoll;
    }

    public int getBuffMaxRoll() {
        return buffMaxRoll;
    }

    public void setBuffMaxRoll(int buffMaxRoll) {
        this.buffMaxRoll = buffMaxRoll;
    }

    public int getBuffMinRoll() {
        return buffMinRoll;
    }

    public void setBuffMinRoll(int buffMinRoll) {
        this.buffMinRoll = buffMinRoll;
    }

    public int getBaseDamageMaxRoll() {
        return damageMaxRoll;
    }

    public void setBaseDamageMaxRoll(int damageMaxRoll) {
        this.damageMaxRoll = damageMaxRoll;
    }

    public int getBaseDamageMinRoll() {
        return damageMinRoll;
    }

    public void setBaseDamageMinRoll(int damageMinRoll) {
        this.damageMinRoll = damageMinRoll;
    }

    public DamageTypes.DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageTypes.DamageType damageType) {
        this.damageType = damageType;
    }

    public int getDamageMaxRoll() {
        return damageMaxRoll;
    }

    public void setDamageMaxRoll(int damageMaxRoll) {
        this.damageMaxRoll = damageMaxRoll;
    }

    public int getDamageMinRoll() {
        return damageMinRoll;
    }

    public void setDamageMinRoll(int damageMinRoll) {
        this.damageMinRoll = damageMinRoll;
    }

    public boolean isDamageItem() {
        return damageItem;
    }

    public void setDamageItem(boolean damageItem) {
        this.damageItem = damageItem;
    }    
}
