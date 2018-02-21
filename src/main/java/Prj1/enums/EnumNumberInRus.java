package Prj1.enums;
/*
* The class is help to find correct number of Enums
* @see LampInRus.
* @see WindowInRus.
* It has only one static method that return number(byte).*/
public final class EnumNumberInRus {

    /*Method needs to input int value.
    * return byte type number.
    * The value can be: 0, 1, 2.*/
    public static byte numberOfEnumValue(int size) {
        byte result = 0;
        if (size > 0) {
            if (size < 21) {
                if (size == 0) result = 0;
                if (size == 1) result = 1;
                if ((size > 1) && (size < 5)) result = 2;
                if ((size > 4) && (size < 21)) result = 0;
            } else {
                int size1 = size % 10;
                if (size1 == 0) result = 0;
                if (size1 == 1) result = 1;
                if ((size1 > 1) && (size < 5)) result = 2;
                if ((size1 > 4) && (size < 10)) result = 0;
            }
        }
        return result;
    }
}
