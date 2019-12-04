package kvagames.test;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;

/**
 * A lookup list binding lookup keywords to Image icons.
 */
public class IconEntryList {
    private String[] lookups;
    private Image[] icons;
    private boolean needUpdateFlag;
    private String searchPattern;

    public IconEntryList() {
        this(new String[0], new Image[0]);
    }

    public IconEntryList(String[] lookupNames, Image[] icons) {
        this.lookups = lookupNames;
        this.icons = icons;
        needUpdateFlag = true;
    }

    public void Add(String lookup, Image icon){
        ArrayUtils.add(lookups, lookup.toUpperCase());
        ArrayUtils.add(icons, icon);
        needUpdateFlag = true;
    }
    public void Add(String[] lookups, Image[] icons){
        if(lookups.length != icons.length)
            throw new IllegalArgumentException("Length of lookups to add does not match length of icons");
        for (int i = 0; i < lookups.length; i++) {
            Add(lookups[i], icons[i]);
        }
    }
    public void Remove(String lookup) {
        int index = ArrayUtils.indexOf(lookups, lookup);
        if (index < 0)
            return;
        ArrayUtils.remove(lookups, index);
        ArrayUtils.remove(icons, index);
        needUpdateFlag = true;
    }
    public void Remove(String[] lookups){
        for (String lookup : lookups) {
            Remove(lookup);
        }
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
        if(needUpdateFlag) {
            searchPattern = "[" + String.join("|", lookups) + "]";
            needUpdateFlag = false;
        }
        String[] ret = text.split(searchPattern);
        return ret;
    }
}
