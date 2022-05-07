
package com.mycompany.producer_consumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private char buffer;
    
    Buffer() {
        this.buffer = 0;
    }
    
    synchronized char consume() {
        char product = 0;
        
        while(this.buffer == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = 0;
        notifyAll();
        
        return product;
    }
    
    synchronized void produce(char product) {
        while(this.buffer != 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = product;
        
        notifyAll();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
