package com.litwnb.spring6webclient.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.litwnb.spring6webclient.model.BeerDTO;
import reactor.core.publisher.Flux;

import java.util.Map;

public interface BeerClient {
    Flux<String> listBeers();

    Flux<Map> listBeersMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDTO> listBeerDtos();
}
