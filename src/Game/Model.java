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
    
    public Model() {//sets up db and game info
        game = new Game();
        this.db = new Database();
        this.db.dbsetup(game);
        this.db.setupItemLists();
        this.db.SetupEnemyList();
        game.PickEnemy(db.getEnemyList());
        this.db.setupSaveList();
    }

    public Database getDb() {
        return db;
    }

    public Game getGame() {
        return game;
    }
    
    //saves when person presses the x button
    public void saveAndClose(){
        db.deleteSave(db.saveName);
        db.save(game.getPlayer(), game.getEnemy(), db.saveName);
    }
}
