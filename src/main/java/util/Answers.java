package util;

public class Answers {
    private boolean anwer = false;
    private String from ="";
    public Answers(boolean ans)
    {
        this.anwer = ans;
    }
    public Answers(String addr)
    {
        this.from = addr;
    }
    public Answers (boolean ans, String addr)
    {
        this.anwer = ans;
        this.from = addr;
    }
    public void setFrom(String addr)
    {
        this.from = addr;
    }
    public void setAnwer(boolean ans)
    {
        this.anwer = ans;
    }
    public String getFrom()
    {
        return this.from;
    }
    public boolean getAnwer()
    {
        return this.anwer;
    }
}
