import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main extends GameScreen {
    public static void main(String[] args) {
        Main main = new Main();

//        System.out.println("main - before init");
        main.init();
//        System.out.println("main - before run");
        main.run();
//        System.out.println("main - end of main");
    }

    Image[] icons;
    String[] messages;

    private void init() {
        Game.init();
        Image cursor;


        cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics g = cursor.getGraphics();
        g.setColor(Color.lightGray);
        g.drawLine(0, 0, 16, 16);
        g.drawLine(0, 0, 16, 0);
        g.drawLine(0, 0, 0, 16);
        Game.window().getRenderComponent().setCursor(cursor);

        messages = new String[] {
                "Hello, this is a test, and [BLUE] is a friendly blue dot.",
                "Hey, I'm a rival test. I despise [BLUE]! Glory to [RED]!",
                "I may be just a simple test with two lines, but I heard from [YELLOW] that\n" +
                        " [RED] and [BLUE] are actually engaged",
                "[GREEN]",
                "You can't just have a single dot alone.. [RED[BLUE] oops, this might be invalid..",
                "You know [RED], I am feeling a bit [WHITE], even though there is no white dot.",
                "[That's enough test. Let's leave GREEN and the others alone]"
        };
    }
    private void run() {
        Game.start();
        Game.screens().display(this);
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        Font newFont = g.getFont().deriveFont(Font.PLAIN,20);
        g.setFont(newFont);
        //render control tests

        g.setColor(Color.blue);
        TextRenderer.render(g, get(0), 50, 30);
        TextRenderer.renderRotated(g, get(1), 60, 50, 30f);
        TextRenderer.renderWithLinebreaks(g, get(2), 50, 120, 500);
        TextRenderer.render(g, get(3), 50, 240);
        TextRenderer.render(g, get(4), 50, 300);
        TextRenderer.renderWithOutline(g, get(5), 50, 360, Color.white);
        TextRenderer.render(g, get(6), 50, 420);
        
    }
    String get(int i){
        return messages[i % messages.length];
    }

}
