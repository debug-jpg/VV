package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//import java.util.Collections;
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
    public QuizManager quiz = new QuizManager(this);
    public CollisionChecker hitbox = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler handler = new EventHandler(this);
    Thread gameThread;

    // ENTITIES and OBJECTS
    public Saki saki = new Saki(this, key);
    public Entity obj[] = new Entity[100];
    public Entity npc[] = new Entity[100];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public static final int titleState = 0;
    public static final int playState = 1;
    public static final int pauseState = 2;
    public static final int dialogState = 3;
    public static final int optionState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void setupGame() {
        assetSetter.setObject();
//        assetSetter.setNPC();
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        g2 = (Graphics2D) tempScreen.getGraphics();

//        setFullScreen();
    }

//    public void setFullScreen() {
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        fullScreenWidth = (int) screenSize.getWidth();
//        fullScreenHeight = (int) screenSize.getHeight();
//        Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        final double drawInterval = 1_000_000_000.0 / FPS;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;
        double delta = 0;

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            while (delta >= 1) {
                update();
                drawTempScreen();
                drawScreen();
                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            saki.update();
            for (Entity npcEntity : npc) {
                if (npcEntity != null) {
                    npcEntity.update();
                }
            }
        }
        if (gameState == pauseState) {}
    }

    public void updateEntityList() {
        entityList.clear();
        entityList.add(saki);

        for (Entity npcEntity : npc) {
            if (npcEntity != null) {
                entityList.add(npcEntity);
            }
        }

        for (Entity objEntity : obj) {
            if (objEntity != null) {
                entityList.add(objEntity);
            }
        }

        entityList.sort(Comparator.comparingInt(e -> e.worldY));
    }

    public void drawTempScreen() {
        if (gameState == titleState) {
            ui.draw(g2);
        }
        else {
            tile.draw(g2);
            updateEntityList();

            for (Entity entity : entityList) {
                entity.draw(g2);
            }

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
}
