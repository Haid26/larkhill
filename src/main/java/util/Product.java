package util;

public class Product {
    private int id, amount, sold;
    private String name;
    public Product(int id, String name, int am, int sold)
    {
        this.id=id;
        this.name=name;
        this.amount=am;
        this.sold=sold;

    }
    public void setAmount(int amount)
    {
        this.amount=amount;
    }
    public void setSold(int sold)
    {
        this.sold=sold;
    }
    public int getId()
    {
        return this.id;
    }
    public String getName()
    {
        return this.name;
    }
    public int getAmount(){
        return this.amount;
    }
    public int getSold()
    {
        return this.sold;
    }
}
