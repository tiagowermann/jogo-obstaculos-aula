package com.tiago.jogoaulasexta;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaJogo extends JFrame implements Runnable {

    private final int MAX = 10;
    private final int MAX_VEL_PLAYER = 4;
    private final int MAX_VEL_BOSS = 8;
    private int vetX[] = new int[MAX];
    private int vetY[] = new int[MAX];

    private int playerX, playerY;
    private int tendenciaX = 0;
    private int tendenciaY = 0;

    private int bossX = 0;
    private int bossY = 0;
    private int lado = MAX_VEL_BOSS;

    private float pontuacao = 0;
    private JLabel pontosLabel;

    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;
    private static final int OBSTACLE_WIDTH = 200;
    private static final int OBSTACLE_HEIGHT = 10;
    private static final int OBSTACLE_X = 400;
    private static final int OBSTACLE_Y = 150;

    private static final int OBSTACLE2_WIDTH = 200;
    private static final int OBSTACLE2_HEIGHT = 10;
    private static final int OBSTACLE2_X = 600;
    private static final int OBSTACLE2_Y = 400;

    private static final int OBSTACLE3_WIDTH = 200;
    private static final int OBSTACLE3_HEIGHT = 10;
    private static final int OBSTACLE3_X = 100;
    private static final int OBSTACLE3_Y = 300;

    public TelaJogo() {
        initComponents();

        pontosLabel = new JLabel("Pontos: 0");
        pontosLabel.setBounds(10, 10, 100, 20);
        add(pontosLabel);

        setSize(800, 600);

        new Thread(this).start();

        for (int i = 0; i < MAX; i++) {

            if (vetX[i] + lado > OBSTACLE_X &&  vetX[i] + lado < OBSTACLE_X + OBSTACLE_WIDTH
                    && vetY[i] > OBSTACLE_Y && vetY[i] < OBSTACLE_Y + OBSTACLE_HEIGHT) {
                lado = -MAX_VEL_BOSS;
            }
            if (vetX[i] + lado > OBSTACLE2_X && vetX[i] + lado < OBSTACLE2_X + OBSTACLE2_WIDTH
                    && vetY[i] > OBSTACLE2_Y && vetY[i] < OBSTACLE2_Y + OBSTACLE2_HEIGHT) {
                lado = -MAX_VEL_BOSS;
            }
            if (vetX[i] + lado > OBSTACLE3_X && vetX[i] + lado < OBSTACLE3_X + OBSTACLE3_WIDTH
                    && vetY[i] > OBSTACLE3_Y && vetY[i] < OBSTACLE3_Y + OBSTACLE3_HEIGHT) {
                lado = -MAX_VEL_BOSS;
            } else {

                vetX[i] = getWidth() / 2;
                vetY[i] = getHeight() / 2;
            }
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
                            lado = -MAX_VEL_BOSS;
                        }
                        if (bossX < 1) {
                            lado = MAX_VEL_BOSS;
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

        // Desenha a pontuação
        g.drawString("Pontuação: " + pontuacao, 10, 20);

        // Desenha a tela de fundo
//        g.setColor(Color.WHITE);
//        g.fillRect(0, 0, WIDTH, HEIGHT);
        // Desenha o obstáculo
        g.setColor(Color.BLACK);
        g.fillRect(OBSTACLE_X, OBSTACLE_Y, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
        g.fillRect(OBSTACLE2_X, OBSTACLE2_Y, OBSTACLE2_WIDTH, OBSTACLE2_HEIGHT);
        g.fillRect(OBSTACLE3_X, OBSTACLE3_Y, OBSTACLE3_WIDTH, OBSTACLE3_HEIGHT);

        // Desenha os elementos do jogo...
        for (int i = 0; i < MAX; i++) {
            g.setColor(new Color(i * 20, 30, (int) (Math.random() * 255)));
            g.drawOval(vetX[i], vetY[i], 5 + i * 2, 5 + i * 2);

            if (playerX < OBSTACLE_X + OBSTACLE_WIDTH && playerX + WIDTH > OBSTACLE_X && playerY < OBSTACLE_Y + OBSTACLE_HEIGHT && playerY + HEIGHT > OBSTACLE_Y) {
                // Colisão com o obstáculo 1
                if (playerY < OBSTACLE_Y + OBSTACLE_HEIGHT / 2) {
                    playerY = OBSTACLE_Y - HEIGHT;
                } else {
                    playerY = OBSTACLE_Y + OBSTACLE_HEIGHT;
                }
            }
            if (playerX < OBSTACLE2_X + OBSTACLE2_WIDTH && playerX + WIDTH > OBSTACLE2_X && playerY < OBSTACLE2_Y + OBSTACLE2_HEIGHT && playerY + HEIGHT > OBSTACLE2_Y) {
                // Colisão com o obstáculo 2
                if (playerY < OBSTACLE2_Y + OBSTACLE2_HEIGHT / 2) {
                    playerY = OBSTACLE2_Y - HEIGHT;
                } else {
                    playerY = OBSTACLE2_Y + OBSTACLE2_HEIGHT;
                }
            }
            if (playerX < OBSTACLE3_X + OBSTACLE3_WIDTH && playerX + WIDTH > OBSTACLE3_X && playerY < OBSTACLE3_Y + OBSTACLE3_HEIGHT && playerY + HEIGHT > OBSTACLE3_Y) {
                // Colisão com o obstáculo 3
                if (playerY < OBSTACLE3_Y + OBSTACLE3_HEIGHT / 2) {
                    playerY = OBSTACLE3_Y - HEIGHT;
                } else {
                    playerY = OBSTACLE3_Y + OBSTACLE3_HEIGHT;
                }
            }
            
            
            if (vetX[i] < OBSTACLE_X + OBSTACLE_WIDTH && vetX[i] + WIDTH > OBSTACLE_X && vetY[i] < OBSTACLE_Y + OBSTACLE_HEIGHT && vetY[i] + HEIGHT > OBSTACLE_Y) {
                // Colisão com o obstáculo 1
                if (vetY[i] < OBSTACLE_Y + OBSTACLE_HEIGHT / 2) {
                    vetY[i] = OBSTACLE_Y - HEIGHT;
                } else {
                    vetY[i] = OBSTACLE_Y + OBSTACLE_HEIGHT;
                }
            }
            if (vetX[i] < OBSTACLE2_X + OBSTACLE2_WIDTH && vetX[i] + WIDTH > OBSTACLE2_X && vetY[i] < OBSTACLE2_Y + OBSTACLE2_HEIGHT && vetY[i] + HEIGHT > OBSTACLE2_Y) {
                // Colisão com o obstáculo 2
                if (vetY[i] < OBSTACLE2_Y + OBSTACLE2_HEIGHT / 2) {
                    vetY[i] = OBSTACLE2_Y - HEIGHT;
                } else {
                    vetY[i] = OBSTACLE2_Y + OBSTACLE2_HEIGHT;
                }
            }
            if (vetX[i] < OBSTACLE3_X + OBSTACLE3_WIDTH && vetX[i] + WIDTH > OBSTACLE3_X && vetY[i] < OBSTACLE3_Y + OBSTACLE3_HEIGHT && vetY[i] + HEIGHT > OBSTACLE3_Y) {
                // Colisão com o obstáculo 3
                if (vetY[i] < OBSTACLE3_Y + OBSTACLE3_HEIGHT / 2) {
                    vetY[i] = OBSTACLE3_Y - HEIGHT;
                } else {
                    vetY[i] = OBSTACLE3_Y + OBSTACLE3_HEIGHT;
                }
            }
         
            
            if (bossX < OBSTACLE_X + OBSTACLE_WIDTH && bossX + WIDTH > OBSTACLE_X && bossY < OBSTACLE_Y + OBSTACLE_HEIGHT && bossY + HEIGHT > OBSTACLE_Y) {
                // Colisão com o obstáculo 1
                if (bossY < OBSTACLE_Y + OBSTACLE_HEIGHT / 2) {
                    bossY = OBSTACLE_Y - HEIGHT;
                } else {
                    bossY = OBSTACLE_Y + OBSTACLE_HEIGHT;
                }
            }
            if (bossX < OBSTACLE2_X + OBSTACLE2_WIDTH && bossX + WIDTH > OBSTACLE2_X && bossY < OBSTACLE2_Y + OBSTACLE2_HEIGHT && bossY + HEIGHT > OBSTACLE2_Y) {
                // Colisão com o obstáculo 2
                if (bossY < OBSTACLE2_Y + OBSTACLE2_HEIGHT / 2) {
                    bossY = OBSTACLE2_Y - HEIGHT;
                } else {
                    bossY = OBSTACLE2_Y + OBSTACLE2_HEIGHT;
                }
            }
            if (bossX < OBSTACLE3_X + OBSTACLE3_WIDTH && bossX + WIDTH > OBSTACLE3_X && bossY < OBSTACLE3_Y + OBSTACLE3_HEIGHT && bossY + HEIGHT > OBSTACLE3_Y) {
                // Colisão com o obstáculo 3
                if (bossY < OBSTACLE3_Y + OBSTACLE3_HEIGHT / 2) {
                    bossY = OBSTACLE3_Y - HEIGHT;
                } else {
                    bossY = OBSTACLE3_Y + OBSTACLE3_HEIGHT;
                }
            }
            
            

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

            // Verifica se o jogador colidiu com o outros inimigos
            if (playerX < vetX[i] + 40 && playerX + 20 > vetX[i] && playerY < vetY[i] + 40 && playerY + 20 > vetY[i]) {
                adicionarPontos(1); // Adiciona 5 pontos à pontuação
//            bossX = 0; // Reinicia a posição do inimigo boss
//            bossY = 0;
            }

        }

        for (int i = 0; i < 10; i++) {
            g.setColor(new Color(i * 20, 0, 0));
            g.drawRect(playerX, playerY, i * 2, i * 2);
        }

        g.setColor(Color.BLUE);
        g.drawRect(bossX, bossY, 40, 40);

        // Verifica se o jogador colidiu com o inimigo boss
        if (playerX < bossX + 40 && playerX + 20 > bossX && playerY < bossY + 40 && playerY + 20 > bossY) {
            adicionarPontos(5); // Adiciona 5 pontos à pontuação
//            bossX = 0; // Reinicia a posição do inimigo boss
//            bossY = 0;
        }

    }

    public void adicionarPontos(float pontos) {
        this.pontuacao += pontos;
        pontosLabel.setText("Pontos: " + this.pontuacao);
    }

    public void reiniciarPontuacao() {
        this.pontuacao = 0;
        pontosLabel.setText("Pontos: " + this.pontuacao);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
                repaint();
            } catch (Exception e) {
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
        if (evt.getKeyCode() == evt.VK_R) {
            reiniciarPontuacao(); // Reinicia a pontuação do jogador
//            bossX = 0; // Reinicia a posição do inimigo boss
//            bossY = 0;
        }
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
