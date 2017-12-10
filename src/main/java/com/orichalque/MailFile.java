package com.orichalque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vandorallen on 10/12/2017.
 */
public class MailFile {
    private List<String> names;
    private Map<String, String> incompatibles;

    public MailFile() {
        names = new ArrayList<>();
        incompatibles = new HashMap<>();
    }

    public MailFile(List<String> names, Map<String, String> incompatibles) {
        this.names = names;
        this.incompatibles = incompatibles;
    }

    /**
     * @return current names
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * @param names the names to set
     */
    public void setNames(List<String> names) {
        this.names = names;
    }

    /**
     * @return current incompatibles
     */
    public Map<String, String> getIncompatibles() {
        return incompatibles;
    }

    /**
     * @param incompatibles the incompatibles to set
     */
    public void setIncompatibles(Map<String, String> incompatibles) {
        this.incompatibles = incompatibles;
    }
}
