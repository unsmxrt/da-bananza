package client.setting;

public class DoubleSetting extends Setting {
    private double value;

    public DoubleSetting(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
