echo "Running GOODMUSIC"

#creazione rete globale
echo "creating global network"
docker network create global-network

# Avvia Consul utilizzando Docker Compose
echo "Starting Consul service..."
cd /home/vagrant/projects/asw-goodmusic && docker-compose -f docker-compose.yml up -d

# Attendere qualche secondo affinché Consul sia pronto
sleep 4

# Avvia i vari servizi tramite Docker Compose
echo "Starting recensioni service..."
cd /home/vagrant/projects/asw-goodmusic/recensioni && docker-compose -f docker-compose-recensioni.yml up -d

echo "Starting connessioni service..."
cd /home/vagrant/projects/asw-goodmusic/connessioni && docker-compose -f docker-compose-connessioni.yml up -d

echo "Starting recensioni-seguite service..."
cd /home/vagrant/projects/asw-goodmusic/recensioni-seguite && docker-compose -f docker-compose-recensioni-seguite.yml up -d

echo "Starting api-gateway service..."
cd /home/vagrant/projects/asw-goodmusic/api-gateway && docker-compose -f docker-compose-api-gateway.yml up -d

echo "All services are up and running!"
