package ru.java2e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int MAX_V = 50;
    public static final int MAX_TOP = 490;
    public static final int MAX_BOTTOM =725;

    int x = 30;
    int y = 480;
    int dy = 0;

    Image image_C = new ImageIcon("res/player.png").getImage();
    Image image_L = new ImageIcon("res/player_left.png").getImage();
    Image image_R = new ImageIcon("res/player_right.png").getImage();

    Image image = image_C;

    public Rectangle getRec() {
        return new Rectangle(x,y,150,53);
    }

    int v = 0;
    int dv = 0;
    int s = 0;
    int layer1 = 0;
    int layer2 = 1180;


    public void move() {
        s += v;
        v += dv;
        if (v <= 0) v = 0;
        if (v >= MAX_V) v = MAX_V;
        y -= dy;
        if (y <= MAX_TOP) y = MAX_TOP;
        if (y >= MAX_BOTTOM) y = MAX_BOTTOM;

        if (layer2 - v <=0) {
            layer1 = 0;
            layer2 = 1180;
        }
        else {
            layer1 -= v;
            layer2 -= v;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            dv = 1;

        }
        if (key == KeyEvent.VK_LEFT) {
            dv = -1;

        }
        if (key == KeyEvent.VK_UP) {
            dy = 5;
            image = image_L;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -5;
            image = image_R;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            dv = 0;
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
            image = image_C;
        }

    }
}
