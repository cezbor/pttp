package cbko.projekt.pttp.klient;

import java.io.File;

import cbko.projekt.pttp.serwer.Serwer;

public class KlientConfig 
{
	public static enum Protokol {PTTP, PTTPU;}
	
	public static String ip = "localhost";			//Domyslne wartosci
	public static String url = "pttp://";
	public static String sciezka = "";
	public static KlientConfig.Protokol protokol = Protokol.PTTP; 
	
	private final static String delim = "/"; 
	
	
	public static String buildURL()
	{/*
		String path = "";
		if (sciezka.startsWith(delim))
			sciezka = sciezka.substring(1);
		String[] tab = sciezka.split(delim);
		//System.out.println("substring: " + substring);
		for (String str : tab)
		{
			path += File.separator;
			path += str;
		}
		System.out.println(path + "\n");
		*/
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
		String nowyUrl =url;
		
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
					sciezka = nowyUrl.substring(nowyUrl.indexOf("/") );
					ip = nowyUrl.substring(0, nowyUrl.indexOf("/"));
				} catch (Exception e) {
					sciezka = "/";
					ip = nowyUrl;
				}			
				
		
	}
		
	
	
	public static void main(String[] args)
	{
		
		protokol = Protokol.PTTPU;
		ip = "192.168.1.101";
		sciezka = "/aaaa/bbb/cccc/ddd";
		System.out.println(buildURL());
		sciezka = "aaaa/bbb/cccc/ddd";
		System.out.println(buildURL());
		sciezka = "/aaaa/bbb/cccc/ddd/";
		System.out.println(buildURL());
		wypelnianiePol();
		protokol = Protokol.PTTP;
		wypelnianiePol();
		System.out.println("IP  "+ip);
		System.out.println("Sciiezka "+sciezka);	
		System.out.println("prot "+ protokol);	
	}
}
