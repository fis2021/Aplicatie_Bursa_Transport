package org.app.transport.model;

public class Offer {
    private String transportUsername;
    private String truckingUsername;
    private String price;
    private String dueDate;
    private String infoTrucking;
    private  Good g;
    public Offer(String transportUsername,String truckingUsername,String price,String dueDate,String infoTrucking,Good g)
    {
        this.transportUsername=transportUsername;
        this.infoTrucking=infoTrucking;
        this.dueDate=dueDate;
        this.truckingUsername=truckingUsername;
        this.price=price;
        this.g=g;
    }
    public String toString()
    {
        return transportUsername+"~"+truckingUsername+"~"+price+"~"+dueDate+"~"+infoTrucking+"|"+g.toString();
    }
}
