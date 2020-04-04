package com.twodgame.main;

import java.awt.*;

public class BasicEnemy extends GameObject {

    private Handler handler;


    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        speedX = 5;
        speedY = 5;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16,16);
    }



    public void tick() {
        x += speedX;
        y += speedY;

        if(y <= 0 || y>= Game.HEIGHT - 32) {
            speedY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH - 32) {
            speedX *= -1;

        }

        handler.addObject(new Trail((int)x, (int)y, ID.trail, 16,16, Color.red, 0.02f, handler));

    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16,16);
    }
}
