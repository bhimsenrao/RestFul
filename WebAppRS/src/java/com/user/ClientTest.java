/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Bhimsen
 */
public class ClientTest {
    public static void main(String[] args) throws Exception {
        URL url = 
          new URL("http://localhost:31567/WebDBRs/rest/persons/102" );
  InputStream result = (InputStream) url.getContent();
BufferedReader reader =
new BufferedReader(new InputStreamReader(result));
  System.out.println(  reader.readLine() );

    }
}
