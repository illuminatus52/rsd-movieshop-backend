# Semesterprojekt RSD-MovieShop

für Backend Development ILV
(zugehöriges Frontend [hier](https://github.com/DavidKitz/rsdMovieShop_Frontend))

Im Projekt soll das Backend für einen Webshop samt
- Controllern
- Repositories
- Fileupload
- Authentifizierung und
- Datenbank

auf Basis von `Java`, `Spring Boot` und `MariaDB` entwickelt werden.

**Die Verwendung von CMS o.Ä. ist dabei untersagt.**

## Hinweis zur Verwendung
1. Bitte vor dem Starten des Backends die unter [`src/main/resources`](src/main/resources) beiliegende SQL-Datenbank
    - per [phpMyAdmin](https://help.dreamhost.com/hc/en-us/articles/214395768-phpMyAdmin-How-to-import-a-database-or-table) oder
    - per Kommandozeile in der [mySQL-Shell](https://www.digitalocean.com/community/tutorials/how-to-import-and-export-databases-in-mysql-or-mariadb)

    importieren.

2. Danach in den [Spring Application Properties](src/main/resources/application.properties) unter
    - `spring.datasource.username=` den eigenen MySQL-Usernamen sowie bei
    - `spring.datasource.password=` das zugehörige Passwort
    
    einfügen.

3. Das Backend als Spring Boot Applikation starten und dann per WebStorm die [index.html](https://github.com/DavidKitz/rsdMovieShop_Frontend/blob/608a8419406e4a8dc469332a0c7de2d0f5267e54/view/index.html) aufrufen, um den Webshop zu starten.

4. Zum Testen des Backends steht natürlich eine [Postman-Kollektion](src/test/resources/RSD MovieShop.postman_collection.json) zur Verfügung.


Viel Spaß!

### umgesetzt von Team K
  👉 Abdulhadi Rajeh  
  👉 Kitz David  
  👉 Sheikh Salim
