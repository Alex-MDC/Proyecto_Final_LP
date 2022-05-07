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
        System.out.println("Hello World!");
         //CODIGO DE PRODUCER CONSUMER----------------
        Buffer buffer = new Buffer();
      
        Producer producer = new Producer(buffer);
        producer.start();
        
        Consumer consumer = new Consumer(buffer);
        consumer.start();
    }
}
