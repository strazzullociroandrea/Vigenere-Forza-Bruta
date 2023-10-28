/**
 *
 * @author Strazzullo Ciro Andrea
 * @version 1.0
 * @date 28/10/2023
 *
 */

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Funzione per ottenere la stringa inserita su console utilizzando l'oggetto scanner
     *
     * @param messaggio stringa per il primo messaggio da visualizzare
     * @param messaggioErrore stringa per il messaggio visualizzato dopo aver sbagliato almeno una volta
     *                        il formato della stringa
     * @return stringa inserita
     */
    public static String valueKey(String messaggio, String messaggioErrore){
        String chiave = "";
        boolean tentativoUno = true;
        do {
            if (tentativoUno)
                System.out.println(messaggio);
            else
                System.out.println(messaggioErrore);
            if (scanner.hasNextLine())
                chiave = scanner.nextLine();
            else
                tentativoUno = false;
        } while (chiave.equals(""));
        return chiave.toUpperCase();
    }

    public static void main(String[] args) throws Exception{

        //recupero della stringa da crittare nel file presente nel progetto
        String stringaDaCrittare = File.leggiDaFile("src/TestoDaCrittare.txt");

        //... inizio crittazione
        String chiaveCrittazione = valueKey("Inserisci la stringa di crittazione {A-Za-z}:",
                "Errore! Inserisci la stringa di crittazione" +
                        " nel modo corretto {A-Za-z}:");
        CifrarioVigenere cv = new CifrarioVigenere(stringaDaCrittare,
                chiaveCrittazione
                );
        String crittata = cv.critta();
        File.generaFile("src/crittata/crittata.txt", crittata);
        System.out.println("La stringa è stata crittata correttamente");
        //... fine crittazione e generazione del file

        //... inizio decrittazione
        String chiaveDecrittazione = valueKey("Inserisci la stringa di decrittazione {A-Za-z}:",
                "Errore! Inserisci la stringa di decrittazione" +
                        " nel modo corretto {A-Za-z}:");
        String decrittata = cv.decritta(crittata, chiaveDecrittazione);
        File.generaFile("src/decrittata/decrittata.txt", decrittata);
        System.out.println("La stringa è stata decrittata correttamente");
        //... fine decrittazione e generazione del file
        scanner.close();
        System.out.println("Fine programma");
    }
}