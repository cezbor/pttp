package cbko.projekt.pttp.serwer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

final class SerwerWysylajRunnable implements Runnable
{
	private final Socket socket;

	SerwerWysylajRunnable(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	   public void run() 
	   {
		   System.out.println("<D> wysylanie dziala");
	       try {
	    	   BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		       bufferedWriter.flush();
		       //BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
}