/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class Enemy {
    private String name;
    
    private int health;
    private int dodge;
    private int damageReduction;
    private int damageModifier;
    private int speed;
    private int critChance;
    
    private int currentHealth;
    
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    
    private int damageReductionBoost;
    
    private int damageBoost;
    
    //Create a new enemy
    public Enemy(String name ,int health, int dodge, int damageReduction, int damageModifier, int speed, int critChance, Item item1, Item item2, Item item3, Item item4) {
        this.name = name;
        this.health = health;
        this.dodge = dodge;
        this.damageReduction = damageReduction;
        this.damageModifier = damageModifier;
        this.speed = speed;
        this.critChance = critChance;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.currentHealth = this.health;
        this.damageReductionBoost = 0;
        this.damageBoost = 0;
    }
    //Copy an enemy
    public Enemy(Enemy enemy){
        this.name = enemy.name;
        this.health = enemy.health;
        this.dodge = enemy.dodge;
        this.damageReduction = enemy.damageReduction;
        this.damageModifier = enemy.damageModifier;
        this.speed = enemy.speed;
        this.critChance = enemy.critChance;
        this.item1 = enemy.item1;
        this.item2 = enemy.item2;
        this.item3 = enemy.item3;
        this.item4 = enemy.item4;
        this.currentHealth = this.health;
        this.damageReductionBoost = 0;
        this.damageBoost = 0;
    }

    //Create an enemy from save
    public Enemy(String name, int health, int dodge, int damageReduction, int damageModifier, int speed, int critChance, int currentHealth, Item item1, Item item2, Item item3, Item item4, int damageReductionBoost, int damageBoost) {
        this.name = name;
        this.health = health;
        this.dodge = dodge;
        this.damageReduction = damageReduction;
        this.damageModifier = damageModifier;
        this.speed = speed;
        this.critChance = critChance;
        this.currentHealth = currentHealth;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.damageReductionBoost = damageReductionBoost;
        this.damageBoost = damageBoost;
    }
    
    

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Item getItem1() {
        return item1;
    }

    public void setItem1(Item item1) {
        this.item1 = item1;
    }

    public Item getItem2() {
        return item2;
    }

    public void setItem2(Item item2) {
        this.item2 = item2;
    }

    public Item getItem3() {
        return item3;
    }

    public void setItem3(Item item3) {
        this.item3 = item3;
    }

    public Item getItem4() {
        return item4;
    }

    public void setItem4(Item item4) {
        this.item4 = item4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void TakeDamage(int damage){
        if (damage < 1){
            damage = 1;
        }
        this.currentHealth -= damage;
        if (this.currentHealth < 0){
            this.currentHealth = 0;
        }
    }
    
    public boolean Alive(){
        if (currentHealth <= 0){
            return false;
        }
        else {
            return true;
        }
    }
    //Pick a arandom move while also avoiding healing above 75% health
    public Item PickMove(){
        int move1Chance = 25;
        int move2Chance = 25;
        int move3Chance = 25;
        int move4Chance = 25;
        
        if (!item1.isDamageItem()){
            if(item1.getBuffType() == BuffTypes.Buffs.Heal){
                if(currentHealth/health > 0.75){
                    move1Chance = 0;
                }
            }
            if (item1.getBuffType() == BuffTypes.Buffs.DamageBuff){
                if (this.damageBoost > item1.getBuffMaxRoll()/2){
                    move1Chance = 0;
                }
            }
            if (item1.getBuffType() == BuffTypes.Buffs.DamageReduction){
                if (this.damageReductionBoost > item1.getBuffMaxRoll()/2){
                    move1Chance = 0;
                }
            }
        }
        
        if (!item2.isDamageItem()){
            if(item2.getBuffType() == BuffTypes.Buffs.Heal){
                if(currentHealth/health > 0.75){
                    move2Chance = 0;
                }
            }
            if (item2.getBuffType() == BuffTypes.Buffs.DamageBuff){
                if (this.damageBoost > item2.getBuffMaxRoll()/2){
                    move2Chance = 0;
                }
            }
            if (item2.getBuffType() == BuffTypes.Buffs.DamageReduction){
                if (this.damageReductionBoost > item2.getBuffMaxRoll()/2){
                    move2Chance = 0;
                }
            }
        }
        
        if (!item3.isDamageItem()){
            if(item3.getBuffType() == BuffTypes.Buffs.Heal){
                if((float)(currentHealth/health) > 0.75){
                    move3Chance = 0;
                }
            }
            if (item3.getBuffType() == BuffTypes.Buffs.DamageBuff){
                if (this.damageBoost > item3.getBuffMaxRoll()/2){
                    move3Chance = 0;
                }
            }
            if (item3.getBuffType() == BuffTypes.Buffs.DamageReduction){
                if (this.damageReductionBoost > item3.getBuffMaxRoll()/2){
                    move3Chance = 0;
                }
            }
        }
        
        if (!item4.isDamageItem()){
            if(item4.getBuffType() == BuffTypes.Buffs.Heal){
                if(currentHealth/health > 0.75){
                    move4Chance = 0;
                }
            }
            if (item4.getBuffType() == BuffTypes.Buffs.DamageBuff){
                if (this.damageBoost > item4.getBuffMaxRoll()/2){
                    move4Chance = 0;
                }
            }
            if (item4.getBuffType() == BuffTypes.Buffs.DamageReduction){
                if (this.damageReductionBoost > item4.getBuffMaxRoll()/2){
                    move4Chance = 0;
                }
            }
        }        
        
        
        
        int range = (move1Chance + move2Chance + move3Chance + move4Chance) + 1;
        int choosenNum = (int)(Math.random() * range);
        
        if (choosenNum <= move1Chance && move1Chance > 0){
            return item1;
        }
        else if (choosenNum <= move2Chance + move1Chance  && move2Chance > 0){
            return item2;
        }
        else if (choosenNum <= move3Chance + move2Chance + move1Chance  && move3Chance > 0){
            return item3;
        }
        else if (choosenNum <= move4Chance + move3Chance + move2Chance + move1Chance && move4Chance > 0){
            return item4;
        }
        else{
            return item1;
        }
    }
    
    public int getDamageBoost() {
        return damageBoost;
    }

    public void setDamageBoost(int damageBoost) {
        this.damageBoost = damageBoost;
    }
    
    public void calculateDamageReductionBoost(){
        this.damageReduction = this.damageReduction + this.damageReductionBoost;
        if (this.damageReduction > 90){
            this.damageReduction = 90;
        }
    }
    
    public void BuffDefense(int buff){
        if (this.damageReductionBoost < buff){
            this.damageReductionBoost = buff;
        }
        
        calculateDamageReductionBoost();
    }
    
    public void Heal(int healAmount){
        this.currentHealth += healAmount;
        if (this.currentHealth > this.health){
            this.currentHealth = this.health;
        }
    }
    
    public void BuffDamage(int buff){
        if (this.damageBoost < buff){
            this.damageBoost = buff;
        }
        
        calculateDamageBoost();
    }
    
    public void calculateDamageBoost(){
        this.damageModifier = this.damageModifier + this.damageBoost;
    }

    public int getDamageReductionBoost() {
        return damageReductionBoost;
    }

    public void setDamageReductionBoost(int damageReductionBoost) {
        this.damageReductionBoost = damageReductionBoost;
    }
    
    
}
