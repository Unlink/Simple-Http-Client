/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unlink.common.HttpClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;

/**
 * Trieda na jednoduchý HTTP klient
 * @author Unlink
 */
public class HttpClient {
    
    private CookieManager aCookieManager;

    public HttpClient(CookieManager aCookieManager) {
        this.aCookieManager = aCookieManager;
    }

    public HttpClient() {
        this(new CookieManager());
    }
    
    /**
     * Načíta stránku z Url Adresy
     * @param paUrl
     * @return String - obsah stránky
     * @throws IOException 
     */
    public String get(String paUrl) throws IOException{
        return this.get(paUrl, "UTF-8");
    }
    
    /**
     * Načíta stránku z Url Adresy zo zadaným kodovaním
     * @param paUrl
     * @param paEnconding
     * @return String - obsah stránky
     * @throws IOException 
     */
    public String get(String paUrl, String paEnconding) throws IOException {
        URL url = new URL(paUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        aCookieManager.setCookies(conn);
        conn.setDoInput(true); 
        conn.connect();
        aCookieManager.storeCookies(conn);
        return readStream(conn, paEnconding);
    }
    
    /**
     * Načíta stránku z Url Adresy, pomocou post požiadavky
     * @param paUrl
     * @param paParams
     * @return String - obsah stránky
     * @throws IOException 
     */
    public String post(String paUrl, List<BasicNameValuePair> paParams) throws IOException{
        return post(paUrl, paParams, "UTF-8");
    }

    /**
     * Načíta stránku z Url Adresy, pomocou post požiadavky, zo zadaným kodovanim
     * @param paUrl
     * @param paParams
     * @param paEnconding
     * @return String - obsah stránky
     * @throws IOException 
     */
    public String post(String paUrl, List<BasicNameValuePair> paParams, String paEnconding) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (BasicNameValuePair p:paParams){
            sb.append(p.getKey()).append("=").append(URLEncoder.encode(p.getValue(), paEnconding)).append("&");
        }
        if (sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        
        
        URL url = new URL(paUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-length", String.valueOf(sb.length())); 
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
        conn.setDoOutput(true); 
        conn.setDoInput(true); 
        aCookieManager.setCookies(conn);
        
        DataOutputStream output = new DataOutputStream(conn.getOutputStream());  
        output.writeBytes(sb.toString());
        output.close();
        
        conn.connect();
        
        aCookieManager.storeCookies(conn);
        return readStream(conn, paEnconding);
    }

    /**
     * Prečíta sream a vráti string
     * @param conn
     * @param paEnconding
     * @return
     * @throws IOException 
     */
    private String readStream(HttpURLConnection conn, String paEnconding) throws IOException {
        Scanner s = new Scanner(conn.getInputStream(), paEnconding).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
	
	public String getCookies(String paDomain) {
		return aCookieManager.getCookies(paDomain);
	}
    
}
