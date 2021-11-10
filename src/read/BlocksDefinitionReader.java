package read;
import utillity.BlockCreator;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * this class is responsible for blocks definition reader.
 * @author Dvir Asaf 313531113.
 */
public class BlocksDefinitionReader {
    private Map<String, BlockCreator> blockCreatorMap = new TreeMap<>();
    private Map<String, Integer> widthSpacersMap = new TreeMap<>();
    private List<String> blocksDefinitionReaders = new ArrayList<>();
    private List<String> stringsDefinitionReaders = new ArrayList<>();
    private List<String> lines = new ArrayList<>();
    private List<String> defaults = new ArrayList<>();
    private Reader reader;


    /**
     * this method read lines.
     */
    public void readLines() {
        Reader newReader = this.reader;
        try {
            String line = ((BufferedReader) newReader).readLine();
            while (line != null) {
                if ((!line.isEmpty()) && (!line.startsWith("#"))) {
                    this.lines.add(line);
                }
                line = ((BufferedReader) newReader).readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Separate starts.
     */
    public void separateStarts() {
        for (String line : this.lines) {
            if (line.startsWith("bdef")) {
                this.blocksDefinitionReaders.add(line);
            } else if (line.startsWith("default")) {
                this.defaults.add(line);
            } else if (line.startsWith("sdef")) {
                this.stringsDefinitionReaders.add(line);
            } else {
                throw new RuntimeException("no default/blocksDefinitionReaders/stringsDefinitionReaders");
            }
        }
    }


    /**
     * Extract creators.
     */
    public void extractCreators() {
        Map<String, String> defaultsMap = new TreeMap<>();
        Map<String, String> blocksMap = new TreeMap<>();

        if (!this.defaults.isEmpty()) {
            String[] defaultsBlock = defaults.get(0).split(" ");
            for (int i = 1; i < defaultsBlock.length; i++) {
                String[] defaultsBlockSplit = defaultsBlock[i].split(":");
                defaultsMap.put(defaultsBlockSplit[0], defaultsBlockSplit[1]);
            }
        }

        for (String blocksDefinitionReader : blocksDefinitionReaders) {
            blocksMap.clear();
            String[] whiteSpaceSplitBlocksDefinitionReaders = blocksDefinitionReader.split(" ");
            for (int j = 1; j < whiteSpaceSplitBlocksDefinitionReaders.length; j++) {
                String[] properties = whiteSpaceSplitBlocksDefinitionReaders[j].split(":");
                blocksMap.put(properties[0], properties[1]);
            }

            String symbol = null;
            int point = 1;
            int height = 0;
            int width = 0;

            if (blocksMap.containsKey("symbol")) {
                symbol = blocksMap.get("symbol");
            }
            if (blocksMap.containsKey("hit_points")) {
                point = Integer.parseInt(blocksMap.get("hit_points"));
            } else if (!defaultsMap.isEmpty() && defaultsMap.containsKey("hit_points")) {
                point = Integer.parseInt(defaultsMap.get("hit_points"));
            }
            Object[] blockKeysObject = blocksMap.keySet().toArray();
            String[] blockKeys = new String[blockKeysObject.length];
            Map<String, String> fillStartsMap = new TreeMap<>();
            for (int j = 0; j < blockKeysObject.length; j++) {
                blockKeys[j] = (String) blockKeysObject[j];
                if (blockKeys[j].startsWith("fill")) {
                    fillStartsMap.put(blockKeys[j], blocksMap.get(blockKeys[j]));
                }
            }
            if (blocksMap.containsKey("height")) {
                height = Integer.parseInt(blocksMap.get("height"));
            } else if (!defaultsMap.isEmpty() && defaultsMap.containsKey("height")) {
                height = Integer.parseInt(defaultsMap.get("height"));
            }
            if (blocksMap.containsKey("width")) {
                width = Integer.parseInt(blocksMap.get("width"));
            } else if (!defaultsMap.isEmpty() && defaultsMap.containsKey("width")) {
                width = Integer.parseInt(defaultsMap.get("width"));
            }
            BlockCreator blockSymbol = new FactoryBlockCreator(width, height, fillStartsMap, point);

            blockCreatorMap.put(symbol, blockSymbol);
        }
    }

    /**
     * parseSpacers.
     * <p>
     * return spacers map
     */
    public void parseSpacers() {

        for (String stringsDefinitionReader : stringsDefinitionReaders) {
            String symbolSpace = "";
            int widthSpace = 0;
            Map<String, String> spacersMap = new TreeMap<>();
            String[] spacers;
            spacers = stringsDefinitionReader.split(" ");
            for (int j = 1; j < spacers.length; j++) {
                String[] afterColonSplit = spacers[j].split(":");
                spacersMap.put(afterColonSplit[0], afterColonSplit[1]);
            }
            if (spacersMap.containsKey("symbol")) {
                symbolSpace = spacersMap.get("symbol");
            }
            if (spacersMap.containsKey("width")) {
                widthSpace = Integer.parseInt(spacersMap.get("width"));
            }

            widthSpacersMap.put(symbolSpace, widthSpace);
        }
    }


    /**
     * @return the block creator map.
     */
    public Map<String, BlockCreator> getBlockCreatorMap() {
        return blockCreatorMap;
    }

    /**
     * this method set the block creator map.
     * @param blockCreatorMap a given map.
     */
    public void setBlockCreatorMap(final Map<String, BlockCreator> blockCreatorMap) {
        this.blockCreatorMap = blockCreatorMap;
    }

    /**
     * Gets spacer widths map.
     *
     * @return the spacer widths map
     */
    public Map<String, Integer> getWidthSpacersMap() {
        return widthSpacersMap;
    }

    /**
     * this method set the width spacers map.
     * @param widthSpacersMap
     */
    public void setWidthSpacersMap(final Map<String, Integer> widthSpacersMap) {
        this.widthSpacersMap = widthSpacersMap;
    }

    /**
     * Sets reader.
     *
     * @param r the r
     */
    public void setReader(final Reader r) {
        this.reader = r;
    }


    /**
     * this method read From blocks symbols factory.
     *
     * @param reader a given reader.
     * @return blocks from symbols factory.
     */
    public static BlocksFromSymbolsFactory fromReader(final Reader reader) {
        BlocksDefinitionReader blocksDefinitionReader = new BlocksDefinitionReader();
        blocksDefinitionReader.setReader(reader);
        blocksDefinitionReader.readLines();
        blocksDefinitionReader.separateStarts();
        blocksDefinitionReader.extractCreators();
        blocksDefinitionReader.parseSpacers();
        Map<String, Integer> spacerWidths = new TreeMap<>(blocksDefinitionReader.getWidthSpacersMap());
        Map<String, BlockCreator> blockCreators = new TreeMap<>(blocksDefinitionReader.getBlockCreatorMap());
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
}
