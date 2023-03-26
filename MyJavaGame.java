import java.awt.;
import java.awt.event.;
import javax.swing.*;

/**

MyGame is a 2D game where the player controls a square, dodging enemy squares

that fall from the top of the screen. The objective of the game is to survive

for as long as possible, avoiding collisions with the enemy squares.

This game was developed over a period of 6 months as a personal project by

[Your Name Here].
*/
public class MyGame extends JPanel implements ActionListener, KeyListener {

// Constants
private static final int SCREEN_WIDTH = 800;
private static final int SCREEN_HEIGHT = 600;
private static final int PLAYER_SIZE = 32;
private static final int ENEMY_SIZE = 16;
private static final int PLAYER_SPEED = 5;
private static final int ENEMY_SPEED = 3;
private static final int MAX_ENEMIES = 10;

// Variables
private Timer timer;
private Player player;
private Enemy[] enemies;

/**

Initializes the game by setting up the player, enemies, and timer.
*/
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

/**

Draws the player and enemies on the screen.
*/
public void paintComponent(Graphics g) {
super.paintComponent(g);
player.draw(g);
for (Enemy enemy : enemies) {
enemy.draw(g);
}
}
/**

Updates the player and enemies' positions and checks for collisions.
*/
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
/**

Handles the player's movement based on keyboard input.
*/
public void keyPressed(KeyEvent e) {
if (e.getKeyCode() == KeyEvent.VK_LEFT) {
player.setLeft(true);
} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
player.setRight(true);
}
}
/**

Handles the player's movement based on keyboard input.
*/
public void keyReleased(KeyEvent e) {
if (e.getKeyCode() == KeyEvent.VK_LEFT) {
player.setLeft(false);
} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
player.setRight(false);
}
}
/**

Not used.
*/
public void keyTyped(KeyEvent e) {}
/**

Creates a JFrame and adds the game panel to it.
*/
public static void main(String[] args) {
JFrame frame = new JFrame("My Game");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
frame.add(new MyGame());
frame.pack();
frame.setLocationRelativeTo(null);
frame.setVisible(true);
}
/**

Represents the player square.
*/
public class Player {

// Variables
private int x;
private int y;
private int size;
private int speed;
private boolean left;
private boolean right;

/**

Constructs a new player with the given x and y coordinates, size, and speed.
*/
public Player(int x, int y, int size, int speed) {
this.x = x;
this.y = y;
this.size = size;
this.speed = speed;
}

/**

Draws the player as a red square.
*/
public void draw(Graphics g) {
g.setColor(Color.RED);
g.fillRect(x, y, size, size);
}

/**

Updates the player's position based on whether the left or right arrow key is pressed.
*/
public void update() {
if (left && x > 0) {
x -= speed;
} else if (right && x < MyGame.SCREEN_WIDTH - size) {
x += speed;
}
}

/**

Sets the left movement flag to the given value.
*/
public void setLeft(boolean left) {
this.left = left;
}

/**

Sets the right movement flag to the given value.
*/
public void setRight(boolean right) {
this.right = right;
}

/**

Returns true if the player collides with the given enemy.
*/
public boolean collidesWith(Enemy enemy) {
if (x + size < enemy.getX() || x > enemy.getX() + enemy.getSize() ||
y + size < enemy.getY() || y > enemy.getY() + enemy.getSize()) {
return false;
} else {
return true;
}
}

/**

Kills the player by stopping the game timer.
*/
public void kill() {
MyGame game = (MyGame) getParent();
game.timer.stop();
}
}

/**

Represents an enemy square.
*/
public class Enemy {

// Variables
private int x;
private int y;
private int size;
private int speed;

/**

Constructs a new enemy with the given x and y coordinates, size, and speed.
*/
public Enemy(int x, int y, int size, int speed) {
this.x = x;
this.y = y;
this.size = size;
this.speed = speed;
}

/**

Draws the enemy as a white square.
*/
public void draw(Graphics g) {
g.setColor(Color.WHITE);
g.fillRect(x, y, size, size);
}

/**

Updates the enemy's position by moving it down the screen.
*/
public void update() {
y += speed;
if (y > MyGame.SCREEN_HEIGHT) {
reset();
}
}

/**

Resets the enemy to a random position above the screen.
*/
public void reset() {
x = (int) (Math.random() * MyGame.SCREEN_WIDTH);
y = -(int) (Math.random() * MyGame.SCREEN_HEIGHT);
}

/**

Returns the x-coordinate of the enemy.
*/
public int getX() {
return x;
}

/**

Returns the y-coordinate of the enemy.
*/
public int getY() {
return y;
}

/**

Returns the size of the enemy.
*/
public int getSize() {
return size;
}
}



public class Player {

// Variables
private int x;
private int y;
private int size;
private int speed;
private boolean left;
private boolean right;

/**

Constructs a new player with the given x and y coordinates, size, and speed.
*/
public Player(int x, int y, int size, int speed) {
this.x = x;
this.y = y;
this.size = size;
this.speed = speed;
}

/**

Draws the player as a red square.
*/
public void draw(Graphics g) {
g.setColor(Color.RED);
g.fillRect(x, y, size, size);
}

/**

Updates the player's position based on whether the left or right arrow key is pressed.
*/
public void update() {
if (left && x > 0) {
x -= speed;
} else if (right && x < MyGame.SCREEN_WIDTH - size) {
x += speed;
}
}

/**

Sets the left movement flag to the given value.
*/
public void setLeft(boolean left) {
this.left = left;
}

/**

Sets the right movement flag to the given value.
*/
public void setRight(boolean right) {
this.right = right;
}

/**

Returns true if the player collides with the given enemy.
*/
public boolean collidesWith(Enemy enemy) {
if (x + size < enemy.getX() || x > enemy.getX() + enemy.getSize() ||
y + size < enemy.getY() || y > enemy.getY() + enemy.getSize()) {
return false;
} else {
return true;
}
}

/**

Kills the player by stopping the game timer.
*/
public void kill() {
MyGame game = (MyGame) getParent();
game.timer.stop();
}
}

/**

Represents an enemy square.
*/
public class Enemy {

// Variables
private int x;
private int y;
private int size;
private int speed;

/**

Constructs a new enemy with the given x and y coordinates, size, and speed.
*/
public Enemy(int x, int y, int size, int speed) {
this.x = x;
this.y = y;
this.size = size;
this.speed = speed;
}

/**

Draws the enemy as a white square.
*/
public void draw(Graphics g) {
g.setColor(Color.WHITE);
g.fillRect(x, y, size, size);
}

/**

Updates the enemy's position by moving it down the screen.
*/
public void update() {
y += speed;
if (y > MyGame.SCREEN_HEIGHT) {
reset();
}
}

/**

Resets the enemy to a random position above the screen.
*/
public void reset() {
x = (int) (Math.random() * MyGame.SCREEN_WIDTH);
y = -(int) (Math.random() * MyGame.SCREEN_HEIGHT);
}

/**

Returns the x-coordinate of the enemy.
*/
public int getX() {
return x;
}

/**

Returns the y-coordinate of the enemy.
*/
public int getY() {
return y;
}

/**

Returns the size of the enemy.
*/
public int getSize() {
return size;
}
}
