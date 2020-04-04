package com.twodgame.main;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject {

    private Handler handler;
    Random r = new Random();
    private int timer = 70;
    private int timer2 = 50;

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        speedX = 0;
        speedY = 2;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 96,96);
    }



    public void tick() {
        x += speedX;
        y += speedY;

        if (timer <= 0) {
            speedY = 0;
        } else {
            timer--;
        }

        if (timer <= 0) {
            timer2--;
        }
        if (timer2 <= 0) {
            if (speedX == 0) {
                speedX = 5;
            }
            int spawn = r.nextInt(10);
            if (spawn == 0) {
                handler.addObject(new EnemyBullet((int)x + 48, (int)y + 48, ID.enemyBullet, handler));
            }
        /*if(y <= 0 || y>= Game.HEIGHT - 32) {
            speedY *= -1;
        }*/
            if (x <= 0 || x >= Game.WIDTH - 32) {
                speedX *= -1;

            }
        }
            //handler.addObject(new Trail((int)x, (int)y, ID.trail, 96,96, Color.red, 0.02f, handler));

        }


        public void render (Graphics g){
            g.setColor(Color.red);
            g.fillRect((int) x, (int) y, 96, 96);
        }
}
