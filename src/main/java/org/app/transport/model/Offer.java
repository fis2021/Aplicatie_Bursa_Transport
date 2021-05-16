package org.app.transport.model;

public class Offer {
    private String transportUsername;
    private String truckingUsername;
    private String price;
    private String dueDate;
    private String infoTrucking;
    private String offerState;
    private  Good g;
    private String rating;

    public String getTransportUsername() {
        return transportUsername;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getInfoTrucking() {
        return infoTrucking;
    }

    public void setInfoTrucking(String infoTrucking) {
        this.infoTrucking = infoTrucking;
    }

    public String getOfferState() {
        return offerState;
    }

    public void setOfferState(String offerState) {
        this.offerState = offerState;
    }

    public Good getG() {
        return g;
    }

    public void setG(Good g) {
        this.g = g;
    }

    public void setTransportUsername(String transportUsername) {
        this.transportUsername = transportUsername;
    }

    public Offer(String transportUsername, String truckingUsername, String price, String dueDate, String infoTrucking, String offerState, Good g)
    {
        this.transportUsername=transportUsername;
        this.infoTrucking=infoTrucking;
        this.dueDate=dueDate;
        this.truckingUsername=truckingUsername;
        this.price=price;
        this.offerState=offerState;
        this.g=g;
        this.rating = "0";
    }
    public String toString()
    {
        return transportUsername+"~"+truckingUsername+"~"+price+"~"+dueDate+"~"+infoTrucking+"~"+offerState+"|"+g.toString();
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
