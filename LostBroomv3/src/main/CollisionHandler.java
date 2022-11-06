/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import Entities.Entity;
import static Utilities.directions.*;
import static main.GameScreen.TILES_SIZE;
/**
 *
 * @author LIZHIEL MAE
 */
public class CollisionHandler {

    GameScreen gs;
    public CollisionHandler(GameScreen gs) {
        this.gs = gs;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.terrainx + entity.hitbox.x;
        int entityRightWorldX = entity.terrainx + entity.hitbox.x + entity.hitbox.width;
        int entityTopWorldY = entity.terrainy + entity.hitbox.y;
        int entityBottomWorldY = entity.terrainy + entity.hitbox.y + entity.hitbox.height;
        
        int entityLeftCol = entityLeftWorldX/TILES_SIZE;
        int entityRightCol = entityRightWorldX/TILES_SIZE;
        int entityTopRow = entityTopWorldY/TILES_SIZE;
        int entityBottomRow = entityBottomWorldY/TILES_SIZE;
        
        int tileNum1,tileNum2;
        
        switch(entity.direction){
            case UP:
                entityTopRow = (entityTopWorldY - entity.speed)/TILES_SIZE;
                tileNum1 = gs.tilehandler.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gs.tilehandler.mapTileNum[entityRightCol][entityTopRow];
                if(gs.tilehandler.levelData[tileNum1].collision == true || gs.tilehandler.levelData[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed)/TILES_SIZE;
                tileNum1 = gs.tilehandler.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gs.tilehandler.mapTileNum[entityRightCol][entityBottomRow];
                if(gs.tilehandler.levelData[tileNum1].collision == true || gs.tilehandler.levelData[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed)/TILES_SIZE;
                tileNum1 = gs.tilehandler.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gs.tilehandler.mapTileNum[entityLeftCol][entityBottomRow];
                if(gs.tilehandler.levelData[tileNum1].collision == true || gs.tilehandler.levelData[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.speed)/TILES_SIZE;
                tileNum1 = gs.tilehandler.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gs.tilehandler.mapTileNum[entityRightCol][entityBottomRow];
                if(gs.tilehandler.levelData[tileNum1].collision == true || gs.tilehandler.levelData[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
    
}
