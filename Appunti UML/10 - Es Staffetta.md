# Es. Staffetta

**Requisiti**

- Consideriamo la seguente applicazione relativa a gare di staffetta: Ogni squadra
(con nome) è formata da 10 giocatori ciascuno con un proprio ordine di gara. Ogni
giocatore (che appartiene a esattamente una squadra) è un 100 metrista con nome e
parametro intero che indica la sua velocità nominale sui 100 metri. Le gare (con
nome) consistono in percorrere 1km usando ogni giocatore per 100 metri. Alle
gare partecipano almeno 2 squadre.
- Un giocatore è inizialmente “in allenamento”. Quando inizia una gara passa allo
stato “in gara”. Ciascun giocatore che è in gara quando riceve il bastone corre
percorrendo un tratto compreso tra 1 e 100 metri (il numero effettivo è random).
Alla fine del tratto percorso se non ha superato i 100 corre per un altro tratto.
Altrimenti, smette di giocare e passa il bastone al successivo giocatore della
squadra (secondo l’ordine di squadra). Quando la gara è finita torna in
allenamento. Il giocatore può essere modificato solo quando è in allenamento.

**Diagramma delle classi**

![diag_classi.jpg](https://github.com/BFl47/2.1.PdS/blob/main/Appunti%20UML/Immagini/10%20-%20diag_classi.jpg)

**Responsabilità sulle associazioni**

| Associazione | Classi | ha Responsabilità? |
| --- | --- | --- |
| contiene | Squadra | Si: M O |
|  | Giocatore | Si: M O |
| partecipa | Squadra | No |
|  | Gara | Si: M O |

M molteplicità, O operazione, R richieste

**Diagramma degli stati e delle transizioni**

![diag_stati gioc.jpg](https://github.com/BFl47/2.1.PdS/blob/main/Appunti%20UML/Immagini/10%20-%20diag_stati_gioc.jpg)

**Specifica degli stati di Giocatore**

InizioSpecificaStatiClasse Giocatore

&nbsp;&nbsp;&nbsp;&nbsp;Stato: {Allenamento, InGara, FineGioco}

&nbsp;&nbsp;&nbsp;&nbsp;Variabili di stato ausiliarie:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     trattopercorso: reale

&nbsp;&nbsp;&nbsp;&nbsp;Stato iniziale:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     statoCorrente = Allenamento\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     trattopercorso= —

FineSpecifica

**Specifica delle transizioni di Giocatore**

InizioSpecificaTransizioniClasse Giocatore

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: Allenamento → InGara
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     inizio

&nbsp;&nbsp;&nbsp;&nbsp;Evento: inizio

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: nessuna

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: this.trattopercorso = 0

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: InGara → InGara\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     bastone[nonarrivato]/bastone{dest: this}

&nbsp;&nbsp;&nbsp;&nbsp;Evento: bastone

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: this.trattopercorso < 100

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: nuovoevento = bastone{mitt = this, dest = this} and this.trattopercorso = pre(this.trattopercorso) + rand[1,101)

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: InGara → FuoriGioco\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     bastone[arrivato]/bastone{dest: prossimo}

&nbsp;&nbsp;&nbsp;&nbsp;Evento: bastone

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: this.trattopercorso ≥ 100

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: sia: s tale che <this, s> in squadra (s è unico); ip =indexOf(squadra(this,s))+1; se ip < 10 allora sia: prossimo tale che indexOf(squadra(prossimo, s)) = ip; nuovoevento = bastone{mitt = this, dest = prossimo}

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: FuoriGioco → Allenamento\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     fine

&nbsp;&nbsp;&nbsp;&nbsp;Evento: fine

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: nessuna

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: —

FineSpecifica

**Realizzazione classe Giocatore**

```jsx
public class Giocatore implements Listener {
	private final String nome;
	private int velocita;
	private TipoLinkContiene link;
	
	public Giocatore(String n, int v) {
		if (v < 1 || v > 100)
			throw new RuntimeException("Eccezione precondizioni");
		nome = n;
		velocita = v;
	}
	public String getNome() {return nome;}
	public void setVelocita(int velocita) {
		if (statocorrente != Stato.ALLENAMENTO)
			throw new RuntimeException("stato non adatto a modifiche");
		this.velocita = velocita;
	}
	public int getVelocita() {return velocita;}
	
	public void inserisciLinkContiene(TipoLinkContiene t) {
		if (t != null && t.getGiocatore() == this)
			ManagerContiene.inserisci(t);
	}
	public void eliminaLinkContiene(TipoLinkContiene t) {
		if (t != null && t.getGiocatore() == this)
			ManagerContiene.elimina(t);
	}
	public TipoLinkContiene getLinkContiene() {
		if (link == null)
			throw RuntimeException("Eccezione molteplicità: deve essere 1..1");
		return link;
	}
	public void inserisciPerManagerContiene(ManagerContiene a) {
		if (a != null && link == null)
			link = a.getLink();
	}
	public void eliminaPerManagerContiene(ManagerContiene a) {
		if (a!= null)
			link = null;
	}
	public String toString() {return nome;}
	
	//gestione stato
	public static enum Stato {ALLENAMENTO, INGIOCO, FINEGIOCO}
	Stato statocorrente = Stato.ALLENAMENTO //visibilità package
	double trattopercorso;                  //visibilità package
	
	public Stato getStato() {return statocorrente;}
	
	public void fired(Evento e) {
		TaskExecutor.getInstance().perform(new GiocatoreFired(this, e));
	}
}
```

**Realizzazione classe GiocatoreFired**

```jsx
class GiocatoreFired implements Task {
	private boolean eseguita = false;
	private Giocatore g;
	private Evento e;
	
	public GiocatoreFired(Giocatore g, Evento e) {
		this.g = g;
		this.e = e;
	}
	public synchronized void esegui() {
	if (eseguita || (e.getDestinatario() != g && e.getDestinatario != null))
		return;
	eseguita = true;
	switch (g.getStato()) {
	case ALLENAMENTO:
		if (e.getClass == Inizio.class) {
			g.trattopercorso = 0;
			g.statocorrente = Stato.INGIOCO;
		}
		break;
	case INGIOCO:
		if (e.getClass() == Bastone.class) {
			if (g.trattopercorso < 100) {
				g.trattopercorso = g.trattopercorso + Math.random() * g.getVelocita() + 1;
				Environment.aggiungiEvento(new Bastone(g, g));
				g.statocorrente = Stato.INGIOCO;
			} else {
				Squadra sc = g.getLinkContiene().getSquadra();
				List<TipoLinkContiene> links = sc.getLinkContiene();
				int i = links.indexOf(new TipoLinkContiene(sc, g));
				if (i+1 < sc.getLinkContiene().size()) {
					Giocatore prossimo = links.get(i+1).getGiocatore();
					Environment.aggiungiEvento(new Bastone(g, prossimo));
				}
				g.statocorrente = Stato.FINEGIOCO;
			}
		}
		break;
	case FINEGIOCO:
		if (e.getClass() == Fine.class)
			g.statocorrente = Stato.ALLENAMENTO;
		break;
	default: throw new RuntimeException("Stato corrente non riconosciuto");
	}
}
```

**Realizzazione classe Squadra**

```jsx
public class Squadra {
	private final String nome;
	private ArrayList<TipoLinkContiene> insiemeLink;
	public final int MINCONTIENE = 10;
	public final int MAXCONTIENE = 10;
	
	public Squadra(String n) {
		nome = n;
		insiemeLink = new ArrayList<TipoLinkContiene>();
	}
	public String getNome() {return nome;}
	
	public void inserisciLinkContiene(TipoLinkContiene t) {
		if (t != null && t.getSquadra == this)
			ManagerContiene.inserisci(t);
		}
	}
	public void eliminaLinkContiene(TipoLinkContiene t) {
		if (t != null && t.getSquadra == this)
			ManagerContiene.elimina(t);
		}
	}
	public List<TipoLinkContiene> getLinkContiene() {
		if (quantiContiene() < MINCONTIENE && quantiContiene() > MAXCONTIENE)
			throw new RuntimeException("Eccezione molteplicità: deve essere 10..10;
		return (ArrayList<TipoLinkContiene>)insiemeLink.clone();
	}
	public void inserisciPerManagerContiene(ManagerContiene a) {
		if (a != null)
			insiemeLink.add(a.getLink());
	}
	public void eliminaPerManagerContiene(ManagerContiene a) {
		if (a != null)
			insiemeLink.remove(a.getLink());
	}
	public int quantiContiene() {return insiemeLink.size();}
}
```

**Realizzazione classe Gara e associazione Partecipa, responsabilità singola,  2..\* ←—→ 0..**

```jsx
public class Gara {
	private final String nome;
	private HashSet<Squadra> insiemeLink;
	public final int MINPARTECIPA = 2;
	
	public Gara(String n) {
		nome = n;
		insiemeLink = new HashSet<Squadra>();
	}
	public String getNome() {return nome;}
	
	public void inserisciLinkPartecipa(Squadra s) {
		if (s != null)
			insiemeLink.add(s);
	}
	public void eliminaLinkPartecipa(Squadra s) {
		if (s != null)
			insiemeLink.remove(s);
	}
	public Set<Squadra> getLinkPartecipa() {
		if (quanteSquadre() < MINPARTECIPA)
			throw RuntimeException("Eccezione molteplicità: deve essere 2..* ");
		return (HashSet<Squadra>) insiemeLink.clone();
	}
	public int quanteSqudre() {return insiemeLink.size();}
}
```

**Realizzazione associazione Contiene, doppia responsabilità, 1..1 ←—→ 10..10**

TipoLinkContiene

```jsx
public class TipoLinkContiene {
	private final Squadra laSquadra;
	private final Giocatore ilGiocatore;
	public TipoLinkContiene(Squadra x, Giocatore y) {
		if (x == null || y == null)
			throw new RuntimeException("Gli oggetti devono essere inizializzati");
		laSquadra = x;
		ilGiocatore = y;
	}
	public boolean equals(Object o) {
		if (o != null && getClass().equals(o.getClass())) {
			TipoLinkContiene l = (TipoLinkContiene)o;
			return l.laSquadra == laSquadra && l.ilGiocatore == ilGiocatore;
		}
		else return false;
	}
	public int hashCode() {return laSquadra.hashCode() + ilGiocatore.hashCode();}
	public Squadra getSquadra() {return laSquadra;}
	public Giocatore getGiocatore() {return ilGiocatore;}
}
```

ManagerContiene

```jsx
public final class ManagerContiene {
	private ManagerContiene(TipoLinkContiene t) {link = t;}
	private TipoLinkContiene link;
	public TipoLinkContiene getLink() {return link;}
	
	public static void inserisci(TipoLinkContiene t) {
		if (t != null) {
			if (t.getGiocatore().getStato() != Giocatore.Stato.ALLENAMENTO)
				throw new RuntimeException("Stato non adatto alla modifica");
			ManagerContiene k = new ManagerContiene(t);
			t.getSquadra().inserisciPerManagerContiene(t);
			t.getGiocatore().inserisciPerManagerContiene(t);
		}
	}
	public static void elimina(TipoLinkContiene t) {
		if (t != null) {
			if (t.getGiocatore().getStato() != Giocatore.Stato.ALLENAMENTO)
				throw new RuntimeException("Stato non adatto alla modifica");
			ManagerContiene k = new ManagerContiene(t);
			t.getSquadra().eliminaPerManagerContiene(t);
			t.getGiocatore().eliminaPerManagerContiene(t);
		}
	}
}
```

**Realizzazione eventi**

Classe Evento

```jsx
public class Evento {
	private Listener mittente;
	private Listener destinatario;

	public Evento(Listener m, Listener d) {
		mittente = m; 
		destinatario = d; // se null, il messaggio e' in broadcasting
	}
	public Listener getMittente() {return mittente;}
	public Listener getDestinatario() {return destinatario;}

	public boolean equals(Object o) {
		if (o != null && getClass().equals(o.getClass())) {
			Evento e = (Evento) o;
			return mittente == e.mittente && destinatario == e.destinatario;
		} else return false;
	}
	public int hashCode() {
		return mittente.hashCode() + destinatario.hashCode();
	}
}
```

Inizio

```jsx
public class Inizio extends Evento {
public Inizio(Listener m, Listener d) {super(m, d);}
public boolean equals(Object o) {return super.equals(o);}
public int hashCode() {
	return super.hashCode() + getClass().hashCode();
}
public String toString() {
	return "Inizio " + getMittente() + " -> " + getDestinatario();
}
```

Bastone

```jsx
public class Bastone extends Evento {
public double trattoPercorso;
public Bastone(Listener m, Listener d) {super(m, d);}
public boolean equals(Object o) {return super.equals(o);}
public int hashCode() {
	return super.hashCode() + getClass().hashCode();
}
public String toString() {
	return "Bastone " + getMittente() + " -> " + getDestinatario();
Bastone
```

Fine

```jsx
public class Fine extends Evento {
public Fine(Listener m, Listener d) {super(m, d);}
public boolean equals(Object o) {return super.equals(o);}
public int hashCode() {
	return super.hashCode() + getClass().hashCode();
}
public String toString() {
	return "Fine " + getMittente() + " -> " + getDestinatario();
```
