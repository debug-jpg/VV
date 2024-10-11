package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;

import tile.TileManager;

import entity.*;

@SuppressWarnings("all")
public class GamePanel extends JPanel implements Runnable {
    TileManager tile = new TileManager(this);

    // ----------SCREEN----------//
    final int originalTileSize = 16; // 16x16 Tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 Upscaled
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 960px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    public int ScreenWidth = screenWidth;
    public int ScreenHeight = screenHeight;

    // ----------WORLD----------//
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // ----------FULL SCREEN----------//
    public int fullScreenWidth = screenWidth;
    public int fullScreenHeight = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    int FPS = 60;

    // Initiate

    public KeyHandler key = new KeyHandler(this);
    Sound music = new Sound();
    Sound effects = new Sound();
    public CollisionChecker hitbox = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler handler = new EventHandler(this);
    Thread gameThread;

    // ENTITIES and OBJECTS
    public Saki saki = new Saki(this, key);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int optionState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        g2 = (Graphics2D) tempScreen.getGraphics();

        setFullScreen();
    }

    public void setFullScreen() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fullScreenWidth = (int) width;
        fullScreenHeight = (int) height;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawTempScreen();
                drawScreen();
                delta--;
            }

        }
    }

    public void update() {

        if (gameState == playState) {
            saki.update();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // -----
        }

    }

    public void drawTempScreen() {

        // ---------- TITLE ----------//
        if (gameState == titleState) {
            ui.draw(g2);
        }
        // ---------- OTHERS ----------//
        else {

            // ---------- TILE ------------//
            tile.draw(g2);

            // ADDS ENTITIES
            entityList.add(saki);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // EMPTY ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

            // ---------- UI ----------//
            ui.draw(g2);

        }

    }

    public void drawScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, fullScreenWidth, fullScreenHeight, null);
        g.dispose();

    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        effects.setFile(i);
        effects.play();
    }

    public int getScreenWidth() {
        return ScreenWidth;
    }

    public int getScreenHeight() {
        return ScreenHeight;
    }

    public int getButton() {
        return button;
    }

    int button;
}
