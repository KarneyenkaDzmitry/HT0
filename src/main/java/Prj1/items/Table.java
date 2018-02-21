package Prj1.items;

/*
 * The real class being the heir of the abstract class Furniture. It describe item for building - Table.*/
public class Table extends Furniture {

    /*The parameter is used for making default name: "No name table №" + count.*/
    private static int count = 1;

    /*Constructor with  three parameters:
     *  @param name (String) - name of  an object;
     *  @param minSpace (int) - minSpace that the object can take up;
     *  @param maxSpace (int)- maxSpace that the object can take up.
     *  Call the suitable constructor of parent Furniture.*/
    public Table(String name, int minSpace, int maxSpace) {
        super(name, minSpace, maxSpace);
    }

    /*Constructor with two parameters:
     *  @param name (String) - name of object;
     *  @param maxSpace (int)- maxSpace that the object can take up.
     *  In this case minSpace == maxSpace.
     *  Call the suitable constructor of parent Furniture.*/
    public Table(String name, int maxSpace) {
        super(name, maxSpace);
    }

    /*Constructor with one parameter:
     *  @param maxSpace (int)- maxSpace that the object can take up.
     *  In this case minSpace == maxSpace.
     *  Call the suitable constructor of parent Furniture with two parameters:
     *  @param name - "No name table №" + count (@see count);
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  And increment private static int count (@see count).*/
    public Table(int maxSpace) {
        super("No name table №" + count, maxSpace);
        count++;
    }

    /*Constructor with two parameters:
     *  @param minSpace (int) - minSpace that the object can take up;
     *  @param maxSpace (int)- maxSpace that the object can take up.
     *  Call the suitable constructor of parent Furniture with three parameters:
     *  @param name - "No name table №" + count (@see count);
     *  @param minSpace (int) - minSpace that the object can take up;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  And increment private static int count (@see count).*/
    public Table(int minSpace, int maxSpace) {
        super("No name table №" + count, minSpace, maxSpace);
        count++;
    }

}
