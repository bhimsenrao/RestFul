/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.saas.root;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * Rootapplication Service
 *
 * @author Bhimsen
 */
public class Rootapplication {

    /**
     * Creates a new instance of Rootapplication
     */
    public Rootapplication() {
    }
    
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Throwable th) {
        }
    }

    /**
     *
     * @return an instance of RestResponse
     */
    public static RestResponse getAnimalsAsHtml() throws IOException {
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{};
        RestConnection conn = new RestConnection("http://localhost:31567/WebAppRS/rest//animals", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
