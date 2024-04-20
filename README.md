# Test automatyczny kalkulatora online z wykorzystaniem Selenium oraz Maven

## Kroki testu
1. Przejście do strony http://web2.0calc.com/
2. Obliczenie następującego działania: 35*999+(100/4)= i sprawdzenie czy wynikiem będzie 34990
3. Obliczenie cos(pi) w radianach i sprawdzenie, czy wynikiem będzie -1
4. Obliczenie sqrt(81) i sprawdzenie, czy wynikiem będzie 9
5. Naciśnięcie przycisku historii obliczeń i sprawdzenie, czy zawiera ona 3 wykonane działania: 35*999+(100/4)=, cos(pi),sqrt(81)

## Obsługiwane systemy operacyjne
1. Windows
2. Linux

## Obsługiwane przeglądarki:
1. Chrome

## Użyte technologie i narzędzia:
1. Java
2. Maven
3. Selenium
4. TestNG
5. Chromedriver
6. Apache Commons IO (wykonywanie zrzutu ekranu w przypadku niepowodzenia testu)

## Jak uruchomić:

Z głównego folderu projektu, wywołać w konsoli polecenie:

`mvn clean test -Dsurefire.suiteXmlFiles=TestSuite.xml`
