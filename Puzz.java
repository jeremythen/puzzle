
package tests;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import static tests.TilesClass.Tiles;
import java.util.*;
public class Puzz extends Application {
    @Override
    public void start(Stage stage) throws InterruptedException {
        GridPane gPane = TilesClass.getTilesGrid(5);
        Scene scene = new Scene(gPane, 1000, 600);
        stage.setScene(scene);
        stage.show();

        new Thread(() -> {
            try {
                findPath();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private static final Map<Character, Character> directionMap = new HashMap<>();
    private static final Map<Character, Character> searchMap = new HashMap<>();

    static {
        directionMap.put('r', 'u');
        directionMap.put('d', 'r');
        directionMap.put('l', 'd');
        directionMap.put('u', 'l');

        searchMap.put('r', 'd');
        searchMap.put('d', 'l');
        searchMap.put('l', 'u');
        searchMap.put('u', 'r');
    }

    public static void findPath() throws InterruptedException {

        Tiles start = Tiles.getFirst();
        Tiles end = Tiles.getLast();

        Tiles current = start;
        Tiles endCurrent = end;

        Tiles searchedTile;
        Tiles nextTile;
        Tiles endSearchedTile;
        Tiles endNextTile;

        char currentDirection = 'r';
        char searchDirection = directionMap.get(currentDirection);

        char endCurrentDirection = 'l';
        char endSearchDirection = searchMap.get(endCurrentDirection);

        int counter = 0;
        while(!current.getText().equals("End")) {

            Thread.sleep(250);

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
                String message = "END found Start mark.";
                endCurrent.setStyle("-fx-background-color: #97429b; -fx-border-color: #878787;");
                System.out.println(message);
                break;
            }

            if(current.getMark() == 2) {
                endCurrent.setStyle("-fx-background-color: #f4e841; -fx-border-color: #878787;");
                String message  = "START found End mark.";
                current.setStyle("-fx-background-color: #97429b; -fx-border-color: #878787;");
                System.out.println(message);
                break;
            }

            current.setMark(1);
            endCurrent.setMark(2);

            if(searchedTile.getType() != Tiles.WALL) {
                current = searchedTile;
                char c2 = directionMap.get(currentDirection);
                currentDirection = c2;
                searchDirection = directionMap.get(c2);
            }else if(nextTile.getType() != Tiles.WALL) {
                current = nextTile;
            }else {
                currentDirection = searchMap.get(currentDirection);
                searchDirection = directionMap.get(currentDirection);
            }

            // END search
            if(endSearchedTile.getType() != Tiles.WALL) {
                endCurrent = endSearchedTile;
                char c2 = searchMap.get(endCurrentDirection);
                endCurrentDirection = c2;
                endSearchDirection = searchMap.get(c2);
            }else if(endNextTile.getType() != Tiles.WALL) {
                endCurrent = endNextTile;
            }else {
                endCurrentDirection = directionMap.get(endCurrentDirection);
                endSearchDirection = searchMap.get(endCurrentDirection);
            }

            if(current.getPassed() > 3) {
                String message  = "Start didn't find end.";
                System.out.println(message);
                break;
            }

            if(endCurrent.getPassed() > 3) {
                String message  = "End didn't find start.";
                System.out.println(message);
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
