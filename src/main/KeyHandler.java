package main;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("all")
public class KeyHandler implements KeyListener{

    public boolean up, down, left, right, enter;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if (gp.gameState == gp.titleState) {

            if (gp.ui.titleScreenState == 0) {

                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1) {
                        JOptionPane.showMessageDialog(null, "Coming Soon...");
                    }
                    if (gp.ui.commandNum == 2) {
                        JOptionPane.showMessageDialog(null, "Coming Soon...");
                    }
                    if (gp.ui.commandNum == 3) {
                        int confirm = JOptionPane.showConfirmDialog(null, "ゲームを終了します。", "確認", JOptionPane.YES_NO_OPTION);
                        if (confirm == 0) {
                            System.exit(0);
                        }
                    }
                }
            }

            else if (gp.ui.titleScreenState == 1) {

                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        System.err.println("Green Onion");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 1) {
                        System.err.println("Baguette");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 2) {
                        System.err.println("SUS");
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    if (gp.ui.commandNum == 3) {
                        gp.ui.titleScreenState = 0;
                    }
                }
            }
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                up = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                down = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                left = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                right = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                enter = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.optionState;
            }
        }

        else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }

        else if (gp.gameState == gp.dialogState) {
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
        }

        else if (gp.gameState == gp.optionState) {
            optionState(code);
        }

    }

    public void optionState(int code) {

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enter = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0:
                maxCommandNum = 4;
                break;
            case 2:
                maxCommandNum = 1;
                break;
        }

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                }
                if (gp.ui.commandNum == 1 && gp.effects.volumeScale > 0) {
                    gp.effects.volumeScale--;
                }
            }
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 0 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                }
                if (gp.ui.commandNum == 1 && gp.effects.volumeScale < 5) {
                    gp.effects.volumeScale++;
                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            up = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }
}
























