# Stati e transizioni - Analisi

Il diagramma  degli stati e della transizione viene definito per **una classe,** descrive **l’evoluzione di un generico oggetto** di quella classe e rappresenta le sequenze di stati, le risposte e le azioni che un oggetto attraversa durante la sua vita in risposta agli stimoli ricevuti

Uno **stato** rappresenta una situazione in cui un oggetto ha un insieme di proprietà stabili

Una **transizione** modella un cambiamento di stato ed è denotata da:
**Evento[Condizione]/Azione**

![diagramma_transizione.jpg](Stati%20e%20transizioni%20-%20Analisi%204015334c1cd04584b68bc6805ed5b9ab/diagramma_transizione.jpg)

**Significato:**

- **se** l’oggetto si trova nello **stato S1** e riceve l’**evento E** e la **condizione C** è verificata
- **allora** attiva l’esecuzione dell’**azione A** e passa nello **stato S2**

Talvolta i cambiamenti di stato non hanno bisogno di azioni, ad esempio quando sono automatici

### Stato

Lo stato di un oggetto racchiude le **proprietà** (di solito statiche) dell’oggetto e i **valori correnti** di tali proprietà

| ● | stato iniziale |
| --- | --- |
| ◉ | stato finale |

**Stato composto**

Uno stato composto (o **macro-stato**) è uno stato che ha un nome e contiene a sua volta un diagramma, con un suo stato iniziale
I **sottostati** ereditano le transizioni in uscita del macro-stato

### Transizione

Ogni transizione connette due stati

- Il diagramma corrisponde ad un automa **deterministico,** in cui un evento è un input, mentre un’azione è un output
- La condizione è detta **guardia**
- L’evento è (quasi) sempre presente, mentre condizione e azione sono opzionali

Per rappresentare dei processi che l’oggetto esegue senza cambiare stato, si utilizza la notazione: **do/attività**

### Metodo

- Individuare gli stati di interesse
- Individuare le transizioni
- Individuare le attività
- Determinare gli stati iniziali e finali
- Controllo → correggere, modificare, estendere

Dati diversi **oggetti reattivi**, cioè con associato diagramma stati-transizioni, la cui interazione è basata su scambio esplicito di **eventi** (con un **mittente** e un **destinatario**) di tipo:

- messaggi **punto-punto**, un oggetto manda un messaggio ad un altro oggetto
- messaggi in **broadcasting**, un oggetto manda un messaggio a tutti gli altri oggetti

Gli eventi inoltre possono avere **parametri** con specifico contenuto informativo (payload del messaggio)

Un’**azione** può lanciare a sua volta un evento per un altro oggetto o in broadcasting

**Osservazioni**

Nel diagramma degli stati e transizioni, si identificherà l’azione stessa con l’evento lanciato

Ad ogni transizione bisogna specificare:

- quali eventi sono **recepiti** e **lanciati** (a chi)
- come cambiano le **variabili di stato ausiliarie** associate allo stato dell’oggetto
Le variabili di stato ausiliarie servono solo per la corretta realizzazione delle azioni associate alle varie transizioni, non bisogna confonderle con gli attributi dell’oggetto stesso

Il diagramma è sempre corredato da una **specifica** che chiarisce in dettaglio la semantica

*es. Diagramma delle classi*

| Playlist | contiene | Brano |
| --- | --- | --- |
| Nome: stringa | 0..* ———————— 0..* | Nome: stringa |
| DurataTot(): int | {ordered} | Durata: int |
|  |  | NomeFile: stringa |
|  | Player |  |
|  |  |  |

**Nota**: la classe Player non contiene alcun dato, ma ad essa è associato un diagramma stati e transizioni

*es. Diagramma degli stati e delle transizioni*

![diagramma_transizione1.jpg](Stati%20e%20transizioni%20-%20Analisi%204015334c1cd04584b68bc6805ed5b9ab/diagramma_transizione1.jpg)

*es. Specifica degli stati di Playlist*

**InizioSpecificaStatiClasse Playlist**

Stato: {Attesa, Esecuzione}

Variabili di stato ausiliarie:
     player: Player
     prossimobrano: intero

Stato iniziale:
     statoCorrente = Attesa
     player = —
     prossimobrano = — `//non definito`

**FineSpecifica**

**Note**: “statoCorrente” denota lo stato attuale dell’oggetto, si aggiorna automaticamente con le transizioni

*es Specifica delle transizioni di Playlist*

**InizioSpecificaTransizioniClasse Playlist**

Transizione: Attesa → Esecuzione
     **play**(player)[playlist non vuota]/playSong{dest: player}(br)

Evento: play(player: Player)

Condizione: this.contiene non vuoto

Azione:
     pre: nessuna
     post: nuovoevento = playSong{mitt = this, dest = player}(br: Brano) and this.player = player and this.prossimobrano = 0 and <this, br> in contiene and posizione(contiene(this, br)) = this.prossimobrano

**Note:** “evento” denota l’evento ricevuto, “mitt” e “dest” il mittente e il destinatario dell’evento, “nuovoevento” denota l’evento da mandare con l’azione

Transizione: Esecuzione → Esecuzione
     **done**[ci sono ancora brani da ascoltare]/playSong{dest: player}(br)

Evento: done

Condizione: this.prossimobrano < |{b | <this, b> in contiene}|

Azione:
     pre: nessuna
     post: nuovoevento = playSongo{mitt = this, dest = this.player}(br: Brano) and this.player = pre(this.player) and this.prossimobrano = pre(this.prossimobrano)+1 and <this, br> in contiene and posizione(contiene(this, br)) = this.prossimobrano

Transizione Esecuzione → Attesa
     **done**[non ci sono brani da ascoltare]

Evento: done

Condizione: this.prossimobrano ≥ |{b | <this,b> in contiene}|

Azione:
     pre: nessuna
     post: this.prossimobrano = — and this.player = —

Transizione Esecuzione → Attesa
     **reset/stopSong**{dest: player}

Evento: reset

Condizione: nessuna

Azione:
     pre: nessuna
     post: nuovoevento = stopSong{mitt = this, dest = pre(this.player)} and this.prossimobrano = — and this.player = —

**FineSpecifica**

*es. Specifica degli stati di Player*

**InizioSpecificaStatiClasse Player**

Stato: {Pronto, Esecuzione}

Variabili di stato ausiliarie:
     playlist: Playlist
     brano: Brano

Stato iniziale:
     statoCorrente = Pronto
     playlist = —
     brano = —

**FineSpecifica**

*es. Specifica delle transizioni di Player*

**InizioSpecificaTransizioniClasse Player**

Transizione: Pronto → Esecuzione
     **playSong**{mitt: playlist}(br)

Evento: playSong(br: Brano)

Condizione: nessuna

Azione:
     pre: nessuna
     post: this.playlist = evento.mitt and this.brano = br

Transizione: Esecuzione → Pronto
     **/done**{dest:playlist}

Evento: evento interno generato da this stesso

Condizione: nessuna

Azione:
     pre: nessuna
     post: nuovoevento = done{mitt = this, dest = pre(this.playlist)} and this.playlist = — and this.brano == —

Transizione: Esecuzione → Pronto
     **stopSong**

Evento: stopSong

Condizione: nessuna

Azione:
     pre: evento.mitt = pre(this.playlist)
     post: this.playlist = — and this.brano = —

**FineSpecifica**