package com.orichalque;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Vandorallen on 10/12/2017.
 */
public class SecretSantaPicker {
    private static MailFile mailFile;
    private static final List<String> gifted = new ArrayList<>();

    public SecretSantaPicker(File file) {
        try {
            mailFile = new ObjectMapper(new YAMLFactory()).readValue(file, MailFile.class);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, "could not read file", e);
        }
    }

    public Map<String, String> generateMap() {
        Map<String, String> secretSanta = new HashMap<>();

        mailFile.getNames().forEach(s -> {
            List<String> compatibles = getCompatibles(s);
            int pick = ThreadLocalRandom.current().nextInt(0, compatibles.size());
            String picked = compatibles.get(pick);
            gifted.add(picked);
            secretSanta.put(s, picked);
        });

        return secretSanta;
    }

    private static List<String> getCompatibles(String name) {
        return mailFile.getNames().stream().filter(s -> isCompatible(name, s)).collect(Collectors.toList());
    }

    private static boolean isCompatible(String mail, String mail2) {
        if (!mail.equals(mail2))
            if (((mailFile.getIncompatibles().get(mail) == null) || !mailFile.getIncompatibles().get(mail).equals(mail2)) && ((mailFile.getIncompatibles().get(mail2) == null) || !mailFile.getIncompatibles().get(mail2).equals(mail)))
                if (!gifted.contains(mail2))
                    return true;

        return false;
    }
}
