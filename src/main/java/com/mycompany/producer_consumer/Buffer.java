
package com.mycompany.producer_consumer;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    
    private Queue<String> buffer_queue;
    //lleva el control de cuantas operaciones ya se hicieron del buffer.
    private int bufferSize;
    boolean produceFlag;
    boolean consumeFlag;
    private int consumeCap;
   
    Buffer(int size) {
      this.bufferSize = size;
        //iniciar el queue
      this.buffer_queue = new LinkedList<>();
      System.out.println("bufferSize:  "+ this.bufferSize);
      this.produceFlag = true;
      this.consumeFlag = true;
      this.consumeCap = bufferSize;
    }
    
    synchronized String consume() {
        String product;
        
        //si esta vacio el buffer,no hay nada que consumir
        while(this.buffer_queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       // revisar si ya se consumio todo
        if(this.buffer_queue.size() == this.consumeCap){
            this.consumeFlag = false;
        }
       //hacemos "pop"
       product = this.buffer_queue.remove();
        notifyAll();
        
        return product;
    }
    

    synchronized void produce(String product) {
        //si no esta vacio, no podemos producir algo al buffer
        //llevar el control del tamano del buffer
        this.bufferSize--;
      //  System.out.println("bufferSize:  "+ this.bufferSize);
        while(this.bufferSize < 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //push
        this.buffer_queue.add(product);
        //si ya acabamos todas las operaciones detener la produccion.
        if(bufferSize < 0){
            this.produceFlag = false;
        }
       
        notifyAll();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}

// test