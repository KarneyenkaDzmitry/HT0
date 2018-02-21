package Prj1;

import Prj1.exceptions.IncorrectNameOfRoomException;
import Prj1.exceptions.UniqueNameException;
import Prj1.items.*;
import Prj1.exceptions.IlluminanceTooMuchException;
import Prj1.exceptions.SpaceUsageTooMuchException;

/*The class is used to check functional this module.
* Tester can use it for test the module Building. He can make different tests like negative or positive...*/
public class BuildingTestDrive {

    public static void main(String[] args) throws SpaceUsageTooMuchException, IlluminanceTooMuchException, UniqueNameException, IncorrectNameOfRoomException {
        Building building = new Building("Здание 1");
        building.addRoom("Комната 1", 100, 3);
        building.addRoom("Комната 2", 5, 2);
        //building.addRoom("Комната 2", 5, 2);
        building.addRoom("Комната 3", 5);
        building.addRoom(5);
        building.addRoom(7);

        //building.getRoom("Komnata 3").add(new Lamp(299));
        building.getRoom("Комната 3").add(new Lamp(299));
        building.getRoom("Комната 1").add(new Lamp(150));
        building.getRoom("Комната 1").add(new Lamp(150));
        building.getRoom("Комната 1").add(new Lamp(150));
        building.getRoom("Комната 1").add(new Lamp(150));
        building.getRoom("Комната 1").add(new Lamp(150));
        building.getRoom("Комната 1").add(new Lamp(250));
        building.getRoom("Комната 1").add(new Lamp(250));
        building.getRoom("Комната 1").add(new Lamp(250));
        building.getRoom("Комната 1").add(new Lamp(250));
        //building.getRoom("Комната 1").add(new Lamp(250));
        //building.getRoom("Комната 1").add(new Lamp(250));
        Table table = new Table("Стол письменный", 3);
        building.getRoom("Комната 1").add(table);
        building.getRoom("Комната 1").add(new Chair("Кресло мягкое", 2, 3));
        building.getRoom("Комната 1").add(new Chair("Кресло пушистое", 5, 6));
        building.getRoom("Комната 1").add(new Chair("Кресло пушистое", 4, 5));
        building.getRoom("Комната 1").add(new Chair("Кресло мягкое", 1, 2));
        building.getRoom("Комната 1").add(new Chair("Кресло", 1, 2));
        building.getRoom("Комната 1").add(new Sofa("Диван современный", 5));
        building.getRoom("Комната 1").add(new Cupboard("Шкаф кухонный", 3));
        building.getRoom("Комната 1").add(new Cupboard("Шкаф кухонный", 4));
        building.getRoom("Комната 1").add(new Cupboard("Шкаф кухонный", 5));
        building.getRoom("Комната 1").add(new Cupboard("Шкаф кухонный", 2));
        building.getRoom("Комната 1").add(new Cupboard("Шкаф кухонный", 2));
        building.getRoom("Комната 1").add(new Cupboard("Шкаф кухонный", 5));
        building.getRoom("Комната 1").add(new Cooker("Плита кухонная", 2));
        building.describe();

    }
}
