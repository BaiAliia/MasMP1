package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;

import java.io.Serializable;
//My complex attribute
public class Adress implements Serializable {
    private String city ;
    private String street;
    private String zip ;

    public Adress(String city, String street, String zip) {
       setCity(city);
       setStreet(street);
       setZip(zip);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city==null||city.isBlank()){
            throw new ModelValException("The genre cant be null or empty ");
        }
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if(street==null||street.isBlank()){
            throw new ModelValException("The street cant be null or empty ");
        }
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        if(zip==null||zip.isBlank()){
            throw new ModelValException("The zip cant be null or empty ");
        }
        this.zip = zip;
    }
}
