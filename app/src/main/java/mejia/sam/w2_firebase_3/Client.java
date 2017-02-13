package mejia.sam.w2_firebase_3;

/**
 * Created by User on 11/23/2016.
 */

public class Client {

    private int id;
    private String name;
    private String charge;

    public Client() {
        //Es obligatorio incluir constructor por defecto
    }

    public Client(int id, String name,String charge)
    {
        this.id = id;
        this.name = name;
        this.charge = charge;
    }
    public Client(int id)
    {
        this.id = id;
    }

    public Client(String name,String charge)
    {
        this.name = name;
        this.charge = charge;
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

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }
}
