# GOODMUSIC

Progetto del corso di Analisi e progettazione del software per l'anno accademico 2024-2025. 

Il progetto presenta un uso di tecnologie come:

    1.Consul per la scoperta dei servizi
    2.Kafka per la comunicazione asincrona
    3.Postgres per la gestione dei dati persistenti per ogni microservizio ad eccezione del microservizio "filtro"
    4.Docker per la containerizzazione dei servizi
    5.Docker compose per l'orchestrazione dei container e delle sue repliche
    6.Kubernetes per l'orchestrazione di container e delle sue repliche
    7.Framework Spring Cloud Gateway per l'instradamento delle rotte (già presente)
    8.Libreria Spring Cloud Load Balancer per la gestione del carico di lavoro per ogni replica dei microservizi

L'architettura proposta si basa su una serie di tecnologie moderne e scalabili che permettono la costruzione di un sistema robusto e facilmente manutenibile. Tuttavia, come con ogni soluzione, ci sono pro e contro che devono essere valutati in base alle specifiche necessità e al carico di lavoro dell'applicazione.

    Punti di Forza:
        Scalabilità: L'uso di Kafka e la containerizzazione via Docker offrono un'ottima base per la scalabilità.
        Affidabilità: Grazie a Spring Cloud e Consul, la scoperta dei servizi e la gestione dei fallimenti è semplificata.
        Modularità: I microservizi permettono una gestione separata delle diverse funzionalità (Connessioni, Recensioni, Filtro), aumentando la manutenibilità.

    Aree di Miglioramento:
        Single Point of Failure: Il sistema dipende fortemente da Consul e dal Kafka broker, che potrebbero diventare un collo di bottiglia o punto di fallimento.
        Gestione della Complessità: La configurazione e il monitoraggio di un sistema basato su numerosi microservizi, ciascuno con un proprio database, possono risultare complessi.
        Performance: Sebbene Kafka sia estremamente potente, il tempo di latenza nella comunicazione tra microservizi asincroni potrebbe essere un fattore limitante in scenari ad alta performance.

L'adozione di una soluzione come questa è particolarmente vantaggiosa per sistemi che necessitano di un alto livello di scalabilità e resilienza, ma richiede un'attenta gestione e monitoraggio per evitare problemi legati alla complessità e al mantenimento delle dipendenze tra i vari componenti.	
	