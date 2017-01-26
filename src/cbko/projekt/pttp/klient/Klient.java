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

public class Klient 
{
	public static void polacz() 
	{

		ExecutorService executorService = Executors.newFixedThreadPool(10);
	    
		
		/*
		public static String ip = "";
		public static String url = "pttp://";
		public static String sciezka = "";
		public static enum Protokol {PTTP, PTTPU;}
		public static KlientConfig.Protokol protokol; 
		*/
		
		//final Socket socket = new Socket("192.168.1.101", 5400);
		System.out.println("³¹czê " + KlientConfig.ip);
		try{
		final Socket socket = new Socket(KlientConfig.ip, 5400);
		
		
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
        
        executorService.submit(czytaj);
        executorService.submit(wysylaj);
		} 
		catch (IOException e) {
			System.out.println("Nie mogê po³¹czyæ");
			
		}
	
	}
	
	public static void main(String[] args) throws Exception
	{
		Frame f = new Frame();
		f.setVisible(true);
	}
	

}
