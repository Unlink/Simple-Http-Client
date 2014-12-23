/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unlink.common.HttpClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Unlink
 */
public class Test {
    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();
        System.out.println("Featching http://st.fri.uniza.sk/~duracik2/httpTest.php?a=b&c=d");
        System.out.println(client.get("http://st.fri.uniza.sk/~duracik2/httpTest.php?a=b&c=d"));
        
        ArrayList<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("kluc", "hodnota"));
        params.add(new BasicNameValuePair("kluc2", "ina hodnota"));
        System.out.println("\n\n\nFeatching with post http://st.fri.uniza.sk/~duracik2/httpTest.php");
        System.out.println(client.post("http://st.fri.uniza.sk/~duracik2/httpTest.php", params));
    }
}
