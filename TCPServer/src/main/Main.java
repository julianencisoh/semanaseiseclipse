package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet {
	

	//Globales
	int xBolita = 250;
	int yBolita = 250;
	int red = 255;
	int green = 0;
	int blue = 0;
	
	
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	// 1
	public void settings() {
		size(500, 500);
	}

	// 1
	public void setup() {

		// Ejecutar en segundo
		new Thread(() -> {
			try {
				ServerSocket server = new ServerSocket(5000);
				System.out.println("Esperando cliente...");
				Socket socket = server.accept();
				System.out.println("Cliente esta conectado");

				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				// Hacer que el objeto is tenga la capacidad de leer Strings completos
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader breader = new BufferedReader(isr);

				while (true) {
					// Esperando mensaje
					System.out.println("Esperando mensaje...");
					String mensajeRecibido = breader.readLine(); //BW::X::Y::ALTO::ANCHO
					
					System.out.println(mensajeRecibido);
					
					Gson gson = new Gson();
					
					//Deserializacion
					Bola bolita = gson.fromJson(mensajeRecibido, Bola.class);
					
					switch (bolita.getI()) {
					
				 	
					    case 1: yBolita = yBolita-5;
						
					    	break;
						
                        case 2: yBolita = yBolita+5;
						
					    	break;
						
                        case 3: xBolita = xBolita+5;
	
	                        break;
	
                        case 4: xBolita = xBolita-5;
	
	                        break;
	                    
                        case 5: 
                        	
                        red = (int)(Math.random()*255+1);
                        green = (int)(Math.random()*255+1);
                        blue = (int)(Math.random()*255+1);
                        	
    	                    break;
					
					}
					
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();

	}

	// Inifito
	public void draw() {
		background(0, 0, 0);
		fill(red, green, blue);
		ellipse(xBolita, yBolita, 50, 50);
	}

}
