/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GameScreen;
/**
 *
 * @author rayst
 */
public class MouseInputs implements MouseListener,MouseMotionListener {
    //INITIALIZE GAMESCREEN
    private GameScreen gameScreen;
    //CONSTRUCTOR
    public MouseInputs(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    //CHECK FOR CLICKS
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("dragg");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        gameScreen.setRectPos(e.getX(), e.getY());
    }
    
}
