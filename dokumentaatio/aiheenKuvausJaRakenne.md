**Aihe:** modifioitu laivanupotus Toteutetaan järjestelmä, jonka avulla kaksi henkilöä voi pelata tietokoneella hieman perinteisestä versioista eroavaa laivanupotus -peliä. 

Pelaajat asettelevat vuorollaan toisiltaan piilossa joukon laivoja graafiseen käyttöliittymään rakennettuun koordinaatistoon. Asetteluun liittyy sääntöjä. Kun kummatkin ovat saaneet aseteltua laivansa, peli voi alkaa.

Pelaajalle näytetään kaksi koordinaatistoa, yksi, johon hänen omat laivansa on aseteltu ja toinen, jossa pidetään kirjaa vastustajan laivoista. Pelaajat yrittävät nyt vuorollaan arvata missä vastustajan laivat sijaitsevat. Jos pelaajan arvaamassa pisteessä on osa laivaa, ilmoittaa ohjelma osumasta, muuten tulee huti. Osumat ja hutit näkyvät pelaajien koordinaatistoissa ja helpottavat arvausta. 

Pelaaja voi kerrallaan arvata niin monta kertaa sijaintia, voidaan myös sanoa *tulittaa*, kuin hänen vuoronsa alussa valitsemansa *hyökkäävä* laiva peittää koordinaatistoruutuja. Vastustajalle ilmoitetaan jokaisen arvauksen yhteydessä koordinaatistosta arvaus arvaukselta tarkentuva alue, missä tulittava laiva sijaitsee. vuoron päätyttyä ohjelma näyttää tyhjän ruudun ja kehottaa toista pelaajaa käynnistämään vuoronsa. Näin peli jatkuu kunnes jommankumman kaikki laivat on tuhottu ja voittaja selvinnyt.


**Käyttäjät:** pelaajat

**Kaikkien käyttäjien toiminnot:**
- ohjelman käynnistäminen
- ohjelman suorituksen lopettaminen
- oman vuoron aloitus
   * onnistuu jos toinen on lopettanut vuoronsa tai kyse on ensimmäisestä  vuorosta
- oman vuoron lopetus
   * onnistuu missä kohtaa tahansa omaa vuoroa
- laivojen asettelu koordinaatistoon
   * ensimmäisellä omalla vuorolla
- hyökkäävän laivan valinta
   * oman vuoron aluksi kerran toisesta omasta vuorosta lähtien
- vihollislaivan sijainnin arvaus klikkaamalla vastustajan koordinaatistoa
   * toisesta omasta vuorosta eteenpäin. Hyökkäävä laiva pitää olla ensin  valittu


![Alt text](/home/jambo/SinkTheShips/dokumentaatio/luokkakaavio.png)




