/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GameScreen;
/**
 *
 * @author rayst
 */
public class KeyboardInputs implements KeyListener{

    //initialize gameScreen
    public boolean pressedW, pressedS,pressedA,pressedD;
    private GameScreen gameScreen;
    
    
    //CONSTRUCTOR
    public KeyboardInputs(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    
    //FUNCTION TO CHECK FOR KEY PRESSES
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            //CHECKS WHICH KEY WAS PRESSED
            //And adjusts their position by 5 pixels 
            //Y = UP AND DOWN
            //X = LEFT AND RIGHT
            case KeyEvent.VK_W:
                System.out.println("W");
                pressedW = true;
                break;
            case KeyEvent.VK_A:
                System.out.println("A");
                pressedA = true;
                break;
            case KeyEvent.VK_S:
                System.out.println("S");
                pressedS = true;
                break;
            case KeyEvent.VK_D:
                System.out.println("D");
                pressedD = true;
                break;
        }
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()){
            // CHECKS IF THE KEY IS RELEASED
            // THEN SETS MOVING = FALSE
            case KeyEvent.VK_W:
                System.out.println("WR");
                pressedW = false;
                break;
            case KeyEvent.VK_A:
                System.out.println("AR");
                pressedA = false;
                break;
            case KeyEvent.VK_S:
                System.out.println("SR");
                pressedS = false;
                break;
            case KeyEvent.VK_D:
                System.out.println("DR");
                pressedD = false;
                break;
        }
    }
    
}
