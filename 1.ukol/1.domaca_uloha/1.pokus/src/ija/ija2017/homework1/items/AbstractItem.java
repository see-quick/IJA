package ija.ija2017.homework1.items;

import ija.ija2017.repository.Item;

import java.util.Objects;


public abstract class  AbstractItem implements Item {


    protected int stateInformation;
    protected String orderStateInformation;          // poradie itemov
    protected int numberOfPorts;

    public AbstractItem(String orderStateInformation , int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
        this.orderStateInformation = orderStateInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItem that = (AbstractItem) o;
        return numberOfPorts == that.numberOfPorts &&
                Objects.equals(orderStateInformation, that.orderStateInformation);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderStateInformation, numberOfPorts);
    }
}
