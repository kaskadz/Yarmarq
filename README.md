# Yarmarq

The program will be using [NBP API](http://api.nbp.pl/).
JSON file will be fetched from the web.

Program should have following features:
- [x] Wypisuje, dla podanego dnia, obowiązującą cenę złota oraz cenę podanej waluty (tabela A)
- [x] Oblicza średnią cenę złota za podany okres
- [x] Odszukuje walutę (tabela A), której kurs, począwszy od podanego dnia, uległ największym wahaniom (waluta, której amplituda zmian kursu jest największa)
- [x] Odszukuje walutę (tabela C), której kurs kupna był najmniejszy w podanym dniu
- [x] Wypisuje N walut (tabela C), posortowanych względem różnicy pomiędzy ceną sprzedaży a ceną kupna, w podanym dniu
- [x] Dla podanej waluty (tabela A) wypisuje informację kiedy dana waluta była najtańsza, a kiedy najdroższa
- [ ] Rysuje (w trybie tekstowym) wspólny (dla wszystkich tygodni) wykres zmian ceny (np. wykres słupkowy, za pomocą różnorodnych znaków ASCII) podanej waluty (tabela A) w układzie tygodniowym, tzn. jaka była cena w poniedziałki, wtorki, itd. w pierwszym tygodniu, drugim tygodniu, ...
  - Jako parametry wejściowe podajemy symbol waluty oraz dwa okresy: początkowy oraz końcowy
  - Każdy z okresów podajemy w postaci: rok, miesiąc, numer tygodnia
  - Przyjmujemy, że pierwszym dniem okresu początkowego jest poniedziałek, a ostatnim dniem okresu końcowego jest piątek
- [x] Po wywołaniu programu bez argumentów wyświetla pomoc (opis opcji oraz sposobu uruchamiania programu)

Notices:
- [ ] It shouldReady for extension to [sejmometr API](https://mojepanstwo.pl/api/sejmometr)
- [x] At least one design pattern.
