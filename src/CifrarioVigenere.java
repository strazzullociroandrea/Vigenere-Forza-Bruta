/**
 *
 * @author Strazzullo Ciro Andrea
 * @version 1.0
 * @date 28/10/2023
 *
 */

public class CifrarioVigenere {
    private String info, chiave, chiaveTotal;
    private final String alfabeto = "ABCDEFGHILMNOPQRSTUVZ";

    /**
     * Costruttore
     * @param info Stringa che contiene il testo da criptare
     * @param chiave Stringa che permette di criptare il file
     */
    public CifrarioVigenere(String info, String chiave){
        this.info = maiuscola(replaceCaratteriInterpunzione(info));
        this.chiave = maiuscola(replaceCaratteriInterpunzione(chiave));
        this.chiaveTotal = this.generaChiaveTotale();//genero la chiave di crittazione totale
    }

    /**
     * Metodo privato per eliminare i caratteri di interpunzione e il carattere spazio
     *
     * @param stringa Stringa da modificare
     * @return Stringa modificata
     */
    private String replaceCaratteriInterpunzione(String stringa) {
        return stringa.replaceAll("[\\p{Punct}\\s]", "");
    }

    /**
     * Metodo privato per rendere la stringa maiuscola per poi criptarla
     *
     * @param stringa Stringa da modificare
     * @return Stringa modificata
     */
    private String maiuscola(String stringa) {
        return stringa.toUpperCase();
    }

    /**
     * Metodo privato per generare la chiave totale di crittatura
     *
     * @return chiave di crittazione completa
     */
    private String generaChiaveTotale(){
        StringBuilder fin = new StringBuilder();
        int divisione = this.info.length() / this.chiave.length();
        int numeroRipetizioni =  (this.info.length() % this.chiave.length()) == 0 ?
                divisione : divisione + 1;
        for(int i=0;i<numeroRipetizioni;i++)
            fin.append(this.chiave);
        return fin.toString();
    }

    /**
     * Metodo privato per generare la chiave totale di crittatura
     *
     * @param chiave chiave da convertire
     * @param daConvertire stringa da convertire
     * @return chiave di crittazione completa
     */
    private String generaChiaveTotale(String daConvertire, String chiave){
        StringBuilder fin = new StringBuilder();
        int divisione = daConvertire.length() / chiave.length();
        int numeroRipetizioni =  (daConvertire.length() % chiave.length()) == 0 ?
                divisione : divisione + 1;
        for(int i=0;i<numeroRipetizioni;i++)
            fin.append(chiave);
        return fin.toString();
    }

    /**
     * Metodo utilizzato per crittare la stringa passata come parametro al costruttore insieme alla chiave
     *
     * @return Stringa crittata
     */
    public String critta() {
        String total = "";
        for(int i = 0; i < this.info.length(); i++){
            char carattereStringa = this.info.charAt(i);
            char carattereChiave = this.chiaveTotal.charAt(i);
            int indexStringa =  alfabeto.indexOf(carattereStringa) + 1;//trovo la posizione del carattere da convertire nell'alfabeto
            int indexChiave =  alfabeto.indexOf(carattereChiave) + 1;//trovo la posizione del carattere da usare per convertire nell'alfabeto
            //ricerca del carattere finale
            int sommaPosizioni = indexStringa + indexChiave;
            if(sommaPosizioni > alfabeto.length() )
                total += (alfabeto.charAt(sommaPosizioni-alfabeto.length()-1));
            else
                total += (alfabeto.charAt(sommaPosizioni-1));
        }
        return total;
    }

    /**
     * Metodo pubblico per decrittare una stringa crittata con il cifrario di Vigenere
     *
     * @param crittata stringa crittata
     * @param key chiave per decrittare la stringa
     * @return stringa crittata
     */
    public String decritta(String crittata, String key){
        String finale = "";
        String chiaveTotal = this.generaChiaveTotale(crittata, key.toUpperCase());//genero la chiave di crittazione totale
        for(int i = 0; i < crittata.length(); i++){
            char carattereStringa = crittata.charAt(i);
            char carattereChiave = chiaveTotal.charAt(i);
            int indexStringa =  this.alfabeto.indexOf(carattereStringa) + 1;//trovo la posizione del carattere da convertire nell'alfabeto
            int indexChiave =  this.alfabeto.indexOf(carattereChiave) + 1;//trovo la posizione del carattere da usare per convertire nell'alfabeto
            int sottrazione = indexStringa - indexChiave; //posizione finale
            if(sottrazione > 0)
                finale += this.alfabeto.charAt(sottrazione - 1);
            else
                finale += this.alfabeto.charAt((sottrazione + this.alfabeto.length() - 1));
        }
        return finale;
    }

}