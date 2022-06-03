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
import java.util.ArrayList;

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
    
    ArrayList<Item> playerItemList = new ArrayList<Item>();
    ArrayList<Item> itemList = new ArrayList<Item>();
    ArrayList<String> saveNameList = new ArrayList<String>();
    
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    String saveName = "";
    
    Game game;
    
    public void dbsetup(Game game){
        this.game = game;
        
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            
            String tableName = "ItemList";
            
            statement.executeUpdate("DROP TABLE RPG.\"ItemList\"");
                    
            if (!checkTableExisting(tableName)) {
                String query = "CREATE TABLE \"" + tableName + "\" (damageItem INT, itemName VARCHAR(24), maxRoll INT, minRoll INT, buffType VARCHAR(24), damageType VARCHAR(24), playerWeapon INT)";
                System.out.println(query);
                statement.executeUpdate(query);
                
                fillItemDatabase();
            }
            
            tableName = "EnemyList";

            if (!checkTableExisting(tableName)) {
                String query = "CREATE TABLE \"" + tableName + "\" (name VARCHAR(24), health INT, dodge INT, damageReduction INT, damageModifier INT, speed INT, critChance INT, item1 VARCHAR(24), item2 VARCHAR(24), item3 VARCHAR(24), item4 VARCHAR(24))";
                System.out.println(query);
                statement.executeUpdate(query);
                
                fillEnemyDatabase();
            }
            
            tableName = "PlayerSaves";

            if (!checkTableExisting(tableName)) {
                String query = "CREATE TABLE \"" + tableName + "\" (playerName VARCHAR(24), className VARCHAR(24), baseHealth INT, baseDodge INT, baseDamageReduction INT, baseDamageModifier INT, baseSpeed INT, baseCritChance INT, health INT, dodge INT, damageReduction INT, damageModifier INT, speed INT, critChance INT, currentHealth INT, weapon1 VARCHAR(24), weapon2 VARCHAR(24), classDamage VARCHAR(24), classDefensive VARCHAR(24), level INT, damageReductionBoost INT, damageBoost INT, passiveBuff INT, attackedLastTurn INT, repeatedUseCount INT, lastUsedItem VARCHAR(24), magicSurge INT)";
                System.out.println(query);
                statement.executeUpdate(query);
            }
            
            tableName = "EnemySaves";

            if (!checkTableExisting(tableName)) {
                String query = "CREATE TABLE \"" + tableName + "\" (playerName VARCHAR(24), name VARCHAR(24), health INT, dodge INT, damageReduction INT, damageModifier INT, speed INT, critChance INT, currentHealth INT, item1 VARCHAR(24), item2 VARCHAR(24), item3 VARCHAR(24), item4 VARCHAR(24), damageReductionBoost INT, damageBoost INT)";
                System.out.println(query);
                statement.executeUpdate(query);
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
                this.itemList.add(item);
                if (item.getIsPlayerItem() == 1){
                    this.playerItemList.add(item);
                }
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
            
            for (Enemy enemy : enemyList.LoadEnemies(this)){
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
    
    public void setupItemLists(){
        if (itemList.size() == 0){
            try {
                conn = DriverManager.getConnection(url, dbusername, dbpassword);
                Statement statement = conn.createStatement();
                String tableName = "RPG.\"ItemList\"";

                String query = "SELECT * FROM " + tableName + "WHERE PLAYERWEAPON = 1";
                System.out.println(query);
                ResultSet set = statement.executeQuery(query);
                while (set.next()){
                    if (set.getInt(1) == 1){
                        Item item = new DamageItem(set.getString(2), set.getInt(3), set.getInt(4), DamageTypes.DamageType.valueOf(set.getString(6)), set.getInt(7));
                        System.out.println(item.getItemName());
                        playerItemList.add(item);
                    }
                    else{
                        Item item = new DefensiveItem(set.getString(2), BuffTypes.Buffs.valueOf(set.getString(5)), set.getInt(3), set.getInt(4), set.getInt(7));
                        System.out.println(item.getItemName());
                        playerItemList.add(item);
                    }
                }

                query = "SELECT * FROM " + tableName;
                System.out.println(query);
                set = statement.executeQuery(query);
                while (set.next()){
                    if (set.getInt(1) == 1){
                        Item item = new DamageItem(set.getString(2), set.getInt(3), set.getInt(4), DamageTypes.DamageType.valueOf(set.getString(6)), set.getInt(7));
                        System.out.println(item.getItemName());
                        itemList.add(item);
                    }
                    else{
                        Item item = new DefensiveItem(set.getString(2), BuffTypes.Buffs.valueOf(set.getString(5)), set.getInt(3), set.getInt(4), set.getInt(7));
                        System.out.println(item.getItemName());
                        itemList.add(item);
                    }
                }
                statement.close();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Item> getPlayerItemList() {
        return playerItemList;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }
    
    //Returns a random hashset of items to be used in item selection by the player
    public ArrayList<String> ReturnRandomList(int numberOfItems){
        ArrayList<String> itemArrayList = new ArrayList<String>();
        
        while (itemArrayList.size() < numberOfItems){
            int max = this.playerItemList.size() -1 ;
            int min = 0;
            int range = max - min + 1;

            Item tryItem = this.playerItemList.get((int)(Math.random() * range) + min);

            if (!itemArrayList.contains(tryItem.getItemName())){
                itemArrayList.add(tryItem.getItemName());
            }
        }
        
        return itemArrayList;
    }
    
    public void SetupEnemyList(){
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "RPG.\"EnemyList\"";
            
            String query = "SELECT * FROM " + tableName;
            System.out.println(query);
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                Enemy enemy = new Enemy(set.getString(1), set.getInt(2), set.getInt(3), set.getInt(4), set.getInt(5), set.getInt(6), set.getInt(7), GetItemByName(set.getString(8)), GetItemByName(set.getString(9)), GetItemByName(set.getString(10)), GetItemByName(set.getString(11)));
                System.out.println(enemy.getName());
                enemyList.add(enemy);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Item GetItemByName(String name){
        for(Item item : this.itemList){
            if (item.getItemName().equals(name)){
                return item;
            }
        }
        
        return this.itemList.get(0);
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
    
    public void setupSaveList(){
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "RPG.\"PlayerSaves\"";
            
            String query = "SELECT PLAYERNAME FROM " + tableName;
            System.out.println(query);
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                String name = set.getString(1);
                if (!saveNameList.contains(name)){
                    saveNameList.add(name);
                }
            }
            
            statement.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getSaveNameList() {
        return saveNameList;
    }
    
    public void save(Ally player, Enemy enemy, String saveName){
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "RPG.\"PlayerSaves\"";
            
            String query = "INSERT INTO " + tableName + " VALUES('"+ saveName +"', '"+ player.getClassString() +"', "+ player.getBaseHealth() +", "+ player.getBaseDodge() +", "+ player.getBaseDamageReduction() +", "+ player.getBaseDamageModifier() +", "+ player.getBaseSpeed() +", "+ player.getBaseCritChance() +", "+ player.getHealth() +", "+ player.getDodge() +", "+ player.getDamageReduction() +", "+ player.getDamageModifier() +", "+ player.getSpeed() +", "+ player.getCritChance() +", "+ player.getCurrentHealth() +", '"+ player.getLeftHand().getItemName() +"', '"+ player.getRightHand().getItemName()+"', '"+ player.getClassDamage().getItemName() +"', '"+ player.getClassDefensive().getItemName() +"', "+ player.getLevel() +", "+ player.getDamageReductionBoost() +", "+ player.getDamageBoost() +", "+ player.getPassiveBuff();
            if (player.getClassString().equals("Barbarian")){
                query += ", 0, 0, NULL, 0";
                
                query += ")";
            }
            else if (player.getClassString().equals("Paladin")){
                int attackedLastTurn = player.getAttackedLastTurn()? 1 : 0;
                query += ", "+ attackedLastTurn +", 0, NULL, 0";
                
                query += ")";
            }
            else if (player.getClassString().equals("Sorcerer")){
                int wildSurge = player.isMagicSurge()? 1 : 0;
                query += ", 0, 0, NULL, "+ wildSurge +"";
                
                query += ")";
            }
            else if (player.getClassString().equals("Monk")){
                query += ", 0, "+ player.getRepeatedUseCount() +", '"+ player.getLastUsedItem().getItemName() +"', 0";
                
                query += ")";
            }
            
            System.out.println(query);
            statement.executeUpdate(query);
            
            tableName = "RPG.\"EnemySaves\"";
            
            query = "INSERT INTO " + tableName + " VALUES('"+ saveName +"', '"+ enemy.getName()+"', "+ enemy.getHealth() +", "+ enemy.getDodge() +", "+ enemy.getDamageReduction() +", "+ enemy.getDamageModifier() +", "+ enemy.getSpeed() +", "+ enemy.getCritChance() +", "+ enemy.getCurrentHealth()+", '"+ enemy.getItem1().getItemName() +"', '"+ enemy.getItem2().getItemName()+"', '"+ enemy.getItem3().getItemName() +"', '"+ enemy.getItem4().getItemName() +"', "+ enemy.getDamageReductionBoost() +", "+ enemy.getDamageBoost() +")";
            System.out.println(query);
            statement.executeUpdate(query);
            
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteSave(String saveName){
        if (saveNameList.contains(saveName)){
            try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "RPG.\"PlayerSaves\"";
            
            String query = "DELETE FROM " + tableName + " WHERE playerName = '" + saveName + "'";
            System.out.println(query);
            statement.executeUpdate(query);
            
            tableName = "RPG.\"EnemySaves\"";
            
            query = "DELETE FROM " + tableName + " WHERE playerName = '" + saveName + "'";
            System.out.println(query);
            statement.executeUpdate(query);
            
            statement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
    
    public void LoadSave(String saveName){
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "RPG.\"PlayerSaves\"";
            
            String query = "SELECT * FROM " + tableName + "WHERE playerName = '"+ saveName +"'";
            System.out.println(query);
            ResultSet set = statement.executeQuery(query);
            
            set.next();
            
            if (set.getString(2).equalsIgnoreCase("Paladin")){
                Ally player = new Paladin(set.getInt(3), set.getInt(4), set.getInt(5), set.getInt(6), set.getInt(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getInt(11), set.getInt(12), set.getInt(13), set.getInt(14), set.getInt(15), GetItemByName(set.getString(16)), GetItemByName(set.getString(17)), GetItemByName(set.getString(18)), GetItemByName(set.getString(19)), set.getInt(20), set.getInt(21), set.getInt(22), set.getInt(23));
                player.loadPassive(set.getInt(24), set.getInt(25), GetItemByName(set.getString(26)), set.getInt(27));
                System.out.println(player.getClass());
                game.player = player;
            }
            else if (set.getString(2).equals("Barbarian")){
                Ally player = new Barbarian(set.getInt(3), set.getInt(4), set.getInt(5), set.getInt(6), set.getInt(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getInt(11), set.getInt(12), set.getInt(13), set.getInt(14), set.getInt(15), GetItemByName(set.getString(16)), GetItemByName(set.getString(17)), GetItemByName(set.getString(18)), GetItemByName(set.getString(19)), set.getInt(20), set.getInt(21), set.getInt(22), set.getInt(23));
                player.loadPassive(set.getInt(24), set.getInt(25), GetItemByName(set.getString(26)), set.getInt(27));
                System.out.println(player.getClass());
                game.player = player;
            }
            else if (set.getString(2).equalsIgnoreCase("Monk")){
                Ally player = new Monk(set.getInt(3), set.getInt(4), set.getInt(5), set.getInt(6), set.getInt(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getInt(11), set.getInt(12), set.getInt(13), set.getInt(14), set.getInt(15), GetItemByName(set.getString(16)), GetItemByName(set.getString(17)), GetItemByName(set.getString(18)), GetItemByName(set.getString(19)), set.getInt(20), set.getInt(21), set.getInt(22), set.getInt(23));
                player.loadPassive(set.getInt(24), set.getInt(25), GetItemByName(set.getString(26)), set.getInt(27));
                System.out.println(player.getClass());
                game.player = player;
            }
            else if (set.getString(2).equals("Sorcerer")){
                Ally player = new Sorcerer(set.getInt(3), set.getInt(4), set.getInt(5), set.getInt(6), set.getInt(7), set.getInt(8), set.getInt(9), set.getInt(10), set.getInt(11), set.getInt(12), set.getInt(13), set.getInt(14), set.getInt(15), GetItemByName(set.getString(16)), GetItemByName(set.getString(17)), GetItemByName(set.getString(18)), GetItemByName(set.getString(19)), set.getInt(20), set.getInt(21), set.getInt(22), set.getInt(23));
                player.loadPassive(set.getInt(24), set.getInt(25), GetItemByName(set.getString(26)), set.getInt(27));
                System.out.println(player.getClass());
                game.player = player;
            }
            
            statement.close();
            statement = conn.createStatement();
            tableName = "RPG.\"EnemySaves\"";
            
            query = "SELECT * FROM " + tableName + "WHERE playerName = '"+ saveName +"'";
            System.out.println(query);
            set = statement.executeQuery(query);

            set.next();
            Enemy enemy = new Enemy(set.getString(2), set.getInt(3), set.getInt(4), set.getInt(5), set.getInt(6), set.getInt(7), set.getInt(8), set.getInt(9), GetItemByName(set.getString(10)), GetItemByName(set.getString(11)), GetItemByName(set.getString(12)), GetItemByName(set.getString(13)), set.getInt(14), set.getInt(15));
            System.out.println(enemy.getName());
            game.enemy = enemy;
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
