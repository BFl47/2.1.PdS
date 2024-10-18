import java.util.HashSet;
import java.util.Set;

public class Persona {
	private String nome;
	private HashSet<Azienda> insiemeLink;
	public Persona(String n) {
		nome = n;
		insiemeLink = new HashSet<Azienda>();}
	public String getNome() {return nome;}
	public void inserisciLinkHaLavorato(Azienda a) {
		if (a != null)
			insiemeLink.add(a);
	}
	public void eliminaLinkHaLavorato(Azienda a) {
		if (a != null)
			insiemeLink.remove(a);
	}
	public Set<Azienda> getLinkHaLavorato() {
		return (HashSet<Azienda>) insiemeLink.clone();
	}
	
}
