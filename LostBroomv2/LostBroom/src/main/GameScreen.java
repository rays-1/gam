/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Entities.Player;
import Inputs.*;
import Tiles.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author rayst
 */
public class GameScreen extends JPanel implements Runnable{
    //initialize the max Frames per second & Updates per second (final keyword means final // hindi na pwede baguhin)
    private final int FPS = 60;
    private final int UPS = 100;
    //to see pixel art on screen you need to scale your character so that you can see it!
    
    public final static int  TILES_DEFAULT_SIZE = 24; //default sprite size here is 24x24 px
    public final static float SCALE = 2.5f; // we scale it by 2.5x
    
    public final static int TILES_WIDTH = 21; // in your screen we need 22 blocks in width
    public final static int TILES_HEIGHT = 15; // and max of 20 block height
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE); //60
    
    public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH; //1260 SCREEN WIDTH
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_HEIGHT; //900 SCREEN HEIGHT
    TileManager tilehandler = new TileManager(this);
    KeyboardInputs keylistener = new KeyboardInputs(this);
    MouseInputs mouselistener = new MouseInputs(this);
    Thread gameLoop;
    public Player player = new Player(this,keylistener);
    
    //set player default pos
    int playerX = 100,playerY = 100,speed = 2;
    //world variables
    public final int maxWorldColumns = 61;
    public final int maxWorldRow = 61;
    public final int worldWidth = TILES_SIZE * maxWorldColumns;
    public final int worldHeight = TILES_SIZE * maxWorldRow;
    
    
    
    public GameScreen() {
        this.setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keylistener);
        this.addMouseListener(mouselistener);
        this.setFocusable(true);
    }

    public void startGameLoop(){
        gameLoop = new Thread(this);
        //if the start function is called the run function is called too.
        gameLoop.start();
    }
    
    @Override
    public void run() {
        //stores the value of 1million/fps && 1 million / ups
        //this whole function is a gameloop
        //a game loop a sequence of processes that run continuously as long as the game is running.
        
        //What this basically does is for every second or even milisecond nanosecond
        
        
        //initialize variables
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
        //System.currentTimeMillis returns the current time in nanoseconds
        long prevTime = System.nanoTime();
        int frames = 0,updates = 0;
        //System.currentTimeMillis returns the current time in miliseconds
        long lastCheck = System.currentTimeMillis();
        double deltaUpd = 0,deltaFps = 0;
        //infinite loop
        while(true){
            long currentTime = System.nanoTime();
            
            //Update time to (currentTime - previousTime) then divide by timePerFrame;
            deltaUpd += (currentTime - prevTime) / timePerUpdate;
            deltaFps += (currentTime - prevTime) / timePerFrame;
            prevTime = currentTime;
            
            
            //if deltaUpdate is greater than or equal to 1 the game UPDATES
            if(deltaUpd >= 1){
                update();
                updates++;
                deltaUpd--;
            }
            
            //if the game fps is greater than or equal to 1 the game repaints
            //repaint is basically repainting all the contents of the screen thus Updating the sprites animations etc etc
            if(deltaFps >= 1){
                repaint();
                frames++;
                deltaFps--;
            }
             
            //this is to print out the frames onto the console
            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                //prints out the current frames per second and updates per second
                System.out.println("Fps: " + frames + "|" + " Ups: " + updates);
                frames=0;
                updates = 0;
            }
        }
    }
    
    public void update(){
        player.update();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //change type of g to Graphics2D
        Graphics2D g2 = (Graphics2D)g;
        tilehandler.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
