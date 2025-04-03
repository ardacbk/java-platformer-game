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
        Dimension screenSize = new Dimension(1280,800);
        setMinimumSize(screenSize);
        setPreferredSize(screenSize);
        setMaximumSize(screenSize);
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
