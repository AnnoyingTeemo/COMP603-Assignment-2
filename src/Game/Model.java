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
    public Database db;
    
    public Model() {
        this.db = new Database();
        this.db.dbsetup();
        this.db.getItemList();
    }
}
