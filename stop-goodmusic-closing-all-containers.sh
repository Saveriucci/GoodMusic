# Script per fermare l'applicazione GoodMusic

echo "Halting GOODMUSIC"

# Fermiamo i container dei servizi e dei database, se esistono
echo "Stopping and removing services and databases containers..."

# Lista dei container dei servizi e dei database
containers=("asw-consul" "recensioni-seguite-db" "recensioni-seguite-service" 
            "recensioni-db" "recensioni-service" "connessioni-db" "connessioni-service" 
            "api-gateway-service")

# Fermiamo e rimuoviamo tutti i container specificati
for container in "${containers[@]}"
do
    echo "Stopping and removing container: $container"
    docker stop $container
    docker rm $container
done

echo "removing global network"
docker network rm global-network

echo "All services and containers and networks have been stopped and removed!"
