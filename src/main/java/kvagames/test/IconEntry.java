package kvagames.test;
import java.awt.*;

/**
 * An entry for an icon-lookuptext binding.
 * Meant so an icon can be rendered in place of some text.
 */
public class IconEntry {
    private String lookup;
    private Image icon;

    public IconEntry(String lookup, Image icon){
        this.lookup = lookup.toUpperCase();

        this.icon = icon;
    }

    public String getLookup() {
        return lookup;
    }

    public Image getIcon() {
        return icon;
    }
    public Image getIcon(int size) {
        return icon.getScaledInstance(size, size, 0);
    }
}
