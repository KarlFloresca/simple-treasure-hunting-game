package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // screen settings
    final int basicTileSize = 16;
    final int scale = 3;
    public final int tileSize = basicTileSize * scale;

    // set screen size
    public final int maxScreenCol = 15;
    public final int maxScreenRow = 9;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // world settings
    public final int maxWorldCol = 80;
    public final int maxWorldRow = 30;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // Frames per second
    int fps = 60;

    // instanciation of classes
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public AssetSetter aSetter = new AssetSetter(this); 
    public Player player = new Player(this, keyH);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public SuperObject obj[] = new SuperObject[10];
   
    // displays the gamepanel
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        aSetter.setObject();
    }

    // allows the use of threads during the runtime of the game
    Thread gameThread;
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // game loop setup
    public void run(){
        
        double interval = 1000000000/fps;
        double nextDrawTime = System.nanoTime() + interval;

        while(gameThread != null){

            // update information
            update();

            // draw screen with updated information
            repaint();

            // use to limit the update and repaint method
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += interval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void update(){

        // handles movement using the key handler
        player.update();
    }

    // handles the display of characters and objects
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // class with functions for graphics
        Graphics2D g2 = (Graphics2D)g;

        // draw background tiles
        tileM.draw(g2);
        
        // draws objects
        for(int i = 0; i < obj.length; i++){
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // draws the character
        player.draw(g2);

        // just to save storage
        g2.dispose();

    }
}
