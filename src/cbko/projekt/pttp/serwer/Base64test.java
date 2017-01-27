package cbko.projekt.pttp.serwer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64test
{
	
	public static String encodeString(String tekst)
	{
		Encoder encoder = Base64.getEncoder();
		byte[] tekst2 = tekst.getBytes(StandardCharsets.UTF_8); //Charset.forName("UTF-8")
		String nowyTekst = new String(encoder.encode(tekst2), StandardCharsets.UTF_8);
		return nowyTekst;
	}
	
	public static String decodeString(String tekst)
	{
		Decoder decoder = Base64.getDecoder();
		byte[] tekst2 = tekst.getBytes(StandardCharsets.UTF_8); //Charset.forName("UTF-8")
		String nowyTekst = new String(decoder.decode(tekst2), StandardCharsets.UTF_8);
		return nowyTekst;
	}
	
	public static void decodeToFile(String tekst, File plik)
	{
		Decoder decoder = Base64.getDecoder();
		byte[] bajty = decoder.decode(tekst);
		try
		{
			Files.write(plik.toPath(), bajty);
		}
		catch (IOException e)
		{
			System.out.println("B³¹d - nie mo¿na zapisaæ pliku");
		}
	}
	
	public static String encodeFile(File plik)
	{
		Path path = plik.toPath();
		return encodeFile(path);
	}
	
	public static String encodeFile(Path path)
	{
		Encoder encoder = Base64.getEncoder();
		byte[] bajty;
		try
		{
			bajty = Files.readAllBytes(path);
			String nowyTekst = encoder.encodeToString(bajty);
			return nowyTekst;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "blad kodowania";
		}
	}
	/*		//Funkcja testowa
	public static void main(String[] args)
	{
		String tekstDoZakodowania = "Czeœæ";
		System.out.println("Koduje: \""+ tekstDoZakodowania +"\"");
		System.out.println(Base64test.encodeString(tekstDoZakodowania) + "\n");
		
		String tekstDoOdkodowania = "Q3plxZvEhw==";
		System.out.println("Odkodowuje: \""+ tekstDoOdkodowania +"\"");
		System.out.println(Base64test.decodeString(tekstDoOdkodowania) + "\n");
		
		File plik = new File("C:\\Users\\Czarek\\Desktop\\studia\\Semestr 4\\Programowanie obiektowe projekt\\flagi\\a.png");
		//File plik = new File(getClass().getResource("s").toURI());	//nie dzia³a?
		System.out.println("Koduje: \""+ plik +"\"");
		System.out.println(Base64test.encodeFile(plik));
	}
	*/
	
}
