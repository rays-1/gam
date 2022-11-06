/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author rayst
 */
public class Main {
    public static void main(String[]args){
        JFrame Screen = new JFrame();
        Screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen.setResizable(false);
        Screen.setTitle("Lost Broom");
        GameScreen gameScreen = new GameScreen();
        Screen.add(gameScreen);
        Screen.pack();
        Screen.setLocationRelativeTo(null);
        Screen.setVisible(true);
        
        gameScreen.startGameLoop();
    }
}
