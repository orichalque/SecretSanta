package com.orichalque;

import javax.mail.MessagingException;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App
{
    private static MailSender mailSender;

    public static void main( String[] args )
    {
        if (args.length < 1)
            return;

        final File file = new File(args[0]);

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter your gmail without the @gmail.com part : ");
        final String username = reader.next();

        System.out.println("Enter your gmail password: ");
        final String password = reader.next();

        reader.close();

        mailSender = new MailSender(username, password);

        new SecretSantaPicker(file).generateMap().forEach((s, s2) -> {
            try {
                mailSender.sendMail(s, "Secret Santa", MailBodyGenerator.generateSecretSantaBody(s, s2, 10));
            } catch (MessagingException e) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Could not send the emails", e);
            }
        });
    }


}
