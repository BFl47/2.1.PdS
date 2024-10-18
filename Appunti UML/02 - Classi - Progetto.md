# Classi - Progetto

La fase di progetto ha come obiettivo definire l’architettura del programma e scegliere le strutture di rappresentazione

- **Input:** output della fase di analisi, cioè:
diagramma delle classi e degli oggetti;
diagramma delle attività;
diagramma degli stati e delle transizioni
- **Output:** input della fase di realizzazione, cioè:
****specifica (formale) delle operazioni;
scelta delle classi UML che hanno **responsabilità** sulle associazioni:
scelta delle **strutture di dati;**
scelta della corrispondenza fra **tipi** UML e Java;
…

### Responsabilità sulle associazioni

Una classe C ha responsabilità sull’associazione A se è possibile, dato un oggetto x di C:
**conoscere** l’istanza di A alle quali x partecipa;
**aggiungere** una nuova istanza
**cancellare** un’istanza
**aggiornare** il valore di un attributo 

**Criteri:**

- requisiti (R)
nel documento vi è una richiesta esplicita di “conoscere, aggiungere, cancellare, aggiornare”
- operazioni (O)
esistono delle operazioni che richiedono di conoscere il verso di navigabilità dell’associazione
- molteplicità (M)
solo 0..* non richiede responsabilità, gli altri casi implicano la responsabilità
in particolare se vi è: molteplicità massima finita o molteplicità minima diversa da 0

**Tabella delle responsabilità**

*es. Data ScuolaElem interessa conoscere Provveditorato? 
  se SI, perché?*

| Associazione | Classe | Responsabilità |
| --- | --- | --- |
| insegna | Classe | O M |
|  | Insegnante | R O M |
| dipendente | ScuolaElem | - |
|  | LavoratoreScol | R M |
| appartiene | ScuolaElem | R M |
|  | Provveditorato | - |

Criterio di verifica: per ogni associazione deve esserci almeno un “SI”

### Scelta delle strutture di dati

Se emerge la necessità di rappresentare **collezioni omogenee** di oggetti, si utilizza il collection framework di java, attraverso l’uso di generics:

```jsx
Set<Elem>, HashSet<Elem>, ...
List<Elem>, LinkedList<Elem>,..
```

### Corrispondenza tipi UML Java

Ogni volta che c’è una chiara corrispondenza con il tipo UML, bisogna scegliere l’opportuno tipo di base Java (int, float,…) o classe di Java (String, …)

**Tabella di corrispondenza dei tipi**

| **Tipo** UML | **Rappresentazione** in Java |
| --- | --- |
| intero | int |
| interoPositivo | int |
| 1..8 | int |
| reale | double |
| stringa | String |
| Insieme | Set |

**Considerazioni**

- Se il tipo UML è necessario per un attributo di classe con una sua molteplicità:
*es. NumeroTelefonico: Stringa {0..*}*
si sceglie di rappresentare l’attributo mediante **collezioni omogenee**, come Set
*es. HashSet<Integer> s = new HashSet<Integer>();*
- Se non esiste in Java un tipo base o una classe che corrisponda al tipo dell’attributo UML:
si realizzano classi UML con:
`toString()`: si può prevedere di farne overriding
`equals()`: è **necessario** fare overriding 
`hashCode()`: è **necessario** fare overriding (devono avere lo stesso hashCode)
`clone()`: se gli oggetti sono immutabili (non si effettua side-effect) non è necessario ridefinirlo; ma se qualche funzione effettua side-effect è **necessario** fare overriding

*es. classe Java Data*

```jsx
public class Data implements Cloneable {
//serve la ridifenizione di clone, poichè una funzione fa side-effect
	public Data(int a, int me, int g) {
		giorno = g;
		mese = me;
		anno = a;
	}
	public int giorno() {
		return giorno;
	}
	public int mese() {
		return mese;
	}
	public int anno() {
		return anno;
	}
	public void avanzaUnGiorno() {
	//fa side-effect
	//...
	}
	public String toString() {
		return giorno + "/" + mese + "/" + anno;
	}
	public Object clone() {
		try {
			Data d = (Data)super.clone();
			return d;
		} catch(CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}
	public boolean equals(Object o) {
		if (o != null && get.Class().equals(o.getClass())) {
			Data d = (Data)o;
			return d.giorno == giorno && d.mese == mese && d.anno == anno;
		}
		else return false;
	}
	public int hashCode() {
		return giorno + mese + anno:
	}
	
	//campi dati
	private int giorno, mese, anno;
```

Si può realizzare avanzaUnGiorno anche senza fare side-effect sull’oggetto di invocazione, tramite metodo funzionale, in tal caso:

```jsx
public class DataFunzionale {
//...
	public static DataFunzionale unGiornoDopo(DataFunzionale d) {
		//non fa side effect
		DataFunzionale ris = new DataFunzionale(d.anno, d.mese, d.giorno);
		//...
		return ris;
	}
```

### Gestione delle precondizioni

Un’operazione potrebbe avere delle precondizioni, come ad esempio l’operazione NumeroMedioAlunniPerDocente ha la precondizione che il suo argomento non sia l’insieme vuoto

**Verifica nel lato client**
Il cliente ha bisogno di un certo grado di conoscenza dei meccanismi di funzionamento della classe, il controllo verrà duplicato in ognuno dei clienti

**Verifica nel lato server**
Un altro approccio prevede che sia la classe stessa a occuparsi della verifica delle precondizioni, le funzioni quindi lanceranno un’eccezione qualora le condizioni non siano rispettate

*es.*

```jsx
public class EccezionePrecondizioni extends Exception {
	****private String messaggio;
	public EccezionePrecondizioni(String m) {
		messaggio = m;
	}
	public EccezionePrecondizioni() {
		messaggio = "Si è verificata una violazione delle precondizioni";
	}
	public String toString() {
		return messaggio;
	}
}

public class Amministrativo {
	public int livello;
	public Amministrativo(int l) throws EccezionePrecondizioni {
		if (l < 1 || l > 8) //controllo precondizioni
			throw new EccezionePrecondizioni("livello deve essere compreso tra 1 e 8");
		livello = l;
	}
	public int getLivello() {return livello;}
	public void setLivello(int l) throws EccezionePrecondizioni {
		if (l < 1 || l > 8) //controllo precondizioni
					throw new EccezionePrecondizioni("livello deve essere compreso tra 1 e 8");
				livello = l;
	}
	public String toString() {
		return "livello = " + livello;
	}
}	
```

### Gestione delle proprietà di classi UML

Distinzione: 

proprietà **singole** (attributi e associazioni con molteplicità 1..1)
proprietà **multiple**

Assunzioni di default:

tutte le proprietà sono **mutabili**;
****le proprietà singole sono **note alla nascita**;
le proprietà multiple **non sono note alla nascita**

Ovviamente si possono effettuare anche scelte differenti da quelle di default, ad esempio è possibile anche avere proprietà **immutabili**

### Api delle classi Java progettate

- **costruttori**
- funzioni per la **gestione degli attributi**
- funzioni per la **gestione delle associazioni**
- funzioni corrispondenti alle **operazioni di classe**
- funzioni per la **gestione degli eventi**
- funzioni di **servizio** (es. stampa)

*es. classe Java ScuolaElementare*

```jsx
public class ScuolaElementare {
//costruttori
	public ScuolaElementare (String nome, String indirizzo, Provveditorato appartiene) {};
//gestione attributi
	public String getNome() {return null;}
	public String getIndirizzo() {return null;}
	public void setIndirizzo(String i) {}
	public void setNome(String n) {}
//gestione associazioni
	public Provveditorato getProvveditorato() {return null;}
//stampa
	public String toString() {return null;}
}
```

---