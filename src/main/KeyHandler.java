package main;

import java.awt.event.*;

public class KeyHandler implements KeyListener{

    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

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


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            up = !true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            down = !true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            left = !true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            right = !true;
        }
    }
}
























