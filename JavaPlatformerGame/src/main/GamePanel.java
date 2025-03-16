package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta=100, yDelta=100;
    private BufferedImage img,subImg;

    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        importImg();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void setPanelSize() {
        Dimension screenSize = new Dimension(1280,800);
        setMinimumSize(screenSize);
        setPreferredSize(screenSize);
        setMaximumSize(screenSize);
    }

    public void changeXDelta(int val){
        this.xDelta+=val;
    }

    public void changeYDelta(int val){
        this.yDelta+=val;
    }

    public void setRectPosition(int x, int y){
        xDelta=x;
        yDelta=y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        subImg = img.getSubimage(1*64,8*40,64,40);
        g.drawImage(subImg,(int)xDelta,(int)yDelta,128,80,null);

    }



}
