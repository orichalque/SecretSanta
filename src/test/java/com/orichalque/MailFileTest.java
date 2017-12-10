package com.orichalque;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


/**
 * Created by Vandorallen on 10/12/2017.
 */
public class MailFileTest {

    @Test
    public void testSerializable() throws JsonProcessingException {
        MailFile mailFile = new MailFile();
        mailFile.getNames().add("name1@adress.com");
        mailFile.getNames().add("name2@adress.com");
        mailFile.getNames().add("name3@adress.com");
        mailFile.getNames().add("name4@adress.com");

        mailFile.getIncompatibles().put("name1@adress.com", "name2@adress.com");
        mailFile.getIncompatibles().put("name2@adress.com", "name1@adress.com");
        YAMLFactory yamlFactory = new YAMLFactory();
        String serialized = new ObjectMapper(yamlFactory).writeValueAsString(mailFile);
        
        System.out.println(serialized);
    }

    @Test
    public void testReadable() throws IOException {
        File file = new File("src/test/resources/mailList.yaml");
        YAMLFactory yamlFactory = new YAMLFactory();
        MailFile mailFile = new ObjectMapper(yamlFactory).readValue(file, MailFile.class);

        Assert.assertEquals(mailFile.getNames().size(), 4);
        Assert.assertEquals(mailFile.getIncompatibles().size(), 4);
    }
}
