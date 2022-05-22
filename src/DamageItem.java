
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
public class DamageItem extends Item{
    public DamageItem(String itemName,int baseDamageMaxRoll, int baseDamageMinRoll, DamageTypes.DamageType damageType) {
        super(itemName, baseDamageMaxRoll, baseDamageMinRoll, damageType);
    }

}
