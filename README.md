# job-search-api
Álláskereső API ami saját adatbázisból és a REED Jobseeker API segítségével is keres állásokat.

### Felhasználók
Az alkalmazás egy felhasználótípussal rendelkezik:
 -Kliens (továbbiakban felhasználó)

### Funkciók
- A felhasználók regisztrálhatnak név és email megadásával, sikeres regisztráció után egy API kulcsot kapnak vissza
- A felhasználó hozzáadhat állásokat név és fölrajzi hely megadásával. Sikeres autentikáció és validáció után pozíció lementődik adatbázisba és a felhasználó kap egy URL-t kap vissza
- A felhasználó egy kulcsszó és lokáció alapján lekérdezheti a pozíciók listáját. Sikeres autentikáció és validáció után az applikáció keresést indít az adatbázisban, illetve kérést
  indít a REED Jobseeker API felé. A beérkező adatokat az applikáció összefésüli és az egységes eredménnyel tér vissza. 
- Companies can post job offers and manage(view, edit or delete) received users applications for certain job

### Technology stack used in app
- Spring boot - web keretrendszer
- Spring Data JPA - adatbázis menedzsment
- MySQL - relációs adatbázis
- Maven - függőségkezelés
- Swagger - API dokumentáció

### Az applikáció használata
1. Klónozd a repót: git clone https://github.com/tamlaz/job-search-api.git
2. Importáld a projektet az általad preferált IDE-be.
3. Az application.yaml file alapján hozd létre az adatbázis kapcsolat és sémát, majd indítsd el az alkalmazást.
4. POSTMAN-ből indíts kérést regisztráció végpontokra
5. Sikeres regisztráció utn el tudod kezdeni használni a többi funkciót

### API endpoints to lookout for(accessible on https://dukanacv.github.io/jooble-clone/)
- /api/clients (POST) - új felhasználó regisztráció
- /api/positions (POST) - új állás felvitele
- /api/positions (GET) - az összes olyan állások listájának lekérése keresési felté
