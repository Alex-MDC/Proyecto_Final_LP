
package com.mycompany.producer_consumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int producer_id;
    
    Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    public void set_id(int id){
        this.producer_id = id;
    }
    public int get_id(){
        return this.producer_id;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
      //operadores en un string para ser randomly selected
        String products = "+-*/";
        Random r = new Random(System.currentTimeMillis());
        //char product;
        String product;
        
        //el num comparado a i es cuantos productos genera el producer por llamada
        for(int i=0 ; i<5 ; i++) {
            //logiga de construccion de operacion scheme
            product = "(";
            //agregar una operacion aleatoria
            product += products.charAt(r.nextInt(4));
            //TODO: logica de agregar numeros en rango n a m
            //este codigo es solo demostrativo,no esta en n a m aun, solo rango 0 a 9
            product += " " +(new Random()).nextInt(10); 
            product += " " +(new Random()).nextInt(10); 
            
            //cerrar el scheme op
            product += ")";
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer" + producer_id+" produced: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
