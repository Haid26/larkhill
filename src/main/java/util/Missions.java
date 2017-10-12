package util;

public class Missions {
    String desc;
    int status, id;
    public Missions(int i, String d, int s)
    {
        this.id = i;
        this.desc = d;
        this.status = s;
    }
    public void setStatus(int NewStatus)
    {
        this.status = NewStatus;
    }
    public int getId()
    {
        return this.id;
    }
    public int getStatus()
    {
        return this.status;
    }
    public String getDesc()
    {
        return this.desc;
    }
}
