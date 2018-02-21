package Prj1;

import Prj1.exceptions.IncorrectNameOfRoomException;
import Prj1.exceptions.UniqueNameException;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/*
 * The class is concrete. It is used to make buildings.*/
public class Building {

    /*It needs to create default uniqueName of building: "Building " + count.*/
    private static int count = 1;

    /*It keeps uniqueName of a building.
     * The variable has to be unique.*/
    private String uniqueName;

    /*It keeps map of rooms in a sorted state. It means that rooms have to have different names
     * because of map.
     * Key is uniqueName of room;
     * Value is object of room.*/
    private Map<String, Room> mapOfRooms = new TreeMap<String, Room>();

    /*Constructor with one input parameter: uniqueName (@see uniqueName). It inicialise the variable uniqueName. */
    public Building(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    /*Default constructor. It inicialise the variable uniqueName default value: "Building " + count.(@see count) */
    public Building() {
        this.uniqueName = "Building " + count;
        count++;
    }

    /*Getter. It returns a uniqueName of a building.*/
    public String getName() {
        return uniqueName;
    }

    /*Getter. Return map of rooms (@see mapOfRooms).*/
    public Map<String, Room> getMapOfRooms() {
        return mapOfRooms;
    }

    /*Method receives three parameters and creates new object room with them:
     * uniqueName - uniqueName of room;
     * area - area of room;
     * amountOfWindows - amount of windows.
     * Then it puts the room in map (@see mapOfRoom)
     * throws UniqueNameException if mapOfRooms contains input uniqueName.*/
    public void addRoom(String uniqueName, int area, int amountOfWindows) throws UniqueNameException {
        if (!mapOfRooms.containsKey(uniqueName)) {
            Room room = new Room(uniqueName, area, amountOfWindows);
            mapOfRooms.put(uniqueName, room);
        } else {
            throw new UniqueNameException("\nName of room \"" + uniqueName + "\" exists. UniqueName have to be unique.");
        }
    }

    /*Method receives two parameters and creates new object room with them:
     * uniqueName - uniqueName of room;
     * area - area of room;
     * Then it puts the room in map (@see mapOfRoom)
     * throws UniqueNameException if mapOfRooms contains input uniqueName.*/
    public void addRoom(String uniqueName, int area) throws UniqueNameException {
        if (!mapOfRooms.containsKey(uniqueName)) {
            Room room = new Room(uniqueName, area, 0);
            mapOfRooms.put(uniqueName, room);
        } else {
            throw new UniqueNameException("\nName of room \"" + uniqueName + "\" exists. UniqueName have to be unique.");
        }
    }

    /*Method receives two parameters and creates new object room with them:
     * area - area of room;
     * amountOfWindows - amount of windows.
     * Then it puts the room in map (@see mapOfRoom)*/
    public void addRoom(int area, int amountOfWindows) {
        Room room = new Room(area, 0);
        mapOfRooms.put(room.getUniqueName(), room);
    }

    /*Method receives one parameter and creates new object room with it:
     * area - area of room;
     * Then it puts the room in map (@see mapOfRoom)*/
    public void addRoom(int area) {
        Room room = new Room(area);
        mapOfRooms.put(room.getUniqueName(), room);
    }

    /*Getter. It returns map of rooms (@see mapOfRooms)
    * @throw IncorrectNameOfRoomException.*/
    public Room getRoom(String uniqueName) throws IncorrectNameOfRoomException {
        if (mapOfRooms.containsKey(uniqueName)) {
            return mapOfRooms.get(uniqueName);
        } else {
            throw new IncorrectNameOfRoomException("\nRoom \"" + uniqueName + "\" doesn't exist in the building \""
                    + this.uniqueName+"\"");
        }
    }

    /*Output in console specific information about a building.
     * uniqueName building and information about all rooms in it.*/
    public void describe() {
        System.out.println(uniqueName);
        for (Map.Entry<String, Room> pair : mapOfRooms.entrySet()) {
            System.out.println(pair.getValue().toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Building)) return false;
        Building building = (Building) o;
        return Objects.equals(uniqueName, building.uniqueName) &&
                Objects.equals(getMapOfRooms(), building.getMapOfRooms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueName, getMapOfRooms());
    }
}
