package ro.sda.app;

import ro.sda.entity.*;
import ro.sda.repository.*;
import ro.sda.util.HibernateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class App {



    public static void main(String[] args) throws ParseException {

        UomRepository uomRepo = new UomRepository();
        ProdusRepository prodRepo = new ProdusRepository();
        ServiciuRepository serviciuRepo = new ServiciuRepository();
        MedicRepository medicRepo = new MedicRepository();
        //ClientRepository clientRepo = new ClientRepository();
        ServiciuCumparatRepository serviciuCumparatRepo = new ServiciuCumparatRepository();
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");


        GenericRepository<Client> clientRepo = new GenericRepository<>(Client.class);

        clientRepo.read(12);
        clientRepo.createUpdate(new Client());


        HibernateUtil.getEM();

        Uom uom = new Uom();
        uom.setText("g");
        uom = uomRepo.createUpdate(uom);


        Uom uom2 = new Uom();
        uom2.setText("ml");
        uom2 = uomRepo.createUpdate(uom2);


        Produs prod = new Produs();
        prod.setNume("prod1");
        prod.setPret(1.1);
        prod.setStoc(11);
        prod.setCantitate(1.11);
        prod.setUom(uom);
        prod = prodRepo.createUpdate(prod);

        Produs prod2 = new Produs();
        prod2.setNume("prod2");
        prod2.setPret(2.2);
        prod2.setStoc(22);
        prod2.setCantitate(2.22);
        prod2.setUom(uom2);
        prod2 = prodRepo.createUpdate(prod2);

        prod = prodRepo.createUpdate(prod);
        prod2 = prodRepo.createUpdate(prod2);



        Serviciu serviciu = new Serviciu();
        serviciu.setDenumire("serv1");
        serviciu.setProduse(Arrays.asList(prod));

        Serviciu serviciu2 = new Serviciu();
        serviciu2.setDenumire("serv2");
        serviciu2.setProduse(Arrays.asList(prod, prod2));


        serviciu = serviciuRepo.createUpdate(serviciu);
        serviciu2 = serviciuRepo.createUpdate(serviciu2);



        Medic medic = new Medic();
        medic.setNume("med1Name");
        medic.setPrenume("med1Prenume");
        medic.setTelefon("11111111111");
        medic.setServicii(Arrays.asList(serviciu));

        Medic medic2 = new Medic();
        medic2.setNume("med2Name");
        medic2.setPrenume("med2Prenume");
        medic2.setTelefon("22222222222");
        medic2.setServicii(Arrays.asList(serviciu, serviciu2));

        medic = medicRepo.createUpdate(medic);
        medic2 = medicRepo.createUpdate(medic2);


        ServiciuCumparat serviciuCumparat = new ServiciuCumparat();
        serviciuCumparat.setDataAleasa(fmt.parse("11/11/2019"));
        serviciuCumparat.setServiciu(serviciu);

        ServiciuCumparat serviciuCumparat2 = new ServiciuCumparat();
        serviciuCumparat2.setDataAleasa(fmt.parse("22/02/2019"));
        serviciuCumparat2.setServiciu(serviciu2);

        serviciuCumparat = serviciuCumparatRepo.createUpdate(serviciuCumparat);
        serviciuCumparat2 = serviciuCumparatRepo.createUpdate(serviciuCumparat2);




        Client client = new Client();
        client.setNume("client1Nume");
        client.setPrenume("client1Prenume");
        client.setTelefon("client1Tel");
        client.setServiciiCumparate(Arrays.asList(serviciuCumparat));


        Client client2 = new Client();
        client2.setNume("client2Nume");
        client2.setPrenume("client2Prenume");
        client2.setTelefon("client2Tel");
        client2.setServiciiCumparate(Arrays.asList(serviciuCumparat2));


        client = clientRepo.createUpdate(client);
        client2 = clientRepo.createUpdate(client2);


        HibernateUtil.closeEMF();

    }

}
