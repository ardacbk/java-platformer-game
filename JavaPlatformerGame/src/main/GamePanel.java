package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;


import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game){
        this.game = game;
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }





    private void setPanelSize() {
        Dimension screenSize = new Dimension(Game.GAME_WIDTH,Game.GAME_HEIGHT);
        setPreferredSize(screenSize);
        System.out.println(Game.GAME_WIDTH + "x" + Game.GAME_HEIGHT);
    }

    public void updateGame(){}


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }


    public Game getGame(){
        return game;
    }

}
