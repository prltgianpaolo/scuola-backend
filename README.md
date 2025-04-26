# Scuola Privata - Backend API

## Nome del progetto
Scuola Privata - Backend REST API

## Obiettivo
Realizzare un'applicazione backend per la gestione di una scuola privata, con funzionalità di gestione studenti, corsi, materie, docenti, lezioni, esami, presenze e iscrizioni, con sicurezza JWT e esportazione dati.

## Tecnologie
- Java 21
- Spring Boot 3
- Spring MVC
- Spring Data JPA (Hibernate)
- Spring Security con JWT
- H2 Database (in memoria)
- Apache POI (per esportazioni Excel)
- OpenPDF (per esportazioni PDF)
- Maven
- Postman (per test API)
- Git & GitHub

## Architettura
- Architettura a livelli basata su MVC (Model-View-Controller)
- Separazione tra Entità e DTO
- Mapper dedicati per la conversione Entità <-> DTO
- Uso di Service Layer e Repository Layer
- Configurazione globale di sicurezza (SecurityConfig)
- Gestione errori centralizzata con `@ControllerAdvice`
- Logging delle operazioni principali nei Service

## Sicurezza e autenticazione
- Login (`/api/auth/login`) e Registrazione (`/api/auth/registrazione`) accessibili liberamente
- Protezione di tutti gli endpoint `/api/**` tramite JWT
- Filtro JWT (`JwtAuthenticationFilter`) per intercettare e validare token
- Sicurezza configurata tramite Spring Security (`SecurityFilterChain`)
- Generazione e validazione token con `JwtUtil`
- Accesso negato (`403`) o non autorizzato (`401`) gestito automaticamente

## Funzionalità principali
- CRUD completo per Studenti, Corsi, Materie, Docenti, Aule, Lezioni, Esami, Iscrizioni, Registro Presenze, Valutazioni
- Registrazione di nuovi utenti
- Login utenti e generazione di JWT
- Accesso protetto alle API
- Dataset iniziale precaricato
- Export dati:
    - CSV: anagrafiche studenti
    - PDF: profilo studente completo
    - Excel: corsi, materie ed esami associati
- Documentazione API tramite Swagger/OpenAPI

## Funzionalità aggiuntive
- Logging automatico delle operazioni CRUD nei Service
- Gestione errori personalizzata e risposta in formato JSON
- Esportazione della Collection Postman per test API
- Dataset di esempio caricato automaticamente all'avvio (`DataInitializer`)

## Testing
- Test manuali eseguiti tramite Postman
- Validazione endpoint tramite Swagger
- Test JWT: accesso consentito solo con token valido
- Verifica corretta gestione degli errori (`GlobalExceptionHandler`)

## Avvio del progetto
1. Clonare il repository GitHub:
   ```bash
   git clone https://github.com/tuo-username/scuola-privata-backend.git
   ```
2. Importare il progetto in IntelliJ IDEA o Eclipse
3. Assicurarsi di avere installato Java 21
4. Avviare l'applicazione Spring Boot
5. Accessi utili:
    - Swagger UI: `http://localhost:8080/swagger-ui/index.html`
    - H2 Database Console: `http://localhost:8080/h2-console`

> Username H2 Console: `sa`  
> Password: *(lascia vuoto)*

## Dataset iniziale
All'avvio vengono creati automaticamente:
- Studenti (esempio: Mario Rossi, Luca Bianchi)
- Docenti associati a materie
- Corsi associati a materie
- Lezioni pianificate
- Esami collegati alle materie
- Iscrizioni studenti ai corsi
- Valutazioni studenti sugli esami
- Utente admin:
    - Username: `admin`
    - Password: `password123`

## Design Pattern utilizzati
- MVC (Model-View-Controller)
- DTO Pattern per separazione dati
- Mapper Pattern per conversione Entità <-> DTO
- Singleton Pattern per gestione JWT (`JwtUtil`)
- Facade Pattern nei Service
- Repository Pattern per accesso ai dati

## Note finali
- Il progetto è stato sviluppato rispettando le best practice Java e Spring Boot
- Ogni funzionalità è documentata e testabile tramite Postman
- L'applicazione è facilmente estendibile con nuove funzionalità
- Pronto per essere containerizzato in Docker in caso di necessità future

## Extra
- File `.gitignore` presente e configurato
- File `README.md` completo
- File Postman Collection incluso (`/postman/Scuola-Privata-API.postman_collection.json`)
- Branch principale: `main`
