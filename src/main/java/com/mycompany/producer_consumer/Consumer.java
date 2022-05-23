
package com.mycompany.producer_consumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int consumer_id;
    int tiempoDeEspera;
    String productFinal;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    public void set_id(int id){
        this.consumer_id = id;
    }
    public int get_id(){
        return this.consumer_id;
    }
    public void set_product(String product){
        this.productFinal = product;
    }
    public String get_product(){
        return this.productFinal;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        String product;
        
        //consumr mientras que el flag lo mencione
        for(int i=0 ; this.buffer.consumeFlag ; i++) {
            product = this.buffer.consume();
            set_product(product);
            System.out.println("Consumer" + consumer_id +" consumed: " + product);
            //Buffer.print("Consumer consumed: " + product);
            
            try {
                Thread.sleep(tiempoDeEspera);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

//test Beto