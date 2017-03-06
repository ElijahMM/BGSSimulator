package com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations;

import io.realm.RealmObject;

/**
 * Created by mihai on 06.03.2017.
 */

public class TestItem extends RealmObject {
    private String value = "";
    private int number;
    private boolean validState;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isValidState() {
        return validState;
    }

    public void setValidState(boolean validState) {
        this.validState = validState;
    }

    public TestItem() {
    }

    public TestItem(String _value, int _number, boolean _validState) {
        this.value = _value;
        this.number = _number;
        this.validState = _validState;
    }
}