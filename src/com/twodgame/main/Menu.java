package com.twodgame.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Random r = new Random();

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.menu){
            //play button
            if (mouseOver(mx,my,210,150,200,64)){
                game.gameState = Game.STATE.game;
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.player, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.basicEnemy, handler));
            }
            //Help Button
            if(mouseOver(mx,my, 210,250,200,64)){
                game.gameState = Game.STATE.help;
            }
            //Exit button
            if( mouseOver(mx,my,210,350,200,64)){
                System.exit(1);
            }
        }

        //Back Button for help
        if(game.gameState == Game.STATE.help){
            if(mouseOver(mx,my,210,350,200,64)){
                game.gameState = Game.STATE.menu;
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width,int height) {
        if((mx > x) && (mx < (x + width))) {
            if(my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void tick(){

    }

    public void render(Graphics g) {
        if (game.gameState == Game.STATE.menu){
            Font font = new Font("arial",1,50);
            Font font2 = new Font("arial",1,30);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Menu",240,70);

            g.setFont(font2);
            g.drawString("Play",270,190);
            g.drawRect(210,150,200,64);

            g.drawString("Help",270,290);
            g.drawRect(210,250,200,64);

            g.drawString("Quit",270,390);
            g.drawRect(210,350,200,64);
        } else if(game.gameState == Game.STATE.help){
            Font font = new Font("arial",1,50);
            Font font2 = new Font("arial",1,30);
            Font font3 = new Font("arial",1,20);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("Help",240,70);

            g.setFont(font3);
            g.drawString("Use WASD to move player and dodge enemies =)",50,200);

            g.setFont(font2);
            g.drawRect(210,350,200,64);
            g.drawString("Back",270,390);
        }



    }
}
