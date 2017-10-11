package util;
import javax.management.relation.Role;
import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 6297385302078200511L;

    private String name;

    private int id;
    private String role;

    public User(String nm, String role, int i){
        this.name=nm;
        this.id=i;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }




    public void setId(int id) {
        this.id = id;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }



    public int getId() {
        return id;
    }
    public String getLogin() {return name;}

    public String getRole() {
        return role;
    }

    @Override
    public String toString(){
        return "Name="+this.name+", role="+this.role;
    }
}