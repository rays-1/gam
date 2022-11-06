package Utilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rayst
 */
public class constants {
    
    //the number of animations in order 
    
    public static final int IDLE = 0;
    public static final int RUN = 1;
    public static final int JUMP = 2;
    public static final int HIT = 3;
    public static final int ATTACK = 4;
    
    
    public static int getFrameAmount(int action){
        //this function returns how many frames a certain action has
        //for example out  idle animation only has 4 frames so it returns 4
        switch(action){
            case IDLE:
                return 4;
            case RUN:
                return 6;
            case JUMP:
                return 3;
            case HIT:
                return 1;
            case ATTACK:
                return 3;
            default:
                return 1;
        }
    }
}
