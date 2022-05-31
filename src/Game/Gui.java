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
public class Gui {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model.getDb());
        Controller controller = new Controller(view, model.getGame(), model.getDb()                                                                                                                          );
    }
}
