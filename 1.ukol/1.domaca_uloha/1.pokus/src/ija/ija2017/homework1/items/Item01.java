package ija.ija2017.homework1.items;

public class Item01 extends AbstractItem {

    public Item01( String orderStateInformation  , int stateInformation) {
        super(orderStateInformation , stateInformation );
    }


    @Override
    public void execute() {
        stateInformation++;
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
