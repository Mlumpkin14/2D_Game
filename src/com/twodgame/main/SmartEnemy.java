package com.twodgame.main;

import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;


    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.player) {
                player = handler.object.get(i);
            }
        }

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16,16);
    }

    public void tick() {
        x += speedX;
        y += speedY;

        float diffX = x - player.getX()- 8;
        float diffY = y - player.getY()- 8;
        float distance = (float)Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())* (y-player.getY()));

        speedX = (float)((-1.0/distance)*diffX);
        speedY = (float)((-1.0/distance)*diffY);

        if(y <= 0 || y>= Game.HEIGHT - 32) {
            speedY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH - 16) {
            speedX *= -1;

        }

        handler.addObject(new Trail((int)x, (int)y, ID.trail, 16,16, Color.green, 0.02f, handler));

    }


    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)x, (int)y, 16,16);
    }
}
