# Yarmarq

The program will be using [NBP API](http://api.nbp.pl/).
JSON file will be fetched from the web.

Program should have following features:
- [ ] Wypisuje obowiązującą cenę złota oraz cenę podanej waluty (tabela A) w podanym dniu
- [ ] Oblicza średnią cenę złota za podany okres
- [ ] Odszukuje walutę (tabela A), której kurs, począwszy od podanego dnia, uległ największym wahaniom (waluta, której amplituda zmian kursu jest największa)
- [ ] Odszukuje walutę (tabela C), której kurs kupna był najmniejszy w podanym dniu
- [ ] Wypisuje N walut (tabela C), posortowanych względem różnicy pomiędzy ceną sprzedaży a ceną kupna, w podanym dniu
- [ ] Dla podanej waluty (tabela A) wypisuje informację kiedy dana waluta była najtańsza, a kiedy najdroższa
- [ ] Rysuje (w trybie tekstowym) wykres zmian ceny (np. wykres słupkowy, za pomocą gwiazdek) podanej waluty (tabela A) w układzie tygodniowym, tzn. jaka była cena w poniedziałek, wtorek, itd. Jako parametr wejściowy podajemy: rok, miesiąc, numer tygodnia oraz symbol waluty

Notices:
- [ ] It shouldReady for extension to [sejmometr API](https://mojepanstwo.pl/api/sejmometr)
- [ ] At least one design pattern.
