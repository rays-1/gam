/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Inputs.KeyboardInputs;
import static Utilities.constants.*;
import static Utilities.directions.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import main.GameScreen;
import static main.GameScreen.*;

/**
 *
 * @author rayst
 */
public class Player extends Entity{
    public final int screenx;
    public final int screeny;
    GameScreen gs;
    KeyboardInputs ki;
    private int aniTick,aniIndex,animSpeed = 15;
    private int currentani = IDLE;
    private boolean moving = false,attacking = false,wasleft = false,wasright= true;
    public static final String PLAYER_SPRITE = "sprite.png";
    public static final String LEVEL_SPRITE = "levelsprite.png";
    public static final String LEVEL_DATA = "levelonedata.png";
    
    public Player(GameScreen gs,KeyboardInputs ki) {
        this.gs = gs;
        this.ki = ki;
        
        screenx = GAME_WIDTH/2 - (TILES_SIZE/2);
        screeny = GAME_HEIGHT/2- (TILES_SIZE/2);
        loadSpriteAnim();
        init();
    }
    //init // initialize
    public void init(){
        terrainx = TILES_SIZE * 10;
        terrainy = TILES_SIZE *87;
        speed = 5;
        direction =  RIGHT;
    } 
    
    public void update(){
        moving = false;
        if(ki.pressedW == true){
            moving = true;
            direction = UP;
            terrainy -= speed;
        }
        else if(ki.pressedS == true){
            moving = true;
            direction = DOWN;
            terrainy += speed;
        }
        else if(ki.pressedA == true){
            moving = true;
            direction = LEFT;
            terrainx -= speed;
        }
        else if(ki.pressedD == true){
            moving = true;
            direction = RIGHT;
            terrainx += speed;
        }
        updateAnimation();
        setState();
    }
    
    public void draw(Graphics2D g2){
        int xAxis = screenx,yAxis = screeny,width = 64,height = 64;
        
        //This checks if which button was clicked so
        //if the A button is clicked the sprite is supposed to be facing the left side
        //thus making the width negative and adding the width to the xAxis THUS flipping the image
        //basically all of this code just flips the sprite based on what button was clicked
        //so left = faces the character to the left right = faces the character to the right
        
        if(ki.pressedA){
            g2.drawImage(animations[currentani][aniIndex],xAxis+width,yAxis,-width,height,null);
            wasleft = true;
            wasright = false;
        }else if(ki.pressedD){
            g2.drawImage(animations[currentani][aniIndex],xAxis,yAxis,width,height,null);
            wasright = true;
            wasleft = false;
        }else{
            if(wasleft == true){
                g2.drawImage(animations[currentani][aniIndex],xAxis+width,yAxis,-width,height,null);

            }else if(wasright == true){
                g2.drawImage(animations[currentani][aniIndex],xAxis,yAxis,width,height,null);

            }else{
                g2.drawImage(animations[currentani][aniIndex],xAxis,yAxis,width,height,null);
            }
        }
    }

    private void loadSpriteAnim() {
                
        //gets the player sprites from LoadSave class
        BufferedImage img = getSprites(PLAYER_SPRITE);


        // sets the animation variable to a new 2D ARRAY
        //2d arrays are basically arrays inside arrays
        //ex [[a,b,c],[d,e,f],[g,h,i]];
        //to get the value g we need to go to the third box thus array[2]
        //then we get the first element from the third box thus array[2][0]
        //Here we set the size of the 2d array to have 5 BOXES and 6 ElEMENTS in each one
        animations = new BufferedImage[5][6];

        //Here we loop through each box
        for(int j = 0; j < animations.length;j++){
            //Here we loop through each element in a Box
            for(int i = 0; i < animations[j].length;i++){
                //Here we store the images we get into an array
                animations[j][i] = img.getSubimage(i*16, j*16, 16, 16);
            }
        }
    }
    
    public static BufferedImage getSprites(String filename){
        BufferedImage img = null;
                //Get the sprite sheet from  our resources folder
        InputStream is = Player.class.getResourceAsStream("/"+filename);
        try {
            img = ImageIO.read(is);
        } catch (IOException ex) {
           ex.printStackTrace();
        }finally{
            try{
                is.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return img;
    }
    
        private void updateAnimation() {
        
        //updates the tick of the animation or so called speed of the animation to match
        //the frames per second.
        aniTick++;
        //checks if the tick is greater OR equal to the speed of the animation which should be 0.143 frames per second
        if(aniTick >= animSpeed){
            //resets the tick
            aniTick = 0;
            //increments the index thus getting the next frame on the animation
            aniIndex++;
            //after all that it checks for another condition
            //if the index is greater or equal to the amount of frames a certain animation has
            //for ex our idle animation only has 4 frames
            if(aniIndex >= getFrameAmount(currentani)){
                //if it reached the max amount of frames it resets the index to 0
                //THUS creating an infinite loop
                aniIndex = 0;
                //sets attacking to false
                attacking = false;
            }
        }
    }

    private void setState() {
        //checks which animation state its currently in
        // for ex IDLE,RUN,JUMP,HIT,ATTACK
        int startAni = currentani;
        //checks if the character is moving
        if(moving){
            //sets the animation to be the running animation
            currentani = RUN;
        }else{
            //if its not moving the animation stays idle
            currentani = IDLE;
        }
        //if the character is attacking
        if(attacking){
            //sets the animation to attack
            currentani = ATTACK;
        }
        
        //checks if the variable startingAni is not equal to the current animation
        if(startAni != currentani){
            //if so it  resets the animation tick 
            resetAnimationTick();
        }
    }

    private void resetAnimationTick() {
        //same as name implies basically resets.
        aniTick = 0;
        aniIndex = 0;
    }
}
