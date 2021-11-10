package parsers;

import utillity.ImageBackground;
import utillity.BackGround;
import utillity.ColorBackground;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * a ColorsParser class.
 * @author Dvir Asaf 313531113.
 */
public class BackGroundParser {

    public Color createNewColor(final String[] singleDigits) {
        return new Color(Integer.parseInt(singleDigits[0]), Integer.parseInt(singleDigits[1]),
                Integer.parseInt(singleDigits[2]));
    }
    private ImageBackground getImageBackground(final String string, final int lenStr) {
        int len = "image".length();
        Image image;
        String imageName = string.substring(len + 1, lenStr - 1);
        try {
            InputStream input  = null;
            try {
                input = new FileInputStream(imageName);

            } catch (Exception e) {
                e.printStackTrace();

            }
            assert input != null;
            image = ImageIO.read(input);
            return new ImageBackground(image);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    /**
     * backGroundFromString - parse background definition and return the specified Background.
     *
     * @param string a given string.
     * @return color.
     */
    public BackGround backGroundFromString(final String string) {
        Pattern pat;
        Matcher matcher;
        int lenStr = string.length();

        String regex = "(\\d*,\\s*\\d*,\\s*\\d*)";
        String[] singleDigits;
        Color[] colors = {Color.BLACK, Color.BLUE, Color.cyan, Color.GRAY, Color.DARK_GRAY, Color.GREEN,
                Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
        String[] colorNames = {"black", "blue", "cyan", "gray", "lightGray", "green",
                "orange", "pink", "red", "white", "yellow"};
        if (string.startsWith("color")) {
            if (string.contains("RGB")) {
                pat = Pattern.compile(regex);
                matcher = pat.matcher(string);

                matcher.find();
                String color = matcher.group(1);
                singleDigits = color.split(",");
                ColorBackground backgroundColor = new ColorBackground();
                backgroundColor.setColor(createNewColor(singleDigits));
                return backgroundColor;
            } else {
                Map<String, Color> nameColorMap = new TreeMap<>();
                for (int i = 0; i < colorNames.length; i++) {
                    nameColorMap.put(colorNames[i], colors[i]);
                }
                String colorType = string.substring("color".length() + 1, lenStr - 1);
                ColorBackground colorBackground = new ColorBackground();
                for (String colorName : colorNames) {
                    if (colorName.equals(colorType)) {
                        colorBackground.setColor(nameColorMap.get(colorType));
                        return colorBackground;
                    }
                }
            }
        } else if (string.startsWith("image")) {
            ImageBackground imageBackground = getImageBackground(string, lenStr);
            if (imageBackground != null) {
                return imageBackground;
            }
        }
        return new ColorBackground();
    }
}
