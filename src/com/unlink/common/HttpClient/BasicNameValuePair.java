/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unlink.common.HttpClient;

/**
 *
 * @author Unlink
 */
public class BasicNameValuePair {
    private final String aKey;
    private final String aValue;

    public BasicNameValuePair(String aKey, String aValue) {
        this.aKey = aKey;
        this.aValue = aValue;
    }

    /**
     * Vráti kľúč
     * @return 
     */
    public String getKey() {
        return aKey;
    }

    /**
     * Vráti hodnotu
     * @return 
     */
    public String getValue() {
        return aValue;
    }
}
