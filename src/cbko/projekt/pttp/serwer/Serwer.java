package cbko.projekt.pttp.serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serwer
{
	public static String sciezka = "C:\\pttp_udostepnianie"; // Windows
	// public final static String sciezka = "/var/pttp_udostepnianie"; //Linux
	public final static String preffix = "GET ";
	public final static String suffix = " PTTP/1.0";
	public final static String koniecPliku = "<<PTTP END>>";

	public static enum Protokol
	{
		PTTP, PTTPU;
	}

	public static void main(String[] args) throws IOException
	{
		
		ServerSocket serverSocket1 = new ServerSocket(5400);
		ServerSocket serverSocket2 = new ServerSocket(5401);
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		SerwerRunnable watek1 = new SerwerRunnable(serverSocket1, Protokol.PTTP);
		SerwerRunnable watek2 = new SerwerRunnable(serverSocket2, Protokol.PTTPU);
		
		if (args.length != 0)
			sciezka = args[0];
		
		System.out.println("Sciezka udostêpniania: " + sciezka);
		
		Runnable a = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					while (true)
					{
						System.out.println("Nas³uchuje na porcie: "+ serverSocket1.getLocalPort());
						watek1.otworzSocket();
						executorService.submit(watek1);
						System.out.println("£¹czê " + serverSocket1.getLocalPort());
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		};
		Runnable b = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					while (true)
					{
						System.out.println("Nas³uchuje na porcie: "+ serverSocket2.getLocalPort());
						watek2.otworzSocket();
						executorService.submit(watek2);
						System.out.println("£¹czê " + serverSocket2.getLocalPort());
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		};

		executorService.submit(a);
		executorService.submit(b);
	}

}
