package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private Player player;

	public Game() {
		initClases();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLoop();
	}

	private void initClases() {
		player = new Player(200,200);
	}

	private void startGameLoop(){
		gameThread = new Thread(this);
		gameThread.start();
	}

	private void update(){
		player.update();
	}

	public void render(Graphics g){
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
