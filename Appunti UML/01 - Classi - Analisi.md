# Classi - Analisi

La fase di analisi è la fase del ciclo di sviluppo del software caratterizzata da:

- **Input**: requisiti raccolti
- **Output**: schema concettuale (modello di analisi)
- **Obiettivo**: costruire un modello completo, preciso, rigoroso, ma anche leggibile e indipendente da linguaggi di programmazione, che sarà un riferimento per tutte le fasi successive del ciclo di vita del software

## Schema concettuale

- **Diagramma delle classi e degli oggetti**\
descrive le classi e le loro proprietà
- **Diagramma delle attività**\
descrive le funzionalità fondamentali che il sistema deve realizzare
- **Diagramma degli stati e delle transizioni**\
descrive, per le classi significative, il tipico ciclo di vita delle sue istanze
- **Documenti di specifica**\
uno per ogni classe, descrivono quali condizioni devono soddisfare i programmi che realizzano il sistema

## Linguaggio UML

In questo corso si farà riferimento solo ad un sottoinsieme di meccanismi previsti in UML per descrivere il diagramma delle classi

### **Classi in UML**

Una classe modella un insieme di oggetti omogenei (istanze della classe) ai quali sono associate proprietà statiche e dinamiche (operazioni). Ogni classe è descritta da un **nome** e un **insieme di proprietà “locali”**.\
*es. classe Libro*

Tra un oggetto istanza di *C* e la *classe C* si traccia un arco **Istance-of**\
*es. Iliade: Libro*

Un **attributo** modella una proprietà locale ed è caratterizzato da:\
Nome dell’attributo : tipo dell’attributo\
*es. Titolo : stringa, NumPagine : int*

### **Associazioni in UML binarie**

Caso: tra due classi

Un’associazione tra due classi modella una proprietà di **entrambe le classi**.\
Le istanze di associazioni si chiamano **link** ed ogni link è implicitamente identificato dalla coppia di oggetti che esso rappresenta.

Alcune volte si specifica il **verso** per il nome dell’associazione, che non corrisponde con la navigabilità! \
È possibile assegnare due nomi con i relativi versi alla stessa associazione\
*es. Persona lavora in → Azienda, Persona ← ha dipendente Azienda*

Il **ruolo** è un’informazione che specifica il ruolo che una classe gioca nell’associazione\
es. Persona **dipendente** lavora in **→** Azienda\
In generale è opzionale se l’associazione è simmetrica\
*es. Stato con associazione “confina-con”*\
ma è **obbligatorio** se l’associazione insiste più volte sulla stessa classe e rappresenta una relazione non simmetrica, per eliminare ambiguità\
*es. Persona con associazione “genitore-figlio”\
<genitore: x, figlio: y>*

**Attributi di associazione**

Un attributo è una **funzione** che associa ad ogni link un valore di un determinato tipo\
*es. Studente → Corso con associazione “Esame”\
Esame ha come attributo Voto: int*

**Vincoli di molteplicità**

Si definiscono solo in associazioni binarie

Possibili molteplicità:

- 0 .. *     nessun vincolo, si può anche omettere
- 0 .. 1     al massimo una
- 1.. *      minimo una
- 1 .. 1     esattamente una
- 0 .. y     nessun limite inferiore, al massimo y
- x .. *      minimo x, nessun limite superiore
- x .. y      minimo x, al massimo y

**Molteplicità di attributi**

Un attributo T della classe C con molteplicità diversa da {1..1} si dice **multivalore**\
_es. Persona con attributi: CodFiscale: stringa, NumTel: int {0..*}\
indica che una persona può avere un numero qualunque di numeri di telefono_

**Associazioni ordinate**

Si può semplificare la descrizione con l’asserzione **{ordered}**\
_es. Graduatoria 0..* formataDa → 0..* Persona\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {ordered}\
indica che le istanze di Graduatoria sono formate da un gruppo ordinato di istanze di Persona_\
Nel caso in cui bisogna poter distinguere la posizione, si può introdurre la classe Slot

### **Associazioni in UML n-arie**

Caso: tra n insiemi

Ogni istanza di un’associazione n-aria è un **link n-ario** (una ennupla)\
Anche le associazione n-arie possono avere attributi (un valore per attributo)\
*es. link n-ario Esame ha come attributi Voto = 28, Data = 20/12/01*

Vincolo di molteplicità in linguaggio testuale con un commento

### Generalizzazione in UML

La generalizzazione coinvolge una superclasse ed una o più sottoclassi (classi derivate)

**Ereditarietà**

Ogni proprietà della superclasse è anche proprietà della sottoclasse e non si riporta esplicitamente nel diagramma\
Discorso equivalente per associazioni e molteplicità

Le generalizzazioni possono essere:

- **{disjoint}**\
_es. Maschio e Femmina disgiunta\
     Studente e Lavoratore non è disgiunta_\
non è possibile definire una classe che sia sottoclasse di Maschio e Femmina
- **{complete}**\
_es. Maschio e Femmina completa\
     Studente e Lavoratore non è completa_

**Specializzazione**

- **di un attributo:** \
_es Persona ha come attributo Età: 0..120\
    Anziano (sottoclasse di P) ha Età: 70..120_
- **di un’associazione:**\
si possono ridefinire molteplicità più specifiche

### Operazioni

Le classi sono caratterizzate anche da proprietà **dinamiche,** definite tramite operazioni

**Definizione** tramite **segnatura** \
nome (nome parametri: classe del parametro): tipo dell’eventuale risultato\
alfa (X: T1, …, Xn: Tn): T\
*es. operazione di Persona: Età(oggi: data): int*

Un’operazione viene chiamata tramite **oggetto di invocazione**\
*es. p.Età(oggi)*
dove p è un oggetto della classe Persona\
quindi c’è sempre in gioco un oggetto della classe in cui l’operazione è definita

**Attenzione**\
In questa fase si definiscono le operazioni che caratterizzano concettualmente la classe, le operazione orientate alla realizzazione del software non devono essere definite in questa fase

---
