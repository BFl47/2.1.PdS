# Stati e transizioni - Realizzazione

## Implementazione sequenziale

Oltre a tutte le proprietà viste in precedenza, ci si dovrà occupare dell’aspetto reattivo di un oggetto, come modellato dal diagramma stati e transizioni, facendo in modo che gli oggetti reattivi possano scambiarsi eventi, utilizzando il pattern **Observable-Observer** dove l’Observable è un oggetto di supporto chiamato **Environment**

Gli oggetti reattivi implementeranno tutti **l’interfaccia Listener**

### Realizzazione degli eventi

Gli eventi sono rappresentati da oggetti di una classe Java Evento, che rappresenta eventi generici, dotati di mittente e destinatario

- quando il **destinatario = null**, l’evento è in **broadcasting**
- quando il **mittente = null**, il mittente resta nascosto, in quanto non si è dichiarato

Tutti gli eventi sono istanze di classi **derivate da Evento**

Dal punto di vista tecnico la classe Evento e le sue derivate rappresentano valori (gli eventi/i messaggi scambiati), dal punto di vista realizzativo sono **record** che contengono riferimenti al **mittente** e al **destinatario** più eventuali **parametri** (nelle classi derivate)

Gli eventi sono oggetti Java **immutabili** i cui metodi pubblici **non** fanno side-effect

**Elementi:**

- **costruttore**
- funzioni **get** per restituire le informazioni
- **equals** e **hashCode** ridefinite, in modo che oggetti con le stesse informazioni risultino uguali
- **non** si ridefinisce **clone()** poiché sono oggetti immutabili e possono avere condivisione di memoria

La classe Evento

```jsx
public class Evento {
	private Listener mittente;
	private Listener destinatario;
	
	public Evento(Listener m, Listener d) {
		mittente = m;      //null se non rilevante
		destinatario = d;  //null se in broadcasting
	}
	public Listener getMittente() {return mittente;}
	public Listener getDestinatario() {return destinatario;}
	
	public boolean equals(Object o) {
		if (o != null && getClass().equals(o.getClass())) {
			Evento e = (Evento)o;
			return mittente == e.mittente && destinatario == e.destinatario;
		}
		else return false;
	}
	public int hashCode() {return mittente.hashCode() + destinatario.hashCode();}
}
```

**Ridefinizione di Evento**

```jsx
public class MioEvento extends Evento {
	private Object info;
	
	public MioEvento(Listener m, Listener d, Object i) {
		super(m, d);
		if (i == null) 
			throw RuntimeException("Playload del messaggio mancante");
		info = i;
	}
	public Object getInfo() {return info;}
	public boolean equals(Object o) {
		if (super.equals(o)) {
			MioEvento e = (MioEvento) o;
			return info == e.info;
		}
		else return false;
	}
	public int hashCode() {return super.hashCode() + info.hashCode();}
}
```

### Realizzazione degli stati

Per rappresentare lo stato di un oggetto reattivo si può usare una **enumerazione Java**, per costruire una costante per ogni stato del diagramma associandovi un tipo

- Per rappresentare lo **stato corrente**, si avrà una specifica **variabile di stato** (di tipo enum)
- Se sarà necessario si farà uso di **variabili di stato ausiliarie,** per rappresentare informazioni necessarie alle transizioni che non sono già rappresentate nei campi dato dell’oggetto corrispondenti agli attributi del diagramma delle classi

```jsx
public class MioOggettoConStato implements Listener {
	...
	
	//Gestione dello stato
	public static enum Stato{STATO_0, STATO_1,..., STATO_n}
	private Stato statocorrente = Stato.STATO_0;
	private Object varAux = null; 
	public Stato getStato() {
		return statocorrente;
	}
	
	//Gestione delle transizioni
	...
}
```

### Realizzazione delle transizioni

La gestione delle transizione avviene tramite la funzione **fired()**, che prende come parametro l’**evento scatenante** e restituisce il **nuovo evento** lanciato dall’azione della transizione oppure **null** in caso l’azione non lanci eventi

L’interfaccia Listener prevede solo la funzione fired()

Ogni oggetto reattivo implementa Listener

*es. Implementazione sequenziale (ogni oggetto reattivo può mandare massimo un evento ad ogni passo)*

```jsx
public interface Listener {
	public Evento fired(Evento e);
}
```

```jsx
public class OggettoConStato implements Listener {
	...
	
	//Gestione delle transizioni
	public Evento fired(Evento e) {
		if (e.getDestinatario() != this && e.getDestinatario() != null)
			return null;
		Evento nuovoevento = null;
		switch(statocorrente) {
		...
		case Stato.STATO_i:
			if (e.getClass() == EventoRilevante.class)
				if (Cond_ij) {
					//azioni con l'evento: eventualemnte con (EventoRilevate)e.getArgs()
					nuovoevento = new MioEvento(this, destinatario, info) //se la transizione non genera eventi, nuovoevento = null
					statocorrente = Stato.STATO_j;
				}
			break;
		...
		default: throw new RuntimeException("stato corrente non riconosciuto");
		}
		return nuovoevento;	
	}
}
```

**Note:** il corpo della funzione fired() è costituito da un **case** sullo stato corrente che definisce come si risponde all’evento passato come parametro di ingresso

![statoi_j.jpg](Stati%20e%20transizioni%20-%20Realizzazione%2029e1f27a2ddf4ea6b01e11362169ef78/statoi_j.jpg)

### Realizzazione dell’Environment sequenziale

**L’Environment** (l’observable) lancia ad ogni turno un evento per ciascun **Listener** (l’observer), garantendo che l’ordine di inoltro dei messaggia sia l’ordine di arrivo. 

È perciò dotato di una **coda di eventi** separata per ciascun Listener:

- avere una struttura dati separata per ciascun Listener garantisce la gestione indipendente degli stessi
- il fatto che la struttura sia una coda garantisce l’ordinamento giusto dei messaggi

**Struttura dati Environment**

Un **Map** (o dizionario) con:
- **Chiave**: il listener
- **Informazione**: la coda degli eventi indirizzati al listener

Quindi associa ad ogni Listener registrato una coda di eventi specifica

```jsx
public class Environment {
	private HashMap<Listener, LinkedList<Evento>> codeEventiDeiListener;
	
	public Environment() {
		codeEventiDeiListener = new HashMap<Listener, LinkedList<Evento>>();
	}
	//funzione per registrare listener
	public void addListener(Listener l) {
		codeEventiDeiListener.put(l, new LinkedList<Evento>());
	}
	public void aggiungiEvento(Evento e) {
		//aggiugne evento e nella coda del destinatario di e
		Listener destinatario = e.getDestinatario();
		if (destinatario != null)                            //destinatario specifico
			codeEventiDeiListener.get(destinatario).add(e);
		else {                                               //messaggio in broadcasting
			Iterator<Listener> it = codeEventiDeiListener.keySet().iterato();
			while (it.hasNext()) {
				Listener l = it.next();
				codeEventiDeiListener.get(l).add(e);
			}
		}
	}
	public void eseguiEnvironment() {
		boolean eventoProcessato;
		do {
			//finchè c'è almeno un evento da processare in una delle code
			//prende tutti i primi elementi e li manda ai rispettivi Listener
			evento Processato = false;
			
			//codice corrispondente al fireAll() dell'observable
			Iterator<Listener> it = codeEventiDeiListener.keySet().iterator();
			while (it.hasNext()) {
				Listener l = it.next();
				LinkedList<Evento> coda = codeEventiDeiListener.get(l);
				if (coda.isEmpty())
					continue;
				Evento e = coda.remove(0);
				eventoProcessato = true;
		
				Evento ne = listed.fired(e);  //chiama l'esecuzione del listener
				if (ne == null)
					continue;
				aggiungiEvento(ne);           //aggiunge evento nella coda del destinatario
			}
		//fine fireAll()
		} while (eventoProcessato);
	}
}
```

## Implementazione concorrente

In presenza di attività concorrenti, tutto ciò che si è visto in precedenza continua ad essere valido (rappresentazione degli attributi, partecipazione ad associazioni, responsabilità sulle associazioni, …)

Inoltre ci si dovrà occupare del suo aspetto reattivo come modellato dal diagramma degli stati e delle transizioni. 

Si andrà ad associare a ciascun oggetto reattivo un **thread** separato per la gestione degli eventi, quindi nell’associazione conviveranno thread dedicati alle **attività del diagramma delle attività** e thread dedicati alla **gestione degli eventi**

- La realizzazione di **Eventi** e **Stati** è identica al caso visto con implementazione sequenziale
- La realizzazione delle **Transizioni** sarà diversa per sfruttare la concorrenze
- La realizzazione dell’**Environment** sarà completamente concorrente:
    - Ogni oggetto reattivo esegue il suo diagramma stati transizioni su un thread separato
    - L’Environment lavora su un thread separato ed è dotato di una struttura dati condivisa thread-safe per lo scambio di eventi

Gli eventi generati saranno inseriti direttamente nella struttura dati thread-safe dell’Environment concorrente

*es. Implementazione concorrente* 

```jsx
public interface Listener {
	public void fired(Evento e);
}
```

```jsx
public class OggettoConStato implements Listener {
	...

	//Gestione delle transizioni
	public void fired(Evento e) {
		...
	}
}
```

**Nota:** ogni oggetto che implementa l’intefaccia Listener (dichiarando implicitamente di essere un oggetto attivo) sarà analogo al caso precedente, tranne per la funzione **fired()**

*es. classe Giocatore che implementa interfaccia Listener*

```jsx
public class Giocatore implements Listener {
	//gestione stato
	public static enum Stato {ALLENAMENTO, INGIOCO, FINEGIOCO}
	Stato statocorrente = Stato.ALLENAMENTO;   //visibilità package
	double trattopercorso;                     //visibilità package
	public Stato getStato() {return statocorrente;}
	
	//gestione transizioni
	public void fired(Evento e) {
		TaskExcetutor.getInstance().perform(new GiocatoreFired(this, e));
	}
```

La **funzione fired()** lavora con il diagramma delle classi condiviso da tutti i thread, diventa, quindi, analogo ad un Task dei diagrammi delle attività

- non restituisce eventi
- prende come parametro l’**evento scatenante**
- il corpo della funzione deve essere eseguito concorrentemente agli altri thread, quindi è costituito da una **chiamata** all’esecuzione di un **funtore di tipo Task**
Cioè delega la gestione delle transizioni ad un Funtore; infatti, fired() invocherà sull’istanza singleton di TaskExecutor l’esecuzione di un funtore, che conterrà tutto ciò che prima (nel caso sequenziale) era nel corpo del fired()

Il **funtore** che realizza fired()

- associato ad una classe NomeClasse avrà come nome NomeClasseFired
- **non è pubblico** e sarà accessibile solo dall’oggetto reattivo corrispondente (si usa la **visibilità di Package**). Verrà infatti messo nel package della classe (che invece è pubblica)
- il corpo di **esegui()** fa quello che nel caso sequenziale da direttamente fired(), quindi è costituito da un case sullo stato corrente che definisce come si risponde all’evento in ingresso:
    - controlla la **rilevanza** dell’evento
    - controlla la **condizione** che seleziona la transizione
    - prende gli **eventuali parametri** dell’evento
    - fa eventuali **side-effect** sulle proprietà (campi dati) dell’oggetto
    - crea il nuovo evento da mandare e lo inserisce nell’Environment con l’istruzione:
    **Environment.aggiungiEvento(new Evento(…));**
    

La classe GiocatoreFired

```jsx
public GiocatoreFired implements Task {
	private boolean eseguita = false;
	private Giocatore g;
	private Evento e;
	
	public GiocatoreFired(Giocatore g, Evento e) {
		this.g = g;
		this.e = e;
	}
	public synchronized void esegui() {
		if (eseguita || (e.getDestinatario() != g && e.getDestinatario() != null))
			return;
		eseguita = true;
		switch(g.getStato()) {
		...
		case INGIOCO:
			if (e.getClass() == Bastone.class)
				if (g.trattopercorso < 100) {
					...
					Environment.aggiungiEvento(new Bastone(g, g));
					g.statocorrente = Stato.INGIOCO;
				}
				else {...}
				break;
		...
		default: throw new RuntimeException("Stato corrente non riconosciuto");
		}
	}
	public synchronized boolean estEseguita() {return eseguita;}
}
```

### Realizzazione dell’Environment concorrente

Si utilizza il **pattern observable-observer**, dove l’observable è un Environment e gli observer sono Listener

L’Environment è diviso in due classi:

- una classe **Environment** che conterrà una struttura dati thread-safe formata da una **coda bloccante** per ciascun Listener, dove memorizzare gli eventi che arrivano e sono in attesa di essere processati
- una classe **EsecuzioneEnvironment** che si occuperà degli aspetti dinamici dell’environment, cioè inizializzazione, attivazione e spegnimento.

Entrambe le classi potranno essere realizzate con il **pattern singleton**, poiché si dovranno realizzare un unico oggetto Environment e un unico oggetto EsecuzioneEnviroment; 
oppure come classi con tutti i **metodi static**

**Struttura dati Environment**

Una **ConcurrentMap** (o dizionario thread-safe) con:
- **Chiave**: il listener
- **Informazione**: la coda **bloccante** degli eventi indirizzati al listener

```jsx
public final class Environment {      //non si possono definire sottoclassi
	private Environment() {}            //non si possono costruire oggetti Environment
	private static ConcurrentHashMap<Listener, LinkedBlockingQueue<Evento>> codeEventiDeiListener = 
			new ConcurrentHashMap<Listener, LinkedBlockingQueue<Evento>>();
	
	//funzione per registrare listener
	public static void addListener(Listener l, EsecuzioneEnvironment e) {
		if (e == null)
			return
		codeEventiDeiListener.put(l, new LinkedBlockingQueue<Evento>());
	}
	public static Set<Listener> getInsiemeListener() {
		return codeEventiDeiListener.keySet();
	}
	public static void aggiungiEvento(Evento e) {
		//unico meccanismo per aggiungere eventi
		if (e == null) return;
		Listener destinatario = e.getDestinatario();
		//evento per un destinatario attivo
		if (destinatario != null && codeEventiDeiListener.containsKey(destinatario)) {
			try {                            
				codeEventiDeiListener.get(destinatario).put(e);
			} catch(InterruptedException e1) {
					e1.printStackTrace();
			}
		//evento in broadcasting
		} else if (destinatario == null) {                                               
			Iterator<Listener> it = codeEventiDeiListener.keySet().iterator();
			while (it.hasNext()) {
				Listener l = it.next();
				try {
					codeEventiDeiListener.get(l).put(e);
				} catch (InterruptedException e1) {
						e1.printStackTrace();
				}
			}
		}
	}
	//non è synchronized
	public static Evento prossimoEvento(Listener l) throws InterruptedException {
		return codeEventiDeiListener.get(l).take();
	}
}
```

**Note:** aggiungiEvento ha la stessa logica del caso sequenziale, ma ora utilizza code bloccanti e concurrent map che permettono l’accesso concorrente

**Classe EsecuzioneEnvironment**

La classe attiva un thread per ogni oggetto Listener, ha quindi una struttura dati che associa ad ogni Listener un oggetto Thread corrispondente al proprio thread:

**private static ConcurrentHashMap<Listener, Thread> listenerAttivi**, manipolata dalle funzioni per **aggiungere** Listener, **far partire** tutti i Listener e **fermare** tutti i Listener:

- **addListener()** che aggiunge una coppia Listener, Thread
- **attivaListener()** che manda in esecuzione ciascun Listener, invocando start() sul Thread associato
- **disattivaListener()** che:
    - **avvelena** ciascun thread mettendo in coda agli eventi di ciascun Listener un evento speciale stop
    - **aspetta** la fine del thread di ciascun Listener, con join()

Queste funzioni possono essere usate solo in certi stati, per limitare le invocazioni ai metodi e per non scambiare eventi

![stati e_environment.jpg](Stati%20e%20transizioni%20-%20Realizzazione%2029e1f27a2ddf4ea6b01e11362169ef78/stati_e_environment.jpg)

**La classe EsecuzioneListener** 

- implementa il **patter Funtore**, come le attività complesse del diagramma delle attività, con:
    - Thread come **esecutore**
    - EsecuzioneListener come **eseguibile**
- prende il **prossimo evento** dalla coda associata al suo Listener e lancia il fired() associato ad esso
- tratta in modo speciale l’evento **Stop,** poiché non lo passa al fired del suo listener, ma lo processa direttamente