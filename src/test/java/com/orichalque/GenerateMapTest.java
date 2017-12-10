package com.orichalque;

import org.junit.Test;

import java.io.File;

/**
 * Created by Vandorallen on 10/12/2017.
 */
public class GenerateMapTest {

    @Test
    public void testGenerateMap() {
        SecretSantaPicker secretSantaPicker = new SecretSantaPicker(new File("src/test/resources/mailList.yaml"));
        secretSantaPicker.generateMap();
    }
}
