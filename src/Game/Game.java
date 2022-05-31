/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class Game {
    public Ally player;
    public Enemy enemy;
    
    public void SetPlayerClass(Ally ally){
        this.player = ally;
    }
    
    public Enemy PickEnemy(ArrayList<Enemy> enemyList){
        int max = enemyList.size() -1;
        int min = 0;
        int range = max - min + 1;

        int enemyNum = (int)(Math.random() * range) + min;
        Enemy enemy = enemyList.get(enemyNum);
        this.enemy = enemy;
        return enemy;
    }

    public Ally getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }
    
    
}
