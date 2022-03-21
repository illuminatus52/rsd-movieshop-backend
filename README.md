# Semesterprojekt RSD-MovieShop

für Backend Development ILV
(passendes Frontend [hier](https://github.com/DavidKitz/rsdMovieShop_Frontend))

Im Projekt soll das Backend für einen Webshop samt
- Controllern
- Repositories
- Fileupload
- Authentifizierung und
- Datenbank

auf Basis von `Java`, `Spring Boot` und `MariaDB` entwickelt werden.

Die Verwendung von CMS o.Ä. ist dabei untersagt.

## Hinweis zur Verwendung der Datenbank
1) Bitte vor dem Starten des Backends die unter [`src/main/resources`](src/main/resources) beiliegende SQL-Datenbank
    - per [phpMyAdmin](https://help.dreamhost.com/hc/en-us/articles/214395768-phpMyAdmin-How-to-import-a-database-or-table) oder
    - per Kommandozeile in der [mySQL-Shell](https://www.digitalocean.com/community/tutorials/how-to-import-and-export-databases-in-mysql-or-mariadb)

    importieren.

2) Danach in den Spring Application Properties unter
    - `spring.datasource.username=` den eigenen MySQL-Usernamen sowie bei
    - `spring.datasource.password=` das zugehörige Passwort
    
    einfügen.

3) Das Backend starten und dann per WebStorm die index.html aufrufen, um im Webshop zu navigieren.

Viel Spaß!

### umgesetzt von Team K
  👉 Abdulhadi Rajeh  
  👉 Kitz David  
  👉 Sheikh Salim
