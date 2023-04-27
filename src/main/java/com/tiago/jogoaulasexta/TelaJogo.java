package com.tiago.jogoaulasexta;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class TelaJogo extends JFrame implements Runnable {

    private final int MAX = 10;
    private final int MAX_VEL_PLAYER = 4;
    private int vetX[] = new int[MAX];
    private int vetY[] = new int[MAX];

    private int playerX, playerY;
    private int tendenciaX = 0;
    private int tendenciaY = 0;

    private int bossX = 0;
    private int bossY = 0;
    private int lado = MAX_VEL_PLAYER;
    
    public TelaJogo() {
        initComponents();

        setSize(800, 600);

        new Thread(this).start();

        for (int i = 0; i < MAX; i++) {
            vetX[i] = getWidth() / 2;
            vetY[i] = getHeight() / 2;
        }
        playerX = getWidth() / 2;
        playerY = getHeight() / 2;

        bossX = getWidth() / 4;
        bossY = getHeight() / 4;

        new Thread(this) {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        bossX += lado;
                        if (bossX > getWidth()) {
                            lado = -MAX_VEL_PLAYER;
                        }
                        if (bossX < 1) {
                            lado = MAX_VEL_PLAYER;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
 
     @Override
      public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < MAX; i++) {
            g.setColor(new Color(i * 20, 30, (int) (Math.random() * 255)));
            g.drawOval(vetX[i], vetY[i], 5 + i * 2, 5 + i * 2);

            if (Math.random() > 0.9) {
                if (playerX > vetX[i]) {
                    tendenciaX = 2;
                } else {
                    tendenciaX = -2;
                }
                if (playerY > vetY[i]) {
                    tendenciaY = 2;
                } else {
                    tendenciaY = -2;
                }
            } else {
                tendenciaX = 0;
                tendenciaY = 0;
            }

            vetX[i] += (int) (Math.random() * 11) - 5 + tendenciaX;
            vetY[i] += (int) (Math.random() * 11) - 5 + tendenciaY;

        }

        for (int i = 0; i < 10; i++) {
            g.setColor(new Color(i * 20, 0, 0));
            g.drawRect(playerX, playerY, i * 2, i * 2);
        }

        g.setColor(Color.BLUE);
        g.drawRect(bossX, bossY, 40, 40);

    }
    
    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(20);
                repaint();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
         if (evt.getKeyCode() == evt.VK_UP) {
            playerY -= MAX_VEL_PLAYER;
        }
        if (evt.getKeyCode() == evt.VK_DOWN) {
            playerY += MAX_VEL_PLAYER;
        }
        if (evt.getKeyCode() == evt.VK_LEFT) {
            playerX -= MAX_VEL_PLAYER;
        }
        if (evt.getKeyCode() == evt.VK_RIGHT) {
            playerX += MAX_VEL_PLAYER;
        }
    }//GEN-LAST:event_formKeyPressed
                       


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
