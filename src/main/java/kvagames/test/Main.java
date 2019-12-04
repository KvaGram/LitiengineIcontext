package kvagames.test;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends GameScreen {
    public static void main(String[] args) {
        Main main = new Main();

//        System.out.println("main - before init");
        main.init();
//        System.out.println("main - before run");
        main.run();
//        System.out.println("main - end of main");
    }

    IconEntryList iconEntries;
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

        //set up test-icons (colored filled circles)
        Color[] colors = new Color[]{Color.blue, Color.RED, Color.YELLOW, Color.GREEN}; //but there is no white..
        String[] lookupNames = new String[]{"Blue", "RED", "YelLoW", "GREEN"};//All names are corrected to upper case.
        Image[] icons = new Image[lookupNames.length];
        for (int i = 0; i < lookupNames.length; i++) {
            BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
            g = image.createGraphics();
            g.setColor(colors[i]);
            g.fillOval(0, 0, 32, 32);
            icons[i] = image;
        }
        
        iconEntries = new IconEntryList(lookupNames, icons);
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
        TextRenderer.renderRotated(g, get(1), 60, 50, 6f);
        TextRenderer.renderWithLinebreaks(g, get(2), 50, 120, 500);
        TextRenderer.render(g, get(3), 50, 240);
        TextRenderer.render(g, get(4), 50, 300);
        TextRenderer.renderWithOutline(g, get(5), 50, 360, Color.white);
        TextRenderer.render(g, get(6), 50, 420);

        //render new text with icons
        //set icon entry list
        TextRenderer2.setIconEntryList(iconEntries);
        g.setColor(Color.red);
        TextRenderer2.render(g, get(0), iconEntries, 50, 530);
        TextRenderer2.renderRotated(g, get(1), iconEntries, 60, 550, 6f);
        TextRenderer2.renderWithLinebreaks(g, get(2), iconEntries, 50, 620, 500);
        TextRenderer2.render(g, get(3), iconEntries, 50, 740);
        TextRenderer2.render(g, get(4), iconEntries, 50, 800);
        TextRenderer2.renderWithOutline(g, get(5), iconEntries, 50, 860, Color.white);
        TextRenderer2.render(g, get(6), iconEntries, 50, 920);
    }
    String get(int i){
        return messages[i % messages.length];
    }

}
