package client.setting;

public class IntSetting extends Setting {
    private int value;

    public IntSetting(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
