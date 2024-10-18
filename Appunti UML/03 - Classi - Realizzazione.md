# Classi - Realizzazione

La fase di progetto ha come obiettivo: scrivere il **codice** del programma e produrre parte della **documentazione**

- **Input:** output della fase di analisi e output della fase di progetto

## Realizzazione di una classe con solo attributi

Si assuma che le molteplicità di tutti gli attributi si 1..1

Gli attributi diventano campi privati (o protetti), gestiti da:

- funzione **get** per restituire il valore dell’attributo
- funzione **set** per cambiare il valore dell’attributo (per i campi che possono mutare)

Si sceglie l’opportuno valore iniziale:

- con il valore di **default** di Java
- fissando il valore nella **dichiarazione**
- tramite **costruttore**

Funzioni speciali:

- **equals()**: non è necessario fare overriding, poiché due entità sono uguali solo se sono la stessa entità
- **clone():** in generale non è necessario, a meno che ogni oggetto non debba essere utilizzato singolarmente e direttamente
- **toString():** si può prevedere di farne overriding

*es. classe Persona*

| Persona |
| --- |
| Nome: stringa |
| Cognome: stringa |
| Nascita: data |
| Coniugato: boolean |

| **Tipo** UML | **Rappresentazione** in Java |
| --- | --- |
| stringa | String |
| data | int, int, int |
| booleano | boolean |

Con nome, cognome e nascita proprietà immutabili\
Per default, una persona non è coniugata

```jsx
public class Persona {
	private String nome, cognome;
	private int giorno_nascita, mese_nascita, anno_nascita;
	private boolean coniugato;
	public Persona(String n, String c, int g, int m, int a) {
		nome = n;
		cognome = c;
		giorno_nascita = g;
		mese_nascita = m;
		anno_nascita = a;
	}
	public String getNome() {return nome;}
	public String getCognome() {return cognome;}
	public int getGiornoNascita() {return giorno_nascita;}
	public int getMeseNascita() {return mese_nascita;}
	public int getAnnoNascita() {return anno_nascita;}
	public void set Coniugato(boolean c) {
		coniugato = c;
	}
	public boolean getConiugato() {
		return coniugato;
	}
	public String toString() {
		return nome + " " + cognome + "  " + giorno_nascita + "/" + mese_nascita + "/" +
					anno_nascita + " " + (coniugato?"coniugato":"celibe");
	}
}
```

### Verifica di valori non ammessi (lato server)

*es. attributo Età: 0..120*

```jsx
public class Persona {
	private int eta;
	public Persona(int e) throw EccezionePrecondizioni {
		if (e < 0 || e > 120)
			throw new EccezionePrecondizioni("L'età deve essere compresa tra 0 e 120");
		eta = e;
	}
	public int getEta() {return eta;}
	public int setEta(int e) throws EccezionePrecondizioni {
		if (e < 0 || e > 120)
			throw new EccezionePrecondizini();
		eta = e;
	}
	public String toString() {
		return ...;
	}
}
```

Se in fase di analisi viene data la seguente specifica:

**InizioSpecificaOperazioni Analisi Statistica**

&nbsp;&nbsp;&nbsp;&nbsp;**QuantiConiugati** (i: Insieme(Persona)): intero\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: *result* è il numero di coniugati nell’insieme di persone i

**FineSpecifica**

```jsx
public class AnalisiStatistica {
	public static int quantiConiugati(Set<Persona> i) {
	int ris = 0;
	Iterator<Persona> it = i.iterator();
	while (it.hasNext()) {
		Persona elem = it.next();
		if (elem.getConiugato))
			ris++;
	}
	return ris;
	private AnalisiStatistica() {}
}
```

### Molteplicità di attributi

Se la classe UML C ha attributi con una loro molteplicità si può usare per la loro rappresentazione HashSet<String>

- Va previsto un campo di tale classe, inizializzato con new() dal costruttore della classe Java C
- per la scrittura sono necessarie due funzioni:\
**inserimento** di elementi nell’insieme\
**cancellazione** degli stessi
- per la lettura è necessaria la funzione **get**

_es. numTel: stringa {0..*}_

| Persona |
| --- |
| nome: stringa |
| numTel: stringa {0..*} |

```jsx
public class Persona {
	private String nome;
	private HashSet<String> numTel;
	public Persona(String n) {
		numTel = new HashSet<String>();
		nome = n;
	}
	public String getNome() {return nome;}
	public void aggiungiNumTel(String n) {
		if (n!=null) 
			numTel.add(n);
	}
	public void eliminaNumTel(String n) {
		numTel.remove(n);
	}
	public Set<String> getNumTel() {
		return (HashSet<String>)numTel.clone();
	}
	public String toString() {
		return nome + " " + numTel;
	}
}
```

**Osservazioni:**

- La classe ha un **campo** di tipo HashSet
- Il **costruttore** di Persona crea un oggetto di tale classe
- **Funzioni** per gestire l’insieme:\
aggiungiNumTel(String)\
eliminaNumTel(String)\
getNumTel()
- La funzione **get** restituisce una **copia** dell’insieme

Queste considerazioni valgono ogni volta che un tipo UML viene realizzato mediante una classe Java i cui oggetti sono mutabili


Se in fase di analisi viene data la seguente specifica:

**InizioSpecificaOperazioni Gestione Rubrica**

&nbsp;&nbsp;&nbsp;&nbsp;**TuttiNumTel** (p1: Persona, p2: Persona): Insieme(stringa)\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: *result* è l’insieme unione dei numeri di telefono di p1 e p2

**FineSpecifica**

```jsx
public class GestioneRubrica {
	public static Set<String> tuttiNumTel (Persona p1, Persona p2) {
	Set<String> result = p1.getNumTel();
	Iterator<String> it = p2.getNumTel().iterator();
	while(it.hasNext())
		retult.add(it.next());
	return result;
	}
	private GestioneRubrica() {};
}
```

Se getNumTel() non restituisse una copia dell’insieme dei numeri di telefono, questa funzione farebbe un **side-effect indesiderato**

L’errore di progettazione che consente al cliente di distruggere le strutture di dati private di un oggetto si chiama **interferenza**


Se in fase di analisi viene data la seguente specifica:

**InizioSpecificaOperazioni Analisi Recapiti**

&nbsp;&nbsp;&nbsp;&nbsp;**Convivono** (p1: Persona, p2: Persona): booleano\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: *result* vale true se p1 e p2 hanno almeno un numero telefonico in comune, false altrimenti

**FineSpecifica**

Si potrebbe realizzare getNumTel() restituendo un iteratore dell’insieme dei numeri di telefono, con un minore utilizzo di memoria.\
Per eliminare la possibilità che i clienti facciano interferenza, bisogna restituire un iteratore realizzato tramite la classe IteratoreSolaLettura<T> che elimina remove() da Iterator.

**Schemi realizzativi**

|  | getNumTel() | Vantaggi | Svantaggi |
| --- | --- | --- | --- |
| Senza condivisione di memoria | restituisce copia profonda (clone()) | client più semplice | potenziale spreco di memoria |
| Con condivisione di memoria | restituisce IteratoreSolaLettura | client più complicato | risparmio di memoria |

## Realizzazione di classe con attributi e operazioni

- Per quanto riguarda gli **attributi**, si procede come nel caso precedente
- Ogni **operazione** viene realizzata da una funzione public della classe Java\
Se non si vuol rendere l’operazione disponibile ai clienti, sono possibili anche funzioni private o protected

*es. Persona con Reddito*

| Persona |
| --- |
| Nome: stringa |
| Cognome: stringa |
| Nascita: data |
| Coniugato: boolean |
| Reddito: intero |
| Aliquota(): intero |

**InizioSpecificaOperazioniClasse Persona**

&nbsp;&nbsp;&nbsp;&nbsp;**Aliquota** (): intero\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: result vale 0 se this.Reddito < 5001, vale 20 se this.Reddito compreso tra 5001 e 10000, vale 30 se this.Reddito è compreso tra 10001 e 30000, vale 40 se this.Reddito > 30000 

**FineSpecifica**

```jsx
public class Persona {
	private String nome, cognome;
	private int giorno_nascita, mese_nascita, anno_nascita;
	private boolean coniugato;
	private int reddito;
	public Persona(String n, String c, int g, int m, int a) {
		nome = n;
		cognome = c;
		giorno_nascita = g;
		mese_nascita = m;
		anno_nascita = a;
	}
	public String getNome() {return nome;}
	public String getCognome() {return cognome;}
	public int getGiornoNascita() {
		return giorno_nascita;
	}
	public int getMeseNascita() {
		return mese_nascita;
	}
	public int getAnnoNascita() {
		return anno_nascita;
	}
	public void setConiugato(boolean c) {
		coniugato = c;
	}
	public boolean getConiugato() {
		return coniugato;
	}
	public void setReddito(int r) {
		reddito = r;
	}
	public int getReddito() {return reddito;}
	public int aliquota() {
		if (reddito < 5001) 
			return 0;
		else if (reddito < 10001)
			return 20;
		else if (reddito < 30001)
			return 30;
		else return 40;
	}
	public String toString() {
		return nome + " " + cognome + ", " + giorno_nascita + "/" + mese_nascita + "/" +
			anno_nascita + ", " + (coniugato?"coniugato":"celibe") + ", aliquota fiscale: " +
			aliquota();
	}
}
```

## Realizzazione di associazioni

### Associazioni binarie, con molteplicità 0..1, a responsabilità singola, senza attributi

C ———A——— D\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0..1

La tabella delle responsabilità prodotta in fase di progetto ci indica che C è l’unica ad avere responsabilità sull’associazione A

In questo caso, la realizzazione è simile a quella per un attributo, per ogni associazione A, si aggiunge alla classe Java C:

- un **campo dato di tipo D private** (o protected) che rappresenta l’oggetto della classe D connesso tramite associazione
- una funzione **get** che consente di calcolare l’oggetto della classe D connesso 
(restituisce null se l’oggetto non partecipa a nessuna istanza di A)
- una funzione **set** che consente di stabilire che l’oggetto x della classe C è legato ad un oggetto y della classe D tramite l’associazione A
(restituisce null se non vi è legame)

*es. Persona e Azienda collegate da lavora-in*

| Persona |
| --- |
| Nome: stringa
Cognome: stringa
Nascita: data
Coniugato: boolean
Reddito: intero |
| Aliquota(): intero
Eta(): intero |

| Azienda |
| --- |
| RagioneSociale: stringa
PartitaIva: stringa
CapitaleSociale: intero |
| Dimensione(): stringa
Aumenta(intero x)
Diminuisci(intero x) |

**Persona** _____________ **Azienda**\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;lavora in →       0..1

Si assuma che: la ragione sociale e la partita Iva non cambiano e solo Persona ha responsabilità sull’associazione (cioè da Persona è possibile ricavare Azienda)

**InizioSpecificaOperazioniClasse Azienda**

&nbsp;&nbsp;&nbsp;&nbsp;**Dimensione** (): stringa\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: result vale “Piccola” se this.CapitaleSociale < 51, “Media” se this.CapitaleSociale è compreso tra 51 e 250, “Grande” se this.CapitaleSociale > 250

&nbsp;&nbsp;&nbsp;&nbsp;**Aumenta** (i: intero)\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: i > 0;\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: this.CapitaleSociale vale pre(this.CapitaleSociale) + i

&nbsp;&nbsp;&nbsp;&nbsp;**Diminuisci**(i: intero)\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: 1 ≤ i ≤ this.CapitaleSociale\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: this.CapitaleSociale vale pre(this.CapitaleSociale) - i

**FineSpecifica**

```jsx
public class Azienda {
	private String ragioneSociale, partitaIva;
	private int capitaleSociale;
	public Azienda(String r, String p) {
		ragioneSociale = r;
		partitaIva = p;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public int getCapitaleSociale() {
		return capitaleSociale;
	}
	public void aumenta(int i) {
		capitaleSociale += i;
	}
	public void diminuisci(int i) {
		capitaleSociale -=i
	}
	public String dimensione() {
		if (capitaleSociale < 51)
			return "Piccola";
		else if (capitaleSociale < 251) 
			return "Media";
		else return "Grande";
	}
	public String to String() {
		return ragioneSociale + ("PI: " + partitaIva + ", capitale sociale: " + 
				getCapitaleSociale() + ", tipo azienda: " + dimensione());
	}
}
```

```jsx
public class Persona {
//altri campi dati e funzioni
	private Azienda lavoraIn;
	public Azienda getLavoraIn() {
		return lavoraIn;
	}
	public void setLavoraIn(Azienda a) {
		lavoraIn = a;
	}
	public String toString() {
		return ... + (lavoraIn != null?", lavora presso la ditta " + lavoraIn: ", disoccupato");
	}
}
```

Ciò vale anche se l’associazione coinvolge più volte la stessa classe, in questo caso il concetto di responsabilità si attribuisce ai ruoli, piuttosto che alle classi

_es. Azienda ha responsabilità su associazione “holding” solo nel ruolo di controllata (e non controllante), quindi dato un oggetto x della classe Azienda, si vuole poter conoscere l’azienda controllante_

```jsx
public class Azienda {
	private String nome;
	private Azienda controllante; //il nome del campo è uguale al ruolo
	public Azienda(String n) {
		nome = n;
	}
	public Azienda getControllante() {
		return controllante;
	}
	public void setControllante(Azienda a) {
		controllante = a;
	}
	public String to String() {
		return nome + ((controllante == null)? "": " controllata da: "+ controllante);
	}
}
```

### Associazioni binarie, con molteplicità 0..*, a responsabilità singola, senza attributi

C ———A——— D\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0..*

Per rappresentare l’associazione A fra le classi UML C e D, c’è bisogno di una struttura di dati per rappresentare i link tra l’oggetto della classe C e più oggetti della classe D

In particolare, la classe Java C avrà:

- un **campo dati** di tipo opportuno (es HashSet)
- il **costruttore** della classe C crea un oggetto di tale classe (riferimento ad un insieme vuoto)
- dei **campi funzione** che permettano di gestire tale struttura di dati\
(funzioni get, inserisci, elimina)

*es. Persona haLavorato → Azienda*

| Persona |
| --- |
| Nome: stringa |

| Azienda |
| --- |
| RagioneSociale: stringa |

**Persona** __________________ **Azienda**\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ha lavorato in →      0..*

```jsx
public class Persona {
	private String nome;
	private HashSet<Azienda> insieme_link;
	public Persona(String n) {
		nome = n;
		insieme_link = new HashSet<Azienda>();
	}
	public String getNome() {return nome;}
	public void inserisciLinkHaLavorato(Azienda a) {
		if (a != null)
			insieme_link.add(a);
	}
	public void eliminaLinkHaLavorato(Azienda a) {
		if (a != null)
			insieme_link.remove(a);
	}
	public Set<Azienda> getLinkHaLavorato() {
		return (HashSet<Azienda>)insieme_link.clone();
	}
}
```

Le funzioni che permettono di gestire l’insieme sono:
- inserisciLinkHaLavorato(Azienda)
- eliminaLinkHaLavorato(Azienda)
- getLinkHaLavorato();

Schema realizzativo **senza condivisione di memoria**, la funzione getLinkHaLavorato() restituisce una **copia** della struttura di dati

Realizzazione analoga a quella degli attributi di classe con molteplicità 0..*; in questo caso, infatti, i link dell’associazione HaLavorato vengono gestiti solamente dalla classe Persona, cioè la classe con responsabilità

### Associazioni binarie, con molteplicità 0..1, a responsabilità singola, con attributi

C ———A——— D\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0..1

*es. solo Persona ha responsabilità sull’associazione*

| Persona |  | Azienda |
| --- | --- | --- |
| Nome: stringa | —————————→ 0..1 | RagioneSociale: stringa |
|  |     lavora |  |
|  | AnnoAssunzione: intero |  |

La presenza degli attributi sull’associazione impedisce di usare i riferimenti di Java per rappresentare i link UML, bisogna perciò rappresentare la nozione di link in modo esplicito attraverso una classe **TipoLinkA,** che ha lo scopo di rappresentare i link (in questo caso coppie) tra gli oggetti delle classi C e D

**Attenzione**: i link sono **valori**, non oggetti, in particolare, ci sarà un oggetto di classe TipoLinkA per ogni link

La classe **TipoLinkA** avrà i campi per rappresentare:

- gli **attributi** dell’associazione
- i riferimenti agli oggetti delle due classi che costituiscono le componenti della coppia che il link rappresenta\
(cioè delle variabili che contengono gli **identificatori** degli oggetti coivolti)

Inoltre avrà le seguenti funzioni:

- funzioni per la **gestione dei suoi campi dati**:\
costruttore (lancia eccezione se i riferimenti di tipo C e D sono null)\
funzioni **get**
- funzione **equals()** ridefinita in modo da verificare l’uguaglianza solo sugli oggetti collegati dal link, ignorando gli attributi
- funzione **hashCode()** ridefinita

**Non** avrà funzioni set, poichè i suoi oggetti sono **immutabili**, una volta creati non potranno essere cambiati

La classe Java C avrà:

- un **campo dato** di tipo **TipoLinkA,** per rappresentare l’eventuale link\
(se il vale null, vuol dire che l’oggetto di classe C non è associato ad un oggetto di classe D)
- dei **campi funzione** che permettono di gestire il link\
(get, inserisci, elimina)

```jsx
public class TipoLinkLavora {
	private Persona laPersona;
	private Azienda laAzienda;
	private int annoAssunzione;
	public TipoLinkLavora(Azienda x, Persona y, int a) throws EccezionePrecondizioni {
		if (x == null || y == null) 
			throw new EccezionePrecondizioni("Gli oggetti devono essere inizializzati");
		laAzienda = x;
		laPersona = y;
		annoAssunzione = a;
	}
	public boolean equals(Object o) {
		if (o != null && getClass().equals(o.getClass())) {
			TipoLinkLavora b = (TipoLinkLavora) o;
			return b.laPersona == laPersona && b.laAzienda == laAzienda;
		}
		else return false;
	}
	public int hashCode() {
		return laPersona.hashCode() + laAzienda.hashCode();
	}
	public Azienda getAzienda() {return laAzienda;}
	public Persona getPersona() {return laPersona;}
	public int getAnnoAssunzione() {return annoAssunzione;}
}
```

```jsx
public class Persona {
	private String nome;
	private TipoLinkLavora link;
	public Persona(String n) {nome = n;}
	public String getNome() {return nome;}
	public void inserisciLinkLavora(TipoLinkLavora t) {
		if (link == null && t != null && t.getPersona() == this)
			link = t;
	}
	public void eliminaLinkLavora() {
		link = null;
	}
	public TipoLinkLavora getLinkLavora() {return link;}
}
```

La funzione inserisciLinkLavora() della classe Persona deve assicurarsi che:

- la persona oggetto di invocazione non sia già associata a un link
- l’oggetto che rappresenta il link esiste
- la persona a cui si riferisce il link sia l’oggetto di invocazione

Per **cambiare** l’oggetto della classe Azienda a cui una persona è legata, è necessario invocare prima eliminaLinkLavoro() e poi inserisciLinkLavoro()

```jsx
public class EccezionePrecondizioni extends Exception {
	private String messaggio;
	public EccezionePrecondizioni(String m) {
		messaggio = m;
	}
	public EccezionePrecondizioni() {
		messaggio = "Si e’ verificata una violazione delle precondizioni";
	}
	public String toString() {
		return messaggio;
	}
}
```

*es. possibile main*

```jsx
Azienda az = new Azienda("WebSites");
Persona pe = new Persona("Mario"), pe2 = new Persona("Aldo");
try {
	t = new TipoLinkLavora(az, pe, 2002);
} catch (EccezionePrecondizioni e) {
	System.out.println(e);
}
pe.inserisciLinkLavora(t);
```

### Associazioni binarie, con molteplicità 0..*, a responsabilità singola, con attributi

C ———A——— D\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0..*

*es. solo la classe Persona ha responsabilità sull’associazione*

| Persona |  | Azienda |
| --- | --- | --- |
| Nome: stringa | —————————→ 0..* | RagioneSociale: stringa |
|  |     haLavorato |  |
|  | AnnoInizio: intero |  |
|  | AnnoFine: intero |  |

Bisogna definire un’apposita classe Java per la rappresentazione dei link (**TipoLinkHaLavorato**)\
Bisogna utilizzare una struttura di dati per la rappresentazione dei link.

La classe **TipoLinkHaLavorato** deve gestire:

- gli **attributi** dell’associazione (AnnoInizio, AnnoFine)
- i **riferimenti** agli oggetti (di classe Persona e Azienda) relativi al link

Pertanto avrà gli opportuni capi dati e funzioni (costruttori e get)\
Inoltre, avrà la funzione **equals** per verificare l’uguaglianza sugli oggetti, ignorando gli attributi e **hashCode** ridefinita

```jsx
public class TipoLinkHaLavorato {
	private Persona laPersona;
	private Azienda laAzienda;
	private int annoInizio, annoFine;
	public TipoLinkHaLavorato(Azianda x, Persona y, int ai, int af) throws EccezionePrecondizioni {
		if (x == null || y == null)
			throw new EccezionePrecondizioni ("Gli oggetti devono essere inizializzati");
		laAzienda = x;
		laPersona = y;
		annoInizio = ai;
		annoFine = af;
	}
	public boolean equals(Object o) {
		if (o != null && getClass().equals(o.getClass())) {
			TipoLinkHaLavorato b = (TipoLinkHaLavorato) o;
			return b.laPersona == laPersona && b.laAzienda == laAzienda;
		}
		else return false;
	}
	public int hashCode() {
		return laPersona.hashCode() + laAzienda.hashCode();
	}
	public Azienda getAzienda() {return laAzienda;}
	public Persona getPersona() {return laPersona;}
	public int getAnnoInizio() {return annoInizio;}
	public int getAnnoFine() {return annoFine;}
}
```

La classe Java Persona avrà:

- un **campo per la rappresentazione di tutti i link** relativi ad un oggetto della classe\
(si utilizza la classe Java Set)
- la funzione **inserisciLinkHaLavorato()** dovrà effettuare tutti i controlli necessari per mantenere la consistenza dei riferimenti
- la funzione **eliminaLinkHaLavorato()** dovrà assicurarsi che:\
l’oggetto che rappresenta il link esista\
la persona a cui si riferisce il link sia l’oggetto di invocazione
- la funzione **getLinkHaLavorato()**

```jsx
public class Persona {
	private String nome;
	private HashSet<TipoLinkHaLavorato> insieme_link;
	public Persona(String n) {
		nome = n;
		insieme_link = new HashSet<TipoLinkHaLavorato>();
	}
	public String getNome() {return nome;}
	public void inserisciLinkHaLavorato(TipoLinkHaLavorato t) {
		if (t != null && t.getPersona() == this)
			insieme_link.add(t);
	}
	public void eliminaLinkHaLavorato(TipoLinkHaLavorato t){
		if (t != null && t.getPersona() == this)
			insieme_link.remove(t);
	}
	public Set<TipoLinkHaLavorato> getLinkHaLavorato() {
		return (HashSet<TipoLinkHaLavorato>)insieme_link.clone()
	}
}
```

### Riassunto metodi per responsabilità singola

|  |  | 0..1 |  | 0..* |  |
| --- | --- | --- | --- | --- | --- |
|  |  | no attributi | attributo | no attributi | attributo |
| inserimento | argomento | rif. a oggetto | rif. a link | rif. a oggetto | rif. a link |
|  | controllo | - | arg ≠ null; link si rif a this; link == null | arg ≠ null | arg ≠ null; link si rif a this |
| cancellazione | argomento | null | nessuno | rif. a oggetto | rif. a link |
|  | controllo | - | - | arg ≠ null | arg ≠ null; link si rif a this |

### Associazioni binarie a responsabilità doppia, con molteplicità 0..1

*es. sia Persona che Stanza hanno responsabilità sull’associazione*

| Persona |  | Stanza |
| --- | --- | --- |
| Nome: stringa | 0..1 ———————— 0..1 | Numero: intero |
|  | occupazione |  |
|  | DaAnno: int |  |

Quando **si crea** un link tra oggetto pe di classe Persona e un oggetto st di classe Stanza, bisogna cambiare lo stato di entrambi, poiché:\
pe deve fare riferimento a st\
st deve fare riferimento a pe\
Analogo discorso quando **si elimina** un link tra i due oggetti

È preferibile **centralizzare** la responsabilità di assegnare i riferimenti in maniera corretta, in particolare si realizza un’ulteriore classe Java (**ManagerOccupazione**) che gestisce la corretta creazione della rete dei riferimenti, ogni suo oggetto ha un riferimento ad un oggetto Java che rappresenta un link di “occupazione”.

La classe Persona, oltre ai campi dati e funzione per la gestione dei suoi attributi, avrà:

- un **campo di tipo TipoLinkOccupazione**, che viene inizializzato a null dal costruttore
- funzioni per la gestione di questo campo:
    - **void inserisciLinkOccupazione(TipoLinkOccupazione)** per associare un link all’oggetto, ma che delega l’operazione a ManagerOccupazione
    - **void eliminaLinkOccupazione(TipoLinkOccupazione)** per rimuovere l’associazione di un link all’oggetto, anch’esso delega l’operazione a ManagerOccupazione
    - **TipoLinkOccupazione getLinkOccupazione()**
- funzioni utilizzabili solo da ManagerOccupazione
    - **void inserisciPerManagerOccupazione(ManagerOccupazione)** per gestire l’inserimento di link TipoLinkOccupazione
    - **void eliminaPerManagerOccupazione(ManagerOccupazione)** per gestire l’eliminazione di link TipoLinkOccupazione

```jsx
public class Persona {
	private String nome;
	private TipoLinkOccupazione link;
	public Persona(String n) {nome = n;}
	public String getNome() {return nome;}
	public void inserisciLinkOccupazione(TipoLinkOccupazione t) {
		if (t != null && t.getPersona() == this)
			ManagerOccupazione.inserisci(t);
	}
	public void eliminaLinkOccupazione(TipoLinkOccupazione t) {
		if (t != null && t.getPersona() == this)
			ManagerOccupazione.elimina(t);
	}
	public TipoLinkOccupazione getLinkOccupazione() {
		return link;
	}
	public void inserisciPerManagerOccupazione(ManagerOccupazione a) {
		if (a != null)
			link = a.getLink();
	}
	public void eliminaPerManagerOccupazione(ManagerOccupazione a) {
		if (a != null)
			link = null;
	}
}
```

La classe Stanza è del tutto analoga alla classe Persona

```jsx
public class Stanza {
private int numero;
private TipoLinkOccupazione link;
public Stanza(int n) {numero = n;}
public int getNumero() {return numero;}
public void inserisciLinkOccupazione(TipoLinkOccupazione t) {
	if (t != null && t.getStanza() == this) 
		ManagerOccupazione.inserisci(t);
}
public void eliminaLinkOccupazione(TipoLinkOccupazione t) {
	if (t != null && t.getStanza() == this)
		ManagerOccupazione.elimina(t);
}
public TipoLinkOccupazione getLinkOccupazione() {
	return link;
}
public void inserisciPerManagerOccupazione(ManagerOccupazione a) {
	if (a != null)
		link = a.getLink();
}
public void eliminaPerManagerOccupazione(ManagerOccupazione a) {
	if (a != null)
		link = null;
}
```

La classe TipoLinkOccupazione sarà del tutto simile al caso in cui la responsabilità sull’associazione è singola. Avrà:

- **tre campi dati** (per la stanza, per la persona e per l’attributo dell’associazione)
- un **costruttore** che inizializza i suoi argomenti, lancia un’eccezione se i riferimenti alla stanza o alla persona sono null;
- tre funzioni **get**
- funzione **equals** per verificare l’uguaglianza solo sugli oggetti collegati, ignorando gli attributi
- funzione **hashCode**, ridefinita

```jsx
public class TipoLinkOccupazione {
	private Stanza laStanza;
	private Persona laPersona;
	private int daAnno;
	public TipoLinkOccupazione(Stanza x, Persona y, int a) throws EccezionePrecondizioni {
		if (x == null || y == null)
			throw new EccezionePrecondizioni("Gli oggetti devono essere inizializzati");
		laStanza = x;
		laPersona = y;
		daAnno = a;
	}
	public boolean equals(Object o) {
		if (o != null && getClass().equals(o.getClass())) {
			TipoLinkOccupazione b = (TipoLinkOccupazione)o;
			return b.laPersona == laPersona && b.laStanza == laStanza;
		}
		else return false;
	}
	public int hashCode() {
		return laPersona.hashCode() + laStanza.hashCode();
	}
	public Stanza getStanza() {return laStanza;}
	public Persona getPersona() {return laPersona;}
	public int getDaAnno() {return daAnno;}
}
```

La classe ManagerOccupazione avrà:

- un **campo dato** di tipo TipoLinkOccupazione per la rappresentazione del link
- funzioni per la gestione di questo campo:
    - **static void inserisci(TipoLinkOccupazione)** per associare un link tra persona e stanza
    - **static void elimina(TipoLinkOccupazione)** per rimuovere un link tra persona e stanza
    - **TipoLinkOccupazione getLink()** per ottenere il link
- un **costruttore privato**, affinché nessun client sia in grado di creare oggetti di questa classe
- la classe sarà **final**, per evitare che si possa definire una sottoclasse con costruttore pubblico

```jsx
public final class ManagerOccupazione {
	private ManagerOccupazione(TipoLinkOccupazione x) {link = x;}
	private TipoLinkOccupazione link;
	public TipoLinkOccupazione getLink() {return link;}
	public static void inserisci(TipoLinkOccupazione y) {
		if (y != null && y.getPersona().getLinkOccupazione() == null && y.getStanza().getLinkOccupazione() == null) {
			ManagerOccupazione k = new ManagerOccupazione(y);
			y.getStanza().inserisciPerManagerOccupazione(k);
			y.getPersona().inserisciPerManagerOccupazione(k);
		}
	}
	public static void elimina(TipoLinkOccupazione y) {
		if (y != null && y.getPersona().getLinkOccupazione().equals(y)) {
			ManagerOccupazione k = new ManagerOccupazione(y);
			y.getStanza().eliminaPerManagerOccupazione(k);
			y.getPersona().eliminaPerManagerOccupazione(k);
		}
	}
}
```

**Controlli**

- **inserimento**: la funzione inserisci() verifica che il link y (tramite getLinkOccupazione) passato come argomento si riferisca ad oggetti di tipo Stanza e Persona che non sono associati a nessun link; per non inserire link quando gli oggetti sono già “occupati”
- **eliminazione:** la funzione elimina() verifica che il link y (tramite equals) passato come argomento si riferisca agli stessi oggetti di tipo Stanza e Persona del campo dato link; per non eliminare link inesistenti

I client saranno in grado di **creare link** di tipo TipoLinkOccupazione; **associare link** agli oggetti Stanza e Persona, con la funzione ManagerOccupazione.inserisci(); **rimuovere link** con la funzione ManagerOccupazione.elimina()

Le funzioni inserisciPerManagerOccupazione() ed eliminaPerManagerOccupazione() possono essere **invocate solamente** dalla classe ManagerOccupazione, poiché per invocarle bisogna passare un oggetto ManagerOccupazione, il quale può essere creato solo da inserisci() ed elimina() della classe ManagerOccupazione

I client vengono quindi forzati ad usare le **funzioni statiche inserisci() ed elimina()**, svincolate da oggetti di invocazione, anche quando effettuano inserimenti e cancellazioni di link mediante le classi Persona e Stanza, le quali a loro volta delegano la classe ManagerOccupazione

*es. Main*

```jsx
Stanza st = new Stanza(229);
Persona pe = new Persona("Mario");

TipoLinkOccupazione t = null;
try {
	t = new TipoLinkOccupazione(st, pe, 2004);
} catch(EccezionePrecondizioni e) {
	System.out.println(e);
}
ManagerOccupazione.inserisci(t);
```

### Associazioni binarie a responsabilità doppia, con una molteplicità 0..*

*es. sia Persona che Città hanno responsabilità sull’associazione*

| Persona |  | Città |
| --- | --- | --- |
| Nome: stringa | 0..* ———————— 0..1 | Nome: stringa |
|  | residenza |  |
|  | DaAnno: intero |  |

Metodologia simile a quella per la molteplicità 0..1, con le seguenti differenze:

La classe Città avrà:

- un ulteriore **campo dati di tipo Set**, per rappresentare tutti i link (l’oggetto di classe Set verrà creato tramite costruttore)
- tre campi **funzioni per la gestione dell’insieme dei link**:
    - inserisciLinkResidenza()
    - eliminaLinkResidenza()
    - getLinkResidenza() che restituisce una copia dell’insieme dei link
- due campi **funzioni di ausilio per ManagerResidenza**

```jsx
public class Citta {
	private String nome;
	private HashSet<TipoLinkResidenza> insieme_link;
	public Citta(String n) {
		nome = n;
		insieme_link = new HashSet<TipoLinkResidenza>();
	}
	public String getNome() {return nome;}
	public void inserisciLinkResidenza(TipoLinkResidenza t) {
		if (t != null && t.getCitta() == this) 
			ManagerResidenza.inserisci(t);
	}
	public void eliminaLinkResidenza(TipoLinkResidenza t) {
		if (t != null && t.getCitta() == this)
			ManagerResidenza.elimina(t);
	}
	public Set<TipoLinkResidenza> getLinkResidenza() {
		return (HashSet<TipoLinkResidenza>)insieme_link.clone();
	}
	public void inserisciPerManager(ManagerResidenza a) {
		if (a != null)
			insieme_link.add(a.getLink());
	}
	public void eliminaPerManagerResidenza(ManagerResidenza a) {
		if (a != null)
			insieme_link.remove(a.getLink());
	}
}

```

La classe Persona è esattamente identica al caso di entrambe le moltiplicità 0..1

```jsx
public class Persona {
	private String nome;
	private TipoLinkResidenza link;
	public Persona(String n) {nome = n;}
	public String getNome() {return nome;}
	public void inserisciLinkResidenza(TipoLinkResidenza t) {
		if (t != null && t.getPersona() == this)
			ManagerResidenza.inserisci(t);
	}
	public void eliminaLinkResidenza(TipoLinkResidenza t) {
		if (t != null && t.getPersona() == this)
			ManagerResidenza.elimina(t);
	}
	public TipoLinkResidenza getLinkResidenza() {
		return link;
	}
	public void inserisciPerManagerResidenza(ManagerResidenza a) {
		if (a != null)
			link = a.getLink();
	}
	public void eliminaPerManagerResidenza(ManagerResidenza a) {
		if (a != null)
			link = null;
	}
}
```

Analogamente la classe TipoLinkResidenza è esattamente identica al caso della molteplicità 0..1

```jsx
public class TipoLinkResidenza {
	private Citta laCitta;
	private Persona laPersona;
	private int daAnno;
	public TipoLinkResidenza(Citta x, Persona y, int a) throws EccezionePrecondizioni {
		if (x == null || y == null)
			throw new EccezionePrecondizioni("Gli oggetti devono essere inizializzati");
		laCitta = x;
		laPersona = y;
		daAnno = a;
	}
	public boolean equals(Object o) {
		if (o != null && getClass().equals(o.getClass())) {
			TipoLinkResidenza b = (TipoLinkResidenza)o;
			return b.laPersona == laPersona && b.laCitta == laCitta;
		}
		else return false;
	}
	public int hashCode() {
		return laPersona.hashCode() + laCitta.hashCode();
	}
	public Citta getCitta() {return laCitta;}
	public Persona getPersona() {return laPersona;}
	public int getDaAnno() {return daAnno;}
}
```

La classe ManagerResidenza 

```jsx
public final class ManagerResidenza {
	private ManagerResidenza(TipoLinkResidenza x) {link = x;}
	private TipoLinkResidenza link;
	public TipoLinkResidenza getLink() {return link;}
	public static void inserisci(TipoLinkResidenza y) {
		if (y != null && y.getPersona().getLinkResidenza() == null) {
			ManagerResidenza k = new ManagerResidenza(y);
			y.getCitta().inserisciPerManagerResidenza(k);
			y.getPersona().inserisciPerManagerResidenza(k);
		}
	}
	public static void elimina(TipoLinkResidenza y) {
		if (y != null && y.getPersona().getLinkResidenza().equals(y)) {
		ManagerResidenza k = new ManagerResidenza(y);
		y.getCitta().eliminaPerManagerResidenza(k);
		y.getPersona().eliminaPerManagerResidenza(k);
		}
	}
}
```

### Associazioni binarie a responsabilità doppia, entrambe con molteplicità 0..*

*es. sia Studente che Corso hanno responsabilità sull’associazione*

| Studente |  | Corso |
| --- | --- | --- |
| Matricola: stringa | 0..* ———————— 0..* | Nome: stringa |
|  | esame |  |
|  | Voto: intero |  |

In questo caso la classe Studentee la classe Corso sono strutturalmente simili, avranno:

- un ulteriore **campo di tipo HashSet\<TipoLinkEsame\>** per poter rappresentare tutti i link (l’oggetto viene creato tramite costruttore)
- tre **funzioni per la gestione dell’insieme dei link**:
    - inserisciLinkResidenza()
    - eliminaLinkResidenza()
    - getLinkResidenza() che restituisce una copia dell’insieme
- due **funzioni di ausilio** per ManagerEsame

La classe Studente

```jsx
private class Studente {
	private String matricola;
	private HashSet<TipoLinkEsame> insieme_link;
	public Studente(String n) {
		matricola = n;
		insieme_link = new HashSet<TipoLinkEsame>();
	}
	public String getMatricola() {return matricola;}
	public void inserisciLinkEsame(TipoLinkEsame) {
		if (t != null && t.getStudente() == this)
			ManagerEsame.inserisci(t);
	}
	public void eliminaLinkEsame(TipoLinkEsame) {
		if (t != null && t.getStudente == this) 
			ManagerEsame.elimina(t);
	}
	public Set<TipoLinkEsame> getLinkEsame() {
		return (HashSet<TipoLinkEsame>)insieme_link.clone();
	}
	public void inserisciPerManagerEsame(ManagerEsame a) {
		if (a != null) 
			insieme_link.add(a.getLink());
	}
	public void eliminaPerManagerEsame(ManagerEsame a) {
		if (a != null)
			insieme_link.remove(a.getLink());
	}
}
```

La classe Corso

```jsx
public class Corso {
	private String nome;
	private HashSet<TipoLinkEsame> insieme_link;
	public Corso(String n) {
		nome = n;
		insieme_link = new HashSet<TipoLinkEsame>();
	}
	public String getNome() {return nome;}
	public void inserisciLinkEsame(TipoLinkEsame t) {
		if (t != null && t.getCorso == this)
			ManagerEsame.inserisci(t);
	}
	public void eliminaLinEsame(TipoLinkEsame t) {
		if (t != null && t.getCorso() == this)
			ManagerEsame.elimina(t);
	}
	public Set<TipoLinkEsame> getLinkEsame() {
		return (HashSet<TipoLinkEsame>)insieme_link.clone();
	}
	public void inserisciPerManagerEsame(ManagerEsame a) {
		if (a != null)
			insieme_link.add(a.getLink());
	}
	public void eliminaPerManagerEsame(ManagerEsame a) {
		if (a != null)
			insieme_link.remove(a.getLink());
	}
}
```

La classe TipoLinkEsame

```jsx
public class TipoLinkEsame {
	private Corso ilCorso;
	private Studente loStudente;
	private int voto;
	public TipoLinkEsame(Corso x, Studente y, int a) throws EccezionePrecondizioni {
		if (x == null || y == null)
			throw new EccezionePrecondizioni("Gli oggetti devono essere inizializzati");
		ilCorso = x;
		loStudente = y;
		voto = a;
	}
	public boolean equals(Object o) {
	if (o != null && getClass.equals(o.getClass())) {
		TipoLinkEsame b = (TipoLinkEsame)o;
		return b.ilCorso == ilCorso && b.loStudente == loStudente;
	}
	public int hashCode() {
		return ilCorso.hashCode() + loStudente.hashCode();
	}
	public Corso getCorso() {return ilCorso;}
	public Studente getStudente() {return loStudente;}
	public int getVoto() {return voto;}
}
```

La classe Manager Esame

```jsx
public final class ManagerEsame {
	private ManagerEsame(TipoLinkEsame x) {link = x;}
	private TipoLinkEsame link;
	public TipoLinkEsame getLink() {return link;}
	public static void inserisci(TipoLinkEsame y) {
		if (y != null) {
			ManagerEsame k = new ManagerEsame(y);
			y.getCorso().inserisciPerManagerEsame(k);
			y.getStudente().inserisciPerManagerEsame(k);
		}
	}
	public static void elimina(TipoLinkEsame y) {
		if (y != null) {
			ManagerEsame k = new ManagerEsame(y);
			y.getCorso().eliminaPerManagerEsame(k);
			y.getStudente().eliminaPerManagerEsame(k);
		}
	}
}
```

A differenza dei casi con almeno una molteplicità 0..1, non si corre il rischio di creare situazioni inconsistenti con l’eliminazione di link

### Associazioni binarie, con molteplicità diversa da 0..1 e 0..*

**Casi**:

- molteplicità minima diversa da zero
- molteplicità massima finita

In questi due casi, la classe rispetto a cui esiste uno dei vincoli ha necessariamente responsabilità sull’associazione (per verificare il numero di link esistenti)

**Strategia**
Fare in modo che i vincoli siano rispettati in ogni momento è, in generale, molto complicato, perciò si ammette che gli oggetti possano stare in uno stato che non rispetta i vincoli, ma lanciando un’eccezione nel momento in cui un client chieda di utilizzare un link di un oggetto che non rispetta i vincoli

**Molteplicità minima diversa da zero**

*es. sia Studente che CorsoDiLaurea hanno responsabilità sull’associazione*

| Studente |  | CorsoDiLaurea |
| --- | --- | --- |
| Matricola: stringa | 10..* ———————— 0..* | Nome: stringa |
|  | iscritto |  |
|  | DaAnno: intero |  |

La classe CorsoDiLaurea avrà:

- una funzione pubblica int quantiIscritti(), che restituisci il numero di studenti iscritti al corso, per capire se il vincolo è rispettato oppure no
- la funzione int getLinkIscritto() lancia un’eccezione (di tipo EccezioneCardMin) quando l’oggetto di invocazione non rispetta il vincolo di cardinalità minima

Resta invariata la metodologia di realizzazione delle classi: Studente, TipoLinkIscritto, EccezionePrecondizioni, ManagerIscritto

La classe EccezioneCardMin

```jsx
public class EccezioneCardMin extends Exception {
	private String messaggio;
	public EccezioneCardMin(String m) {
		messaggio = m;
	}
	public String toString() {
		return messaggio;
	}
}
```

La classe CorsoDiLaurea

```jsx
public class CorsoDiLaurea {
	private String nome;
	private HashSet<TipoLinkIscritto> insieme_link;
	public static final int MIN_LINK_ISCRITTO = 10;
	public CorsoDiLaurea(String n) {
		nome = n;
		insieme_link = new HashSet<TipoLinkIscritto>();
	}
	public String getNome() {return nome;}
	public int quantiIscritti() {
		return insieme_link.size();
	}
	public void inserisciLinkIscritto(TipoLinkIscritto t) {
		if (t != null && t.getCorsoDiLaurea() == this)
			ManagerIscritto.inserisci(t);
	}
	public void eliminaLinkIscritto(TipoLinkIscritto t) {
		if (t != null && t.getCorsoDiLaurea() == this)
			ManagerIscritto.elimina(t);
	}
	public Set<TipoLinkIscritto> getLinkIscritto() throws EccezioneCardMin {
		if (quantiIscritti() < MIN_LINK_ISCRITTO)
			throw new EccezioneCardMin("Cardinalità minima violata");
		else return (HashSet<TipoLinkIscritto>)insieme_link.clone();
	}
	public void inserisciPerManagerIscritto(ManagerIscritto a) {
		if (a != null)
			insieme_link.add(a.getLink());
	}
	public void eliminaPerManagerIscritto(ManagerIscritto a) {
		if (a != null)
			insieme_link.remove(a.getLink());
	}
}
```

**Molteplicità massima finita**

*es. sia Studente che Corso hanno responsabilità sull’associazione*

| Studente |  | Corso |
| --- | --- | --- |
| Matricola: stringa | 0..* ———————— 0..28 | Nome: stringa |
|  | esame |  |
|  | Voto: intero |  |

La classe Studente avrà:

- una funzione pubblica int quantiEsami(), che restituisci il numero di esami sostenuti dallo studente oggetto di invocazione
- la funzione int getLinkEsami() lancia un’eccezione (di tipo EccezioneCardMax) quando l’oggetto di invocazione non rispetta il vincolo di cardinalità massima

Resta invariata la metodologia di realizzazione delle classi: Corso, TipoLinkEsame, EccezionePrecondizioni, ManagerEsame

La classe EccezioneCardMax

```jsx
public classEccezioneCardMax extends Exception {
	private String messaggio;
	public EccezioneCardMax(String m) {
		messaggio = m;
	}
	public String toString() {
		return messaggio;
	}
}
```

La classe Studente

```jsx
public class Studente {
	private String matricola;
	private HashSet<TipoLinkEsame> insieme_link;
	public static final int MAX_LINK_ESAME = 28;
	public Studente(String n) {
		matricola = n;
		insieme_link = new HashSet<TipoLinkEsame>();
	}
	public String getMatricola() {return matricola;}
	public int quantiEsami() {
		return insieme_link.size();
	}
	public void inserisciLinkEsame(TipoLinkEsame t) {
		if (t != null && t.getStudente() == this)
			ManagerEsame.inserisci(t);
	}
	public void eliminaLinkEsame(TipoLinkEsame t) {
		if (t != null && t.getStudente() == this) 
			ManagerEsame.elimina(t);
	}
	public Set<TipoLinkEsame> getLinkEsame() throws EccezioneCardMax {
		if (insieme_link.size() > MAX_LINK_ESAME)
			throw new EccezioneCardMax("Cardinalità massima violata");
		else return (HashSet<TipoLinkEsame>)insieme_link.clone();
	}
	public void inserisciPerManagerEsame(ManagerEsame a) {
		if (a != null) 
			insieme_link.add(a.getLink());
	}
	public void eliminaPerManagerEsame(ManagerEsame a) {
		if (a != null) 
			insieme_link.remove(a.getLink());
	}
}
```

**Caso particolare - Molteplicità minima 1** 

*es. sia Studente che CorsoDiLaurea hanno responsabilità sull’associazione*

| Studente |  | CorsoDiLaurea |
| --- | --- | --- |
| Matricola: stringa | 0..* ———————— 1..1 | Nome: stringa |
|  | iscritto |  |
|  | DaAnno: intero |  |

La classe Studente ha un campo dati di TipoLinkIscritto (e non Set)

```jsx
public class Studente {
	private final String matricola;
	private TipoLinkIscritto link;
	public static final int MIN_LINK_ISCRITTO = 1;
	public Studente(String n) {matricola = n;}
	public String getMatricola() {return matricola;}
	public int quantiIscritti() {
		if (link == null)
			return 0;
		else return 1;
	}
	public void inserisciLinkIscritto(TipoLinkIscritto t) {
		if (t != null && t.getStudente() == this)
			ManagerIscritto.inserisci(t);
	}
	public void eliminaLinkIscritto(TipoLinkIscritto t) {
		if (t != null && t.getStudente() == this)
			ManagerIscritto.elimina(t);
	}
	public TipoLinkIscritto getLinkIscritto() throws EccezioneCardMin {
		if (link == null)
			throws new EccezioneCardMin("Cardinalità minima violata");
		else return link;
	}
	public void inserisciPerManagerIscritto(ManagerIscritto a) {
		if (a != null)
			link = a.getLink();
	}
	public void eliminaPerManagerIscritto(ManagerIscritto a) {
		if (a != null)
			link = null;
	}
}
```

La classe ManagerIscritto deve effettuare i controlli del caso, cioè che:

- l’inserimento avvenga solo se lo studente non è iscritto (quantiIscritti())
- la cancellazione avvenga solo per link esistenti

```jsx
public final class ManagerIscritto {
	private TipoLinkIscritto link;
	private ManagerIscritto(TipoLinkIscritto x) {link = x;}
	public TipoLinkIscritto getLink() {return link;}
	public static void inserisci(TipoLinkIscritto y) {
		if (y != null && y.getStudente().quantiIscritti() == 0)
			ManagerIscritto k = new ManagerIscritto(y);
			y.getCorsoDiLaurea().inserisciPerManagerIscritto(k);
			y.getStudente().inserisciPerManagerIscritto(k);
		}
	}
	public static void elimina(TipoLinkIscritto y) {
		try {
			if (y != null && y.getStudente().getLinkIscritto().equals(y)) {
				ManagerIscritto k = new ManagerIscritto(y);
				y.getCorsoDiLaurea().eliminaPerManagerIscritto(k);
				y.getStudente().eliminaPerManagerIscritto(k);
			}
		} catch(EccezioneCardMin e) {
				System.out.println(e);
		}
	}
}
```

Restano invece inalterate le classi: TipoLinkIscritto, EccezionePrecondizioni, CorsoDiLaurea

### Associazioni n-arie

Si trattano generalizzando gli schemi di realizzazione per le associazioni binarie, ma si assume che le associazioni n-arie hanno sempre molteplicità 0..*

Per un’associazione n-aria A, anche senza attributi, si definisce la corrispondente classe **TipoLinkA**

- Nel caso di **responsabilità di una sola classe**, si usa la struttura dati adeguata per rappresentare i link solo di quella classe
- Nel caso di **responsabilità di più classi**, si definisce anche la classe **ManagerA**

### Associazioni ordinate

Si realizzano in modo del tutto analogo alle relazioni non ordinate, ma per mantenere l’ordine si fa uso delle classi List, LinkedList (invece di Set e HashSet) 

Inoltre nel memorizzare i link, bisogna fare attenzione a non avere ripetizioni, poiché in un’associazione, seppur ordinata, non ci possono essere due link uguali

**Responsabilità singola**

*es. solo Playlist ha responsabilità sull’associazione*

| Playlist |               Contiene | Brano |
| --- | --- | --- |
| Nome: stringa | 0..* ———————— 0..* | Nome: stringa |
|  | {ordered} | Durata: intero  |
| DurataTotale(): intero |  | NomeFile: stringa |

**InizioSpecificaClasse Playlist**

&nbsp;&nbsp;&nbsp;&nbsp;**durataTotale**(): intero\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: result è pari alla somma delle durate dei brani contenuti in this

**FineSpecifica**

La classe Playlist

```jsx
public class Playlist {
	private String nome;
	private LinkedList<Brano> insieme_link;
	public Playlist(String n) {
		nome = n;
		insieme_link = new LinkedList<Brano>();
	}
	public String getNome() {return nome;}
	public void inserisciLinkContiene(Brano b) {
		if (b != null && !insieme_link.contains(b))
			insieme_link.add(b);
	}
	public void eliminaLinkContiene(Brano b) {
		if (b != null)
			insieme_link.remove(b);
	}
	public List<Brano> getLinkContiene() {
		return (LinkedList<Brano>)insieme_link.clone();
	}
	public int durataTotale() {
		int ris = 0;
		Iterator<Brano> ib = insieme_link.iterator();
		while(ib.hasNext()) {
			Brano b = ib.next();
			ris += b.getDurata();
		}
		return ris;
	}
}
```

La classe Brano

```jsx
public class Brano {
	private String nome;
	private int durata;
	private String nomefile;
	public Brano(String n, int d, String f) {
		nome = n;
		durata = d;
		nomefile = f;
	}
	public String getNome() {return nome;}
	public int getDurata() {return durata;}
	public String getNomeFile() {return nomefile;}
}
```

**InizioSpecificaCliente Analisi Playlist**

&nbsp;&nbsp;&nbsp;&nbsp;**PiuLunghe** (i: Insieme(Playlist)) : Insieme(Playlist)\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: result è costituito dalle Playlist di i la cui durata totale è massima

**FineSpecifica**

```jsx
public final class AnalisiPlaylist {
	public static Set<Playlist> piuLunghe(Set<Playlist> i) {
		HashSet<Playlist> result = maxDurata(i);
		Iterator<Playlist> it = i.iterator();
		while (it.hasNext()) {
			Playlist pl = it.next();
			int durata = pl.durataTotale();
			if (durata == duratamax)
				result.add(pl)
		}
		return result;
	}
	private static int maxDurata(Set<Playlist> i) {
		int duratamax = 0;
		Iterato<Playlist> it = i.iterator();
		while(it.hasNext()) {
			Playlist pl = it.next();
			int durata = pl.durataTotale();
			if (durata > duratamax)
				duratamax = durata;
		}
		return duratamax;
	}
}
```

**Responsabilità doppia**

*es. sia Playlist che Brano hanno responsabilità sull’associazione*

| Playlist |               Contiene | Brano |
| --- | --- | --- |
| Nome: stringa | 0..* ———————— 0..* | Nome: stringa |
|  | {ordered} | Durata: intero  |
| DurataTotale(): intero |  | NomeFile: stringa |

Metodologia generale:

- realizzazione della classe TipoLinkContiene
- realizzazione della classe ManagerContiene
- la classe Brano ha un campo di tipo HashSet, per rappresentare la struttura di dati non ordinata
- la classe Playlist ha un campo di tipo LinkedList, per rappresentare la struttura di dati ordinata

La classe Playlist

```jsx
public class Playlist {
	private String nome;
	private LinkedList<TipoLinkContiene> insieme_link;
	public Playlist(String n) {
		nome = n;
		insieme_link = new LinkedList<TipoLinkContiene>();
	}
	public String getNome() {return nome;}
	public void inserisciLinkContiene(TipoLinkIscritto t) {
		if (t != null && t.getPlaylist() == this)
			ManagerContiene.inserisci(t);
	}
	public void eliminaLinkContiene(TipoLinkIscritto t) {
		if (t != null && t.getPlaylist() == this)
			ManagerContiene.elimina(t);
	}
	public List<TipoLinkContiene> getLinkContiene() {
		return (LinkedList<TipoLinkContiene>)insieme_link.clone();
	}
	public void inserisciPerManagerContiene(ManagerContiene a) {
		if (a != null && !insieme_link.contains(a.getLink()))
			insieme_link.add(a.getLink());
	}
	public void eliminaPerManagerContiene(ManagerContiene a) {
		if (a != null)
			insieme_link.remove(a.getLink());
	}
	public int durataTotale() {
		int ris = 0;
		Iterator<TipoLinkContiene> it = insieme_link.iterator();
		while (it.hasNext()) {
			Brano b = it.next().getBrano();
			result += b.getDurata();
		}
		return result;
	}
}
```

La classe Brano

```jsx
public class Brano {
	private String nome;
	private int durata;
	private String nomefile;
	private HashSet<TipoLinkContiene> insieme_link;
	public Brano(String n, int d, String f) {
		nome = n;
		durata = d;
		nomefile = f;
		insieme_link = new HashSet<TipoLinkContiene>();
	}
	public String getNome() {return nome;}
	public int getDurata() {return durata;}
	public String getNomeFile() {return nomefile;}
	public void inserisciLinkContiene(TipoLinkContiene t) {
		if (t != null && t.getBrano == this)
			ManagerContiene.inserisci(t);
	}
	public void eliminaLinkContiene(TipoLinkContiene t) {
		if (t != null && t.getBrano == this)
			ManagerContiene.elimina(t);
	}
	public Set<TipoLinkContiene> getLinkContiene() {
		return (HashSet<TipoLinkContiene>)insieme_link.clone();
	}
	public void inserisciPerManagerContiene(ManagerContiene a) {
		if (a != null)
			insieme_link.add(a.getLink());
	}
	public void eliminaPerManagerContiene(ManagerContiene a) {
		if (a != null)
			insieme_link.remove(a.getLink());
	}
}
```

La classe TipoLinkContiene

```jsx
public class TipoLinkContiene {
private Playlist laPlaylist;
private Brano ilBrano;
public TipoLinkContiene(Playlist x, Brano y) throws EccezionePrecondizioni {
	if (x == null || y == null)
		throw new EccezionePrecondizioni("Gli oggetti devono essere inizializzati");
	laPlaylist = x;
	ilBrano = y;
}
public boolean equals(Object o) {
	if (o != null && getClass().equals(o.getClass())) {
		TipoLinkContiene b = (TipoLinkContiene)o;
		return b.ilBrano == ilBrano && b.laPlaylist == laPlaylist;
	}
	else return false;
}
public int hashCode() {
	return laPlaylist.hashCode() + ilBrano.hashCode();
}
public Playlist getPlaylist() {return laPlaylist;}
public Brano getBrano() {return ilBrano;}
```

La classe ManagerContiene

```jsx
public final class ManagerContiene {
	private TipoLinkContiene link;
	private MangaterContiene(TipoLinkContiene x) {link = x;}
	public TipoLinkContiene getLink() {return link;}
	public static void inserisci(TipoLinkContiene y) {
		if (y != null) {
			ManagerContiene k = new ManagerContiene(y);
			y.getPlaylist().inserisciPerManager(k);
			y.getBrano().inserisciPerManager(k);
		}
	}
	public static void elimina(TipoLinkContiene y) {
		if (y != null) {
			ManagerContiene k = new ManagerContiene(y);
			y.getPlaylist().eliminaPerManager(k);
			y.getBrano().eliminaPerManager(k);
		}
	}
}
```

## Realizzazione di generalizzazioni

### Generalizzazione (relazione is-a tra due classi)

*es. nel diagramma delle classi è presente una generalizzazione LibroStorico → Libro*

| Libro |
| --- |
| Titolo: stringa |
| AnnoStampa: intero |
|            ↑ |
| LibroStorico |
| Epoca: stringa |

La superclasse UML Libro, diventa una classe base Java e la sottoclasse UML LibroStorico diventa una classe derivata Java 

Ogni proprietà di Libro è anche una proprietà del tipo LibroStorico, quindi tutto ciò che si vuole ereditare è **protetto**

Nella classe LibroStorico ci si affida alla definizione di Libro per quelle proprietà che sono identiche e si definiscono tutte le proprietà (dati e funzioni) che gli oggetti LibroStorico hanno in più rispetto a quelle ereditate da Libro

**Information hiding**

Sia C una classe UML, nella sua corrispondente classe Java si avrà:

- gli attributi di C corrispondono a campi **privati** della classe Java
- le operazioni di C corrispondono a campi **pubblici** della classe Java
- sono **pubblici** anche i costruttori e le funzioni get, set
- sono **private** eventuali funzioni che non si vogliono rendere disponibili ai client
- tutte le classi Java sono nello stesso package

Nella realizzazione di generalizzazioni, invece, i campi di C che non si vogliono rendere visibili dai clienti, sono **protetti**, in modo che siano visibili dalle classi derivate

**Livelli di accesso nelle classi Java**

1. public
2. protected
3. non qualificato (default)
4. private

**Regole di visibilità** (il metodo B vede il campo A?)

| Metodo B \ Campo A | public | protected | non qual. | private |
| --- | --- | --- | --- | --- |
| Stessa Classe | SI | SI | SI | SI |
| Classe stesso package | SI | SI | SI | NO |
| Classe derivata package diverso | SI | SI | NO | NO |
| Classe non derivata package diverso | SI | NO | NO | NO |

Nota: → e ↓ decrescono i diritti

Emerge la necessita di un **package diverso** per ogni classe Java con campi protected 

Ogni classe Java D che deve accedere ai campi di C conterrà la dichiarazione “import C.*;”

La classe Libro

```jsx
package Libro;

public class Libro {
	protected String titolo;
	protected int annoStampa;
	public Libro(String t, int a) {
		titolo = t;
		annoStampa = a;
	}
	public String getTitolo() {return titolo;}
	public int getAnnoStampa() {return annoStampa;}
	public String toString() {
		return titolo + " " + annoStampa;
	}
}
```

**Costruttori di classi derivate**

Costruttore classe D derivata da B:

- se ha come prima istruzione super(), allora viene chiamato il costruttore di B, altrimenti viene chiamato il costruttore senza argomenti di B
- viene eseguito il corpo del costruttore

La classe LibroStorico

```jsx
package LibroStorico;
import Libro.*;

public class LibroStorico extends Libro {
	protected String epoca;
	public LibroStorico(String t, int a, String e) {
		super(t, a);
		epoca = e;
	}
	public String getEpoca() {return epoca;}
	public String toString() {
		return super.toString() + " " + epoca;
	}
}
```

*es. Data la seguente specifica*

**InizioSpecificaCliente Valutazione Biblioteca**

&nbsp;&nbsp;&nbsp;&nbsp;**QuantiAntichi** (i : Insieme(Libro), a: intero): intero\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: result è il numero di libri dati alle stampe prima dell’anno a nell’insieme di libri i

&nbsp;&nbsp;&nbsp;&nbsp;**QuantiStorici** (i : Insieme(Libro)): intero\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: result è il numero di libri storici nell’insieme di libri i

**FineSpecifica**

```jsx
import Libro.*;
import LibroStorico.*;

public class ValutazioneBiblioteca {
	public static int quantiAntichi(Set<Libro> ins, int anno) {
		int quanti = 0;
		Iterator<Libro> it = ins.iterator();
		while(it.hasNext()) {
			Libro elem = it.next();
			if (elem.getAnnoStampa() < anno)
				quanti++;
		}
		return quanti;
	}
	public static int quantiStorici(Set<Libro> ins) {
		int quanti = 0;
		Iterator<Libro> it = ins.iterator();
		while(it.hasNext()) {
			Libro elem = it.next();
			if (elem.getClass().equals(LibroStorico.class))
				quanti++;
		}
		return quanti;
	}
	private ValutazioneBiblioteca() {}
}
```

### Ridefinizione (specializzazione di operazioni)

Nella classe derivata è possibile fare **overriding** delle funzioni della classe base (sono uguali il numero e il tipo degli argomenti e il tipo di ritorno deve essere identico), in modo che le funzioni ridefinite compiono operazioni diverse rispetto a quelle della classe base

*es.*

| Lavoratore |
| --- |
| Anzianità: intero |
| Pensionabile(): bool |
|            ↑ |
| LavoratoreMinerario |
|  |
| Pensionabile(): bool |

I lavoratori sono pensionabili con un’anzianità di 30 anni, i lavoratori minerari sono pensionabili con un’anzianità di 25 anni

La classe base

```jsx
package Lavoratore;

public class Lavoratore {
	protected int anzianita;
	public int getAnzianita() {return anzianita;}
	public void setAnzianita(int a) {anzianita = a;}
	public boolean pensionabile() {return anzianita > 30;}
}
```

La classe derivata

```jsx
package LavoratoreMinerario;
import Lavoratore.*;

public class LavoratoreMinerario extends Lavoratore {
	//overriding
	public boolean pensionabile() {return anzianita > 25;}
}
```

### Generalizzazioni disgiunge e complete

Java non supporta l’ereditarietà multipla, quindi si assume ogni generalizzazione **disgiunta**, quando la generalizzazione è anche **completa**, bisogna fare considerazioni ulteriori

*es. classe base Militare, classi derivate: Ufficiale, Sottoufficiale, MilitareDiTruppa*

|  | Militare |  |
| --- | --- | --- |
|  | Matricola: stringa |  |
|  | Arma: stringa |  |
|  | DegnoDiOnoreficenza(): boolean |  |
|  |     ↑      {disjoint, complete} |  |
| Ufficiale | Sottoufficiale | MilitareDiTruppa |
| AnniComando: intero | Brevetti: intero | NoteMerito: intero |

La classe Militare dovrà essere una **abstract class**, poiché non esistono istanze di Militare che non siano istanze di Ufficiale, Sottoufficiale o MilitareDiTruppa, quindi **non** si potranno definire oggetti che sono **istanze dirette** della classe Militare

Le operazioni dettagliabili solo quando vengono associate ad una delle sottoclassi saranno **dichiarate abstract** e la loro **definizione viene delegata alle sottoclassi**

*Si assuma che per essere degli di onoreficenza i criteri siano i seguenti:*

*Ufficiale: più di dieci anni di comando;*

*Sottoufficiale: più di quattro brevetti di specializzazione;*

*MilitareDiTruppa: più di due note di merito*

La classe Militare

```jsx
package Militare;

public abstract class Militare {
	protected String arma;
	protected String matricola;
	public Militare(String a, String m) {
		arma = a;
		matricola = m;
	}
	public String getArma() {return arma;}
	public String getMatricola() {return matricola;}
	abstract public boolean degnoDiOnoreficenza();
	public String toString() {
		return "Matricola: " + matricola + "Arma di appartenenza: " + arma;
	}
}
```

La classe Ufficiale

```jsx
package Ufficiale;
import Militare.*;

public class Ufficiale extends Militare {
	protected int anni_comando;
	public Ufficiale(String a, String m) {
		super(a, m);
	}
	public int getAnniComando() {return anni_comando;}
	public void incrementaAnniComando() {anni_comando++;}
	public boolean degnoDiOnoreficenza() {
		return anni_comando > 10;
	}
}
```

La classe Sottoufficiale

```jsx
package Sottoufficiale;
import Militare.*;

public class Sottoufficiale extends Militare {
	protected int brevetti_specializzazione;
	public Sottoufficiale (String a, String m) {
		super(a, m);
	}
	public int getBrevettiSpecializzazione() {return brevetti_specializzazione;}
	public void incrementaBrevettiSpecializzazione() {brevetti_specializzazione++;}
	public boolean degnoDiOnoreficenza() {
		return brevetti_specializzazione > 4;
	}
}

```

La classe MilitareDiTruppa

```jsx
package MilitareDiTruppa;
import Militare.*;

public class MilitareDiTruppa extends Militare {
	protected int note_di_merito;
	public MilitareDiTruppa(String a, String m) {
		super(a, m);
	}
	public int getNoteDiMerito() {return note_di_merito;}
	public void incrementaNoteDiMerito() {note_di_merito++;}
	public boolean degnoDiOnoreficenza() {
		return note_di_merito > 2;
	}
}
```

### Organizzazione in packages

Tutta l’applicazione viene mesa in una package Java P, che comprende ogni classe proveniente dal diagramma delle classi (anche quelle definite per le associazioni). Nel caso di classi con campi protetti, va previsto un sottopackage

*es. solo Libro ha responsabilità sull’associazione Autore*

| Libro |                 Autore | Persona |
| --- | --- | --- |
| Titolo: stringa | 0..* ————————— 0..1 | Nome: stringa |
| AnnoStampa: intero |                |  |
|              ↑ |  |  |
| LibroStorico |  |  |
| Epoca: stringa |  |  |

**InizioSpecificaCliente StatisticaAutori**

&nbsp;&nbsp;&nbsp;&nbsp;**Prolifici** (i: Insieme(Libro)): Insieme(Persona)\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;post: result è l’insieme di persone che sono autori di almeno due libri tra quelli di i

**FineSpecifica**

**Struttura file e direttori**

```
|
+---PackageLibri
| | StatisticaAutori.java
| | Persona.java
| | Test
| | MainLibri.java
| |
| +---Libro
| | Libro.java
| |
| \---LibroStorico
| LibroStorico.java
|
```

La classe Persona

```jsx
package PackageLibri;

public class Persona {
	private String nome;
	public Persona(String n) {nome = n;}
	public String getNome() {return nome;}
	public String toString() {
		return nome;
}
```

La classe Libro

```jsx
package PackageLibri.Libro;
import PackageLibri.*;

public class Libro {
	protected String nome;
	protected int annoStampa;
	protected Persona autore;
	public Libro(String n, int a) {
		nome = n;
		annoStampa = a;
	}
	public void setAutore(Persona p) {autore = p;}
	public Persona getAutore() {return autore;}
	public String getTitolo() {return titolo;}
	public int getAnnoStampa() {return annoStampa;}	
	public String toString() {
		return titolo + " " + (autore != null? + autore.toString() : "Anonimo") + " " +
				annoStampa;
	}
}
```

La classe LibroStorico

```jsx
package PackageLibri.LibroStorico;
import PackageLibri.Libro.*;

public class LibroStorico extends Libro {
		protected String epoca;
		public LibroStorico(String t, int a, String e) {
			super(t, a);
			epoca = e;
		}
	public String getEpoca() {return epoca;}
	public String toString() {
		return super.toString + " " + epoca;
	}
}
```

La classe StatisticaAutori

```jsx
package PackageLibri;
import PackageLibri.Libro.*;
import PackageLibri.LibroStorico.*;

public class StatisticaAutori {
	public static Set<Persona> prolifici(Set<Libro> ins) {
		Set<Persona> result = new HashSet<Persona>();
		ArrayList<Persona> autori = new ArrayList<Persona>();
		for (Libro l: ins) {
			autori.add(l.getPersona());
		}
		for (Persona p: autori) {
			if (numOccorrenze(p, autori) >= 2)
				result.add(p);
		}
		return result;
}
	
	public int numOccorrenze(Persona p, ArrayList<Persona> persone) {
		int ris = 0;
		for (Persona pp: persone) {
			if (pp.equals(p)) ris++;
		}
		return ris;
	}
	private StatisticaAutori() {}
}

		

```
