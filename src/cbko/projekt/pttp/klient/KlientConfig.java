package cbko.projekt.pttp.klient;

import java.io.File;

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
	public static void main(String[] args)
	{
		protokol = Protokol.PTTP;
		ip = "192.168.1.101";
		sciezka = "/aaaa/bbb/cccc/ddd";
		System.out.println(buildURL());
		sciezka = "aaaa/bbb/cccc/ddd";
		System.out.println(buildURL());
		sciezka = "/aaaa/bbb/cccc/ddd/";
		System.out.println(buildURL());
	}
}
