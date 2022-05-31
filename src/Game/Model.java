/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author Owner
 */
public class Model {
    private Database db;
    Game game;
    
    public Model() {
        game = new Game();
        this.db = new Database();
        this.db.dbsetup();
        this.db.setupItemLists();
        this.db.SetupEnemyList();
        game.PickEnemy(db.getEnemyList());
    }

    public Database getDb() {
        return db;
    }

    public Game getGame() {
        return game;
    }
}
