package ru.java2e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class Road extends JPanel implements ActionListener, Runnable{

    Timer timer = new Timer(20, this);

    Image image = new ImageIcon("res/bg_road.jpg").getImage();

    Player player = new Player();

    Thread enemiesFactory = new Thread(this);

    List<Enemy> enemies = new ArrayList<>();

    public Road () {
        timer.start();
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }



    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

    }
    public void paint (Graphics g) {
        g.drawImage(image, player.layer1, 0, null);
        g.drawImage(image, player.layer2, 0,null);
        g.drawImage(player.image, player.x, player.y, null);

        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            }
            else {
                e.move();
                g.drawImage(e.image, e.x, e.y, null);

            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        testCollisionWithEnemies();
    }

    private void testCollisionWithEnemies() {

        Iterator<Enemy> i  = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (player.getRec().intersects(e.getRect())){
                JOptionPane.showMessageDialog(null,"Вы проиграли!");
                System.exit(1);
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2000));
                int randY = 490 + random.nextInt(725 - 490 +1);
                enemies.add(new Enemy(1300,randY,random.nextInt(49),this));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
