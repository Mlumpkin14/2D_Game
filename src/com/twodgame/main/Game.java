package com.twodgame.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game  extends Canvas implements Runnable {

    private static final long serialVersionUID = 6441480457671744739L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12* 9;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    public enum STATE {
        menu,
        help,
        game,

    };

    public STATE gameState = STATE.menu;


    public Game() {
        setFocusable(true);
        handler = new Handler();
        menu = new Menu(this, handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "let's build a Game!", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        r = new Random();

        if(gameState == STATE.game) {
            handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT-50), ID.basicEnemy, handler));
        } else {
            for(int i = 0; i < 10; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.menuParticle, handler));
            }
        }




        //handler.addObject(new EnemyBoss((Game.WIDTH / 2) -48,-120, ID.enemyBoss, handler));









    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;

            }

        }
        stop();
    }

    private void tick(){
        handler.tick();

        if(gameState == STATE.game) {
            hud.tick();
            spawner.tick();
        } else if (gameState == STATE.menu){
            menu.tick();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        if(gameState == STATE.game) {
            hud.render(g);
        }  else if (gameState == STATE.menu ||  gameState == STATE.help){
            menu.render(g);
        }

        g.dispose();
        bs.show();

    }

    public static int clamp(float var,float min, float max) {
        if(var >= max) {
            return (int) (var = max);
        } else if (var <= min) {
            return (int) (var = min);
        } else return (int) var;
    }

    public static void main(String[] args) {
	// write your code here
        new Game();
    }
}
