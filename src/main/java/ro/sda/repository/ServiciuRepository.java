package ro.sda.repository;

import ro.sda.entity.Serviciu;

public class ServiciuRepository extends AbstractRepository<Serviciu> {


    @Override
    public Class<Serviciu> entityClass() {
        return Serviciu.class;
    }
}
