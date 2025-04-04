package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x,y;
    protected int width,height;
    protected Rectangle2D.Float hitbox;

    public Entity(float x, float y,int w,int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    protected void drawHitbox(Graphics g){
        // For debbugging hitbox
        g.setColor(Color.RED);
        g.drawRect((int)hitbox.x,(int) hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    protected void initHitbox(float x, float y, float width, float height){
        hitbox = new Rectangle2D.Float(x,y,width,height);
    }

//    protected void updateHitbox(){
//        hitbox.x = x;
//        hitbox.y = y;
//    }

    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
}
