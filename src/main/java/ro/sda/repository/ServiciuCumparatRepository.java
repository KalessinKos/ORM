package ro.sda.repository;

import ro.sda.entity.ServiciuCumparat;

public class ServiciuCumparatRepository extends AbstractRepository<ServiciuCumparat> {


    @Override
    public Class<ServiciuCumparat> entityClass() {
        return ServiciuCumparat.class;
    }
}
