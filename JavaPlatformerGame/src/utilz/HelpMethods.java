package utilz;

import main.Game;

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
}
