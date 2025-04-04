package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData){
        if(!IsSolid(x,y,levelData))
            if(!IsSolid(x+width,y+height,levelData))
                if(!IsSolid(x+width,y,levelData))
                    if(!IsSolid(x,y+height,levelData))
                        return true;

        return false;
    }

    private static boolean IsSolid(float x, float y,int[][] levelData){


        if(x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if(y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILE_SIZE;
        float yIndex = y / Game.TILE_SIZE;

        int value = levelData[(int)yIndex][(int)xIndex];


        if(value != 11)
            return true;
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
        int currentTile = (int)(hitbox.x / Game.TILE_SIZE);
        // Colliding to right
        if(xSpeed > 0) {
            int tileXPos = currentTile * Game.TILE_SIZE;
            int xOffset = (int) (Game.TILE_SIZE - hitbox.width);
            return xOffset + tileXPos - 1;
        }
        //Left
        else{
            return currentTile * Game.TILE_SIZE;
        }
    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed){
        int currentTile = (int)(hitbox.y / Game.TILE_SIZE);
        if(airSpeed > 0) { //Falling
            int tileYPos = currentTile * Game.TILE_SIZE;
            int yOffset = (int) (Game.TILE_SIZE - hitbox.height);
            return yOffset + tileYPos - 1;
        }
        else{ // Jumping
            return currentTile * Game.TILE_SIZE;
        }
    }

    public static boolean IsEntityOnGround(Rectangle2D.Float hitbox,int[][] levelData){
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height +1, levelData)
                && !IsSolid(hitbox.x+ hitbox.width, hitbox.y + hitbox.height + 1,  levelData)){
            return false;
        }
        return true;
    }
}
