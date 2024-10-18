# Attività - Progetto e realizzazione

Nei linguaggi orientati agli oggetti è spesso necessario realizzare classi che rappresentano funzioni, cioè i **funtori**, le cui istanze rappresentano attivazioni di una funzione

Per esempio in Java, sono **funtori** gli oggetti che implementano Runnable() e rappresentano la funzione run(). Essi vengono eseguiti costruendo un oggetto Thread che funge da **esecutore**, cioè chiamando su tale oggetto il metodo start(), esso a sua volta chiama il metodo run() del runnable associato al thread

*es. Runner concorrenti*

```jsx
public class Runner implements Runnable {
	private String nome;
	public Runner(String n) {nome = n;}
	public void run() {
		for (int i = 0; i < 25; i++) 
			System.out.println(nome + " sta girando");
	}
}
```

```jsx
public class Main {
	public static void main(String[] args) {
		//Crea attivazioni del Runner
		Runner r1 = new Runner("Alpha");
		Runner r2 = new Runner("Beta");
		
		//Attivazione passata all'esecutore, cioè Thread
		Thread alpha = new Thread(r1);
		Thread beta = new Thread(r2);

		//Esecuzione dell'attivavazione, cioè start() chiama run()
		alpha.start();
		beta.start();
	}
}
```

## Il pattern Funtore

Cioè una classe le cui istanze rappresentano attivazioni di funzioni

*es. Funzione*

```jsx
public class Funzione {
	public static double function(Object o, int i) {
		System.out.println("faccio cose con " + o + " e " + i);
		return i; //restituisce risultato
	}
} 
```

*es. corrispondente classe Funtore*

```jsx
public class Funtore implements Runnable {
	private boolean eseguita = false;
	private Object o;
	private int i;
	private double ris;
	
	public Funtore(Object oo, int ii) {
		o = oo;
		i = ii;
	}
	public synchronized void run() {
		if (eseguita) 
			return;
		eseguita = true;
		System.out.println("faccio cose con " + o + " e " + i);
		ris = i; //restituisce risultato
	}
	public synchronized double getRis() {
		if (!eseguita) 
			throw new RunTimeException();
		return ris;
	}
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
```

Aspetti fondamentali **oggetto Funtore**:

- il **nome della funzione** diventa il **nome della classe**
- i **parametri della funzione** sono passati in ingresso al costruttore della classe e vengono memorizzati in **campi dati privati**
- il **risultato** viene memorizzato in un **campo privato** ed è reso disponibile attraverso **getRis()**
- il **corpo della funzione** è ora il corpo del **metodo run**
- per fare in modo che ogni oggetto corrisponda esattamente ad un’attivazione della funzione funzione() bisogna introdurre una **variabile booleana eseguita**, che indica se l’attivazione corrente è stata eseguita o meno
    - il metodo responsabile dell’esecuzione **run()** verifica se eseguita è true, in tal caso esce senza fare altro, altrimenti pone eseguita a true ed esegue il metodo
    **Nota**: è possibile generare una nuova attivazione del funtore costruendo un nuovo oggetto
    - il metodo **getRis()** restituisce il risultato solo se eseguita è true
    - il client può leggere la variabile eseguita attraverso il metodo **estEseguita()**
- Per rendere il funtore “**thread safe**”, per evitare cioè che esecuzioni multiple corrompano la correttezza delle informazioni, bisogna rendere **tutti i metodi pubblici synchronized**
In tal modo, per ogni oggetto della classe Funtore, solo uno dei metodi tra run(), getRis() e estEseguita() può essere in esecuzione

*es. Main con funzione*

```jsx
public class MainFunzione {
	public static void main(String[] args) {
		Object o = new Object();
		double ris = Funzione.function(o, 10);
		System.out.println(ris);
	}
}
```

*es. Main con corrispondente oggetto Funtore*

```jsx
public class MainFuntore {
	public static void main(String[] args) {
		Object o = new Object();
		Funtore f = new Funtore(o, 10);
		Thread t = new Thread(f);
		t.start();
		try {
			t.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(f.getRis());
	}
}
```

Aspetti fondamentali **chiamata Funtore**:

- deve essere costruito un oggetto Funtore corrispondente all’attivazione della funzione Funzione
- l’oggetto Funtore va passato all’esecutore (Thread)
- L’esecutore richiama il metodo del funtore che esegue la funzione
start() chiama run()
- una volta terminata l’esecuzione del metodo, il risultato è disponibile attraverso getRis()

## Il pattern Singleton

Cioè una classe con una sola istanza

Nel pattern realizzativo bisogna impedire l’uso dell’operatore new, rendendo il **costruttore privato** e usando un **metodo statico** per restituire il singolo oggetto quando serve

Con eager costruction

```jsx
public class MySingleton {
	private static MySingleton unico = new MySingleton();
	//altre variabili di istanza
	private MySingleton() {
		//inizializza a campi significativi
	}
	//synchronized se deve essere thread-safe
	public static synchronized MySingleton getIstance() {
		return unico;
	}
	//altri metodi
}
```

*es. generatore di numeri progressivi, globale per l’intera applicazione*

```jsx
public class GeneratoreNumProgressivi {
	private static GeneratoreNumPorgressivi unico = new GeneratoreNumProgressivi();
	private int contatore;
	private GeneratoreNumProgressivi() {
		contatore = 0;
	}
	public static synchronized GeneratoreNumProgressivi getIstance() {
		return unico;
	}
	public synchronized String numProgressivoCorrente() {
		return String.valueOf(contatore);
	}
	public synchronized String nuovoNumProgressivo() {
		contatore++;
		return String.valueOf(contatore);
	}
}
```

## Realizzazione dei diagrammi delle attività

In un diagramma delle attività associato ad un’attività complessa (inclusa l’attività principale) vi sono varie **sottoattività** di diverso tipo:

- **Attività atomiche (task)** atte ad interagire con il diagramma delle classi
- **Segnali di ingresso/uscita**, che si rivolgono all’esterno del sistema realizzato
- **Segnali per la gestione degli eventi**, segnali atti a gestire gli eventi degli oggetti dotati di diagramma di transizione
- altre **sottoattività complesse**

Tali attività sono organizzate secondo un **flusso di controllo**

### Attività atomiche (task)

I task sono le attività atomiche fondamentali, servono per accedere e modificare le classi Java che corrispondono alle classi e alle associazioni del diagramma delle classi

Per regolare l’accesso concorrente delle attività alle classi, non si può semplicemente aggiungere synchronized a tutti i metodi delle classi realizzati, altrimenti si rischierebbe un **deadlock** (stallo)

Si risolve il problema facendo uso del pattern funtore, inoltre:

Le attività atomiche che accedono al diagramma delle classi implementano **l’interfaccia Task**

```jsx
public interface Task {
	//utilizzata solo da TaskExecutor
	public void esegui();
}
```

I task sono eseguiti da un **esecutore specifico** che appartiene alla classe Singleton TaskExecutor

```jsx
public class TaskExecutor {
	private static TaskExecutor theTaskE = new TaskExecutor();
	private TaskExecutor();
	public syncronized static TaskExecutor getIstance() {
		return theTaskE;
	}
	//perform DEVE essere synchronized, per avere un accesso 
	//controllato agli oggetti del diagramma delle classi
	public synchronized void perform(Task t) {
		t.esegui();
	}
}
```

Aspetti fondamentali:

- le attività atomiche che accedono al diagramma delle classi implementano l’interfaccia Task che include il solo metodo **esegui(),** che è un metodo “pseudoprivato” perché può essere attivato solo attraverso **perform()** di TaskExecutor
- TaskExecutor è una classe che implementa il **pattern Singleton** e l’unica istanza di TaskExecutor è il solo esecutore effettivo dei task che esegue attraverso perform()
- il metodo perform() è synchronized, questo implica che se un’attivazione di perform() è in esecuzione in un thread, tutte le altre attivazioni di perform() su altri thread restano in attesa

Quindi **un solo Task** alla volta può essere in esecuzione e modificare il diagramma delle classi, in tal modo non si può corrompere il diagramma delle classi, che diventa **thread-safe**

*es. attività atomica*

```jsx
public class AperturaConto implements Task {
	private boolean eseguita = false;
	
	private Persona personaInput1;
	private Persona personaInput2;
	private Conto contoOutput;
	private int numOperazioni;
	private int saldo;
	
	public AperturaConto(Persona p1, Persona p2) {
		personaInput1 = p1;
		personaInput2 = p2;
		countoOutput = new Conto(getID(), 0);
	}
	public String getID() {
		return GeneratoreNumProgressivi.getInstance().nuovoNumProgressivo();
	}
	public synchronized void esegui() {
		if (eseguita)
			return;
		eseguita = true;
		
		personaInput1.inserisciLinkPossedere(new TipoLinkPossedere(personaInput1, contoOutput));
		personaInput2.inserisciLinkPossedere(new TipoLinkPossedere(personaInput2, contoOutput));
		numOperazioni = contoOutput.getNumOperazioni();
		saldo = contoOutput.getSaldo();
	}
	public synchronized Conto getConto() {
		if (!eseguita)
			throw new RuntimeException("Attività non ancora eseguita");
		return contoOutput;
	}
	public synchronized int getNumOperazioni() {
		if (!eseguita)
			throw new RuntimeException("Attività non ancora eseguita");
		return numOperazioni;
	}
	public synchronized int getSaldo() {
		if (!eseguita)
			throw new RuntimeException("Attività non ancora eseguita");
		return saldo;
	}
	public boolean estEseguita() {
		return eseguita;
	}
}
```

AperturaConto segue il pattern funtore implementando l’interfaccia Task, il corpo del funtore è il corpo di esegui()

Prende tre parametri di ingresso e non restituisce nulla, ma fa side-effect sul diagramma delle classi

Va utilizzata attraverso la singola istanza di TaskExecutor:

```jsx
TaskExecutor executor = TaskExecutor.getInstance();
...
AperturaConto ac = new AperturaConto(marito, moglie);
executor.perform(ac);
```

### Segnali di ingresso/uscita

Da realizzare come funzioni o funtori con opportuni esecutori

Si può raccogliere in un’unica classe un insieme di metodi statici che servono per i vari segnali di ingresso/uscita

*es. segnali di ingresso/uscita*

```jsx
public class SegnaliIO {
	public static RecordPersonaPersona acquisizioneDati() {
		RecordPersonaPersona result = new RecordPersonaPersona();
		result.persona1 = ...; //interfaccia con l'utente
		result.persona1 = ...;
		return result;
	}
	public static void aperturaOK() {
		System.out.println("Apertura conto avvenuta con successo");
	}
	public static void stampaOK() {
		System.out.println("Il conto non è in rosso");
	}
	public static void stampaKO() {
		System.out.println("Allarme: non ci sono più soldi nel conto");
	}
}
```

### Segnali per la gestione eventi

Cioè da inviare a tutti gli oggetti dotati di diagramma degli stati e delle transizioni

Da realizzare come funzioni o funtori con opportuni esecutori. Si assuma che gli oggetti dotati di diagramma degli stati e delle transizioni vengano eseguiti su thread separati dai thread delle attività e che le azioni associate alle transizioni avvengano attraverso il maccanismo dei task introdotto in precedenza, per garantire l’integrità dell’informazione

## Realizzazione

Le attività complesse e tutte le sue sottoattività complesse verranno realizzate:

- il funtore implementa **Runnable**
- il codice da eseguire corrispondente al flusso di controllo è nel corpo del metodo **run()**;
- l’esecutore del funtore è la classe **Thread** (start() chiama run())

In questo modo tutte le attività complesse verranno eseguite su thread separati, saranno attivate con un fork e terminate con join

Si assuma che ogni volta che il diagramma delle attività richiede un fork su più attività concorrenti, esse sono sempre complesse (anche se all’interno contengono una sola attività atomica)

### Costrutti sequenziali

Sono costrutti sequenziali le sequenze, le guardie, il nodo condizionale e il nodo merge, si userà: 

- sequenza di istruzioni
- `if else, while, do-while`

### Costrutti concorrenti

Si userà:

- `Thread.start()` per il fork
ciascun ramo di un fork è incapsulato in una sottoattività complessa
- `Thread.join()` per il join

*es. attività complessa*

```jsx
public class AttivitaPrincipale implements Runnable {
	private boolean eseguita = false;
	private TaskExecutor executor = TaskExecutor.getInstance(); 
	
	public synchronized void run() {
	if (eseguita == true)
		return;
	eseguita = true;
	
	Persona marito;
	Persona moglie;
	Conto conto;
	int numOperazioni;
	int saldo
	
	//operazione IO
	RecordPersonaPersona ris = SegnaliIO.acquisizioneDati();
	marito = ris.persona1;
	moglie = ris.persona2;
	
	//task
	AperturaConto ac = new AperturaConto(marito, moglie);
	executor.perform(ac);
	conto = ac.getConto();
	saldo = ac.getSaldo();
	
	while (numOperazioni < 15) {
		//attività complessa - Primo ramo
		Threas ramo1 = new Thread(new AttivitaSottoramo1(moglie, conto));
		ramo1.start();
	
	//attività complessa - Secondo ramo
		Threas ramo2 = new Thread(new AttivitaSottoramo1(marito, conto));
		ramo2.start();
		
		try {
			ramo1.join();
			ramo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Verifica v = new Verifica(conto);
		executor.perform(v);
		numOperazioni = v.getNumOperazioni();
		saldo = v.getSaldo();
	}
		if (saldo > 0)
			SegnaliIO.stampaOK();
		else
			SegnaliIO.stampaKO();
	}
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
```

**Commenti:**

- **AttivitàPrincipale** realizza il pattern funtore e ha come esecutore la classe Thread
- Il corpo di **run()** contiene all’interno l’intero processo associato all’attività complessa
- Vengono definite tre **variabili per l’attività**: moglie, marito, conto
- Il flusso di controllo è inizialmente sequenziale
    - Legge i dati, restituisce un record PersonaPersona
    - Apre il conto con il task AperturaConto
- Nel corpo del while c’è un **fork** con **due sottoattività complesse** concorrenti e infine il **join** delle stesse
- All’uscita del while troviamo un task per recuperare il saldo e un if-else per selezionare il giusto segnale di uscita

*es. Attività complessa: AttivitaSottoramo1*

```jsx
public class AttivitaSottoramo1 implements Runnable {
	private boolean eseguita = false;
	private TaskExecutor executor = TaskExecutor.getInstance();
	
	private Persona persona;
	private Conto conto;
	
	public AttivitaSottoramo1 (Persona p, Conto c) {
		persona = p;
		conto = c;
	}
	public synchronized void run() {
		if (eseguita == true)
			return;
		eseguita = true;
		executor.perform(new Depositare(persona, conto));
		executor.perform(new Prelevare(persona, conto));
	}
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
```

*es. Attività complessa: AttivitaSottoramo2*

```jsx
public class AttivitaSottoramo2 implements Runnable {
	private boolean eseguita = false;
	private TaskExecutor executor = TaskExecutor.getInstance();
	
	private Persona persona;
	private Conto conto;
	
	public AttivitaSottoramo2 (Persona p, Conto c) {
		persona = p;
		conto = c;
	}
	public synchronized void run() {
		if (eseguita == true)
			return;
		eseguita = true;
		executor.perform(new Prelevare(persona, conto));
	}
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
```

*es. Task: AperturaConto*

```jsx
public class AperturaConto implements Task {
	private boolean eseguita = false;
	
	private Persona personaInput1;
	private Persona personaInput2;
	private Conto contoOutput;
	private int numOperazioni;
	private int saldo;
	
	public AperturaConto(Persona p1, Persona p2) {
	personaInput1 = p1;
	personaInput2 = p2;
	contoOutput = new Conto(getID(), 0);
	}
	private String get ID() {
		return GeneratoreNumProgressivi.getInstance().nuovoNumProgressivo();
	}
	public synchronized void esegui() {
		if (eseguita == true) return;
		eseguita = true;
		
		personaInput1.inserisciLinkPossedere(new TipoLinkPossedere(personaInput1, contoOutput));
		personaInput2.inserisciLinkPossedere(new TipoLinkPossedere(personaInput2, contoOutput));
		numOperazioni = contoOutput.getNumOperazioni();
		saldo = contoOutput.getSaldo();
	}
	public synchronized Conto getConto() {
		if (!eseguita) 
			throw new RuntimeException("Attività non ancora eseguita");
		return contoOutput;
	}
	public synchronized int getNumOperazioni() {
		if (!eseguita) 
			throw new RuntimeException("Attività non ancora eseguita");
		return numOperazioni;
	}
	public synchronized int getSaldo() {
		if (!eseguita) 
			throw new RuntimeException("Attività non ancora eseguita");
		return saldo;
	}
	public boolean estEseguita() {
		return eseguita;
	}
}
```

*es. Task: Depositare*

```jsx
public class Depositare implements Task {
	private boolean eseguita = false;
	private Persona persona;
	private Conto conto;
	
	public Depositare(Persona p, Conto c) {
		persona = p;
		conto = c;
	}
	public synchronized void esegui() {
		if (eseguita == true) return;
		eseguita = true;
		
		conto.aumenta(persona, (int) (Math.random() * 900));
	}
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
```

*es. Task: Prelevare*

```jsx
public class Prelevare implements Task {
	private booolean eseguita = false;
	private Persona persona;
	private Conto conto;
	
	public Prelevare(Persona p, Conto c) {
		persona = p;
		conto = c;
	}
	public synchronized void esegui() {
	if (eseguita == true) return;
	eseguita = true;
	
	conto.diminuisci(persona, (int)(Math.random() * 1000));
	}
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
```

*es. Task: Verifica*

```jsx
public class Verifica implements Task {
	private boolean eseguita = false;
	private Conto conto;
	private int saldo;
	private int numOperazioni;
	
	public Verifica(Conto c) {
		conto = c;
	}
	public synchronized void esegui() {
		if (eseguita == true) return;
		eseguita = true;
		saldo = conto.getSaldo();
		numOperazioni = conto.getNumOperazioni();
	}
	public synchronized int getSaldo() {
		if (!eseguita)
			throw new RuntimeException("Attività non ancora eseguita");
		return saldo;
	}
	public synchronized int getNumOperazioni() {
		if (!eseguita)
				throw new RuntimeException("Attività non ancora eseguita");
			return numOperazioni;
	}
	public synchronized boolean estEseguita() {
		return eseguita;
	}
}
```