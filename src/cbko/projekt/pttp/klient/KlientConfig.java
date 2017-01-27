package cbko.projekt.pttp.klient;

import java.nio.file.Path;

public class KlientConfig 
{
	public static enum Protokol {PTTP, PTTPU;}
	
	public static String ip = "localhost";			//Domyslne wartosci
	public static String url = "pttp://";
	public static String sciezka = "";
	public static KlientConfig.Protokol protokol = Protokol.PTTP; 
	public static Path sciezkaZapisu;
	
	public final static String preffix = "GET ";
	public final static String suffix = " PTTP/1.0";
	public final static String koniecPliku = "<<PTTP END>>";
	
	private final static String delim = "/"; 
	
	
	public static String buildURL()
	{
		String nowyurl = "";
		if (protokol == Protokol.PTTP)
			nowyurl = "pttp://";
		else if (protokol == Protokol.PTTPU)
			nowyurl = "pttpu://";
		
		nowyurl += ip;
		
		if (sciezka.startsWith(delim))
			nowyurl += sciezka;
		else 
			nowyurl += delim + sciezka;
		url = nowyurl;
		return nowyurl;
	}
	public static void wypelnianiePol()
	{
		String nowyUrl = url;
		if( nowyUrl.startsWith("pttp://"))
		{
			protokol=Protokol.PTTP;
		}
		else if ( nowyUrl.startsWith("pttpu://"))
		{
			protokol=Protokol.PTTPU;						
		}
		
		try {
			nowyUrl = nowyUrl.substring(nowyUrl.indexOf("//") + 2);
		} catch (Exception e1) {
		}
		try {
			sciezka = nowyUrl.substring(nowyUrl.indexOf("/"));
			ip = nowyUrl.substring(0, nowyUrl.indexOf("/"));
		} catch (Exception e) {
			sciezka = "/";
			ip = nowyUrl;
		}
	}
	public static String skladanieZapytania()
	{
		String zapytanie=null;
		zapytanie = preffix + sciezka + suffix ;
		
		return zapytanie;
	}
		
	public static String getFileName()
	{
		String s = sciezka;
		s = s.substring(s.lastIndexOf("/") + 1);
		return s;
	}
	
	/*		//Funkcja testowa
	public static void main(String[] args)
	{
		
		protokol = Protokol.PTTPU;
		ip = "192.168.1.101";
		sciezka = "/aaaa/bbb/cccc/ddd";
		System.out.println(buildURL());
		sciezka = "aaaa/bbb/cccc/ddd";
		System.out.println(buildURL());
		//sciezka = "/aaaa/bbb/cccc/ddd/";
		System.out.println(buildURL());
		wypelnianiePol();
		protokol = Protokol.PTTP;
		wypelnianiePol();
		System.out.println("IP  " + ip);
		System.out.println("Sciezka " + sciezka);	
		System.out.println("port "+ protokol);	
		System.out.println(skladanieZapytania());
		System.out.println(getFileName());
	}
	*/
}
