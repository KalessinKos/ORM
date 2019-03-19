package ro.sda.repository;

import ro.sda.entity.Produs;

public class ProdusRepository extends AbstractRepository<Produs> {


    @Override
    public Class<Produs> entityClass() {
        return Produs.class;
    }
}
