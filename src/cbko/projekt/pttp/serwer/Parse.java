package cbko.projekt.pttp.serwer;

import java.io.File;

public class Parse
{
	private final static String delim = "/"; 
	
	public static String parseGETtoPath(String s)
	{
		String path = "";
		String substring;
		try {
			substring = s.substring(Serwer.preffix.length(), s.length() - Serwer.suffix.length());
		} catch (StringIndexOutOfBoundsException e) 
		{
			substring = "";
		}
		
		if (substring.startsWith(delim))
			substring = substring.substring(1);
		String[] tab = substring.split(delim);
		for (String str : tab)
		{
			path += File.separator;
			path += str;
		}
		return path;
	}
	
	public static void parseTest(String textToParse)
	{
		System.out.println(textToParse);
		String wyn = parseGETtoPath(textToParse);
		System.out.println(wyn);
	}
	/*			//Funkcja testowa
	public static void main(String[] args)
	{
		parseTest("GET /raz/dwa/trzy/piec PTTP/1.0");
		parseTest("GET raz/dwa/trzy/piec PTTP/1.0");
		parseTest("GET PTTP/1.0");
	}
	*/
}
