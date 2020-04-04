package com.twodgame.main;

import java.awt.*;
import java.util.Random;
import java.util.ResourceBundle;

public class Player extends  GameObject {

    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32,32);
    }

    @Override
    public void tick() {
        x += speedX;
        y += speedY;

        x = Game.clamp((int)x, 0, Game.WIDTH-37);
        y = Game.clamp((int)y, 0, Game.HEIGHT - 60);

        handler.addObject(new Trail((int)x, (int)y, ID.trail, 32,32, Color.cyan, 0.02f, handler));

        collision();

    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);

    }

    private void collision() {
        for(int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject =handler.object.get(i);

            if(tempObject.getId() == ID.basicEnemy || tempObject.getId() == ID.fastEnemy || tempObject.getId() == ID.smartEnemy) {

                if(getBounds().intersects(tempObject.getBounds())){
                    //Collision Code
                    HUD.HEALTH -= 2;
                }
            }



        }
    }


}
