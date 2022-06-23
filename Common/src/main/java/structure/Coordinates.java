package structure;

import lombok.Getter;

import java.io.Serializable;

/**
 * Organization coordinates.
 */
public class Coordinates implements Serializable {
    @Getter
    private float x;
    @Getter
    private float y;

    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        String s = "";
        s += "{\"x\" : " + Float.toString(x) + ", ";
        s += "\"y\" : " + Float.toString(y) + "}";
        return s;
    }

}
