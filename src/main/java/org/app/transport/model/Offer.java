package org.app.transport.model;

public class Offer {
    private String transportUsername;
    private String truckingUsername;
    private String price;
    private String dueDate;
    private String infoTrucking;
    private  Good g;
    public String toString()
    {
        return transportUsername+"-"+truckingUsername+"-"+price+"-"+dueDate+"-"+infoTrucking+"|"+g.toString();
    }
}
