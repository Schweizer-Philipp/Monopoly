package monopoly.controller.manager;

import monopoly.model.Strassentyp;
import monopoly.model.StreetBuyAble;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class DateiManager {

    /**
     * Methode um die Einstellungen zuspeichern.
     * Wenn beim Speichern ein Fehler auftritt, dann
     * wird false zurueckgegeben.
     *
     * @param daten Beliebig viele Paramteter vom Typ String
     * @return false bei Fehler, sonst true
     */
    public static boolean einstellungenSpeichern(ArrayList<String> daten) {
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        BufferedWriter schreiber = null;
        FileWriter datei = null;

        try {

            datei = new FileWriter(new File(projectPath + "/Dateien/Einstellungen.txt"));

            schreiber = new BufferedWriter(datei);

            for (String s : daten) // for-each
            {
                schreiber.write(s);
                schreiber.newLine();
            }


            schreiber.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Methode um die Einstellungen auszulesen.
     * Wenn die Datei nicht gefunden wird, dann wird
     * eine IOException gewurfen und {@code null} zurueck gegeben.
     *
     * @return Array wo die Daten drin stehen. Bei Fehler null.
     * @throws IOException Datei wurde nicht gefunden
     * @throw IOException
     */
    public static ArrayList<String> dateiLesen(String datei) {
        ArrayList<String> data = new ArrayList<String>();
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        try {

            FileReader fr = new FileReader(new File(projectPath + "/Dateien/" + datei));
            BufferedReader leser = new BufferedReader(fr);
            String zeile = "";

            while ((zeile = leser.readLine()) != null) {
                data.add(zeile);
            }
            leser.close();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    public static void mischeGemeinschaftskarten() {
        int index = 1;
        ArrayList<String> data = new ArrayList<String>();
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        try {

            FileReader fr = new FileReader(new File(projectPath + "/Dateien/Gemeinschaftskarten.txt"));
            BufferedReader leser = new BufferedReader(fr);
            String zeile = "";

            while ((zeile = leser.readLine()) != null) {
                data.add(zeile);
            }
            leser.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter schreiber = null;
        FileWriter datei = null;

        try {

            datei = new FileWriter(new File(projectPath + "/Dateien/Gemeinschaftskarten.txt"));

            schreiber = new BufferedWriter(datei);
            Collections.shuffle(data);
            for (String s : data) {
                StringBuilder test = new StringBuilder(s);
                if (String.valueOf(test.charAt(1)).equals("@")) {
                    test.replace(0, 1, String.valueOf(index));
                    schreiber.write(test.toString());
                    schreiber.newLine();
                } else {
                    test.replace(0, 2, String.valueOf(index));
                    schreiber.write(test.toString());
                    schreiber.newLine();
                }

                index++;
            }

            schreiber.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void mischeErgeingniskarten() {
        int index = 1;
        ArrayList<String> data = new ArrayList<String>();
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        try {

            FileReader fr = new FileReader(new File(projectPath + "/Dateien/Ereigniskarten.txt"));
            BufferedReader leser = new BufferedReader(fr);
            String zeile = "";

            while ((zeile = leser.readLine()) != null) {
                data.add(zeile);
            }
            leser.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter schreiber = null;
        FileWriter datei = null;

        try {

            datei = new FileWriter(new File(projectPath + "/Dateien/Ereigniskarten.txt"));

            schreiber = new BufferedWriter(datei);
            Collections.shuffle(data);
            for (String s : data) {
                StringBuilder test = new StringBuilder(s);
                if (String.valueOf(test.charAt(1)).equals("@")) {
                    test.replace(0, 1, String.valueOf(index));
                    schreiber.write(test.toString());
                    schreiber.newLine();
                } else {
                    test.replace(0, 2, String.valueOf(index));
                    schreiber.write(test.toString());
                    schreiber.newLine();
                }
                Math.random();
                index++;
            }


            schreiber.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static int zeilenLesen(String datei) {
        return dateiLesen(datei).size();
    }

    public static int getWuerfelTaste() {
        return Integer.valueOf(DateiManager.dateiLesen("Einstellungen.txt").get(0));
    }

    public static boolean getSpeichern() {
        ArrayList<String> data = new ArrayList<String>();
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        try {

            FileReader fr = new FileReader(new File(projectPath + "/Dateien/Spieler1.txt"));
            BufferedReader leser = new BufferedReader(fr);
            String zeile = "";

            while ((zeile = leser.readLine()) != null) {
                data.add(zeile);
            }
            leser.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.size() > 0;
    }

    public static void emptySpeichern() {
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();

        try {

            FileWriter datei = new FileWriter(new File(projectPath + "/Dateien/Spieler1.txt"));
            FileWriter datei1 = new FileWriter(new File(projectPath + "/Dateien/Spieler2.txt"));
            FileWriter datei2 = new FileWriter(new File(projectPath + "/Dateien/Spieler3.txt"));
            FileWriter datei3 = new FileWriter(new File(projectPath + "/Dateien/Spieler4.txt"));
            FileWriter datei4 = new FileWriter(new File(projectPath + "/Dateien/SpeichernEinstellungen.txt"));
            datei.write("");
            datei1.write("");
            datei2.write("");
            datei3.write("");
            datei4.write("");
            datei.close();
            datei1.close();
            datei2.close();
            datei3.close();
            datei4.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static boolean spielerSpeichern(ArrayList<String> spieler, ArrayList<StreetBuyAble> strasse, String dateiname) {
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        BufferedWriter schreiber = null;
        FileWriter datei = null;

        try {

            datei = new FileWriter(new File(projectPath + "/Dateien/" + dateiname));

            schreiber = new BufferedWriter(datei);

            for (String s : spieler) {
                schreiber.write(s);
                schreiber.write("@");
            }
            schreiber.newLine();
            for (StreetBuyAble s1 : strasse) {
                schreiber.write(String.valueOf(s1.getId()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete1()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete2()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete3()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete4()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete5()));
                schreiber.write("@");
                schreiber.write(s1.getStrassennamen());
                schreiber.write("@");
                schreiber.write(String.valueOf(Strassentyp.getint(s1.getTyp())));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getKosten()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getHauskosten()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getFarbrect().x));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getFarbrect().y));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getFarbrect().width));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getFarbrect().height));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getHauserZaehler()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getHaeuserAnzahl()));
                schreiber.newLine();
            }

            schreiber.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public static boolean einstellungenSpeichernspiel(ArrayList<String> list) {
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        BufferedWriter schreiber = null;
        FileWriter datei = null;

        try {

            datei = new FileWriter(new File(projectPath + "/Dateien/SpeichernEinstellungen.txt"));

            schreiber = new BufferedWriter(datei);

            for (String s : list) {
                schreiber.write(s);
                schreiber.write("@");
            }


            schreiber.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public static int getspieleranzahl() {
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> data1 = new ArrayList<String>();
        ArrayList<String> data2 = new ArrayList<String>();
        ArrayList<String> data3 = new ArrayList<String>();
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        try {

            FileReader fr = new FileReader(new File(projectPath + "/Dateien/Spieler1.txt"));
            FileReader fr1 = new FileReader(new File(projectPath + "/Dateien/Spieler2.txt"));
            FileReader fr2 = new FileReader(new File(projectPath + "/Dateien/Spieler3.txt"));
            FileReader fr3 = new FileReader(new File(projectPath + "/Dateien/Spieler4.txt"));
            BufferedReader leser = new BufferedReader(fr);
            BufferedReader leser1 = new BufferedReader(fr1);
            BufferedReader leser2 = new BufferedReader(fr2);
            BufferedReader leser3 = new BufferedReader(fr3);
            String zeile = "";
            String zeile1 = "";
            String zeile2 = "";
            String zeile3 = "";

            while ((zeile = leser.readLine()) != null) {
                data.add(zeile);
            }
            leser.close();
            while ((zeile1 = leser1.readLine()) != null) {
                data1.add(zeile1);
            }
            leser1.close();
            while ((zeile2 = leser2.readLine()) != null) {
                data2.add(zeile2);
            }
            leser2.close();
            while ((zeile3 = leser3.readLine()) != null) {
                data3.add(zeile3);
            }
            leser3.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        int anazahl = 0;
        if (data.size() > 0) {
            anazahl++;
        }
        if (data1.size() > 0) {
            anazahl++;
        }
        if (data2.size() > 0) {
            anazahl++;
        }
        if (data3.size() > 0) {
            anazahl++;
        }
        return anazahl;
    }

    public static ArrayList<String> getspielerdaten(String data) {
        ArrayList<String> data1 = new ArrayList<String>();
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        try {

            FileReader fr = new FileReader(new File(projectPath + "/Dateien/" + data));

            BufferedReader leser = new BufferedReader(fr);

            String zeile = "";


            while ((zeile = leser.readLine()) != null) {
                data1.add(zeile);
            }
            leser.close();
        } catch (IOException e) {

        }
        return data1;
    }

    public static String getEinstellungen(int nummer) {
        ArrayList<String> data1 = new ArrayList<String>();
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        try {

            FileReader fr = new FileReader(new File(projectPath + "/Dateien/SpeichernEinstellungen.txt"));

            BufferedReader leser = new BufferedReader(fr);

            String zeile = "";


            while ((zeile = leser.readLine()) != null) {
                data1.add(zeile);
            }
            leser.close();
        } catch (IOException e) {

        }
        String test = data1.get(0);
        String[] test1 = test.split("@");
        return test1[nummer];
    }

    public static ArrayList<String> getStrassen() {
        ArrayList<String> data1 = new ArrayList<String>();
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        try {

            FileReader fr = new FileReader(new File(projectPath + "/Dateien/aviableStreets.txt"));

            BufferedReader leser = new BufferedReader(fr);

            String zeile = "";


            while ((zeile = leser.readLine()) != null) {
                data1.add(zeile);
            }
            leser.close();
        } catch (IOException e) {

        }
        return data1;
    }

    public static boolean saveavaleblestreets(ArrayList<StreetBuyAble> list) {
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        BufferedWriter schreiber = null;
        FileWriter datei = null;

        try {

            datei = new FileWriter(new File(projectPath + "/Dateien/aviableStreets.txt"));

            schreiber = new BufferedWriter(datei);

            for (StreetBuyAble s1 : list) {
                schreiber.write(String.valueOf(s1.getId()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete1()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete2()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete3()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete4()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getMiete5()));
                schreiber.write("@");
                schreiber.write(s1.getStrassennamen());
                schreiber.write("@");
                schreiber.write(String.valueOf(Strassentyp.getint(s1.getTyp())));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getKosten()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getHauskosten()));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getFarbrect().x));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getFarbrect().y));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getFarbrect().width));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getFarbrect().height));
                schreiber.write("@");
                schreiber.write(String.valueOf(s1.getHauserZaehler()));
                schreiber.newLine();
            }


            schreiber.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public static boolean clearstreet() {
        File currentDir = new File("");
        String projectPath = currentDir.getAbsolutePath();
        BufferedWriter schreiber = null;
        FileWriter datei = null;

        try {

            datei = new FileWriter(new File(projectPath + "/Dateien/aviableStreets.txt"));

            schreiber = new BufferedWriter(datei);

            schreiber.write("");

            schreiber.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }
}
