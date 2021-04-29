package org.app.transport.model;

public class Offer {
    private String transportUsername;
    private String truckingUsername;
    private String price;
    private String dueDate;
    private String infoTrucking;
    private String offerState;
    private  Good g;
    public Offer(String transportUsername,String truckingUsername,String price,String dueDate,String infoTrucking,String offerState,Good g)
    {
        this.transportUsername=transportUsername;
        this.infoTrucking=infoTrucking;
        this.dueDate=dueDate;
        this.truckingUsername=truckingUsername;
        this.price=price;
        this.offerState=offerState;
        this.g=g;
    }
    public String toString()
    {
        return transportUsername+"~"+truckingUsername+"~"+price+"~"+dueDate+"~"+infoTrucking+"~"+offerState+"|"+g.toString();
    }
}
