package com.litwnb.spring6webclient.client;

import com.litwnb.spring6webclient.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {
    @Autowired
    BeerClient client;

    @Test
    void testCreateBeer() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        BeerDTO newDto = BeerDTO.builder()
                .price(BigDecimal.TEN)
                .beerName("Mongo Bobs")
                .beerStyle("IPA")
                .quantityOnHand(300)
                .upc("124345")
                .build();

        client.createBeer(newDto)
                .subscribe(dto -> {
                    System.out.println(dto);
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByBeerStyle() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.getBeersByStyle("Pale Ale")
                .subscribe(dto -> {
                    System.out.println(dto);
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerById() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos()
                .flatMap(dto -> client.getBeerById(dto.getId()))
                .subscribe(idDto -> {
                    System.out.println(idDto.getBeerName());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerDto() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDtos().subscribe(dto -> {
            System.out.println(dto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerJsonNode() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeersJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetMap() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeersMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeers() {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeers().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}