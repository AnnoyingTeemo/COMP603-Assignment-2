
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
        super(100, 0, 35, 2, 0, 10, leftHand, rightHand, new DamageItem("Smite", 6, 3, DamageTypes.DamageType.Holy), new DefensiveItem("Healing Touch", BuffTypes.Buffs.Heal, 6, 1), "Paladin");
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
    
    //Save passive attributes
    @Override
    public void Save(){
        String output = "";
        
        output += attackedLastTurn;
        
        
        PrintWriter pw = null;
        
        try {
            pw = new PrintWriter(new FileOutputStream("./Resources/ClassSave.txt"));
            pw.print(output);
            pw.close();
        }
        catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
        }
    }
    //Load passive attributes
    @Override
    public void Load(){
        try{
            FileReader fr = new FileReader("./Resources/ClassSave.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            attackedLastTurn = Boolean.valueOf(inputStream.readLine());
            inputStream.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Error reading from file");
        }
    }
    
    //reset passive between battles
    @Override
    public void ResetPassive(){
        attackedLastTurn = false;
        this.setPassiveBuff(0);
    }
}
