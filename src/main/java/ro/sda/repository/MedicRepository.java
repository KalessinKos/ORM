package ro.sda.repository;

import ro.sda.entity.Medic;

public class MedicRepository extends AbstractRepository<Medic> {


    @Override
    public Class<Medic> entityClass() {
        return Medic.class;
    }
}
