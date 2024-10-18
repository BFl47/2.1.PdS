# Stati e transizioni - Analisi

Il diagramma  degli stati e della transizione viene definito per **una classe,** descrive **l’evoluzione di un generico oggetto** di quella classe e rappresenta le sequenze di stati, le risposte e le azioni che un oggetto attraversa durante la sua vita in risposta agli stimoli ricevuti

Uno **stato** rappresenta una situazione in cui un oggetto ha un insieme di proprietà stabili

Una **transizione** modella un cambiamento di stato ed è denotata da:\
**Evento[Condizione]/Azione**

![diagramma_transizione.jpg](https://github.com/BFl47/2.1.PdS/blob/main/Appunti%20UML/Immagini/07%20-%20diagramma_transizione.jpg)

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

![diagramma_transizione1.jpg](https://github.com/BFl47/2.1.PdS/blob/main/Appunti%20UML/Immagini/07%20-%20diagramma_transizione1.jpg)

*es. Specifica degli stati di Playlist*

**InizioSpecificaStatiClasse Playlist**

&nbsp;&nbsp;&nbsp;&nbsp;Stato: {Attesa, Esecuzione}

&nbsp;&nbsp;&nbsp;&nbsp;Variabili di stato ausiliarie:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     player: Player\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     prossimobrano: intero

&nbsp;&nbsp;&nbsp;&nbsp;Stato iniziale:
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     statoCorrente = Attesa\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     player = —\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     prossimobrano = — `//non definito`

**FineSpecifica**

**Note**: “statoCorrente” denota lo stato attuale dell’oggetto, si aggiorna automaticamente con le transizioni

*es Specifica delle transizioni di Playlist*

**InizioSpecificaTransizioniClasse Playlist**

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: Attesa → Esecuzione\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     **play**(player)[playlist non vuota]/playSong{dest: player}(br)

&nbsp;&nbsp;&nbsp;&nbsp;Evento: play(player: Player)

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: this.contiene non vuoto

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: nuovoevento = playSong{mitt = this, dest = player}(br: Brano) and this.player = player and this.prossimobrano = 0 and <this, br> in contiene and posizione(contiene(this, br)) = this.prossimobrano

**Note:** “evento” denota l’evento ricevuto, “mitt” e “dest” il mittente e il destinatario dell’evento, “nuovoevento” denota l’evento da mandare con l’azione

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: Esecuzione → Esecuzione\
     **done**[ci sono ancora brani da ascoltare]/playSong{dest: player}(br)

&nbsp;&nbsp;&nbsp;&nbsp;Evento: done

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: this.prossimobrano < |{b | <this, b> in contiene}|

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: nuovoevento = playSongo{mitt = this, dest = this.player}(br: Brano) and this.player = pre(this.player) and this.prossimobrano = pre(this.prossimobrano)+1 and <this, br> in contiene and posizione(contiene(this, br)) = this.prossimobrano

&nbsp;&nbsp;&nbsp;&nbsp;Transizione Esecuzione → Attesa\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     **done**[non ci sono brani da ascoltare]

&nbsp;&nbsp;&nbsp;&nbsp;Evento: done

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: this.prossimobrano ≥ |{b | <this,b> in contiene}|

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: this.prossimobrano = — and this.player = —

&nbsp;&nbsp;&nbsp;&nbsp;Transizione Esecuzione → Attesa\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     **reset/stopSong**{dest: player}

&nbsp;&nbsp;&nbsp;&nbsp;Evento: reset

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: nessuna

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: nuovoevento = stopSong{mitt = this, dest = pre(this.player)} and this.prossimobrano = — and this.player = —

**FineSpecifica**

*es. Specifica degli stati di Player*

**InizioSpecificaStatiClasse Player**

&nbsp;&nbsp;&nbsp;&nbsp;Stato: {Pronto, Esecuzione}

&nbsp;&nbsp;&nbsp;&nbsp;Variabili di stato ausiliarie:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     playlist: Playlist\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     brano: Brano

&nbsp;&nbsp;&nbsp;&nbsp;Stato iniziale:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     statoCorrente = Pronto\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     playlist = —\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     brano = —

**FineSpecifica**

*es. Specifica delle transizioni di Player*

**InizioSpecificaTransizioniClasse Player**

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: Pronto → Esecuzione\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     **playSong**{mitt: playlist}(br)

&nbsp;&nbsp;&nbsp;&nbsp;Evento: playSong(br: Brano)

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: nessuna

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: this.playlist = evento.mitt and this.brano = br

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: Esecuzione → Pronto\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     **done**{dest:playlist}

&nbsp;&nbsp;&nbsp;&nbsp;Evento: evento interno generato da this stesso

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: nessuna

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: nessuna\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: nuovoevento = done{mitt = this, dest = pre(this.playlist)} and this.playlist = — and this.brano == —

&nbsp;&nbsp;&nbsp;&nbsp;Transizione: Esecuzione → Pronto\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     **stopSong**

&nbsp;&nbsp;&nbsp;&nbsp;Evento: stopSong

&nbsp;&nbsp;&nbsp;&nbsp;Condizione: nessuna

&nbsp;&nbsp;&nbsp;&nbsp;Azione:\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     pre: evento.mitt = pre(this.playlist)\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     post: this.playlist = — and this.brano = —

**FineSpecifica**
