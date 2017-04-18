import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Created by kats on 06/04/17.
 *
 * See on põhiprogramm, kust asjad käivituvad
 */
public class AjahaldusProgramm {
    static ArrayList<Aine> ained = new ArrayList<>();
    static ArrayList<Kohustus> kohustused = new ArrayList<>();

    private static void loeFailistAined() throws Exception {
        File fail = new File("ained.txt");
        if (fail.exists()) {
            System.out.println("Ainete fail leitud");
            Scanner sc = new Scanner(fail, "UTF-8");
            boolean esimene = true;
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                if (esimene)
                    esimene = false;
                else {
                    String[] tükid = rida.split(";");
                    Aine uusaine = new Aine(tükid[0], tükid[1]);
                    ained.add(uusaine);
                    //System.out.println(uusaine);
                }
            }
            sc.close();
        }
        else
            System.out.println("Ainete faili veel ei eksisteeri, sa pead ise aineid sisestama!");

    }

    private static void loeFailistKohustused() throws Exception {
        File fail = new File("kohustused.txt");
        if (fail.exists()) {
            System.out.println("Kohustuste fail leitud");
            Scanner sc = new Scanner(fail, "UTF-8");
            boolean esimene = true;
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                if (esimene)
                    esimene = false;
                else {
                    String[] tükid = rida.split(";");
                    int kood = Integer.parseInt(tükid[0]);
                    String ainekood = tükid[1];
                    String tüüp = tükid[2];
                    String tähtaeg = tükid[3];
                    double maht = Double.parseDouble(tükid[4]);
                    String nimetus = tükid[5];
                    double tehtudmaht = Double.parseDouble(tükid[6]);
                    boolean aktiivne = Boolean.parseBoolean(tükid[7]);
                    Kohustus uus = new Kohustus(kood,ainekood,tüüp,tähtaeg,maht,nimetus,tehtudmaht,aktiivne);
                    kohustused.add(uus);
                    //System.out.println(uus);
                }
            }
            sc.close();
        }
        else
            System.out.println("Kohustuste faili veel ei eksisteeri, sa pead ise kohustused sisestama!");

    }

    private static void kirjutaFailiAined() throws IOException {
        File vana = new File("ained.txt");
        vana.delete();
        File fail = new File("ained.txt");
        fail.createNewFile();
        FileWriter f2 = new FileWriter(fail);
        f2.write("Ainekood;Ainenimetus\n");
        for (Aine a: ained) {
            f2.write(a.kirjutaFailiJaoks());
        }
        f2.close();
    }

    private static void kirjutaFailiKohustused() throws IOException {
        File vana = new File("kohustused.txt");
        vana.delete();
        File fail = new File("kohustused.txt");
        fail.createNewFile();
        FileWriter f2 = new FileWriter(fail);
        f2.write("kood;ainekood;tüüp;tähtaeg;maht;nimetus;tehtudmaht;aktiivne\n");
        for (Kohustus k: kohustused) {
            f2.write(k.kirjutaFailiJaoks());
        }
        f2.close();
    }

    private static void sisestaAine() {
        Scanner sa = new Scanner(System.in);
        System.out.println("Sisesta uue aine kood: ");
        String kood = sa.next();
        System.out.println("Sisesta uue aine nimetus: ");
        String nimi = sa.next();
        Aine uus = new Aine(kood,nimi);
        ained.add(uus);
        System.out.println("Uus aine on sisestatud.");
    }

    private static void sisestaKohustus() {
        Scanner sa = new Scanner(System.in);

        System.out.println("Sisesta uue kohustuse kood: ");
        int kood = Integer.parseInt(sa.next());
        System.out.println("Sisesta uue kohustuse ainekood: ");
        String ainekood = sa.next();
        System.out.println("Sisesta uue kohustuse tüüp: ");
        String tüüp = sa.next();
        System.out.println("Sisesta uue kohustuse tähtaeg: ");
        String tähtaeg = sa.next();
        System.out.println("Sisesta uue kohustuse maht tundides: ");
        double maht = Double.parseDouble(sa.next());
        System.out.println("Sisesta uue kohustuse nimetus: ");
        String nimetus = sa.next();

        Kohustus uus = new Kohustus(kood,ainekood,tüüp,tähtaeg,maht,nimetus,0,true);
        kohustused.add(uus);
        System.out.println("Uus kohustus on sisestatud.");
    }

    private static void kustutaAine() {
        Scanner sa = new Scanner(System.in);
        System.out.println("Hoiatus! Aine kustutamine kustutab ka temaga seotud kohustused!");
        System.out.println("Sisesta kustutatava aine kood: ");
        String kood = sa.next().toUpperCase();

        Predicate<Aine> ainePredicate = p-> p.getAinekood().toUpperCase().equals(kood);
        ained.removeIf(ainePredicate);

        Predicate<Kohustus> kohustusPredicate = p-> p.getAinekood().toUpperCase().equals(kood);
        kohustused.removeIf(kohustusPredicate);

        System.out.println("Aine kustutatud");
    }

    private static void kustutaKohustus() {
        Scanner sa = new Scanner(System.in);
        System.out.println("Sisesta kustutatava kohustuse kood: ");
        int kood = Integer.parseInt(sa.next());

        Predicate<Kohustus> kohustusPredicate = p-> p.getKood() == kood;
        kohustused.removeIf(kohustusPredicate);
        System.out.println("Kohustus kustutatud");
    }


    public static void main(String[] args) throws Exception {
        //ettevalmistavad tegevused
        Scanner scan = new Scanner(System.in);

        //korjame failidest ainete ja kohustuste info
        loeFailistAined();
        loeFailistKohustused();

        System.out.println("Tere! Oled tulnud oma koolikohustuste haldamise programmi, mis üritab aidata sul oma kooliülesannete tegemiseks vajaminevat aega mõistliku(ma)lt planeerida!");
        int valik = 0;

        //paneme programmi seniks jooksma kuni pole väljumiskäsku antud
        while (valik != 100) {

            //juhusliku tsitaadi genereerimine
            System.out.println();
            System.out.println("Vahepalaks üks tsitaat targematelt inimestelt...");

            //menüüpunktide kuvamine
            System.out.println("Anna märku, mida sa edasi tahad teha. Variandid on järgmised: ");
            System.out.println("10 - Näitab ainete nimekirja");
            System.out.println("11 - Näitab kohustuste nimekirja");
            System.out.println("30 - Sisesta uus aine");
            System.out.println("31 - Sisesta uus kohustus");
            System.out.println("40 - Kustuta aine");
            System.out.println("41 - Kustuta kohustus");
            System.out.println("100 - väljub programmist");
            valik = 100;

            //kasutajalt sisendi võtmine

            System.out.println("Sisesta oma valik (numbrina): ");
            valik = Integer.parseInt(scan.next());

            //valitud funktsionaalsuse väljakutsumine
            switch (valik) {
                case 10 :
                    System.out.println("Valisid 10 - Näitab ainete nimekirja");
                    for (Aine a: ained) System.out.println(a);
                    break;
                case 11 :
                    System.out.println("Valisid 11 - Näitab kohustuste nimekirja");
                    for (Kohustus k: kohustused) System.out.println(k);
                    break;
                case 30 :
                    System.out.println("Valisid 30 - Sisesta uus aine");
                    sisestaAine();
                    break;
                case 31 :
                    System.out.println("Valisid 31 - Sisesta uus kohustus");
                    sisestaKohustus();
                    break;
                case 40 :
                    System.out.println("Valisid 40 - Kustuta aine");
                    kustutaAine();
                    break;
                case 41 :
                    System.out.println("Valisid 41 - Kustuta kohustus");
                    kustutaKohustus();
                    break;
                case 100:
                    System.out.println("Valisid väljumise. Lõpetan töö!");
                    kirjutaFailiAined();
                    kirjutaFailiKohustused();
                    System.out.println("Ainete ja kohustuste muutused on failidesse salvestatud.");
                    break;
                default :
                    System.out.println("Sellist käsku ei ole, vali uuesti!");

            } //switchi blokk lõpeb siin
        } //menüükäsu valiku while lõpeb siin, ehk kui valik = 100, siis liigutakse siit edasi ja töö lõpeb, muidu otsast peale
    } //main meetodi lõpp
} //klassi bloki lõpp
