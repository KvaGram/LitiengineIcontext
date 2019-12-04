package kvagames.test;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;

/**
 * An entry for an icon-lookuptext binding.
 * Meant so an icon can be rendered in place of some text.
 */
public class IconEntryList {
    private String[] lookups;
    private Image[] icons;

    public IconEntryList() {
        this(new String[0], new Image[0]);
    }

    public IconEntryList(String[] lookupNames, Image[] icons) {
        this.lookups = lookupNames;
        this.icons = icons;
    }

    public void Add(String lookup, Image icon){
        ArrayUtils.add(lookups, lookup.toUpperCase());
        ArrayUtils.add(icons, icon);
    }

    public String[] getLookups() {
        return lookups;
    }

    public Image[] getIcons() {
        return icons;
    }
    public Image getScaledIcon(int index, int size) {
        return icons[index].getScaledInstance(size, size, 0);
    }
    public Image getScaledIcon(String lookup, int size) {
        return getScaledIcon(ArrayUtils.indexOf(lookups, lookup), size);
    }
    public String[] SplitText(String text) {
        StringBuffer pattern = new StringBuffer();
        pattern.append(String.join("|", lookups));
        pattern.insert(0, "[");
        pattern.append("]");
        String[] ret = text.split(pattern.toString());
        return ret;
    }
}
