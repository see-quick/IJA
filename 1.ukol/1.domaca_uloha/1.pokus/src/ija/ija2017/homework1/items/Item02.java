package ija.ija2017.homework1.items;

public class Item02 extends AbstractItem {

    public Item02(String orderStateInformation, int stateInformation) {
        super(orderStateInformation, stateInformation);
    }

    @Override
    public void execute() {
        stateInformation += 2;
    }

    @Override
    public String getName() {
        return super.orderStateInformation;
    }

    @Override
    public int numberOfPorts() {
        return super.numberOfPorts;
    }

    @Override
    public int getState() {
        return super.stateInformation;
    }
}
