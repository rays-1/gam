/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tiles;

import static Entities.Player.LEVEL_SPRITE;
import static Entities.Player.getSprites;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import main.GameScreen;
import static main.GameScreen.*;

/**
 *
 * @author rayst
 */
public class TileManager {
    GameScreen gs;
    public static BufferedImage[] terrainSprites;
    public static Tile[] levelData;
    int mapTileNum[][];
    
    public TileManager(GameScreen gs){
        this.gs = gs;
        levelData = new Tile[315];
        mapTileNum = new int[gs.maxWorldColumns][gs.maxWorldRow];
        getTiles();
        loadMap();
        
        for (int i = 0; i < gs.maxWorldColumns; i++) {
            for (int j = 0; j < gs.maxWorldRow; j++) {
                if (mapTileNum[i][j] == 1) {
                    System.out.println("here");
                }
            }
        }
    }
    
    public void getTiles(){
        BufferedImage img = getSprites(LEVEL_SPRITE);
        terrainSprites = new BufferedImage[315];
        for(int j = 0; j < 15; j++){
            for(int i = 0; i < 21; i++){
                int index = j*21 + i;
                terrainSprites[index] = img.getSubimage(i*24,j*24,24,24);
            }
        }
        levelData = new Tile[315];
        for(int i = 0; i < 315; i++){
            levelData[i] = new Tile();
            levelData[i].img = terrainSprites[i];
            if(i != 4){
                levelData[i].collision = true;
            }
        }
    }
    
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0,row = 0,mapIndx = 0;
            
            while(col < gs.maxWorldColumns && row < gs.maxWorldRow){
                String line = br.readLine();
                while(row < gs.maxWorldRow){
                    String indexes[] = line.split(" ");
                    
                    int num = Integer.parseInt(indexes[mapIndx]);
                    
                    mapTileNum[row][col] = num;
                    row++;
                    mapIndx++;
                    if(row == gs.maxWorldRow){
                        row = 0;
                        col++;
                    }
                }
            }
            
            br.close();
        }catch(Exception e){
            
        }
    }
    
    public void draw(Graphics2D g2){
        int worldCol = 0,worldRow = 0;
        
        while(worldCol < gs.maxWorldColumns && worldRow < gs.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * TILES_SIZE;
            int worldY = worldRow * TILES_SIZE;
            int screenX = worldX - gs.player.terrainx + gs.player.screenx;
            int screenY = worldY - gs.player.terrainy + gs.player.screeny;
            
            if(worldX + TILES_SIZE> gs.player.terrainx - gs.player.screenx &&
                    worldX - TILES_SIZE< gs.player.terrainx + gs.player.screenx &&
                    worldY + TILES_SIZE> gs.player.terrainy - gs.player.screeny &&
                    worldY - TILES_SIZE< gs.player.terrainy + gs.player.screeny){
                g2.drawImage(levelData[tileNum].img, screenX,screenY,TILES_SIZE,TILES_SIZE,null);  
            }
             

            worldCol++;
            
            if(worldCol == gs.maxWorldColumns){
                worldCol = 0;
                worldRow++;
            }
        }
    }
    
}
