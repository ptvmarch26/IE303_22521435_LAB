import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Main {
    private static final int WIDTH = 360;
    private static final int HEIGHT = 640;
    private static final int distanceTime = 1500;
    private static long lastPipeSpawnTime = System.currentTimeMillis();

    // Biến cơ chế game
    private static boolean gameOver = false;
    private static boolean gameStarted = false;
    private static int score = 0;
    private static final List<Pipe> scoredPipes = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        BufferedImage background = null;
        try {
            background = ImageIO.read(Main.class.getResource("/flappybirdbg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bird bird = new Bird();
        List<Pipe> pipes = new ArrayList<>();

        BufferedImage finalBackground = background;
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBackground != null) {
                    g.drawImage(finalBackground, 0, 0, getWidth(), getHeight(), this);
                }

                for (Pipe pipe : pipes) {
                    pipe.draw(g);
                }

                bird.draw(g);

                // Điểm
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Score: " + score, 20, 30);

                // Thông báo gameOver
                if (gameOver) {
                    g.setColor(new Color(0, 0, 0, 150));
                    g.fillRect(0, 0, getWidth(), getHeight());

                    g.setColor(Color.WHITE);
                    Font gameOverFont = new Font("Arial", Font.BOLD, 36);
                    g.setFont(gameOverFont);
                    String gameOverText = "Game Over";
                    FontMetrics fm1 = g.getFontMetrics(gameOverFont);
                    int textWidth1 = fm1.stringWidth(gameOverText);
                    g.drawString(gameOverText, (getWidth() - textWidth1) / 2, getHeight() / 2 - 50);

                    Font scoreFont = new Font("Arial", Font.BOLD, 24);
                    g.setFont(scoreFont);
                    String finalScore = "Your Score: " + score;
                    FontMetrics fm2 = g.getFontMetrics(scoreFont);
                    int textWidth2 = fm2.stringWidth(finalScore);
                    g.drawString(finalScore, (getWidth() - textWidth2) / 2, getHeight() / 2);

                    Font restartFont = new Font("Arial", Font.PLAIN, 18);
                    g.setFont(restartFont);
                    String restartText = "Press SPACE to restart";
                    FontMetrics fm3 = g.getFontMetrics(restartFont);
                    int textWidth3 = fm3.stringWidth(restartText);
                    g.drawString(restartText, (getWidth() - textWidth3) / 2, getHeight() / 2 + 50);
                }

                if (!gameStarted && !gameOver) {
                    g.setColor(new Color(0, 0, 0, 100)); // Semi-transparent black
                    g.fillRect(0, 0, getWidth(), getHeight());

                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    String startText = "Press SPACE to start";
                    FontMetrics fm = g.getFontMetrics();
                    int textWidth = fm.stringWidth(startText);
                    g.drawString(startText, (getWidth() - textWidth) / 2, getHeight() / 2);
                }
            }
        };

        // Update chim và restart
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (gameOver) {
                        resetGame(bird, pipes);
                    }
                    else if (!gameStarted) {
                        gameStarted = true;
                        bird.fly();
                    }
                    else {
                        bird.fly();
                    }
                }
            }
        });

        // Vẽ lại theo điều kiện
        Timer timer = new Timer(20, e -> {
            if (gameStarted && !gameOver) {
                bird.update();

                if (bird.getY() + bird.getHeight() >= HEIGHT) {
                    gameOver = true;
                }

                for (int i = 0; i < pipes.size(); i++) {
                    Pipe pipe = pipes.get(i);
                    pipe.update();

                    if (checkCollision(bird, pipe)) {
                        gameOver = true;
                    }

                    if (!scoredPipes.contains(pipe) && bird.getX() > pipe.getX() + pipe.getWidth()) {
                        score++;
                        scoredPipes.add(pipe);
                    }

                    if (pipe.isOutOfScreen()) {
                        pipes.remove(i);
                        scoredPipes.remove(pipe);
                        i--;
                    }
                }

                long currentTime = System.currentTimeMillis();
                if (currentTime - lastPipeSpawnTime >= distanceTime) {
                    pipes.add(new Pipe(WIDTH));
                    lastPipeSpawnTime = currentTime;
                }
            }
            panel.repaint();
        });
        timer.start();

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Kiểm tra chim và ống có chạm nhau chưa
    private static boolean checkCollision(Bird bird, Pipe pipe) {
        int birdX = bird.getX();
        int birdY = bird.getY();
        int birdWidth = bird.getWidth();
        int birdHeight = bird.getHeight();

        int pipeX = pipe.getX();
        int pipeWidth = pipe.getWidth();
        int topPipeHeight = pipe.getTopHeight();
        int bottomPipeY = pipe.getBottomY();

        // Ống trên
        boolean topPipe = birdX + birdWidth > pipeX &&
                birdX < pipeX + pipeWidth &&
                birdY < topPipeHeight;

        // Ống dưới
        boolean bottomPipe = birdX + birdWidth > pipeX &&
                birdX < pipeX + pipeWidth &&
                birdY + birdHeight > bottomPipeY;

        return topPipe || bottomPipe;
    }

    // Reset game
    private static void resetGame(Bird bird, List<Pipe> pipes) {
        gameOver = false;
        gameStarted = true;
        score = 0;
        bird.resetPosition();
        pipes.clear();
        scoredPipes.clear();
        lastPipeSpawnTime = System.currentTimeMillis();
    }
}