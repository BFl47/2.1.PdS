# Stati e transizioni - Progetto

Per realizzare gli oggetti “**reattivi**”, cioè con associato un diagramma di stati e transizioni, si considerano gli eventi come messaggi che gli oggetti si scambiano

La **gestione degli eventi** può essere:

- sequenziale, con un unico flusso di controllo
- multi-thread, i flussi dei singolo oggetti sono indipendenti e concorrenti

**Tipo di eventi**: 

- connessioni point-to-point, con mittente e destinatario (il mittente può non dichiararsi quando manda un messaggio)
- connessioni broadcasting, messaggi da inviare a tutti gli oggetti reattivi del sistema

## Pattern Observable-Observer

Pattern seguito dallo scambio degli eventi

| Observable | registered | Observer |
| --- | --- | --- |
| addObservable(Observer) | —————————— | fired() |
| fireAll() |  |  |

**Nota:** la comunicazione Observable-Observer è unidirezionale (responsabilità singola di Observable)

- un **Observable** rappresenta un oggetto osservabile da altri oggetti detti **Observer**
- ogni Observer implementa **fired() per reagire** alle notifiche dell’Observable
- un Observable **registra** i suoi Observer attraverso **addObserver()**
- quando l’Observable vuole comunicare l’avvenimento di qualcosa (**notificare**), chiama attraverso **fireAll()** il metodo fired() di ciascun Observer

Il pattern è implementato attraverso l’interfaccia **Listener**

### Comunicazione bidirezionale

Tutti gli oggetti reattivi ricevono e lanciano eventi

Per realizzare una comunicazione bidirezionale si usa l’oggetto **Environment**, che agisce da canale di comunicazione:

- Tutti gli **oggetti reattivi mandano** all’Environment i propri eventi
- L’**Environment si occupa di inoltrare** ciascun evento al giusto destinatario