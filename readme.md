# Zadanie rekrutacyjne PSI

## Przydatne linki

- https://www.spriters-resource.com/pc_computer/heroes3/ Ikonki z gry dla nowych jednostek (potrzebne do zadania 3)
- http://www.heroesofmightandmagic.com/heroes3/creaturescastle.shtml Opis jednostek do zaimplementowania (potrzebne do
  zadania 3)

## Jak uruchomić projekt?

- Zainstaluj maven (wersja aktualna 3.8.6).
- Zbuduj projekt używając instrukcji `mvn install` w projekcie.
- Wejdź do klasy `LaunchBattle` z poziomu IDE, uruchom.

## Zadania:

### Każde zadań powinno znajdować się w osobnym branchu, w przypadku zadania 3 każdy z podpunktów powinien znajdować się w osobnym commicie.

1. Obróć jednostki z prawej strony tak by patrzyły na przeciwników (lustrzane odbicie).
   Punkt wejścia to metoda odpowiedzialna za wyświetlenie każdej kratki z osobna
   `MainBattleController#refreshGui`
2. Na starcie gry wszystkie kafelki są szare. Po najechaniu na niektóre z nich (środkowa kolumna) ujawnione zostaną pola
   specjalne np.
   Usuń tego buga.
   Dokładnie ten sam punkt wejścia `MainBattleController#refreshGui`.
3. Stwórz dodatkowe jednostki dla frakcji Castle
   Punkt wejścia `CastleCreatureFactory#create`.
   Aby włożyć te jednostkę na planszę należy podmienić implementacje metody `Start#createP1` lub `Start#P2`, tak by
   korzystała z `CastleCreatureFactory`.
    1. `CreatureStatistic.GRIFFIN` potrafi oddać 2 razy na turę
    2. `CreatureStatistic.MARKSMAN` jako jednostka strzelająca oddająca 2 strzały (zauważ proszę że jest to coś
       zupełnie innego niż podwójne obrażenia)
    3. `CreatureStatistic.CHAMPION` dodatkowe obrażenia w zależności od pokonanego dystansu.
    4. `CreatureStatistic.ANGEL` dodatkowe obrażenia dla jednostki Devil z inforno(aktualnie nie istnieje, możesz
       wybrać dowolną inną zaimplementowaną jednostkę np. 50% zwiększone obrażenia dla szkieletów
       z `CreatureStatistic.SKELETON`)
    5. `CreatureStatistic.ARCHANGEL` porafi wskrzesić jednostkę

Oczekuję testów jednostkowych sprawdzających czy nowe jednostki zachowują się dokładnie tak jak tego oczekujemy.
Przykłady w klasie `CreatureTest#creatureShouldHaveTwoCounters` oraz
`CreatureTest#creatureShouldHaveInfiniteCounters` dla kreatur, które
counter-attack wykonują więcej niż 1 raz. Mechanika polega na tym że
jednostka po otrzymaniu obrażeń oddaje jestnostce atakującej (jeśli
jednostka atakująca walczy wręcz – na to testu brak ☹)

W przypadku trudności z zaimplemntowaniem któregoś z podpuktów z zadania 3 był w stanie określić dlaczego nie można /
nie potrafisz zaimplementować danej kreatury i jakiej wieczy o istniejącej kodzie potrzebowałbyś by tego dokonać.