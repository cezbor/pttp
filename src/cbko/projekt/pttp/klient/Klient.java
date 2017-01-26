package cbko.projekt.pttp.klient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextArea;

import cbko.projekt.pttp.serwer.Serwer;

public class Klient 
{
	private static int port;
	
	private static Frame f;
	
	public static void polacz() 
	{
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		//final Socket socket = new Socket("192.168.1.101", 5400);
		System.out.println("��cz� - host: " + KlientConfig.ip + "\n\tprotokol: " + KlientConfig.protokol);
		try{
			
			
		if (KlientConfig.protokol == KlientConfig.Protokol.PTTP)
			port = 5400;
		else if (KlientConfig.protokol == KlientConfig.Protokol.PTTPU)
			port = 5401;
		
		final Socket socket = new Socket(KlientConfig.ip, port);
		
		Runnable nowy = new Runnable() 
        {
            @Override
            public void run() 
            {
                try 
                {
             	   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             	   BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        	       bufferedWriter.write("GET  PTTP/1.0");
        	       bufferedWriter.newLine();
        	       bufferedWriter.flush();
        	       //f.println("GET PTTP/1.0");
        	       String line = bufferedReader.readLine();
        	       //bufferedReader.ready()
         	       while (bufferedReader.ready())
         	       {
         	    	   //System.out.println("serwer: " + line);

         	    	   f.println(line);
         		       line = bufferedReader.readLine();

         	       }
         	       
         	       f.println(line);
         	       System.out.println("Od��czam");
         	       f.println("Od��czam");
         	       socket.close();
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        };
		
		Runnable czytaj = new Runnable() 
        {
            @Override
            public void run() 
            {

                try 
                {
             	   BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         	       String line = reader.readLine();
         	       while (line!=null)
         	       {
         	    	   System.out.println("serwer: " + line);
         	    	   System.out.flush();
         		       line = reader.readLine();
         	       }
         	       socket.close();
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        };
        
        Runnable wysylaj = new Runnable() 

        {
            @Override
            public void run() 
            {

                try {
         	       BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
         	       bufferedWriter.flush();
         	       Scanner odczyt = new Scanner(System.in);
         	       String line = odczyt.nextLine();
         	       while (line!=null)
         	       {
         	    	   bufferedWriter.write(line);
                        bufferedWriter.write("\n");
                        bufferedWriter.flush();
         	           line = odczyt.nextLine();
         	       }
         	       odczyt.close();
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        };

        executorService.submit(nowy);
        //executorService.submit(czytaj);
        //executorService.submit(wysylaj);
        
		} 
		catch (IOException e) {
			System.out.println("Nie mog� po��czy�");
		}
	
	}
	
	public static void main(String[] args) throws Exception
	{
		f = new Frame();
		f.setVisible(true);
		//polacz();
	}
	

}
