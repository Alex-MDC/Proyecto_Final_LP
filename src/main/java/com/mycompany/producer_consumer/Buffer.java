
package com.mycompany.producer_consumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    //hasta ahora buffer funcionaba como un flag de consumido o no
    private String buffer;
    //buffersize es una propuesta para el setting; que se trabaje hasta que
    //contador interno llege al size
    private int bufferSize;
    private String scheme;
    private boolean vacio;
    
    Buffer() {
       // this.buffer = 0;
       this.vacio = true;
    }
    
    synchronized String consume() {
        String product;
        
        //si esta vacio el buffer,no hay nada que consumir
        while(this.vacio == true) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.scheme;
      //  this.buffer = 0;
      //ya se consumio el producto entonces se libera el espacio
        this.vacio = true;
        notifyAll();
        
        return product;
    }
    
    //podemos usar string en vez de char product para armar el scheme op mas sencillo
    synchronized void produce(String product) {
        //si no esta vacio, no podemos producir algo al buffer
        while(this.vacio == false) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.scheme = product;
        this.vacio = false;
        
        notifyAll();
    }
    
    static int count = 1;
    //cuando llegue a el num de buffer establecido en el UI el count
    // es cuando acabamos. podemos updatear la barrita de progreso con esto
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
