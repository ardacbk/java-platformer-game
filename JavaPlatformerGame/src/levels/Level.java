package levels;

import utilz.LoadSave;

public class Level {
    private int levelData[][];
    public Level(int[][] levelData){
        this.levelData = levelData;
    }

    public int getLevelData(int x,int y){
        return levelData[y][x];
    }
}
