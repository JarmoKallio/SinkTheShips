**Aihe:** modifioitu laivanupotus 

Toteutetaan järjestelmä, jonka avulla kaksi henkilöä voi pelata tietokoneella hieman perinteisestä versioista eroavaa laivanupotus -peliä. 

Pelaajat asettelevat vuorollaan toisiltaan piilossa joukon laivoja graafiseen käyttöliittymään rakennettuun koordinaatistoon. Asetteluun liittyy sääntöjä. Kun kummatkin ovat saaneet aseteltua laivansa, peli voi alkaa.

Pelaajalle näytetään kaksi koordinaatistoa, yksi, johon hänen omat laivansa on aseteltu ja toinen, jossa pidetään kirjaa vastustajan laivoista. Pelaajat yrittävät nyt vuorollaan arvata missä vastustajan laivat sijaitsevat. Jos pelaajan arvaamassa pisteessä on osa laivaa, ilmoittaa ohjelma osumasta, muuten tulee huti. Osumat ja hutit näkyvät pelaajien koordinaatistoissa ja helpottavat arvausta. 

Pelaaja voi kerrallaan arvata niin monta kertaa sijaintia, voidaan myös sanoa *tulittaa*, kuin hänen vuoronsa alussa valitsemansa *hyökkäävä* laiva peittää koordinaatistoruutuja, kuitenkin laivaan mahdollisesti aikaisemmin osuneet vastustajan tulitukset vähentävät jokainen yhdellä tulituskykyä. Pelissä on käytössä tutka, joka arpoo jokaisella ammunnalla näytetäänkö pelaajan laivan perän koordinaatti vastustajalle, mitä enemmän ampuu, sitä todennäköisempää. Vuoron päätyttyä ohjelma näyttää tyhjän ruudun ja kehottaa toista pelaajaa käynnistämään vuoronsa. Näin peli jatkuu kunnes jommankumman kaikki laivat on tuhottu ja voittaja selvinnyt.


**Käyttäjät:** pelaajat

**Kaikkien käyttäjien toiminnot:**
- ohjelman käynnistäminen
- ohjelman suorituksen lopettaminen
- oman vuoron aloitus
   * onnistuu jos toinen on lopettanut vuoronsa tai kyse on ensimmäisestä  vuorosta
- oman vuoron lopetus
   * onnistuu missä kohtaa tahansa omaa vuoroa, kunhan on tulittanut ainakin kerran
- laivojen asettelu koordinaatistoon
   * ensimmäisellä omalla vuorolla
- hyökkäävän laivan valinta
   * oman vuoron aluksi kerran toisesta omasta vuorosta lähtien
- vihollislaivan sijainnin arvaus antamalla piste vastustajan koordinaatistoa
   * toisesta omasta vuorosta eteenpäin. Hyökkäävä laiva pitää olla ensin valittu


![Alt text](https://github.com/JarmoKallio/SinkTheShips/blob/master/dokumentaatio/luokkakaavio.png)

![Alt text](https://github.com/JarmoKallio/SinkTheShips/blob/master/dokumentaatio/esimerkkipelivuoro.png)

![Alt text](https://github.com/JarmoKallio/SinkTheShips/blob/master/dokumentaatio/laivan asettelu.png)


**Ohjelman rakenne:**

Ohjelmassa on Outputs -piirtoluokan ja Inputs -komentojen kuunteluluokan lisäksi apuluokka Scheduler, joka vastaa piirtoluokan ajoittaisesta päivittämisestä. Initialize -luokka vastaa JFrameluokka Frame:n, Inputs, Outputs ja Logic -luokkien, sekä Scheduler-luokan alustamisesta ja käynnistämisestä. 

Ohjelma toimii pähkinänkuoressa niin, että päälogiikkaluokka Logic säilyttää tiedot "alempien" logiikkaluokkien, eli Screen- luokan toteuttavien, toiminnasta. Lähinnä niin, että lpäälogiikasta voi kutsua kulloinkin siihen tallennetun Screen-luokan. Logiikasta saa myös kulloinkin vuoroaan suorittavan pelaajan (tälle oma Player-luokka). Screen-luokan toteuttavat luokat, esimerkiksi AttackScreen, sisältävät tietoa annettujen kontrollien vaikutuksesta ja tarvittavista piirtotoiminnoista. Käytännössä tämä toimii niin, että piirtoluokka käy hakemassa päälogiikasta kulloinkin aktiivisen Screen-olion, jolta tämä piirturi sitten hakee tietoja mitä piirretään ja mitä ei. Samoin Inputs-luokka hakee aktiivisen Screenin ja käyttää sen metodeja, esimerkiksi jos Inputs huomaa, että on painettu kirjainta P, se kutsuu aktiivisen Screenin metodia keyTyped(). Kaikista Screen -luokan toteuttavista luokista ei välttämättä löydy kaikkia kutsuttuja metodeja, mutta se ei tietenkään haittaa ja Inputs kutsuu niitä aina kun jotain painetaan.

Screen- luokkia on siksi useampi, että pelin eri vaiheissa samat kontrollit aiheuttavat eri seurauksia, samoin eri vaiheissa piirretään ruudulle erilaisia kuvia. Piirtäminen tapahtuu niin, että Outputs hakee päälogiikasta aktiivisen Screenin ja tältä Screeniltä DrawCommand- luokan olion, joka on käytännössä säiliö täynnä boolean-muuttujia, gettereitä ja settereitä, joiden avulla Outputs sitten piirtää näytölle halutut jutut.

Screen- luokat (ja päälogiikka Playerin tapauksessa) käyttävät lisäksi gameobjects -pakkauksen luokkia kuten Player, Radar, Coordinate, Shot ja Ship. Pelaajilla on laivoja, pelaajien laivoilla on sijainti koordinaatistossa, laukauksilla Shot on koordinaatit ja laivoilla aina oman vuoron alusta lähtien uusi Radar, eli tutka, joka säilöö tietoa pelaajan tekemistä ammunnoista ja arpoo näytetäänkö pala pelaajan laivaa vastustajalle.
