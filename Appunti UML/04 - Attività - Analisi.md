# Attività - Analisi

Oltre alle informazioni di interesse nel dominio dell’applicazione, bisogna anche rappresentare come l’applicazione accede, modifica e usa tali informazioni, cioè i **processi** di interesse

Il **diagramma delle attività** descrive le attività che il sistema deve supportare in termini di:

- **Attività atomiche** realizzate nel sistema
- **Flusso di lavoro** (workflow) in cui esse sono coinvolte

**Attività**

Le attività agiscono, accedono e aggiornano le istanze del diagramma delle classi, modificando lo stato corrente del sistema, cioè le informazione memorizzate; inoltre accedono all’esterno per fornire e ricevere informazioni dagli utilizzatori

Un’attività è **trasversale** rispetto alle classi, cioè **coinvolge più classi**

## Diagramma delle Attività

Esso è composto da:

- **Attività atomiche**, che rappresentano le operazioni (**task**) sul dominio dell’applicazione
- **Flusso di controllo** tra le attività atomiche
- **Segnali di I/O** (input/output) che realizzano lo scambio tra sistema e utente

| **Attività** |  |
| --- | --- |
| \<nome\> | attività atomiche (con input ed output): ha un punto di ingresso e un punto di uscita |
| \<nome\>   □ → □ | attività composta (sottoprocesso) |
| ● ◉ | simboli di inizio e fine (impropriamente stati) |

Nota: le attività hanno un **solo** punto di ingresso e un **solo** punto di uscita

| **Costrutti per controllo di flusso sequenziale** |  
| --- |
| transizione, con condizione opzionale |
| punto di decisione (split) |
| punto di merge (join) |

Si possono rappresentare **if-else**, **while**, **do-while**

| **Costrutti per il controllo di flusso concorrente** |  
| --- |                               
| fork (and split) |
| (merge and) join |

Per ogni flusso (**thread**) di esecuzione vi è un “esecutore” che gestisce l’avanzamento del processo

- nel **fork**, in numero di thread viene **aumentato**, inizialmente è unico e successivamente ci sarà un thread per ogni transizione in uscita
- nel **join** il numero di thread viene **diminuito**, inizialmente c’è un thread per ogni transizione in entrata e successivamente ci sarà un unico thread d’uscita
    - il **thread d’uscita** può partire solo dopo che sono terminati tutti gli altri (join)
    - il join **sincronizza** l’esecuzione dei vari rami del processo

**Differenza tra fork e punto di decisione:** Nel punto di decisione solo una delle transizione in uscita viene eseguita, mentre nel fork vengono eseguite tutte contemporaneamente

**Differenza tra join e punto di merge:** Nel punto di merge solo una transizione entrante è significativa, mentre nel join tutte le transizioni entrante devono essere completate per proseguire

**Swimlane**

Per mettere in evidenza quali azioni sono eseguite da un dato attore, si devono introdurre le swimlane (”corsia di nuoto”), graficamente è una linea verticale

**FlowFinale**

⊗ simbolo di terminazione di un ramo concorrente, serve a segnalare che un ramo concorrente della computazione va a chiudersi

| **Costrutti per gli eventi** |  |
| --- | --- |
| ▢ (tratteggiato) ↯ | regione interrompibile (per raggruppare attività e transizioni che supportano la terminazione del flusso di esecuzione) |
| ⌛︎ at() | genera un evento per sincronizzarsi con una condizione esterna |

| **Input/Output verso l’esterno** |  
| --- |
|invia segnale (in output) verso l’esterno e si prosegue senza aspettare risposte (**write**) |
|ricevi segnale (in input), si resta bloccati finchè non esso non arriva (**read**) |
