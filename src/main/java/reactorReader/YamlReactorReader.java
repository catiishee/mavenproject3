/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reactorReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import react.Reactor;
import react.ReactorSource;

/**
 *
 * @author user
 */
public class YamlReactorReader implements Reader {

    @Override
    public List<Reactor> read(File file) throws IOException {
        Yaml yaml = new Yaml(new Constructor(Map.class));
        List<Reactor> reactors = new ArrayList<>();

        InputStream inputStream = new FileInputStream(file);
        Map<String, Map<String, Object>> data = yaml.load(inputStream);

        data.forEach((key, value) -> {
            Reactor reactor = new Reactor();
            reactor.setName((String) value.get("name"));
            reactor.setBurnup(toDouble(value.get("burnup")));
            reactor.setKpd(toDouble(value.get("kpd")));
            reactor.setEnrichment(toDouble(value.get("enrichment")));
            reactor.setTermalCapacity(toDouble(value.get("termal_capacity")));
            reactor.setElectricalCapacity(toDouble(value.get("electrical_capacity")));
            reactor.setLifeTime(toDouble(value.get("life_time")));
            reactor.setFirstLoad(toDouble(value.get("first_load")));
            reactor.setSource(ReactorSource.YAML);
            reactors.add(reactor);
        });

        return reactors;
    }

    private double toDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        throw new IllegalArgumentException("Expected a number but found: " + obj);
    }
}
