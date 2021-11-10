package read;
import collision.Velocity;
import levels.LevelInformation;
import parsers.BackGroundParser;
import sprites.Sprite;
import utillity.BackGround;

import java.io.*;
import java.util.*;
/**
 * this class responsible of level specification reader.
 * @author Dvir Asaf 313531113.
 */
public class LevelSpecificationReader {
    private List<String> linesList;
    private List<List<String>> levelList;
    private List<String> layoutBlocks;
    private Map<String, String> informationMapOfLevel;
    private List<LevelInformation> levelsInformation;

    /**
     * constructor for this class.
     */
    public LevelSpecificationReader() {
        this.informationMapOfLevel = new TreeMap<>();
        this.levelList = new ArrayList<>();
        this.levelsInformation = new ArrayList<>();
        this.linesList = new ArrayList<>();
    }

    /**
     * this method return list of level information from a given reader.
     *
     * @param reader a given reader.
     * @return list of level information.
     */
    public List<LevelInformation> fromReader(final Reader reader) {
        this.readLines(reader);
        this.separateLevels();
        List<List<String>> levelList = new ArrayList<>(this.levelList);
        for (List<String> list : levelList) {
            this.separateLevel(list);
            this.extractLevelInfo();
        }
        return this.levelsInformation;
    }
    /**
     * this method extract the level information.
     */
    public void extractLevelInfo() {
        String nameLevel;
        int speedPad;
        int widthPad;
        int blockX;
        int blockY;
        int rowHeight;
        int blocksNumber;
        List<Velocity> ballVelocities = new ArrayList<>();
        BackGround background = null;
        List<String> blocksInformation = this.layoutBlocks;
        BlocksFromSymbolsFactory symbolsFactory = null;
        if (informationMapOfLevel.containsKey("level_name")) {
            nameLevel = informationMapOfLevel.get("level_name");
        } else {
            throw new RuntimeException("Error! level name not found");
        }
        if (informationMapOfLevel.containsKey("ball_velocities")) {
            String[] pairs = informationMapOfLevel.get("ball_velocities").split(" ");
            Velocity velocity;
            for (String pair : pairs) {
                String[] angleAndSpeed = pair.split(",");
                double angle = Double.parseDouble(angleAndSpeed[0]);
                double speed = Double.parseDouble(angleAndSpeed[1]);
                velocity = Velocity.fromAngleAndSpeedPatch(angle, -speed);
                ballVelocities.add(velocity);
            }
        } else {
            throw new RuntimeException("Error! ball velocity not found");
        }
        if (this.informationMapOfLevel.containsKey("background")) {
            String background1 = informationMapOfLevel.get("background");
            BackGroundParser backGroundParser = new BackGroundParser();
            background = backGroundParser.backGroundFromString(background1);
        }
        if (this.informationMapOfLevel.containsKey("paddle_speed")) {
            String speedPadS = this.informationMapOfLevel.get("paddle_speed");
            speedPad = Integer.parseInt(speedPadS);
        } else {
            throw new RuntimeException("Error! paddle speed not found");
        }
        if (this.informationMapOfLevel.containsKey("paddle_width")) {
            String widthPadS = this.informationMapOfLevel.get("paddle_width");
            widthPad = Integer.parseInt(widthPadS);
        } else {
            throw new RuntimeException("Error! paddle width not found");
        }
        if (informationMapOfLevel.containsKey("blocks_start_x")) {
            blockX = Integer.parseInt(informationMapOfLevel.get("blocks_start_x"));
        } else {
            throw new RuntimeException("Error! blocks start x not found");
        }
        if (informationMapOfLevel.containsKey("blocks_start_y")) {
            blockY = Integer.parseInt(informationMapOfLevel.get("blocks_start_y"));
        } else {
            throw new RuntimeException("Error! blocks start y not found");
        }
        if (informationMapOfLevel.containsKey("row_height")) {
            rowHeight = Integer.parseInt(informationMapOfLevel.get("row_height"));
        } else {
            throw new RuntimeException("Error! row height not found");
        }
        if (informationMapOfLevel.containsKey("num_blocks")) {
            blocksNumber = Integer.parseInt(informationMapOfLevel.get("num_blocks"));
        } else {
            throw new RuntimeException("Error! number blocks not found");
        }
        if (informationMapOfLevel.containsKey("block_definitions")) {
            //string name : clocks definitions file.
            String bDF = informationMapOfLevel.get("block_definitions");

            InputStream input  = null;
            try {
                input = new FileInputStream(bDF);

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                BufferedReader buffReader = new BufferedReader(
                        new InputStreamReader(Objects.requireNonNull(input)));
                symbolsFactory = BlocksDefinitionReader.fromReader(buffReader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Error! block definitions not found");
        }
        List<Object> blocksProps = new ArrayList<>();
        blocksProps.add(rowHeight);
        blocksProps.add(blockX);
        blocksProps.add(blockY);
        blocksProps.add(blocksInformation);
        blocksProps.add(symbolsFactory);
        levelsInformation.add(new LevelFactory(ballVelocities, speedPad, widthPad,
                nameLevel, (Sprite) background, blocksProps, blocksNumber));
    }
    /**
     * this method get reader and read line from it.
     *
     * @param r a given reader.
     */
    public void readLines(final Reader r) {
        try {
            BufferedReader bufferedReader = new BufferedReader(r);
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                if ((!readLine.isEmpty()) && (!readLine.startsWith("#"))) {
                    this.linesList.add(readLine);
                }
                readLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * separateLevels - split the lines to levels.
     */
    public void separateLevels() {
        int j = 0;
        int i = 0;
        int size = linesList.size();
        while (i < size) {
            if (linesList.get(i).startsWith("START_LEVEL")) {
                ++i;
                this.levelList.add(new ArrayList<>());
                while (!linesList.get(i).startsWith("END_LEVEL")) {
                    this.levelList.get(j).add(linesList.get(i));
                    ++i;
                    if (linesList.get(i).startsWith("END_LEVEL")) {
                        ++j;
                        break;
                    }
                }
            }
            ++i;
        }
    }
    /**
     * this method separate a given list of string to level information.
     *
     * @param information a given list of string that contain a single level information.
     */
    public void separateLevel(final List<String> information) {
        int i = 0;
        this.layoutBlocks = new ArrayList<>();
        int infoSize = information.size();
        if (i < infoSize) {
            do {
                if (information.get(i).startsWith("START_BLOCKS")) {
                    i++;
                    while (!information.get(i).startsWith("END_BLOCKS")) {
                        this.layoutBlocks.add(information.get(i));
                        i++;
                    }
                }
                if (information.get(i).contains(":")) {
                    String[] afterDivide = information.get(i).split(":");
                    informationMapOfLevel.put(afterDivide[0], afterDivide[1]);
                }
                i++;
            } while (i < infoSize);
        }
    }

}

