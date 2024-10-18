# Specifica classi e attività

Il diagramma delle classi e il diagramma delle attività viene corredato da:

- una **specifica per ogni classe**, per definire il comportamento di ogni operazione della classe
- una **specifica per ogni attività**, per definire il comportamento di ogni operazione di cui l’attività è costituita e il comportamento dell’attività nel suo complesso

## Specifica (delle Operazioni) delle Classi

Specifica di una classe C:

**InizioSpecificaClasse C**

Specifica della operazione 1
…
Specifica della operazione N

**FineSpecifica**

Specifica di un’operazione:

**alfa (X1: T1, …, Xn: Tn): T
     pre:** condizione
     **post:** condizione

- alfa (X1: T1, …, Xn: Tn): T è la **segnatura** dell’operazione
- pre rappresenta la **precondizione** dell’operazione, cioè l’insieme delle condizioni che devono valere **prima** dei ogni esecuzione dell’operazione
- post rappresenta le **postcondizioni** dell’operazione, cioè l’insieme delle condizioni che devono valere **alla fine** di ogni esecuzione dell’operazione
****

*es. di specifica di un’operazione*

| Studente | iscritto | CorsoDiLaurea |
| --- | --- | --- |
| Nome: stringa | —————————— | Nome: stringa |
| Cognome: stringa |  | Codice: stringa |
| Matricola: int |  | NumStudenti() : int |
| Età: int |  |  |
| Iscrizione (c: CorsoDiLaurea) |  |  |
| MediaVoti(): real |  |  |

**InizioSpecificaClasse** **CorsoDiLaurea**

NumeroStud() : int
     pre: nessuna
     post: result è uguale al numero di studenti iscritti nel corso di laurea this

**FineSpecifica**

Nota: nella specifica si usa:

- nelle **precondizioni**
    - **this** per riferirsi all’oggetto di invocazione dell’operazione
- nelle **postcondizioni**
    - **this** per riferirsi all’oggetto di invocazione nello stato corrispondente **alla fine** dell’esecuzione dell’operazione
    - **result** per riferirsi al risultato restituito dall’esecuzione dell’operazione
    - **pre(alfa)** per riferirsi al valore dell’espressione alfa nello stato corrispondente alla precondizione

*es.*

| Studente |  | CorsoDiLaurea |
| --- | --- | --- |
| Matricola: int | iscritto | Nome: stringa |
| Età: int | ——————— 0..1 | Codice: stringa |
| NumEsami: int |  | NumStudenti(): int |
| Iscrizione (c: CorsoDiLaurea) |  |  |
| MediaVoti(): real |  |  |
|  |  |  |
| Professore | ————————— | Corso |
| Codice: stringa | Esame | Nome: stringa |
| Età: int | Voto: int | Disciplina: stringa |
| NumVerb: int |  |  |
| Verbalizza (s: Studente, c: Corso, v: int) |  |  |

**InizioSpecificaClasse Professore**

Verbalizza (s: Studente, c: Corso, v: int)
     pre: s non ha ancora eseguito l’esame c, 18 ≤ v ≤ 31
     post: this, s e c sono collegati da un link di tipo Esame, con voto v.Esame = pre(Esame), inoltre s.NumEsami == pre(s.NumEsami) +1 e this.NumVerb == pre(this.NumVerb)+1

**FineSpecifica**

**InizioSpecificaClasse Studente**

Iscrizione (c: CorsoDiLaurea)
     pre: this non è iscritto ad alcun CorsoDiLaurea
     post: this è iscritto al CorsoDiLaurea c

MediaVoti(): real
     pre: this.NumEsami > 0
     post: result è la media dei voti degli esami sostenuti da this

**FineSpecifica**

## Specifica di attività

### Specifica di attività atomiche

Del tutto analoga alla specifica delle classi:

**InizioSpecificaAttivitàAtomica A**

…

**FineSpecifica**

**alfa (X1: T1, …, Xn: Tn): (Y1: T1, …, Yn: Tn)
     pre:** condizione
     **post:** condizione

- alfa (X1: T1, …, Xn: Tn): (Y1: T1, …, Yn: Tn) è la **segnatura** dell’operazione
    - alfa è il nome dell’operazione (cioè quello dell’attività atomica)
    - (X1: T1, …, Xn: Tn) sono i parametri dell’operazione
    - (Y1: T1, …, Yn: Tn) sono i risultati dell’operazione
- pre rappresenta la **precondizione** dell’operazione, cioè l’insieme delle condizioni che devono valere **prima** dei ogni esecuzione dell’operazione
- post rappresenta le **postcondizioni** dell’operazione, cioè l’insieme delle condizioni che devono valere **alla fine** di ogni esecuzione dell’operazione

Nota: Nella specifica delle precondizioni e postcondizioni di un’attività atomica non si può usare “this” poiché non c’è l’oggetto di invocazione

Si usano invece:

- **Y1, …, Yn** per riferirsi ai risultati restituiti dall’esecuzione dell’operazione
- **pre(alfa)** per riferirsi al valore dell’espressione alfa nello stato corrispondente alla precondizione

*es. Attività atomica MediaEsami*

**InizioSpecificaAttivitàAtomica MediaEsami**

MediaEsami (c: CorsoDiLaurea) : (result: real)
     pre: c.NumStudenti() > 0
     post: result è uguale al numero degli esami del corso (cioè le triple studente, professore, corso) diviso c.NumStudenti()

**FineSpecifica**

### Specifica di attività complesse

La specifica in questo caso è molto diversa rispetto ai casi precedenti; infatti, è di interesse il **flusso di controllo**

Il **processo** descritto da tale attività insiste sul diagramma delle classi, poiché ogni attività atomica accede e modifica il diagramma

*es. Diagramma delle classi*

| Persona | possedere | Conto |
| --- | --- | --- |
| codFiscale: string | 1..* ———————— 1..1 | codice: string |
| nome: string |  | saldo: int |
| cognome: string |  | numOperazioni: int |

*Diagramma delle attività*

![diagramma_attivita.jpg](Specifica%20classi%20e%20attivita%CC%80%2035034d563f1c41179027bf7330751e36/diagramma_attivita.jpg)

Il diagramma delle attività si compone di:

- Attività atomiche che lavorano sulle classi (**task**)
    - Apertura Conto
    - Prelevare
    - Depositare
    - Verifica
- Segnali di **I/O**
    - Acquisizione Dati
    - Stampa KO
    - Stampa OK
- Strutture di **controllo**

*es. specifica attività atomiche*

**InizioSpecificaAttivitàAtomica AperturaConto**

Apertura Conto (p1: Persona, p2: Persona) : (c: Conto, no: int, s: int)
     pre: nessuna
     post: c è un nuovo conto (con c.id = numero progressivo, c.saldo = 0 e c.numOperazioni = 0) e no = c.numOperazioni e s=c.saldo e possedere = pre(possedere) union {<p1, c>, <p2, c>}

**FineSpecifica**

**InizioSpecificaAttivitàAtomica Depositare**

Depositare (p: Persona, c: Conto)
     pre: <p, c> in possedere
     post: c.saldo == pre(c.saldo) + random (si deposita una somma a caso) && c.numOperazioni == pre(c.numOperazioni)+1

**FineSpecifica**

**InizioSpecificaAttivitàAtomica Prelevare**

Prelevare (p: Persona, c: Conto)
     pre: <p, c> in possedere
     post: c.saldo == pre(c.saldo) - random (si preleva una somma a caso) && c.numOperazioni == pre(c.numOperazioni)+1

**FineSpecifica**

**InizioSpecificaAttivitàAtomica Verifica**

Verifica (c: Conto) : (no: int, s: int)
     pre: nessuna
     post: s == c.saldo and no == c.numOperazioni

**FineSpecifica**

*es. specifica I/O*

**InizioSpecificaI/O AcquisizioneDati**

AcquisizioneDati() : (p1: Persona, p2: Persona)
     pre: nessuna
     post: gli oggetti p1, p2 vengono identificati con i valori letti da input

**FineSpecifica**

**InizioSpecificaI/O StampaKO**

StampaKO()
     pre: nessuna
     post: manda in output messaggio “KO”

**FineSpecifica**

**InizioSpecificaI/O StampaOK**

StampaOK()
     pre: nessuna
     post: manda in output messaggio “OK”

**FineSpecifica**

L’attività complessa si deve occupare di legare gli input e output delle varie operazioni e del flusso di controllo. 

**La specifica delle attività complesse è costituita da**:

- **Segnatura**
Del tutto analoga alla segnatura delle attività atomiche, essa è formata da:
    - **Nome** dell’attività
    - **Parametri di ingresso,** per passare gli oggetti che costituiscono i punti di ingresso
    - **Parametri di uscita**, per restituire eventuali risultati (oltre quelli memorizzati con side-effect sul diagramma delle classi)
    - **Nota**: l’attività oltre ad accedere ai suoi parametri ha accesso a tutto il diagramma delle classi, in lettura e in scrittura
- **Variabili di processo**
Sono **variabili ausiliarie private** del processo, si **aggiungono** ai parametri di ingresso e uscita, possono essere scritte e lette più volte e servono a **memorizzare risultati intermedi** per passarli come parametri a sottoattività successive. 
Le sottoattività **non** hanno accesso a queste variabili, se esse non vengono passate come parametro
- **Specifica del flusso di controllo** (che tenga conto dei dati)
Il flusso di controllo è già stabilito dal diagramma delle attività, ma serve una specifica con l’esatta descrizione di quali dati sono scambiati tra le sottoattività (in pseudocodice Java)

La specifica avrà la seguente forma

**InizioSpecificaAttività** NomeAttività

Segnatura Attività Complessa con nome parametri di input e parametri di output

**VariabiliProcesso**

Definizione delle variabili di processo con nome e tipo

**InizioProcesso**

Descrizione procedurale del processo in pseudocodice

**FineProcesso**

**FineSpecifica**

*es. specifica di attività complessa*

```jsx
**InizioSpecificaAttività** Principale
Principale()

**VariabiliProcesso**
	marito: Persona
	moglie: Persona
	conto: Conto
	numOperazioni: int
	saldo: real

**InizioProcesso**
AquisizioneDati(): (marito, moglie)
AperturaConto(marito, moglie) : (conto, numOperazioni, saldo);
**while** (numOperazioni < 15 {
	**fork** {
		**thread** t1: {
			Prelevare(marito, conto);
		}
		**thread** t2: {
			Deposita(moglie, conto);
			Prelevare(moglie, conto);
		}
	}
	**join** t1, t2;
	verifica(conto): (numOperazioni, saldo)
}
**if** (saldo <= 0)
	StampaKO();
**else**
	StampaOK();
**FineProcesso

FineSpecifica**
```

**Pseudocodice** 

- Per rappresentare **cicli e decisioni** si usano le strutture di controllo Java (if-else, while, do-while)
- Per **invocare una sottoattività**, si usa la sua segnatura come se fosse un metodo statico, passando in input e output i parametri attuali
- Per il passaggio dei **parametri in input**, si assume il passaggio di parametri **per valore** 
La variabile viene valutata e il rifermento corrispondente diventa il valore iniziale del parametro attuale di input dell’attività
- Per il passaggio dei **parametri in output**, si assume il passaggio di parametri **per risultato**
La variabile parametro attuale viene assegnata con il nuovo valore al termine dell’operazione
*es. AquisizioneDati() : (moglie, marito) è come se fosse un’abbreviazione di:
     AquisizioneDati();
     marito = AquisizioneDati.p1;
     moglie = AquisizioneDati.p2;*
- Per **introdurre thread** di controllo concorrenti si usa **fork,** per **eliminarli** si usa **join**
    - A ciascun thread generato si associa un identificatore che poi potrà essere usato nei join