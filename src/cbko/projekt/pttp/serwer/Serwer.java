package cbko.projekt.pttp.serwer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serwer
{
	public final static String sciezka = "C:\\pttp_udostepnianie"; // Windows
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
		// while (true)
		{
			// final Socket socket1 = serverSocket1.accept();
			// final Socket socket2 = serverSocket2.accept();

			SerwerCzytajRunnable czytaj1 = new SerwerCzytajRunnable(serverSocket1, Protokol.PTTP);
			// Runnable wysylaj = new SerwerWysylajRunnable(socket1);
			SerwerCzytajRunnable czytaj2 = new SerwerCzytajRunnable(serverSocket2, Protokol.PTTPU);
			// Runnable wysylaj2 = new SerwerWysylajRunnable(socket2);

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
							czytaj1.otworzSocket();
							executorService.submit(czytaj1);
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
							czytaj2.otworzSocket();
							executorService.submit(czytaj2);
							System.out.println("£¹czê " + serverSocket2.getLocalPort());
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			};

			// czytaj.otworzSocket();
			// executorService.submit(wysylaj);
			// czytaj1.otworzSocket();
			// executorService.submit(czytaj1);
			// executorService.submit(wysylaj2);

			executorService.submit(a);
			executorService.submit(b);
		}
	}

}
