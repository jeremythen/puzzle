
package tests;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class TilesClass {
    static Tiles[][] tiles;
    public static GridPane getTilesGrid() {
        
        return getTilesGrid(5);
        
    }
    
    public static Tiles[][] getTilesArray() {
        return tiles;
    }
  
    public static GridPane getTilesGrid(int index) {
        
        String s = getStrings(index);
        int width = s.indexOf("\n");
        s = s.replace("\n", "");

        int height = s.length() / width;
        
        tiles = new Tiles[height + 2][width + 2];
        
        int len = tiles.length;

        int counter = 0;

        GridPane gPane = new GridPane();
        
        Tiles tile = null;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                
                Tiles temp; // = new Tiles(s.charAt(counter++));
                
                if(i == 0 || j == 0 || j == (len - 1) || i == (len - 1)) {
                    temp = new Tiles();
                    
                }else {
                   temp = new Tiles(s.charAt(counter++));
                   //temp.setText("" + counter);
                }
                
                   if(j != 0)
                    temp.setLeft(tile);
                   
                   if(tile != null) {
                       if(j != 0)
                        tile.setRight(temp);
                   }
                   tile = temp;
                   tiles[i][j] = temp;
                   
                   
                   if(counter > 0) {
                       Tiles upButton = tiles[i - 1][j];
                       tile.setUp(upButton);
                       upButton.setDown(tile);
                   }

                   temp.setOnAction(e -> {
                       Tiles button = (Tiles) e.getSource();
                       System.out.println(button.getUp().getType());
                       System.out.println(button.getRight().getType());
                       System.out.println(button.getDown().getType());
                       System.out.println(button.getLeft().getType());
                   });
                   gPane.add(temp, j, i);
            }
        }
        Tiles.setFirst(tiles[1][1]);
        Tiles.setLast(tiles[width][height]);
        Tiles.getFirst().setText("Start");
        Tiles.getLast().setText("End");
        
        return gPane;
    }

    
    private static String getStrings(int i) {
        
        List<String> stringList = new ArrayList<>();
        
        stringList.add(TilesClass.a);
        stringList.add(TilesClass.b);
        stringList.add(TilesClass.c);
        stringList.add(TilesClass.d);
        stringList.add(TilesClass.e);
        stringList.add(TilesClass.f);
        stringList.add(TilesClass.g);
        
        return stringList.get(i);
    }
    
    public static class Tiles extends Button {
        
        static final int WALL = 1;
        static final int PATH = 0;
        private static Tiles head;
        private static Tiles tail;
        
        private int type;
        private int passed = 0;
        private int mark = 0;
        private Tiles u;
        private Tiles r;
        private Tiles d;
        private Tiles l;

        protected Tiles get(char dir) {
            switch(dir) {
                case 'u': return this.u;
                case 'r': return this.r;
                case 'd': return this.d;
                case 'l': return this.l;
            }
            return null;
        }
        
        protected Tiles(char t) {
            super();
            if('W' == t) {
                this.type = Tiles.WALL;
                this.setStyle("-fx-background-color: #f45342; -fx-border-color: #878787;");

            }else{
                this.type = Tiles.PATH;
                this.setStyle("-fx-background-color: white; -fx-border-color: #878787;");
            }
                this.setPrefHeight(60);
                this.setPrefWidth(60);
        }
        
        protected Tiles() {
            super();
            this.type = Tiles.WALL;
            this.setStyle("-fx-background-color: #f45342; -fx-border-color: #878787;");
            this.setPrefHeight(60);
            this.setPrefWidth(60);
        }
        
        protected static void setLast(Tiles t) {
            tail = t;
        }
        protected static Tiles getLast() {
            return tail;
        }
        protected static void setFirst(Tiles t) {
            head = t;
        }
        protected static Tiles getFirst() {
            return head;
        }
        
        protected void setType(int type) {
            this.type = type;
        }
        protected int getType() {
            return this.type;
        }
        
        protected void setUp(Tiles u) {
            this.u = u;
        }
        protected void setRight(Tiles r) {
            this.r = r;
        }
        protected void setDown(Tiles d) {
            this.d = d;
        }
        protected void setLeft(Tiles l) {
            this.l = l;
        }
        
        protected Tiles getUp() {
            return this.u;
        }
        protected Tiles getRight() {
            return this.r;
        }
        protected Tiles getDown() {
            return this.d;
        }
        protected Tiles getLeft() {
            return this.l;
        }
        
        @Override
        public String toString() {
            return this.getText();
        }
        
        public void setPassed(int n) {
            this.passed = n;
        }
        public int getPassed() {
            return this.passed;
        }
        
        public void setMark(int n) {
            this.mark = n;
        }
        public int getMark() {
            return this.mark;
        }

    }
    
    
    private static String a = ".W.\n"+
                  ".W.\n"+
                  "...",

              b = ".W.\n"+
                  ".W.\n"+
                  "W..",

              c = "......\n"+
                  "......\n"+
                  ".....W\n"+
                  "......\n"+
                  "......\n"+
                  "......",

              d = "......\n"+
                  "......\n"+
                  "......\n"+
                  "......\n"+
                  ".....W\n"+
                  "....W.",

                 e = "..W.....WW..W.W..\n"+
                         "....W....WW....W.\n"+
                         ".......WW........\n"+
                         "....W.W.W.....WW.\n"+
                         ".......WWWW......\n"+
                         ".....W.W.W...WW..\n"+
                         ".W.....WW........\n"+
                         "...W...WWWW...W..\n"+
                         "....W........W...\n"+
                         "..W....W...W.....\n"+
                         "W.......W......W.\n"+
                         "............WW.W.\n"+
                         "..W.WW.....W...W.\n"+
                         "W....WW......W.WW\n"+
                         "W.......W......WW\n"+
                         "..WWW..........W.\n"+
                         ".WW...WW.....W.W.",

                 f = "..WWWW.WW.WW\n"+
                         "....WW......\n"+
                         ".W.WW.W....W\n"+
                         ".W..WWW.....\n"+
                         ".W...W...W..\n"+
                         ".........W.W\n"+
                         "......WW.WW.\n"+
                         "...WW.W..W..\n"+
                         ".W....W....W\n"+
                         ".....W.W....\n"+
                         "W.W.....W.WW\n"+
                         "............",

                 g = ".....W.WW........WW.WW....W...WWW.W...W..WW...\n"+
                         "...WWW.W......W..W....W..W.WW.........W.WWW...\n"+
                         "WW.WW...WWW.W.WW..W.W..................WW...WW\n"+
                         ".........W.W.W.W....W.W.W......WW...W......W..\n"+
                         ".W.....W....W..W.......W......W...W..W..W.....\n"+
                         "W...WW...W....WW.....W...W...W.WWW.WW...WW.W..\n"+
                         ".W.W..W........W.WWW............W......W..W..W\n"+
                         ".WW.WWW.......W...W.W..W...WW.W.W.W........W..\n"+
                         ".....W...W.....W......W.W..WW..W.WW...........\n"+
                         "......WW..W..W.W..WW.WW.........WWW.W....WW...\n"+
                         "W.W...W.......WW..W...WW.W.WW..W.....W.WW..W.W\n"+
                         ".WW..WW.W..W..W.W.W...WWW..WW..WWWWWW....W...W\n"+
                         "..W...W...W...W.W.W..WW..W...W...WW.W.WW......\n"+
                         "W.WWW........WW.W..W.W..WW..W.WW.WW.WW.......W\n"+
                         ".W...WWW..W.WW........W.W.W.W...WWW.W.W.......\n"+
                         "W...........WWW.W....W...W......W..W..W...W...\n"+
                         ".....WW..W.......W...WW.W....W.W.WWWWWWW..W...\n"+
                         "..W.......W....WW..W.W.WWW....W.W...WW...W....\n"+
                         "....W..WW..W...WW......WW...W..W..WWW.WW.W.W..\n"+
                         "W.W..WW..W..WW.WW.WW..W.W..W..W.W.......WWWW..\n"+
                         "...W..W..WW.WWW..........W....W.W...W.WW...W..\n"+
                         ".W..W.WWW.WW..W.....WWWWW.W.....W......WWW....\n"+
                         ".W...W...WW.WW.W..WW.W.....W..W..W.......W....\n"+
                         ".W.....W.....WW.WWWWWW.....W..W.W.......WW..W.\n"+
                         "...W.W...WWW.W....W...WW....WW.W.WW...WW..W...\n"+
                         "...W..W..W.W.W.W..................WW.W.....W.W\n"+
                         "...WW.W....W.......W.W..W...W.WW.WW..W..W..W.W\n"+
                         "...WWW.WWWW.W.WWW.W..W....W.W.W.W...W.WWW..W..\n"+
                         "..WW...WW.W..W....WW....W.WW..W..WW..WWW.WWWW.\n"+
                         "....W.....WWW.W.W.......W.W..W...WW..W.WW.W.WW\n"+
                         "..WW.W.W......W....W...WW...W...W.WW..W..W....\n"+
                         "WWWW....W..W..........W....W.W..WW...W..W..WWW\n"+
                         ".W.....W.W..W.W...W.....WW..W....W.WWW........\n"+
                         ".WW..........W.W.........WWWW.W.W..W......WW.W\n"+
                         "W...WW..W..W...WW....W....W....WWWW..W...W..W.\n"+
                         ".W.W.....WW...WW....W............WW......W....\n"+
                         "...WW...W..W...W.WW...WWWWW.WWW.........W...W.\n"+
                         "...W.WWW......W.W..W....W.W.W.WWWW......W.WW..\n"+
                         "..W..W.W...WW..WW....W.......W..WWW.WW.....WW.\n"+
                         ".W.W.......W...W.W..W..W..WWW......W..........\n"+
                         ".W.W..W.WW.W..W...W.W.W..W........W..W...W..W.\n"+
                         ".W.........W..W.W.W..W....W....W.W....W.W...W.\n"+
                         "WW.WW...........W.W.W...W.WW..W..W..W.W....W..\n"+
                         "W....WW.WWW...W....W..W....W..W..W..W.....W...\n"+
                         "......WWW.W.W..W..W..W..WW.......W..WW.W......\n"+
                         "W....W.W..WWW.W..............W...WW....WW...W.";
    
    
}
