package sockets;

import java.io.*;
import java.net.*;

class Inet_tcp_server_int {

   ServerSocket server;
   Socket ns;
   DataOutputStream out;
   DataInputStream in;
   int valores;

  public void listenSocket(){
    try{
      server = new ServerSocket(2004, 10); 

      ns = server.accept();

      System.out.println("Leyendo tamaño del arreglo del socket...");
      in = new DataInputStream(ns.getInputStream());
      valores = in.readInt();
      System.out.println("Tamaño del arreglo: " + valores);
      
      int arreglo[] = new int [valores];

      System.out.println("Leyendo valores del arreglo del socket...");
      for (int i=0; i<arreglo.length; i++) {
			arreglo[i] = in.readInt();
			System.out.println("Valor [" + i + "]: " + arreglo[i]);
      }
      
      System.out.print("Arreglo introducido: [");
      for (int i=0; i<arreglo.length; i++) {
    	  System.out.print(arreglo[i] + ", ");
      }
      System.out.print("]");
      System.out.println();
      
      int mayor = arreglo[0];
      for (int i=0; i<arreglo.length; i++) {
    	  if (arreglo[i] > mayor)
    		  mayor = arreglo[i];
		}

      System.out.println("El número mayor del arreglo es:" + mayor);
      System.out.println("Enviando a socket...");
      out = new DataOutputStream(ns.getOutputStream());
      out.flush();
      out.writeInt(mayor);
      out.flush();
    } 
    catch (IOException e) {
      System.out.println("Accept failed: 4444");
      System.exit(-1);
    }
  }

  protected void finalize(){
//Clean up 
     try{
        in.close();
        out.close();
        server.close();
    } catch (IOException e) {
        System.out.println("Could not close.");
        System.exit(-1);
    }
  }

  public static void main(String[] args){
      System.out.println("Echo Server...");
        Inet_tcp_server_int s = new Inet_tcp_server_int();

	s.listenSocket();
        s.finalize();
  }
}
