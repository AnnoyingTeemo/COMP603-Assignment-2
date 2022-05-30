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
public class DefensiveItem extends Item{
    public DefensiveItem(String itemName, BuffTypes.Buffs buffType, int buffMaxRoll, int buffMinRoll, int isPlayerItem) {
        super(itemName, buffType, buffMaxRoll, buffMinRoll, isPlayerItem);
    }
}
