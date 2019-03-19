package ro.sda.repository;

import ro.sda.entity.Client;

public class ClientRepository extends AbstractRepository<Client> {


    @Override
    public Class<Client> entityClass() {
        return Client.class;
    }
}
