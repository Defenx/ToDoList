package by.gsu.epamlab.enums;

public enum StateEnum {
    ACTIVE(1), FIXED(2), BIN(3), BIN_FIXED(4);

    private int id;

    StateEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
