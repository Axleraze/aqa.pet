package org.petclinic;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Reading data from properties File
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        String urlFromProperty = System.getProperty("url");
        System.out.println(urlFromProperty);

    }
}