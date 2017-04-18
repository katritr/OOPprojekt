import java.util.Date;

/**
 * Created by kats on 01/04/17.
 */
public class Kohustus {
    protected int kood;
    protected String ainekood;
    protected String tüüp;
    protected String tähtaeg;
    protected double maht;
    protected String nimetus;
    protected double tehtudmaht;
    protected boolean aktiivne;

    public Kohustus(int kood, String ainekood, String tüüp, String tähtaeg, double maht, String nimetus, double tehtudmaht, boolean aktiivne) {
        this.kood = kood;
        this.ainekood = ainekood;
        this.tüüp = tüüp;
        this.tähtaeg = tähtaeg;
        this.maht = maht;
        this.nimetus = nimetus;
        this.tehtudmaht = tehtudmaht;
        this.aktiivne = aktiivne;
    }

    public int getKood() {
        return kood;
    }

    public void setKood(int kood) {
        this.kood = kood;
    }

    public String getAinekood() {
        return ainekood;
    }

    public void setAinekood(String ainekood) {
        this.ainekood = ainekood;
    }

    public String getTüüp() {
        return tüüp;
    }

    public void setTüüp(String tüüp) {
        this.tüüp = tüüp;
    }

    public String getTähtaeg() {
        return tähtaeg;
    }

    public void setTähtaeg(String tähtaeg) {
        this.tähtaeg = tähtaeg;
    }

    public double getMaht() {
        return maht;
    }

    public void setMaht(double maht) {
        this.maht = maht;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    public double getTehtudmaht() {
        return tehtudmaht;
    }

    public void setTehtudmaht(double tehtudmaht) {
        this.tehtudmaht = tehtudmaht;
    }

    public boolean isAktiivne() {
        return aktiivne;
    }

    public void setAktiivne(boolean aktiivne) {
        this.aktiivne = aktiivne;
    }

    public String toString() {
        return kood + " " + ainekood + " " + tüüp + " " + tähtaeg + " " + maht + " " + nimetus + " " + tehtudmaht + " " + aktiivne;
    }

    public String kirjutaFailiJaoks() {
        return kood + ";" + ainekood + ";" + tüüp + ";" + tähtaeg + ";" + maht + ";" + nimetus + ";" + tehtudmaht + ";" + aktiivne + "\n";
    }
}
