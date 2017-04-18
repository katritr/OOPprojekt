/**
 * Created by kats on 01/04/17.
 */
public class Aine {
    protected String ainekood;
    protected String nimetus;

    public Aine(String ainekood, String nimetus) {
        this.ainekood = ainekood;
        this.nimetus = nimetus;
    }

    public String getAinekood() {
        return ainekood;
    }

    public void setAinekood(String ainekood) {
        this.ainekood = ainekood;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    public String toString() {
        return ainekood + " " + nimetus;
    }

    public String kirjutaFailiJaoks() {
        return ainekood + ";" + nimetus + "\n";
    }
}
