import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyGame extends JPanel implements ActionListener, KeyListener {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int PLAYER_SIZE = 32;
    private static final int ENEMY_SIZE = 16;
    private static final int PLAYER_SPEED = 5;
    private static final int ENEMY_SPEED = 3;
    private static final int MAX_ENEMIES = 10;

    private Timer timer;
    private Player player;
    private Enemy[] enemies;

    public MyGame() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        player = new Player(SCREEN_WIDTH / 2 - PLAYER_SIZE / 2,
                SCREEN_HEIGHT - PLAYER_SIZE - 10, PLAYER_SIZE, PLAYER_SPEED);
        enemies = new Enemy[MAX_ENEMIES];
        for (int i = 0; i < MAX_ENEMIES; i++) {
            enemies[i] = new Enemy((int) (Math.random() * SCREEN_WIDTH),
                    -(int) (Math.random() * SCREEN_HEIGHT), ENEMY_SIZE, ENEMY_SPEED);
        }

        timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    public void actionPerformed(ActionEvent e) {
        player.update();
        for (Enemy enemy : enemies) {
            enemy.update();
            if (player.collidesWith(enemy)) {
                player.kill();
            }
        }
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new MyGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class Player {
        private int x;
        private int y;
        private int size;
        private int speed;
        private boolean left;
        private boolean right;
        private boolean alive;

        public Player(int x, int y, int size, int speed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
            left = false;
            right = false;
            alive = true;
        }

        public void draw(Graphics g) {
            if (alive) {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, size, size);
            }
        }

        public void update() {
            if (left && x > 0) {
                x -= speed;
            } else if (right && x < SCREEN_WIDTH - size) {
                x += speed;
            }
        }

        public void setLeft(boolean left) {
            this.left = left;
        }

        public void setRight(boolean right) {
            this.right
