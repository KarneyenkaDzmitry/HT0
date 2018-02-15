package Prj1.items;

/*The class is used to create objects "Window". It describes state and behavior object.
 * the class implements two interfaces:
 * @see Item - marks all items that are used for buildings;
 * @see Lighting - marks all classes that have lighting value.*/
public class Lamp implements Item, Lighting {

    /*Keep a value of lighting of object.*/
    private int lighting = 0;

    /*Constructor with one parameter that initialise the variable lighting.*/
    public Lamp(int lighting) {
        this.lighting = lighting;
    }

    /*Getter.
     *return current value of variable lighting.
     * @see lighting.*/
    public int getLighting() {
        return lighting;
    }

}
