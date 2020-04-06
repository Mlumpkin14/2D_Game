package com.twodgame.main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject {

    private Handler handler;
    private Random r = new Random();

    private Color col;
    int dir = 0;


    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        dir = r.nextInt(2);
        if (dir == 0) {
            speedX = 2;
            speedY = 9;
        } else if (dir == 1) {
            speedX = 9;
            speedY = 2;
        }

        speedX = 2;
        speedY = 9;

        col = new Color(r.nextInt(255), r.nextInt(255),r.nextInt(255));


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

        handler.addObject(new Trail((int)x, (int)y, ID.trail, 16,16, col, 0.02f, handler));

    }


    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16,16);
    }
}