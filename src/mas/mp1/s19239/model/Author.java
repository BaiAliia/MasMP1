package mas.mp1.s19239.model;

import mas.mp1.s19239.model.exceptions.ModelValException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Author implements Serializable {

    private long id;
    private String Name;

    public Author(long id, String name) {
        this.id = id;
       setName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        if(name==null||name.isBlank()){
            throw new ModelValException("name of the author cant be null");
        }
        Name = name;
    }
}
