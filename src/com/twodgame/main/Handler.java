package com.twodgame.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<>();

    public void tick(){

        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }

    }

    public void render(Graphics g){

        for(int i = 0; i < object.size(); i++) {

            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }
    public void clearEnemies(){
        for(int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if(tempObject.getId() == ID.player) {
                object.clear();
                addObject((new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.player, this)));
            }


        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }



}
