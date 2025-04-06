import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bird {
    private int x = 100;
    private int y = 200;
    private int velocity = 0;
    private final int gravity = 1;
    private final int lift = -13;
    private BufferedImage birdImage;
    private final int width = 40;
    private final int height = 40;

    public Bird() {
        try {
            birdImage = ImageIO.read(Bird.class.getResource("/flappybird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        velocity += gravity;
        y += velocity;

        if (y < 0) {
            y = 0;
            velocity = 0;
        }
    }

    public void fly() {
        velocity = lift;
    }

    public void draw(Graphics g) {
        g.drawImage(birdImage, x, y, width, height, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void resetPosition() {
        this.y = 200;
        this.velocity = 0;
    }
}