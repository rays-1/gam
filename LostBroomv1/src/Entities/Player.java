/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Inputs.KeyboardInputs;
import main.GameScreen;

/**
 *
 * @author rayst
 */
public class Player extends Entity{
    GameScreen gs;
    KeyboardInputs ki;

    public Player(GameScreen gs,KeyboardInputs ki) {
        this.gs = gs;
        this.ki = ki;
    }
    
    
}
