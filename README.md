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

### Az app-ban használt technológiák
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

### API végpontok(az alkalmazás indítása után megtekinthető: http://localhost:8080/swagger-ui/index.html)
- /api/clients/register (POST) - új felhasználó regisztráció
- /api/positions (POST) - új állás felvitele
- /api/positions (GET) - az összes olyan állások listájának lekérése keresési felté

### API kulcsok használata
- sikeres regisztráció után ez az üzenet érkezik a szervertől: Successful registration, find your API key in the response header
- Az API kulcsot a szerver által küldött válasz fejléc tartalmazza:
- <img width="849" alt="image" src="https://github.com/tamlaz/job-search-api/assets/108185191/e1a554be-2cb6-4fb6-8bb7-be85cfc54315">
- Ezt követően az API kulcsot a szerver felé indított kérések fejécéhez kell csatolni az alábbi módon:
- <img width="848" alt="image" src="https://github.com/tamlaz/job-search-api/assets/108185191/1e979e8f-3845-4450-9748-33c4f4b70401">

### További lépések, hogy az alkalmazás production ready legyen
- Teljes körű tesztelés: az applikáció teljes körű lefedése unit és integrációs tesztekkel
- Biztonság növelése: Spring Security implementáció, például végpontok védelme method security segítségével, API key beágyazása JWT-be
- Dokumentáció: részletes dokumentáció készítése, kitérve a működtetésre, karbantartásra és hibakezelésre
- Teljesítményvizsgálat: tesztelni az alkalmazás skálázhatóságát és terhelhetőségét, például nagy adatmennyiség esetén érdemes lehet Pagination-t alkalmazni, hogy kíméljük a rendszer 
  erőforrásait
- Forráskód felülvizsgálata: ellenőrizni, hogy van e olyan eleme a kódnak, ami kiszervezhető konfigurációs file-ba a forráskódból
- Logolás / monitorozás: minden fontosabb történés nyomonkövetése az alkalmazáson belül valamilyen logging keretrendszer bevezetésével, például Log4j
- CI/CD folyamatok bevezetése: fejlesztési, tesztelési és telepítési folyamatok automatizálása
