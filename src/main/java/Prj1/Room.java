package Prj1;

import Prj1.enums.EnumNumberInRus;
import Prj1.enums.LampInRus;
import Prj1.enums.WindowInRus;
import Prj1.items.Furniture;
import Prj1.items.Lamp;
import Prj1.items.Window;
import Prj1.exceptions.IlluminanceTooMuchException;
import Prj1.exceptions.SpaceUsageTooMuchException;

import java.util.*;

/*
 * Concrete class. It describes state and behavior of a room.*/
public class Room {

    public static final int MINLIGHTING = 300;//Constant. Minimum lighting
    public static final int MAXLIGHTING = 4000;//Constant. Maximum lighting

    private static int count = 1;//using to make default uniqueName of a room.

    private String uniqueName;//individual uniqueName of a room. The variable has to be unique.
    private int area;//area of a room
    private int maxAreaForItems;//describes maximum area that items can occupy
    private int currentLighting;// describes current lighting of a room
    private int currentAreaForItems;// describes current area that items occupy
    private int amountOfLamps = 0;//needs to count all lamps

    /*Map of lamps that keeps lists of lamps and sorted by key.
     * Key  - Integer value that describe power of lamps.
     * Value - lists of lamps.*/
    private Map<Integer, List<Lamp>> mapOfLamps = new TreeMap<Integer, List<Lamp>>();

    /*List that keeps abjects of Window in a room.*/
    private List<Window> listOfWindows = new ArrayList<Window>();

    /*List that keeps abjects of Furniture in a room.*/
    private List<Furniture> listOfFurniture = new ArrayList<Furniture>();

    /*Constructor receives three parameters and creates new object room with them:
     * uniqueName - uniqueName of room;
     * area - area of room;
     * amountOfWindows - amount of windows.
     * assign maxAreaForItems the value of 70 percent of rooms area;
     * create some objects of Windows equals to amountOfWindows parameter and add them to a list of Windows (@see listOfWindows);
     * assign currentLighting sum of thw Windows' lighting.*/
    public Room(String uniqueName, int area, int amountOfWindows) {
        this.uniqueName = uniqueName;
        this.area = area;
        maxAreaForItems = (int) (area * 0.7);
        for (int i = 0; i < amountOfWindows; i++) {
            Window window = new Window();
            listOfWindows.add(window);
            currentLighting += window.getLighting();
        }
    }

    /*Constructor receives one parameter and creates new object room with it:
     * area - area of room.
     * assign default value to a uniqueName: "No uniqueName room №" + count(@see count).
     * inicialise a variable area.
     * assign maxAreaForItems the value of 70 percent of rooms area;
     * assign currentLighting by 0.*/
    public Room(int area) {
        this.uniqueName = "No uniqueName room №" + count;
        count++;
        this.area = area;
        maxAreaForItems = (int) (area * 0.7);
        currentLighting = 0;
    }

    /*Constructor receives two parameters and creates new object room with them:
     * area - area of room;
     * amountOfWindows - amount of windows.
     * assign default value to a uniqueName: "No uniqueName room №" + count(@see count).
     * inicialise a variable area.
     * assign maxAreaForItems the value of 70 percent of rooms area;
     * assign currentLighting sum of thw Windows' lighting.*/
    public Room(int area, int amountOfWindows) {
        this.uniqueName = "No uniqueName room №" + count;
        count++;
        this.area = area;
        maxAreaForItems = (int) (area * 0.7);
        for (int i = 0; i < amountOfWindows; i++) {
            Window window = new Window();
            listOfWindows.add(window);
            currentLighting += window.getLighting();
        }
    }

    /*return String value uniqueName*/
    public String getUniqueName() {
        return uniqueName;
    }

    /*Add input object Lamp to map of lamps. */
    public void add(Lamp lamp) throws IlluminanceTooMuchException {
        int lighting = lamp.getLighting();
        if ((currentLighting + lighting) < MAXLIGHTING) {
            if (mapOfLamps.containsKey(lighting)) {
                mapOfLamps.get(lighting).add(lamp);
                currentLighting += lighting;
            } else {
                List<Lamp> list = new ArrayList<Lamp>();
                list.add(lamp);
                mapOfLamps.put(lighting, list);
                currentLighting += lighting;
            }
            amountOfLamps++;
        } else {
            throw new IlluminanceTooMuchException("\nIn room \"" + uniqueName + "\"" +
                    " Illuminance is too big. Maximum of the parameter have to be 4000 lk.");
        }
    }

    /*Add input Furniture to list of Furniture (@see listOfFurniture).
     * @throw SpaceUsageTooMuchException if current area for Items with input object's area more than maxAreaForItems.
     * @see maxAreaForItems, @currentAreaForItems*/
    public void add(Furniture furniture) throws SpaceUsageTooMuchException {
        int area = furniture.getMaxSpace();
        if ((currentAreaForItems + area) < maxAreaForItems) {
            listOfFurniture.add(furniture);
            currentAreaForItems += area;
        } else {
            throw new SpaceUsageTooMuchException("\nExceeded the limit of space occupied by furniture.");
        }
    }

    /*Return String as a specific information of a room.*/
    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append(" " + uniqueName + "\n");
        resultString.append("  Освещённость = " + currentLighting + " лк");
        if (currentLighting < MINLIGHTING) {
            resultString.append("  Warning! Too small illuminance! Minimum is 300 lk. Please, add some lamps to the room!");
        }
        resultString.append(strLightingHelper());
        resultString.append("\n  Площадь = " + area + " м^2");
        resultString.append(strAreaHelper());
        resultString.append(strFurnitureHelper());
        return resultString.toString();
    }

    /*Helps to make a line of lighting*/
    private String strLightingHelper() {
        StringBuilder resultString = new StringBuilder();
        int amountOfWindows = listOfWindows.size();
        byte enumValue = 0;
        if ((amountOfWindows > 0) || (amountOfLamps > 0)) {
            resultString.append(" (");
            if (amountOfWindows > 0) {
                enumValue = EnumNumberInRus.numberOfEnumValue(amountOfWindows);
                WindowInRus windowInRus = WindowInRus.values()[enumValue];
                resultString.append(amountOfWindows + " " + windowInRus + " по " + listOfWindows.get(0).getLighting() + " лк;");
            }
            if (amountOfLamps > 0) {
                for (Map.Entry<Integer, List<Lamp>> pair : mapOfLamps.entrySet()) {
                    enumValue = EnumNumberInRus.numberOfEnumValue(pair.getValue().size());
                    LampInRus lampInRus = LampInRus.values()[enumValue];
                    resultString.append(" " + pair.getValue().size() + " " + lampInRus + " по " + pair.getKey() + " лк,");
                }
                resultString.deleteCharAt(resultString.lastIndexOf(","));
            } else {
                resultString.deleteCharAt(resultString.lastIndexOf(";"));
            }
            resultString.append(")");
        }
        return resultString.toString();
    }

    /*Helps to make a line of area*/
    private String strAreaHelper() {
        StringBuilder resultString = new StringBuilder();
        int amountOfFurniture = listOfFurniture.size();
        if (amountOfFurniture > 0) {
            int freeArea = area - currentAreaForItems;
            int percentOfFreeArea = (int) ((freeArea * 100) / area);
            resultString.append(" (занято " + currentAreaForItems + " м^2, гарантированно свободно ");
            resultString.append(freeArea + " м^2 или " + percentOfFreeArea + "% площади)");
        } else {
            resultString.append(" (свободно 100%)");

        }
        return resultString.toString();
    }

    /*Helps to make a line of Furniture*/
    private String strFurnitureHelper() {
        StringBuilder resultString = new StringBuilder();
        if (listOfFurniture.size() > 0) {
            resultString.append("\n  Мебель:");
            for (Furniture furniture : listOfFurniture) {
                resultString.append("\n  " + furniture.toString());
            }
        } else {
            resultString.append("\n  Мебели нет");
        }
        return resultString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return area == room.area &&
                maxAreaForItems == room.maxAreaForItems &&
                currentLighting == room.currentLighting &&
                currentAreaForItems == room.currentAreaForItems &&
                amountOfLamps == room.amountOfLamps &&
                Objects.equals(getUniqueName(), room.getUniqueName()) &&
                Objects.equals(mapOfLamps, room.mapOfLamps) &&
                Objects.equals(listOfWindows, room.listOfWindows) &&
                Objects.equals(listOfFurniture, room.listOfFurniture);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUniqueName(), area, maxAreaForItems, currentLighting, currentAreaForItems,
                amountOfLamps, mapOfLamps, listOfWindows, listOfFurniture);
    }
}
