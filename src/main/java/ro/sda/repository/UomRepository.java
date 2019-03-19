package ro.sda.repository;

import ro.sda.entity.Uom;

public class UomRepository extends AbstractRepository<Uom> {


    @Override
    public Class<Uom> entityClass() {
        return Uom.class;
    }
}
