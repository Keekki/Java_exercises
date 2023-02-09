package Java_exercises.Snake;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Arena extends JPanel implements ActionListener {
    //defining the constants for the game
    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 600;

    private final int BODY_SIZE = 20;
    private final int BODY_ALL = 1000;
    private final int RAND_POS = 14;
    private final int DELAY = 120;
    //two arrays to store the coordinates of the snakes joints
    private final int x[] = new int[BODY_ALL];
    private final int y[] = new int[BODY_ALL];

    private int body;
    private int candy_x;
    private int candy_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image candy;
    private Image head;

    public Arena() {

        initArena();

    }
    //initializing the game arena
    private void initArena() {

        addKeyListener(new TAdapter());
        setBackground(Color.green);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();

    }

    //getting the images for the snake and the candy
    private void loadImages() {

        ImageIcon img_body = new ImageIcon("src/mySnake/body.png");
        ball = img_body.getImage();

        ImageIcon img_candy = new ImageIcon("src/mySnake/candy.png");
        candy = img_candy.getImage();

        ImageIcon img_head = new ImageIcon("src/mySnake/head.png");
        head = img_head.getImage();
    }
    //creating the snake, candy and starting the timer
    private void initGame() {

        body = 3;

        for (int z = 0; z < body; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateCandy();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    //drawing the graphics for the game
    private void doDrawing(Graphics g) {
        //if in game, drawing the candy and the snake
        if (inGame) {

            g.drawImage(candy, candy_x, candy_y, 30, 30, this);

            for (int z = 0; z < body; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        // if game ends, a game over text is printed
        } else {

            gameOver(g);

        }
    }

    //making the game over text
    private void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Impact", Font.BOLD, 42);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }


    private void checkCandy() {

        // Calculate the distance between the head and the candy
        int distance = (int) Math.sqrt(Math.pow(x[0] - candy_x, 2) + Math.pow(y[0] - candy_y, 2));

        // if the head is within 15 pixels of a candy, a new joint is added
        if (distance <= 15) {

            body++;
            locateCandy(); // randomly positions a new candy
        }
    }

    // creating the movement of the snake
    // snakes head moves 1 position up
    // rest of the body moves along with the next joint
    // taking up the position of the previous one
    private void move() {

        for (int z = body; z > 0; z--) { // moves the joints
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) { // moves head to left
            x[0] -= BODY_SIZE;
        }

        if (rightDirection) { // moves head to right
            x[0] += BODY_SIZE;
        }

        if (upDirection) { // moves head up
            y[0] -= BODY_SIZE;
        }

        if (downDirection) { // moves head down
            y[0] += BODY_SIZE;
        }
    }

    // if head hits the joints or the walls, game stops
    private void checkCollision() {

        for (int z = body; z > 0; z--) { // if the head hits any joints, game stops

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) { //if the head hits the bottom, game stops
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) { //if the head hits the sides, game stops
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    // randomising the position of the candy
    private void locateCandy() {

        int r = (int) (Math.random() * RAND_POS);
        candy_x = ((r * BODY_SIZE));

        r = (int) (Math.random() * RAND_POS);
        candy_y = ((r * BODY_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkCandy();
            checkCollision();
            move();
        }

        repaint();
    }

    // binding movement keys to arrow keys
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            //going right
            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            //going left
            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            //going down
            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            //going up
            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}