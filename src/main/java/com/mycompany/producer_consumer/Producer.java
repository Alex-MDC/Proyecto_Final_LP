
package com.mycompany.producer_consumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int producer_id;
    String productFinal = "";
    int tiempoDeEspera;
    
    Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    public void set_id(int id){
        this.producer_id = id;
    }
    public int get_id(){
        return this.producer_id;
    }
    public void set_product(String product){
        this.productFinal = product;
    }
    public String get_product(){
        return this.productFinal;
    }
        
    @Override
    public void run() {
        System.out.println("Running Producer...");
      //operadores en un string para ser randomly selected
        String products = "+-*/";
        Random r = new Random(System.currentTimeMillis());
        String product;
        
        //producir mientras flag sea true
        for(int i=0 ; this.buffer.produceFlag ; i++) {
            //logica de construccion de operacion scheme
            product = "(";
            //agregar una operacion aleatoria
            product += products.charAt(r.nextInt(4));
            
            //TODO: logica de agregar numeros en rango n a m
            //este codigo es solo demostrativo,no esta en n a m aun, solo rango 0 a 9-------------------------------------------------
            product += " " +(new Random()).nextInt(10); 
            product += " " +(new Random()).nextInt(10); 
            
            //cerrar el scheme op
            product += ")";
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer" + producer_id+" produced: " + product + "tiempo espera " + tiempoDeEspera);
            this.set_product(product); 
            
            try {
                Thread.sleep(tiempoDeEspera);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
}
