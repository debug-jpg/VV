package main;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("all")
public class KeyHandler implements KeyListener {

    public boolean up, down, left, right, enter;

    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles key press events in the game.
     * Different key press actions are performed based on the current game state.
     *
     * @param e the KeyEvent object containing information about the key press event
     */
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
                        gp.gameState = gp.playState;
                    }
                    if (gp.ui.commandNum == 1) {
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 2) {
                        gp.ui.titleScreenState = 2;
                    }
                    if (gp.ui.commandNum == 3) {
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Visual Venture", JOptionPane.YES_NO_OPTION);
                        if (confirm == 0) {
                            System.exit(0);
                        }
                    }
                }
            } else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_ESCAPE) {
                    gp.ui.titleScreenState = 0;
                }
            } else if (gp.ui.titleScreenState == 2) {
                if (code == KeyEvent.VK_ESCAPE) {
                    gp.ui.titleScreenState = 0;
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
        } else if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        } else if (gp.gameState == gp.dialogState) {
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.playState;
            }
            handleKeyPress(code);
        } else if (gp.gameState == gp.optionState) {
            optionState(code);
        }

    }

    /**
     * Handles the option state event.
     * <p>
     * This method is called when an option state event occurs. It updates the
     * game state, handles enter key press, and navigates through options using
     * up, down, left, and right keys.
     *
     * @param code the KeyEvent object containing information about the key that was pressed
     */
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

    public void handleKeyPress(int code) {

//        int answerIndex = -1;

        switch (code) {
            case KeyEvent.VK_1:
                gp.quiz.checkAnswer(0);
                break;
            case KeyEvent.VK_2:
                gp.quiz.checkAnswer(1);
                break;
            case KeyEvent.VK_3:
                gp.quiz.checkAnswer(2);
                break;
            case KeyEvent.VK_4:
                gp.quiz.checkAnswer(3);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Error KeyCode", "Place Guessing", JOptionPane.ERROR_MESSAGE);
                gp.gameState = gp.playState;
                break;
        }

//        if (answerIndex != -1) {
//            gp.quiz.checkAnswer(answerIndex);
//        }
    }

    /**
     * Handles the key release event.
     * <p>
     * This method is called when a key is released. It updates the state of the
     * corresponding direction variables (up, down, left, right) to false.
     *
     * @param e the KeyEvent object containing information about the key that was released
     */
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
























