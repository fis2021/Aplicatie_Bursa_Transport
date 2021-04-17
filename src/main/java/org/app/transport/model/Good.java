package org.app.transport.model;

public class Good {
    private String weight;
    private String LocationFrom;
    private String LocationTo;
    private String CompanyName;
    private String DetailedAddress;
    public Good(String weight, String LocationFrom, String LocationTo,String CompanyName,String DetailedAddress) {
        this.weight = weight;
        this.LocationFrom =LocationFrom ;
        this.LocationTo = LocationTo;
        this.CompanyName=CompanyName;
        this.DetailedAddress=DetailedAddress;
    }
    public Good() {
    }
    public String getWeight() {
        return weight;
    }
    public String getLocationFrom(){return LocationFrom;}
    public String getLocationTo() {
        return LocationTo;
    }
    public String getCompanyName(){return CompanyName;}
    public String getDetailedAddress(){return DetailedAddress;}
    public String toString()
    {
        return CompanyName+ ":"+LocationFrom+"-"+LocationTo;
    }
}
