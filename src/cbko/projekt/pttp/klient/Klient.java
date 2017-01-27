package cbko.projekt.pttp.klient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextArea;

import cbko.projekt.pttp.klient.KlientConfig.Protokol;
import cbko.projekt.pttp.serwer.Base64test;

public class Klient 
{
	private static int port;
	
	private static Frame f;
	
	public static void polacz() 
	{
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		//final Socket socket = new Socket("192.168.1.101", 5400);
		System.out.println("³¹czê - host: " + KlientConfig.ip + "\n\tprotokol: " + KlientConfig.protokol);
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
        	       bufferedWriter.write(KlientConfig.skladanieZapytania());
        	       bufferedWriter.newLine();
        	       bufferedWriter.flush();
        	       //String line = null;
        	       //Base64test.decodeString(tekst);
        	       //FileOutputStream ssss = new FileOutputStream(KlientConfig.sciezkaZapisu.toFile()); 
        	       
        	       
        	       
        	       //plikWriter.write(str);
        	       String line = bufferedReader.readLine();
        	       System.out.println(line);
        	       if (KlientConfig.protokol == Protokol.PTTPU)
        	       {
     	    		   line = Base64test.decodeString(line);
     	    		   //f.println(line);
        	       }
        	       System.out.println(line);
        	       if (line.startsWith("Lista plikow"))
        	       {
        	    	   
        	    	   while (bufferedReader.ready())
             	       {
        	    		   f.println(line);
             		       line = bufferedReader.readLine();
             	       }
             	       //if (KlientConfig.protokol == Protokol.PTTPU)
             	    	//   line = Base64test.decodeString(line);
        	    	   line = line.substring(0, line.indexOf(KlientConfig.koniecPliku));
             	       f.println(line);
        	       }
        	       else
        	       {
        	    	   f.wyborSciezkiZapisu();
        	    	   //Path p = Paths.get(KlientConfig.sciezkaZapisu.toString(),"/", KlientConfig.getFileName());
        	    	   File plik  = new File(KlientConfig.sciezkaZapisu.toFile(), KlientConfig.getFileName());
    	    		   System.out.println(plik.getPath());
    	    		   if (KlientConfig.protokol == Protokol.PTTP)
             	       {
    	    			   BufferedWriter plikWriter = new BufferedWriter (new FileWriter(plik)); 
            	    	   //line = bufferedReader.readLine();
            	    	   while (bufferedReader.ready())
                 	       {
            	    		   System.out.println("______----____dzialam____-----____");
            	    		   
            	    		   System.out.println(plik.getPath());
                 	    	   
                 	    	  // f.println(line);
            	    		   plikWriter.write(line);
                 	    	   plikWriter.newLine();

                 		       line = bufferedReader.readLine();
                 		     // f.println(line);

                 	       }
            	    	   line = line.substring(0, line.indexOf(KlientConfig.koniecPliku));
            	    	   plikWriter.write(line);
            	    	   plikWriter.close();
             	       }
    	    		   else if (KlientConfig.protokol == Protokol.PTTPU)
             	       {
             	    	   //line = Base64test.decodeString(line);
             	    	   line = line.substring(0, line.indexOf(KlientConfig.koniecPliku));
            	    	   System.out.println(line);
                	       Base64test.decodeToFile(line, plik);
             	       }
             	      // f.println(line);
             	       //plikWriter.write(line);
             	      // plikWriter.close();
        	       }
        	       
         	       
         	      // System.out.println("Od³¹czam");
         	      // f.println("Od³¹czam");
        	       /*
         	       Scanner scanner =new Scanner(new InputStreamReader(socket.getInputStream()));
         	       scanner.useDelimiter(KlientConfig.koniecPliku);
         	       f.println(scanner.nextLine());
         	       */
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
			System.out.println("Nie mo¿na po³¹czyæ, serer nie odpowiada");
			f.println("Nie mo¿na po³¹czyæ, serer nie odpowiada");
		}
	
	}
	
	public static void main(String[] args) throws Exception
	{
		f = new Frame();
		f.setVisible(true);
		//polacz();
	}
	

}
