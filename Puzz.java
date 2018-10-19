
package tests;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import static tests.TilesClass.Tiles;
public class Puzz extends Application {
    private static boolean found = false;
    private static Tiles[][] tilesArr;
    @Override
    public void start(Stage stage) {
        TilesClass tiles = new TilesClass();
        
        //GridPane gPane = TilesClass.getTilesGrid(1);
        
        GridPane gPane = TilesClass.getTilesGrid(6);
        tilesArr = TilesClass.getTilesArray();
        
        
        Scene scene = new Scene(gPane, 1600, 800);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    public static void findPath() {
         
        Tiles start = tilesArr[0][0];
        Tiles end = tilesArr[0][0];
        
        
        new Thread(() -> {
            Tiles current = start;
            while(!current.getText().equals("End")) {
                
                
                
            }
            
            found = true;
            
        }).start();
        
        
        new Thread(() -> {
            
            Tiles current = start;
            while(current.getPassed() != 1 || !current.getText().equals("Start")) {
                
                Tiles u = current.getUp();
                Tiles r = current.getRight();
                Tiles d = current.getDown();
                Tiles l = current.getLeft();

                if(u.getType() == 1 && r.getType() == 1 && d.getType() == 1 && l.getType() == 1) {
                    
                }
            }
            
            found = true;
            
        }).start();
        

        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
