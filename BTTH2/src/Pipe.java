import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Pipe {
    private int x;
    private int y;
    private int gap = 170;
    private final int width = 50;
    private BufferedImage topPipeImage;
    private BufferedImage bottomPipeImage;
    private final int speed = 5;

    public Pipe(int x) {
        this.x = x;
        Random rand = new Random();
        this.y = rand.nextInt(300) + 100;

        try {
            topPipeImage = ImageIO.read(Pipe.class.getResource("/toppipe.png"));
            bottomPipeImage = ImageIO.read(Pipe.class.getResource("/bottompipe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.drawImage(topPipeImage, x, y - topPipeImage.getHeight(), width, topPipeImage.getHeight(), null);

        g.drawImage(bottomPipeImage, x, y + gap, width, bottomPipeImage.getHeight(), null);
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public boolean isOutOfScreen() {
        return x + width < 0;
    }

    public int getTopHeight() {
        return y;
    }

    public int getBottomY() {
        return y + gap;
    }
}