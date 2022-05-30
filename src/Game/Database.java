/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Owner
 */
public class Database {
    Connection conn = null;
    String url = "jdbc:derby:database\\RPG;create=true";  //url of the DB host
    //jdbc:derby://localhost:1527/Assesment2
    String dbusername = "rpg";  //your DB username
    String dbpassword = "rpg";   //your DB password
    
    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "PlayerSaves";

            if (!checkTableExisting(tableName)) {
                String query = "CREATE TABLE \"" + tableName + "\" (playerName VARCHAR(24), className VARCHAR(24), baseHealth INT, baseDodge INT, baseDamageReduction INT, baseDamageModifier INT, baseSpeed INT, baseCritChance INT, health INT, dodge INT, damageReduction INT, damageModifier INT, speed INT, critChance INT, currentHealth INT, weapon1 VARCHAR(24), weapon2 VARCHAR(24), classDamage VARCHAR(24), classDefensive VARCHAR(24), level INT, damageReductionBoost INT, damageBoost INT, passiveBuff INT)";
                System.out.println(query);
                statement.executeUpdate(query);
            }
            
            tableName = "EnemySaves";

            if (!checkTableExisting(tableName)) {
                String query = "CREATE TABLE \"" + tableName + "\" (playerName VARCHAR(24), name VARCHAR(24), health INT, dodge INT, damageReduction INT, damageModifier INT, speed INT, critChance INT, currentHealth INT, item1 VARCHAR(24), item2 VARCHAR(24), item3 VARCHAR(24), item4 VARCHAR(24), damageReductionBoost INT, damageBoost INT)";
                System.out.println(query);
                statement.executeUpdate(query);
            }
            
            tableName = "EnemyList";

            if (!checkTableExisting(tableName)) {
                String query = "CREATE TABLE \"" + tableName + "\" (name VARCHAR(24), health INT, dodge INT, damageReduction INT, damageModifier INT, speed INT, critChance INT, item1 VARCHAR(24), item2 VARCHAR(24), item3 VARCHAR(24), item4 VARCHAR(24))";
                System.out.println(query);
                statement.executeUpdate(query);
                
                fillEnemyDatabase();
            }
            
            tableName = "ItemList";

            if (!checkTableExisting(tableName)) {
                String query = "CREATE TABLE \"" + tableName + "\" (damageItem INT, itemName VARCHAR(24), maxRoll INT, minRoll INT, buffType VARCHAR(24), damageType VARCHAR(24), playerWeapon INT)";
                System.out.println(query);
                statement.executeUpdate(query);
                
                fillItemDatabase();
            }
            //statement.executeUpdate("INSERT INTO " + tableName + " VALUES('Fiction',0),('Non-fiction',10),('Textbook',20)");
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.printStackTrace());
        }
    }
    
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }
    
    public void fillItemDatabase(){
        System.out.println("Filling Database");
        
        ItemList itemList = new ItemList();
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "RPG.\"ItemList\"";
            
            for (Item item : itemList.getList()){
                if (item.isDamageItem()){
                    String query = "INSERT INTO " + tableName + " VALUES(1, '" + item.getItemName() + "', " + item.getBaseDamageMaxRoll()+ ", " + item.getBaseDamageMinRoll()+ ", NULL, '" + item.getDamageType().toString() + "', "+ item.getIsPlayerItem() +")";
                    System.out.println(query);
                    statement.executeUpdate(query);
                }
                else{
                    String query = "INSERT INTO " + tableName + " VALUES(0, '" + item.getItemName() + "', " + item.getBaseBuffMaxRoll()+ ", " + item.getBaseBuffMinRoll()+ ", '" + item.getBuffType().toString() + "', NULL, "+ item.getIsPlayerItem() +")";
                    System.out.println(query);
                    statement.executeUpdate(query);
                }
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void fillEnemyDatabase(){
        System.out.println("Filling Database");
        
        EnemyList enemyList = new EnemyList();
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "RPG.\"EnemyList\"";
            
            for (Enemy enemy : enemyList.getList()){
                String query = "INSERT INTO " + tableName + " VALUES('" + enemy.getName()+ "', " + enemy.getHealth()+ ", " + enemy.getDodge()+ ", " + enemy.getDamageReduction()+ ", " + enemy.getDamageModifier()+ ", " + enemy.getSpeed()+ ", " + enemy.getCritChance()+ ", '" + enemy.getItem1().getItemName()+ "', '" + enemy.getItem2().getItemName()+ "', '" + enemy.getItem3().getItemName()+ "', '" + enemy.getItem4().getItemName()+ "')";
                System.out.println(query);
                statement.executeUpdate(query);
            }
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getItemList(){
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "RPG.\"ItemList\"";
            
            String query = "SELECT * FROM " + tableName + "WHERE PLAYERWEAPON = 1";
            System.out.println(query);
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
//                System.out.println(set.getString(1));
                if (set.getInt(1) == 1){
                    Item item = new DamageItem(set.getString(2), set.getInt(3), set.getInt(4), DamageTypes.DamageType.valueOf(set.getString(6)), set.getInt(7));
                    System.out.println(item.getItemName());
                }
                else{
                    Item item = new DefensiveItem(set.getString(2), BuffTypes.Buffs.valueOf(set.getString(5)), set.getInt(3), set.getInt(4), set.getInt(7));
                    System.out.println(item.getItemName());
                }
            }
            statement.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
