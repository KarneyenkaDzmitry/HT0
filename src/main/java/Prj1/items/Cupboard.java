package Prj1.items;
/*
 * The real class being the heir of the abstract class Furniture. It describe item for building - Cupboard.*/
public class Cupboard extends Furniture {

    /*The parameter is used for making default name: "No name cupboard №" + count.*/
    private static int count = 1;

    /*Constructor with  three parameters:
     *  @param name (String) - name of furniture;
     *  @param minSpace (int) - minSpace that the furniture can take up;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  Call the suitable constructor of parent Furniture.*/
    public Cupboard(String name, int minSpace, int maxSpace) {
        super(name, minSpace, maxSpace);
    }

    /*Constructor with two parameters:
     *  @param name (String) - name of furniture;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  In this case minSpace == maxSpace.
     *  Call the suitable constructor of parent Furniture.*/
    public Cupboard(String name, int maxSpace) {
        super(name, maxSpace);
    }

    /*Constructor with one parameter:
     *  @param maxSpace (int)- maxSpace that the object can take up.
     *  In this case minSpace == maxSpace.
     *  Call the suitable constructor of parent Furniture with two parameters:
     *  @param name - "No name cupboard №" + count (@see count);
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  And increment private static int count (@see count).*/
    public Cupboard(int maxSpace) {
        super("No name cupboard №" + count, maxSpace);
        count++;
    }

    /*Constructor with two parameters:
     *  @param minSpace (int) - minSpace that the object can take up;
     *  @param maxSpace (int)- maxSpace that the object can take up.
     *  Call the suitable constructor of parent Furniture with three parameters:
     *  @param name - "No name cupboard №" + count (@see count);
     *  @param minSpace (int) - minSpace that the object can take up;
     *  @param maxSpace (int)- maxSpace that the furniture can take up.
     *  And increment private static int count (@see count).*/
    public Cupboard(int minSpace, int maxSpace) {
        super("No name cupboard №" + count, minSpace, maxSpace);
        count++;
    }
}
