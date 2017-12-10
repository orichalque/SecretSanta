package com.orichalque;

/**
 * Created by Vandorallen on 10/12/2017.
 */
public class MailBodyGenerator {

    public static String generateSecretSantaBody(String gifter, String gifted, int minimum) {
        return "Bonjour, "+gifter+
                "\nLe Père Noël secret t'a choisi afin d'envoyer un joli cadeau à "+gifted+"."+
                "\nLe montant maximum de ce cadeau doit être de "+String.valueOf(minimum)+"€."+
                "\nBien entendu, ce charmant cadeau peut aussi être fait à la main (i.e.) DIY !"+
                "\nSurtout, garde ça secret ! Personne ne doit savoir à qui tu offres un cadeau. Et surtout pas cette personne, en tous cas pas avant le jour J! "+
                "\nBisous, et à très bientôt. Coeur coeur.";
    }
}
