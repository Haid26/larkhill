package util;

public class ProdMissions {
    private int id, status, amount;
    private String name;
    public ProdMissions(int id, String name, int amount, int status)
    {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.status = status;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setAmount(int am)
    {
        this.amount = am;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public int getId(){
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public int getAmount()
    {
        return this.amount;
    }
    public int getStatus()
    {
        return this.status;
    }
}
