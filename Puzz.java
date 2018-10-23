
package tests;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import static tests.TilesClass.Tiles;
import java.util.*;
public class Puzz extends Application {
    @Override
    public void start(Stage stage) {
        GridPane gPane = TilesClass.getTilesGrid(6);
        Scene scene = new Scene(gPane, 1600, 800);
        stage.setScene(scene);
        stage.show();
        findPath();
    }
    
    public static void findPath() {
         
        Tiles start = Tiles.getFirst();
        Tiles end = Tiles.getLast();
        Map<Character, Character> directionMap = new HashMap<>();
        Map<Character, Character> searchMap = new HashMap<>();
        
        Tiles current = start;
        Tiles endCurrent = end;

        Tiles searchedTile;
        Tiles nextTile;
        Tiles endSearchedTile;
        Tiles endNextTile;
        
        directionMap.put('r', 'u');
        directionMap.put('d', 'r');
        directionMap.put('l', 'd');
        directionMap.put('u', 'l');
        
        searchMap.put('r', 'd');
        searchMap.put('d', 'l');
        searchMap.put('l', 'u');
        searchMap.put('u', 'r');
        
        char currentDirection = 'r';
        char searchDirection = directionMap.get(currentDirection);
        
        char endCurrentDirection = 'l';
        char endSearchDirection = searchMap.get(endCurrentDirection);
        
        String finished;
        int counter = 0;
        while(!current.getText().equals("End")) {

            searchedTile = current.get(searchDirection);
            nextTile = current.get(currentDirection);
        	
            endSearchedTile = endCurrent.get(endSearchDirection);
            endNextTile = endCurrent.get(endCurrentDirection);
            
            current.setStyle("-fx-background-color: #42f49e; -fx-border-color: #878787;");
            endCurrent.setStyle("-fx-background-color: #41d9f4; -fx-border-color: #878787;");
            
            current.setPassed(current.getPassed() + 1);
            endCurrent.setPassed(endCurrent.getPassed() + 1);
           
            if(endCurrent.getMark() == 1) {
                current.setStyle("-fx-background-color: #f4e841; -fx-border-color: #878787;");
            	finished = "END found Start mark.";
                endCurrent.setStyle("-fx-background-color: #97429b; -fx-border-color: #878787;");
            	System.out.println(finished);
            	break;
            }
            short o = 10;
            if(current.getMark() == 2) {
                endCurrent.setStyle("-fx-background-color: #f4e841; -fx-border-color: #878787;");
            	finished = "START found End mark.";
                current.setStyle("-fx-background-color: #97429b; -fx-border-color: #878787;");
            	System.out.println(finished);
            	break;
            }
            
            current.setMark(1);
            endCurrent.setMark(2);
            
            if(searchedTile.getType() != Tiles.WALL) {
            //if(searchedTile != null) {
            	current = searchedTile;
            	char c2 = directionMap.get(currentDirection);
            	currentDirection = c2;
            	searchDirection = directionMap.get(c2);
            }else if(nextTile.getType() != Tiles.WALL) {
            //}else if(nextTile != null) {
            	current = nextTile;
            }else {
            	char c2 = searchMap.get(currentDirection);
            	currentDirection = c2;
            	searchDirection = directionMap.get(currentDirection);
            }
            
            // END search
            if(endSearchedTile.getType() != Tiles.WALL) {
            //if(endSearchedTile != null) {
            	endCurrent = endSearchedTile;
            	char c2 = searchMap.get(endCurrentDirection);
            	endCurrentDirection = c2;
            	endSearchDirection = searchMap.get(c2);
            }else if(endNextTile.getType() != Tiles.WALL) {
            //}else if(endNextTile != null) {
            	endCurrent = endNextTile;
            }else {
            	char c2 = directionMap.get(endCurrentDirection);
            	endCurrentDirection = c2;
            	endSearchDirection = searchMap.get(endCurrentDirection);
            }
            
            if(current.getPassed() > 3) {
                
            	finished = "Start didn't find end.";
            	System.out.println(finished);
            	break;
            }
            	
            if(endCurrent.getPassed() > 3) {
            	finished = "End didn't find start.";
            	System.out.println(finished);
            	break;
            }
        	
            counter++;
        }
        
        System.out.println("counter " + counter);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
