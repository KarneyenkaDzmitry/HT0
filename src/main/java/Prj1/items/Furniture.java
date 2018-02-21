package Prj1.items;

/*
 * The class is abstract. It describes all common values and methods for classes, that extend it.
 * It marks an interface Item. It helps to find all items that are used for buildings.*/
public abstract class Furniture implements Item {

    /*Constructor with  three parameters:
     *  @param name (String) - name of furniture;
     *  @param minSpace (int) - minSpace that the furniture can take up;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.*/
    public Furniture(String name, int minSpace, int maxSpace) {
        this.name = name;
        if (minSpace < maxSpace) {
            this.minSpace = minSpace;
            this.maxSpace = maxSpace;
        } else {
            this.minSpace = maxSpace;
            this.maxSpace = minSpace;
        }
    }

    /*Constructor with two parameters:
     *  @param name (String) - name of furniture;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  In this case minSpace == maxSpace.*/
    public Furniture(String name, int maxSpace) {
        this.name = name;
        this.maxSpace = maxSpace;
        this.minSpace = maxSpace;
    }


    /* The name of furniture.*/
    private String name;

    /*The minimum space occupied by furniture.*/
    private int minSpace;

    /*The maximum space occupied by furniture.*/
    private int maxSpace;

    /*Return the name of furniture.*/
    public String getName() {
        return name;
    }

    /*Return the value of minSpace.The minimum space occupied by furniture.*/
    public int getMinSpace() {
        return minSpace;
    }

    /*Return the value of maxSpace.The maximum space occupied by furniture.*/
    public int getMaxSpace() {
        return maxSpace;
    }

    /*Return a specific information about an object as a string.*/
    @Override
    public String toString() {
        if (maxSpace == minSpace) {
            return name + " (площадь " + maxSpace + " м^2)";
        } else {
            return name + " (площадь от  " + minSpace + " м^2 до " + maxSpace + " м^2)";
        }
    }

    @Override
    public int hashCode() {
        int result = maxSpace + minSpace;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Furniture)) return false;
        Furniture furniture = (Furniture) obj;
        if (minSpace != furniture.minSpace) return false;
        if (maxSpace != furniture.maxSpace) return false;
        return name.equals(furniture.name);
    }
}
