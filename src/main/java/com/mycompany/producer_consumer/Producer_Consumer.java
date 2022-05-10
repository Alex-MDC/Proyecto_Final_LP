/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.producer_consumer;

/**
 *
 * @author ZeDok
 */
public class Producer_Consumer {

    public static void main(String[] args) {
        System.out.println("Starting GUI");
        //empezar el GUI para entonces correr el codigo con la info del GUI
        GUIFrame GUI_element = new GUIFrame();
        GUI_element.startUI(args);
        
    }
}
