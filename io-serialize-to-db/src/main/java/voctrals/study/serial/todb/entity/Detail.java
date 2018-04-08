package voctrals.study.serial.todb.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Detail implements Serializable {
    private static final long serialVersionUID = 216431074990096288L;
    private String type;

    private String[] sports;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getSports() {
        return sports;
    }

    public void setSports(String[] sports) {
        this.sports = sports;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "type='" + type + '\'' +
                ", sports=" + Arrays.toString(sports) +
                '}';
    }
}
