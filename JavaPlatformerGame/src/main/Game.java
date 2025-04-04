package main;

import entities.Player;
import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private Player player;
	private LevelManager levelManager;

	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILE_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_IN_WIDTH * TILE_SIZE;
	public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILE_SIZE;

	public Game() {
		initClases();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();
	}

	private void initClases() {
		levelManager = new LevelManager(this);
		player = new Player(200,200,(int) (64*SCALE), (int) (40*SCALE));
		player.setLevelData(levelManager.getCurrentLevel().getLevelData());
	}

	private void startGameLoop(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	private void update(){
		player.update();
	}

	public void render(Graphics g){
		levelManager.draw(g);
		player.render(g);
	}

	@Override
	public void run() {
		double timePerFrame = (double) 1000000000 / FPS_SET;
		double timePerUpdate = (double) 1000000000 / UPS_SET;

		long prevTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU=0;
		double deltaF=0;

		while (true) {
			long currentTime = System.nanoTime();


			deltaU += (currentTime - prevTime) / timePerUpdate;

			if(deltaU >= 1){
				update();
				updates++;
				deltaU--;
			}

			deltaF += (currentTime - prevTime) / timePerFrame;
			if(deltaF >= 1){
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			prevTime = currentTime;


			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public Player getPlayer(){
		return player;
	}

	public void windowLostFocus() {
		player.resetDirBools();
	}
}
