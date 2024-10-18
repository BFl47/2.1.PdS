# Es. Esame

**SpecificaStatiClasse Ordine**

Stato: {PREPARAZIONE, CONSEGNA, COMPLETATO}

Variabili di stato ausiliarie:
     Rider rider

Stato iniziale:
     statoCorrente = Stato.PREPARAZIONE;
     rider = —;

**FineSpecifica**

**SegnaturaAttivitàComplesse**

AttivitaPrincipale(insiemeRider insR, insiemeOrdini insO) : ();
ConsegnaOrdini (insiemeRider insR, insiemeOrdini insO) : ();
Analisi (insiemeRider insR, insiemeOrdini insO) : (String report);

**SegnaturaAttivitàAtomiche:**

Verifica(insiemeRider insR, insiemeOrdini insO) : (boolean ok);

**SegnaturaAttivitàIO:**

RichiediAggiornamento() : ();

Report(String report) : ();

**FineSegnatura**

**Realizzazione**

*Classe Ordine*

```jsx
package Ordine;

public class Ordine implements Listener {
	private String codice;
	
	private final int MINE_MOLT = 1;
	private final int MAX_MOLT = 1;
	private TipoLinkEffettua linkEffettua;
	private HashSet<TipoLinkContiene> linkContiene;
	private Rider linkConsegnato;
	
	public Ordine(String c){
		codice = c;
		linkContiene = new HashSet<TipoLinkContiene>();
	}
	public String getCodice() {return codice;}
	public void setCodice(String s) {codice = s;}
	
	public Rider getLinkConsegnato() {
		return linkConsegnato;
	}
	public void setLinkConsegnato(Rider r) {
		linkConsegnato = r;
	}
	public TipoLinkEffettua getLinkEffettua() {
		if(linkEffettua == null)
			throw new RuntimeException("violata molteplicità 1..1");
		else return linkEffettua;
	}
	public int quantiEffettua() {
		if (linkEffettua == null) 
			return 0;
		else return 1;
	}
	public void inserisciTipoLinkContiene(TipoLinkContiene l) {
		if (l != null)
			linkContiene.add(l);
	}
	public void eliminaTipoLinkContiene(TipoLinkContiene l) {
		if (l != null)
			linkContiene.remove(l);
	}
	public Set<TipoLinkContiene> getLinkContiene() {
		if (linkContiene.size() < MIN_MOLT)
			throw new RuntimeException("Violata molteplicità 1..*");
		else 
			return (Set<TipoLinkContiene>) linkContiene.clone();
	}
	public int quantiConsegnato() {return linkContiene.size();}
	
	public void inserisciTipoLinkEffettua(TipoLinkEffettua l) {
		if (l != null && l.getOrdine == this) 
			ManagerEffettua.inserisci(l);
	}
	public void eliminaTipoLinkEffettua(TipoLinkEffettua l) {
		if (l != null && l.getOrdine == this) 
			ManagerEffettua.elimina(l);
	}
	public void inserisciPerManagerEffettua(ManagerEffettua m) {
		if (m != null)
			linkEffettua = m.getLink();
	}
	public void eliminaPerManagerEffettua(ManagerEffettua m) {
		if (m != null)
			linkEffettua = null;
	}
	
	//gestione stato
	public static enum Stato =  {PREPARAZIONE, CONSEGNA, COMPLETATO};
	Stato statocorrente = Stato.Preparazione;
	
	Rider rider;
	public Stato getStato() {return statocorrente;}
	
	public void fired(Evento e) {
		TaskExecutor.getInstance().perform(new OrdineFired(this, e));
	}
}
```

*Sottoclasse OrdinePrioritario*

```jsx
public class OrdinePrioritario extends Ordine {
	private double sovrapprezzo;
	
	public OrdinePrioritario(String c, double s) {
		super(c);
		sovrapprezzo = s;
	}
	public double getSovrapprezzo() {return sovrapprezzo;}
	public void setSovrapprezzo(double s) {this.sovrapprezzo = s;}
}
```

*Associazione Effettua (responsabilità doppia, 1..* ←-→ 1..1)*

```jsx
public class TipoLinkEffettua {
	private Cliente cliente;
	private Ordine ordine;
	
	public TipoLinkEffettua(Cliente c, Ordine o) {
		if (c == null || o == null)
			throw new RuntimeException("link non valido");
		this.cliente = c;
		this.ordine = o;
	}
	public Cliente getCliente() {return cliente;}
	public Ordine getOrdine() {return ordine;}
	
	public boolean equals(Object o) {
		if (o != null && this.getClass().equals(o.getClass())) {
			TipoLinkEffettua l = (TipoLinkEffettua) o;
			return ordine == l.ordine && cliente == l.cliente;
		}
		else return false;
	}
	public int hashCode() {return cliente.hashCode() + ordine.hashCode();}
}
```

```jsx
public class ManagerEffettua {
	private TipoLinkEffettua link;
	private ManagerEffettua(TipoLinkEffettua l) {this.link = l;}
	public TipoLinkEffettua getLink() {return link;}

	public void static inserisci(TipoLinkEffettua l) {
		if (l != null) {
			ManagerEffettua m = new ManagerEffettua(l);
			l.getOrdine.inserisciPerManagerEffettua(m);
			l.getCliente.inserisciPerManagerEffettua(m);
		}
	}
	public void static elimina(TipoLinkEffettua l) {
		if (l != null) {
			ManagerEffettua m = new ManagerEffettua(l);
			l.getOrdine().eliminaPerManagerEffettua(m);
			l.getCliente().eliminaPerManagerEffettua(m);
		}
	}
}
```

*Associazione Contiene (responsabilità singola, 1..* ←-→ 0..*)*

```jsx
public class TipoLinkContiene {
private Pizza pizza;
private Ordine ordine;
private int quantita;

public TipoLinkContiene(Pizza p, Ordine o, int q) {
	if (p == null, o == null)
		throw new RuntimeException("link non valido");
	ordine = o;
	pizza = p;
	quantita = q;
	}
	public int getQuantita() {return quantita;}
	public Ordine getOrdine() {return ordine;}
	public Pizza getPizza() {return pizza;}
	
	public boolean equals(Object o) {
		if (o != null && this.getClass().equals(o.getClass())) {
			TipoLinkContiene l = (TipoLinkContiene) o;
			return ordine == l.ordine && pizza == l.pizza;
		}
		else return false;
	}
	public int hashCode() {return pizza.hashCode() + ordine.hashCode();}
}
```

*Classe OrdineFired*

```jsx
package Ordine;
class OrdineFired implements Task {
	private boolean eseguita = false;
	private Ordine ordine;
	private Evento e;
	
	public OrdineFired(Ordine o, Evento e) {
		ordine = o;
		this.e = e;
	}
	public synchronized void esegui() {
		if (eseguita == true || e.getDestinatario != null && e.getDestinatario() != ordine)
			return;
		eseguita = true;
		switch(ordine.getStato()) {
			case PREPARAZIONE:
				if (e.getClass().equals(Partenza.class)) {
					Partenza p = (Partenza) e;
					ordine.rider = p.getPlayload();
					Environment.nuovoEvento(new Notifica(ordine, ordine.getLinkEffettua().getCliente()));
					ordine.statocorrente = Stato.CONSEGNA;
				}
				break;
			case CONSEGNA:
				if (e.getClass().equals(Consegnato.class)) {
					Consegnato c = (Consegnato) e;
					ordine.setLinkConsegnato(ordine.rider);
					ordine.statocorrente = Stato.COMPLETATO;
				}
				break;
			case COMPLETATO:
				break;
			default: throw new RuntimeException("Stato non trovato");
		}
	}
	public synchronized boolean estEseguita() {return eseguita;}
}
```

*Attività principale*

```jsx
public class AttivitaPrincipale implements Runnable {
	private boolean eseguita = false;
	private HashSet<Rider> insRider;
	private HashSet<Ordini> insOrdini;
	
	public Attività Principale(HashSet<Rider> insR, HashSet<Ordini> insOrdini) {
		insRider = insR;
		insOrdini = insO;
	}
	public synchronized void run() {
		if (eseguita) return;
		eseguita = true;
		
		Verifica v = new Verifica(insRider, insOrdini);
		TaskExecutor.getInstance().perform(v);
		boolean ok = v.getResult();
		
		if (!ok) 
			segnaliIO.richiediAggiornamento();
		else {
			ConsegnaOrdini co = new ConsegnaOrdini(insRider, insOrdini);
			Thread t1 = new Thread(co);
			t1.start();
			Analisi a = new Analisi(insRider, insOrdini);
			Thread t2 = new Thread(a);
			t2.start();
			try {
				t1.join();
				t2.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		String r = a.getResult();
		segnaliIO.report(r);
		}
	}
	public synchronized boolean estEseguita() {return eseguita;}
}
```

*Attività atomica*

```jsx
public class Verifica implements Task {
	private boolean eseguita = false;
	private boolean ok;
	private HashSet<Rider> insRider;
	private HashSet<Ordine> insOrdini;
	
	public Verifica(insO, insR) {
		insRider = insR;
		insOrdini = insO;
	}
	public void synchronized void esegui() {
		if (eseguita) return;
		eseguita = true;
		ok = insRider.size() == insOrdini.size();
	}
	public synchronized boolean estEseguita() {return eseguita;}
	public synchronized boolean getResult() {
		if (!eseguita)
			throw new RuntimeException("attività Verifica non eseguita")
		return ok;
	}
}
```