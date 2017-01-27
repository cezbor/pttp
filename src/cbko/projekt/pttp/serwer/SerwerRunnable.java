package cbko.projekt.pttp.serwer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cbko.projekt.pttp.serwer.Serwer.Protokol;

final class SerwerRunnable implements Runnable
{
	private Socket socket;
	private final Serwer.Protokol protokol;
	private final ServerSocket serwerSocket;
	
	SerwerRunnable(ServerSocket serwerSocket, Serwer.Protokol protokol)
	{
		this.protokol = protokol;
		this.serwerSocket = serwerSocket;
		this.socket = null;
	}

	public void otworzSocket() throws IOException
	{
		socket = serwerSocket.accept();
	}
	
	@Override
	   public void run() 
	   {
		   System.out.println("<I> Klient pod³¹czony");
	       try 
	       {
	    	   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	   BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		       bufferedWriter.flush();
	    	   String line = bufferedReader.readLine();
	           while (!line.contains("END"))
	           {
	        	   System.out.println("klient: " + line);
	    		   if(line.startsWith(Serwer.preffix) && line.endsWith(Serwer.suffix))
	    		   {
	    			   System.out.println("<D> Wykryto polecenie GET");
	    			   String pathString = Parse.parseGETtoPath(line);
	    			   System.out.println("sciezka: " + pathString);		//D
	    			   //Path path = FileSystems.getDefault().getPath(Serwer.sciezka + pathString);
	    			   Path path = Paths.get(Serwer.sciezka + pathString);
	    			   //File plik = new File(Serwer.sciezka + pathString);
	    			   if(path.toFile().exists())
	    			   {
	    				   if(Files.isDirectory(path))		//jest katalogiem
		    			   {
	    					   //System.out.println("Lista plikow i podkatalogow w wybranym katalogu:");
	    					   String doWyslania = "Lista plikow i podkatalogow w wybranym katalogu:\n";
	    					   File[] pliki = path.toFile().listFiles();
	    					   for (File pl : pliki)
	    					   {
	    						   doWyslania += pl.toPath().getFileName().toString();
	    						   doWyslania += "\n";
	    					   }
	    					   doWyslania += Serwer.koniecPliku;
	    					   
	    					   if (protokol == Protokol.PTTPU)
		    				   {
		    					   doWyslania = Base64test.encodeString(doWyslania);
		    				   }
	    					   bufferedWriter.write(doWyslania);
		    				   bufferedWriter.newLine();
	    					   bufferedWriter.flush();
	    					   System.out.println("<I> Wysylam liste plikow");
		    			   }
		    			   else								//jest plikem
		    			   {
		    				   System.out.println("<I> Znalazlem ten plik :)");
		    				   String doWyslania = "";
		    				   if (protokol == Protokol.PTTP)
		    				   {
		    					   byte[] bajty = Files.readAllBytes(path);
		    					   doWyslania = new String(bajty);
		    					   doWyslania += Serwer.koniecPliku;
		    				   } 
		    				   else if (protokol == Protokol.PTTPU)
		    				   {
		    					   doWyslania = Base64test.encodeFile(path);
		    					   doWyslania += Serwer.koniecPliku;
		    					   doWyslania = Base64test.encodeString(doWyslania);
		    				   }
		    				   
		    				   bufferedWriter.write(doWyslania);
		    				   bufferedWriter.newLine();
			    			   bufferedWriter.flush();
		    				   System.out.println("<I> Wysylam");
		    			   }
	    			   }
	    			   else System.out.println("<I> Nie ma takiego pliku");
	    		   }
	    		   else 
	    		   {
	    			   System.out.println("Error 400 Bad Request");
	    			   bufferedWriter.write("Error 400 Bad Request\n");
	    			   bufferedWriter.flush();
	    		   }
	               line = bufferedReader.readLine();
	           }
               System.out.println("<I> Klient od³¹czony");
	           socket.close();
	       } catch (IOException e) { 
	    	   System.out.println("<E> B³¹d - Klient od³¹czony");
	    	   }
	   }
}