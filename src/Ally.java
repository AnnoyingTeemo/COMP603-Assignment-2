/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public abstract class Ally {
    //Paladin passive get bonus for swapping between dmg and support skills
    //Monk every 3rd hit does bonus damage
    
    private int baseHealth;
    private int baseDodge;
    private int baseDamageReduction;
    private int baseDamageModifier;
    private int baseSpeed;
    private int baseCritChance;
    
    private int health;
    private int dodge;
    private int damageReduction;
    private int damageModifier;
    private int speed;
    private int critChance;
    
    private int currentHealth;
    
    private Item leftHand;
    private Item rightHand;
    
    private Item classDamage;
    private Item classDefensive;
    
    private int level;
    
    private int damageReductionBoost;
    
    private int damageBoost;
    
    private int passiveBuff;
    
    private String classString;
    
    //sets the players stats to their base stats, this is useful for creating temporary buffs to the player
    public void calculateAbilities(){
        this.health = this.baseHealth;
        this.dodge = this.baseDodge;
        this.damageReduction = this.baseDamageReduction;
        this.damageModifier = this.baseDamageModifier;
        this.speed = this.baseSpeed;
        this.critChance = this.baseCritChance;
        
        this.currentHealth = this.health;
    }
    
    //resetting some abilities between battles as they can be changed by some items
    public void resetAbilities(){
        this.dodge = this.baseDodge;
        this.damageReduction = this.baseDamageReduction;
        this.damageModifier = this.baseDamageModifier;
        this.speed = this.baseSpeed;
        this.critChance = this.baseCritChance;
        
        this.damageReductionBoost = 0;
        this.damageBoost = 0;
        
        this.ResetPassive();
    }
    
    //Creating a player at the start of the game
    public Ally(int baseHealth, int baseDodge, int baseDamageReduction, int baseDamageModifier, int baseSpeed, int baseCritChance, Item leftHand, Item rightHand, Item classDamage, Item classDefensive, String classString) {
        this.baseHealth = baseHealth;
        this.baseDodge = baseDodge;
        this.baseDamageReduction = baseDamageReduction;
        this.baseDamageModifier = baseDamageModifier;
        this.baseSpeed = baseSpeed;
        this.baseCritChance = baseCritChance;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.classDamage = classDamage;
        this.classDefensive = classDefensive;
        this.damageReductionBoost = 0;
        this.damageBoost = 0;
        this.passiveBuff = 0;
        this.classString = classString;
    }

    //loading a player from a save
    public Ally(int baseHealth, int baseDodge, int baseDamageReduction, int baseDamageModifier, int baseSpeed, int baseCritChance, int health, int dodge, int damageReduction, int damageModifier, int speed, int critChance, int currentHealth, Item leftHand, Item rightHand, Item classDamage, Item classDefensive, int level, int damageReductionBoost, int damageBoost, int passiveBuff, String classString) {
        this.baseHealth = baseHealth;
        this.baseDodge = baseDodge;
        this.baseDamageReduction = baseDamageReduction;
        this.baseDamageModifier = baseDamageModifier;
        this.baseSpeed = baseSpeed;
        this.baseCritChance = baseCritChance;
        this.health = health;
        this.dodge = dodge;
        this.damageReduction = damageReduction;
        this.damageModifier = damageModifier;
        this.speed = speed;
        this.critChance = critChance;
        this.currentHealth = currentHealth;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.classDamage = classDamage;
        this.classDefensive = classDefensive;
        this.level = level;
        this.damageReductionBoost = damageReductionBoost;
        this.damageBoost = damageBoost;
        this.passiveBuff = passiveBuff;
        this.classString = classString;
    }
    
    public abstract void Save();
    public abstract void Load();
    
    public abstract void ResetPassive();
    
    public abstract void Passive(Item item);
    
    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public int getBaseDodge() {
        return baseDodge;
    }

    public void setBaseDodge(int baseDodge) {
        this.baseDodge = baseDodge;
    }

    public int getBaseDamageReduction() {
        return baseDamageReduction;
    }

    public void setBaseDamageReduction(int baseDamageReduction) {
        this.baseDamageReduction = baseDamageReduction;
    }

    public int getBaseDamageModifier() {
        return baseDamageModifier;
    }

    public void setBaseDamageModifier(int baseDamageModifier) {
        this.baseDamageModifier = baseDamageModifier;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getBaseCritChance() {
        return baseCritChance;
    }

    public void setBaseCritChance(int baseCritChance) {
        this.baseCritChance = baseCritChance;
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

    public Item getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Item leftHand) {
        this.leftHand = leftHand;
    }

    public Item getRightHand() {
        return rightHand;
    }

    public void setRightHand(Item rightHand) {
        this.rightHand = rightHand;
    }

    public Item getClassDamage() {
        return classDamage;
    }

    public void setClassDamage(Item classDamage) {
        this.classDamage = classDamage;
    }

    public Item getClassDefensive() {
        return classDefensive;
    }

    public void setClassDefensive(Item classDefensive) {
        this.classDefensive = classDefensive;
    }

    public int getDamageReductionBoost() {
        return damageReductionBoost;
    }

    public void setDamageReductionBoost(int damageReductionBoost) {
        this.damageReductionBoost = damageReductionBoost;
    }

    public int getDamageBoost() {
        return damageBoost;
    }

    public void setDamageBoost(int damageBoost) {
        this.damageBoost = damageBoost;
    }
    //Set damage reduction to base plus the boost from any abilities 
    public void calculateDamageReductionBoost(){
        this.damageReduction = this.baseDamageReduction + this.damageReductionBoost;
        if (this.damageReduction > 90){
            this.damageReduction = 90;
        }
    }
    
    //If the buff that is being inputted is higher than the current buff the damage reducton boost is set to the new buff
    public void BuffDefense(int buff){
        if (this.damageReductionBoost < buff){
            this.damageReductionBoost = buff;
        }
        
        calculateDamageReductionBoost();
    }
    
    //Heal the player the heal amount and check if their current health is too high and fix it if it is
    public void Heal(int healAmount){
        this.currentHealth += healAmount;
        if (this.currentHealth > this.health){
            this.currentHealth = this.health;
        }
    }
    
    //If the buff that is being inputted is higher than the current buff the damage boost is set to the new buff
    public void BuffDamage(int buff){
        if (this.damageBoost < buff){
            this.damageBoost = buff;
        }
        
        calculateDamageBoost();
    }
    
    //setting the damage modifier to base plus the boost
    public void calculateDamageBoost(){
        this.damageModifier = this.baseDamageModifier + this.damageBoost;
    }
  
    //damaging the player
    public void TakeDamage(int damage){
        if (damage < 1){
            damage = 1;
        }
        this.currentHealth -= damage;
        if (this.currentHealth < 0){
            this.currentHealth = 0;
        }
    }
    
    //returns true if the player has more than 1 health
    public boolean Alive(){
        if (currentHealth <= 0){
            return false;
        }
        else {
            return true;
        }
    }

    public int getPassiveBuff() {
        return passiveBuff;
    }

    public void setPassiveBuff(int passiveBuff) {
        this.passiveBuff = passiveBuff;
    }

    public String getClassString() {
        return classString;
    }

    public void setClassString(String classString) {
        this.classString = classString;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
