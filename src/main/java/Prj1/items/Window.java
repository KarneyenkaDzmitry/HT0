package Prj1.items;
/*The class is used to create objects "Window". It describes state and behavior object.
* the class implements two interfaces:
* @see Item - marks all items that are used for buildings;
* @see Lighting - marks all classes that have lighting value.*/
public class Window implements Item, Lighting {

    /*Keep a value of lighting of object.*/
    private int lighting = 0;

    /*Default constructor that assigns the variable lighting default value: 700*/
    public Window() {
        this.lighting = 700;
    }

    /*Getter.
    *return current value of variable lighting.
    * @see lighting.*/
    public int getLighting() {
        return lighting;
    }

}
