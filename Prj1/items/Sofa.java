package Prj1.items;
/*
 * The real class being the heir of the abstract class Furniture. It describe item for building - Microwave.*/
public class Sofa extends Furniture {

    /*The parameter is used for making default name: "No name sofa №" + count.*/
    private static int count = 1;

    /*Constructor with  three parameters:
     *  @param name (String) - name of furniture;
     *  @param minSpace (int) - minSpace that the furniture can take up;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  Call the suitable constructor of parent Furniture.*/
    public Sofa(String name, int minSpace, int maxSpace) {
        super(name, minSpace, maxSpace);
    }

    /*Constructor with two parameters:
     *  @param name (String) - name of furniture;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  In this case minSpace == maxSpace.
     *  Call the suitable constructor of parent Furniture.*/
    public Sofa(String name, int maxSpace) {
        super(name, maxSpace);
    }

    /*Constructor with one parameter:
     *  @param maxSpace (int)- maxSpace that the object can take up.
     *  In this case minSpace == maxSpace.
     *  Call the suitable constructor of parent Furniture with two parameters:
     *  @param name - "No name sofa №" + count (@see count);
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  And increment private static int count (@see count).*/
    public Sofa(int maxSpace) {
        super("No name sofa №" + count, maxSpace);
        count++;
    }

    /*Constructor with two parameters:
     *  @param minSpace (int) - minSpace that the object can take up;
     *  @param maxSpace (int)- maxSpace that the object can take up.
     *  Call the suitable constructor of parent Furniture with three parameters:
     *  @param name - "No name sofa №" + count (@see count);
     *  @param minSpace (int) - minSpace that the object can take up;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  And increment private static int count (@see count).*/
    public Sofa(int minSpace, int maxSpace) {
        super("No name sofa №" + count, minSpace, maxSpace);
        count++;
    }
}
