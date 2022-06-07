package DAO;

import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private String name;
    private String password;

    public Client(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    public String getByIndex(int index){
        if(index==0)
            return String.valueOf("clientId:"+" "+id);
        if(index==1)
            return name;
        return null;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}'+ "\n";
    }
}
