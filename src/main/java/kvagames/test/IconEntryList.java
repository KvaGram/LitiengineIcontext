package kvagames.test;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A lookup list binding lookup keywords to Image icons.
 */
public class IconEntryList {
    private String[] lookups;
    private Image[] icons;
    private boolean needUpdateFlag;
    private String pattern;

    public IconEntryList() {
        this(new String[0], new Image[0]);
    }

    public IconEntryList(String[] lookupNames, Image[] icons) {
        for (int i = 0; i < lookupNames.length; i++) {
            lookupNames[i] = lookupNames[i].toUpperCase();
        }
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

            pattern = "\\[+(\\b" + String.join("|\\b", lookups) + ")+\\]";
            //searchPattern = "\\[+(\\b" + String.join("|\\b", lookups) + ")+\\]";
            needUpdateFlag = false;
        }


        String[] ret = SplitText(text, pattern);
        return ret;
    }

    /**
     * splits a text into an array that alternates between text to be drawn
     *  and a lookup word to be replaced by an icon-image
     * @param input The text to split
     * @param regex The words to search for
     * @return An array of string alternating between texts and icons to draw
     */
    static String[] SplitText(String input, String regex) {
        ArrayList<String> res = new ArrayList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        int pos = 0;
        while (m.find()) {
            res.add(input.substring(pos, m.start()));
            res.add(input.substring(m.start()+1, m.end()-1));
            pos = m.end();
        }
        if(pos < input.length()) res.add(input.substring(pos));
        return res.toArray(new String[res.size()]);
    }
}
