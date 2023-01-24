# Kviz-Fimek

1.0 Instalacija
  
  1.1 Instaliranje potrebnih propratnih programa radi kompajlovanja aplikacije.
  1.2 Instalirajte Netbeans 15 https://netbeans.apache.org/download/nb15/
  1.3 Instalirajte JDKP 19     https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html

2.0 Pokretanje Netbeans 15 i pravljenje projekta
  
  2.1 Skinite tri .java fajla sa https://github.com/Smileyface101/Kviz-Fimek
  2.2 Otvorite NetBeans 15 i kreirajte novi projekat File ---> New Project ---> Java with Maven ---> Java Apllication i dodelite mu naziv
  2.3 Idite na lokaciju C:\Users\<korisnicko ime>\Documents\NetBeansProjects\<naziv vaseg projekta>\src\main\java\ i nalepite prethodno skinuta tri .java fajla sa linka iznad
  2.4 Prevucite vasa tri fajla u <Default package> folder u Netbeans 15
  2.5 U Netbeans-u kliknite desni klik na vas projekat i idite properties zatim run kategorija i izaberite Main Class: KvizFimek
  2.6 Cestitam! Projekat sada mozete pokrenuti opcijom run u NetBeans-u

3.0 Kompajliranje projekta i pokretanje bez NetBeans 15
  
  3.1 U NetBeans-u desni klik na vas projekat ---> properties ---> general | Na ovoj stranici mozete podesiti tip fajla (.jar) pri kreiranju u sekciji "Packaging:" 
      kao i naziv fajla pod sekcijom "Version:" nakon sto ovo podesite kliknite na Ok.
  3.2 Desni klik na vas projekat u NetBeans-u i izaberite opciju "Clean and Build" nakon zavrsetka rada mozete zatvoriti aplikaciju i pronaci vas .jar fajl na lokaciji
      C:\Users\<korisnicko ime>\Documents\NetBeansProjects\<nazivprojekta>\target\<naziv fajla/verzije zadate u koraku 3.1
  3.3 Uspesno ste kreirali vas fajl, sada aplikaciju mozete pokrenuti na bilo kom uredjaju koji ima JDKP 19 instalirano!


4.0 Uputsvo koriscenja i objasnjenje funkcija aplikacije kviz

   4.1 Kviz Fimek Aplikacija se sastoji iz nekoliko elemenata [prostor za ispis teksta pitanja, progressbar kviza, 4 ponudjena odgovora i dva button-a]
   4.2 Klikom na button "Ucitaj kviz" korisniku se otvara novi prozor u kojem dobija opciju da odabere fajl u kojem se nalazi prethodno napravljeni kviz.
       (Napomena: Prilikom kreacije vaseg kviza, molim vas da fajl sacuvate u .txt formatu)
   4.3 Nakon ucitavanja pitanja korisnik je duzan da odabere 1 od 4 radio buttona koji se nalaze pored ponudjenih odgovora. Odabrani radio button predstavlja odgovor
       korisnika na postavljeno pitanje u kvizu. Nakon sto korisnik odabere svoj odgovor, trebao bi da klikne na button sledece pitanje.
   4.4 Prilikom klika na button sledece pitanje program ce prikazati korisniku sledece pitanje i ponudjene odgovore iz niza ucitanog kviza.
   4.5 Nakon iscitavanja svih pitanja iz odabranog kviza korisniku ce se prikazati poruka sa povratnim informacijama o tacnim odgovorima na kviz.

5.0 Uputsvo koriscenja i objasnjenje funkcija aplikacije kviz kreator

   5.1 Kviz Kreator Aplikacija se sastoji od nekoliko elemenata [prostor za ispis teksta pitanja, 4 radio buttona(odabrani radio button prezentuje tacan odgovor} i dva
       button-a (sledece pitanje i sacuvaj kviz)]
   5.2 Tekstualni prostor je namenjen korisniku za ispis svog pitanja
   5.3 Odabrani radio button predstavlja tacan odgovor na ispisano pitanje.
   5.4 Polje pored radio buttona predstavlja jedan od odgovora na zadato pitanje.
   5.5 Sledece pitanje button se koristi u slucaju da korisnik zeli da doda jos pitanja/odgovora u kviz
   5.6 Sacuvaj kviz predstavlja button putem korisnik moze da sacuva kviz u tekstualni fajl.




