# PTTP(U) — Plain Text Transfer Protocol Unsafe
Projekt implementujący interfejs PTTP oraz PTTPU (Plain Text Transfer Protocol Unsafe) służący do przesyłania plików.

## Klient

Klient posiada interfejs graficzny, w którym użytkownik może:
*	wybrać protokół za pomocą którego chce przesłać plik lub wyświetlić listę folderów i plików w katalogu który jest udostępniony:
    *	PTTP – przesyłanie za pomocą tekstu jawnego
    *	PTTPU - przesyłanie w postaci zakodowanej za pomocą algorytmu Base64
*	Podać hosta - jego numer IP lub nazwę (domyślnie localhost)
*	Podać ścieżkę do pliku lub folderu (domyślnie katalog główny udostępniony)
*	Program daje również możliwość wpisania adresu url ręcznie

## Serwer

Uruchamiając serwer użytkownik może podać folder udostępniany jako parametr uruchomienia serwera, w przeciwnym wypadku zostanie użyty domyślny folder.

## Schemat działania

Serwer po uruchomieniu nasłuchuje na dwóch portach(program działa wielowątkowo), jednym do odbioru PTTP, a drugim do odbioru PTTPU.
Po kliknięciu przycisku "wyślij" nawiązywane jest połączenie z serwerem zgodnie z wybranymi ustawieniami. Następnie polecenie(zapytanie) jest formułowane i wysyłane do serwera. Serwer parsuje zapytanie w odpowiedzi wysyłając odpowiednie dane. Jeżeli otrzyma zapytanie o folder to odsyła listę plików, która jest wyświetlana przez klienta w interfejsie użytkownika.  W przypadku zapytania o plik u klienta zostanie wyświetlone okno wyboru ścieżki zapisu przesyłanego pliku. Można również wcześniej wybrać ścieżkę zapisu pliku. 


## Instrukcja obsługi:

1. Włączyć serwer podając jako parametr ścieżkę do folderu przeznaczonego do udostępnienia.
2. Uruchomić klienta
    * Wybrać protokół.
    * Podać numer IP serwera lub jego nazwę.
    * Podać ścieżkę do pliku lub folderu.
    * Ewentualnie wpisać adres url „ręcznie”.
3.	Nacisnąć przycisk wyślij.
4.	W przypadku pliku wybrać ścieżkę zapisu.
5.	Odszukać zapisany plik na dysku lub odczytać listę plików wyświetloną w interfejsie.

Więcej informacji technicznych - [specyfikacja](https://github.com/cezbor/pttp/wiki/Specyfikacja-projektu)
