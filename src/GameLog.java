
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Owner
 */
public class GameLog {
    public static int count;
    
    //Adding strings to game log and printing them out
    static String Log(String string){
        PrintWriter pw = null;
        
        string += "\n";
        
        String path = "./Resources/GameLogs/GameLog" + String.valueOf(count) + ".txt";
        
        try {
            pw = new PrintWriter(new FileOutputStream(path, true));
            pw.print(string);
            pw.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        
        System.out.print(string);
        return string;
    }
    //Finding the last used game log and adding 1 to that number
    static void LogCount(){
        int x = 0;
        while (true) {
            Path path = Paths.get("./Resources/GameLogs/GameLog"+ x + ".txt");
            
            if (!Files.exists(path)){
                count = x;
                break;
            }
            else{
                x++;
            }
        }
    }
}
