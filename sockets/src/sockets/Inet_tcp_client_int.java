package sockets;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Inet_tcp_client_int {
	public static void main(String[] args) {
        Socket echoSocket = null;
        DataOutputStream os = null;
        DataInputStream is = null;
        //DataInputStream stdIn = new DataInputStream(System.in);
        Scanner stdIn = new Scanner( System.in );

        System.out.println("Encuentra el valor mayor del arreglo");
        try {
            echoSocket = new Socket("localhost", 2004);
            os = new DataOutputStream(echoSocket.getOutputStream());
            is = new DataInputStream(echoSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis");
        }

        if (echoSocket != null && os != null && is != null) {
            try {
                int valores;


               // while ((userInput = stdIn.readLine()) != null) {
                    System.out.println("Introduzca el número de valores del arreglo:");
                	valores = stdIn.nextInt();
                    os.writeInt(valores);
                    
                    System.out.println("Tamaño del arreglo " + valores + " valores.");
                    int arreglo[] = new int [valores];
                    
                    System.out.println("Introduzca los valores del arreglo:");
                    for (int i=0; i<arreglo.length; i++) {
            			System.out.print("Valor [" + i + "]: ");
            			arreglo[i] = stdIn.nextInt();
            			os.writeInt(arreglo[i]);
            			System.out.println();
            		}
                    
                    System.out.println("El arreglo introducido es: ");
                    System.out.print("(");
            			for (int i=0; i<arreglo.length; i++)
            				System.out.print(arreglo[i] + ", ");
            		System.out.print(")");
            		System.out.println();
                    
                    System.out.println("El número mayor del arreglo es: " + is.readInt());
                //}
                os.close();
                is.close();
                echoSocket.close();
            } catch (IOException e) {
                System.err.println("I/O failed on the connection to: localhost");
            }
        }
    }
}
