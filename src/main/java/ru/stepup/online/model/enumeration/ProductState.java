package ru.stepup.online.model.enumeration;

public enum ProductState {
    CLOSED(0,"Открыт"),
    OPEN(1,"Открыт"),
    RESERVED(2,"Зарезервирован"),
    DELETED(3,"Удален");

    int id;
    String disc;

    ProductState(int id, String disc) {
        this.id = id;
        this.disc = disc;
    }

    public String getDisc() {
        return disc;
    }
}
