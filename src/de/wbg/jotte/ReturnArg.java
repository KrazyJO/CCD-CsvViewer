package de.wbg.jotte;

public class ReturnArg {

    public boolean isPossible = false;
    private Object value;

    public int getIntValue() {
        return (int) value;
    }

    public String getStringValue() {
        return (String) value;
    }

    public boolean getBoolValue() {
        return (boolean) value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
