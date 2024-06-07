/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aggregation;

import database.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import javax.swing.table.DefaultTableModel;
import react.DatabaseReactor;
import react.LoadFactor;
import react.Reactor;

/**
 *
 * @author user
 */
public class Agregator {
    
    List<LoadFactor> loadFactors;

    public DefaultTableModel computeConsumptionByRegion(Service service,Map<String, List<Reactor>> reactorMap) {
        Map<String, Map<Integer, Float>> regionConsumptionMap = new TreeMap<>();
        Map<String, List<DatabaseReactor>> reactorsByRegion = service.getByRegion();
        loadFactors = service.getLoadFactors();

        for (String region : reactorsByRegion.keySet()) {
            regionConsumptionMap.put(region, calculateReactorConsumption(reactorsByRegion.get(region), service, reactorMap));
        }
        return generateConsumptionTable(regionConsumptionMap, "Регион");
    }

    public DefaultTableModel computeConsumptionByCountry(Service service, Map<String, List<Reactor>> reactorMap) {
        Map<String, Map<Integer, Float>> countryConsumptionMap = new TreeMap<>();
        Map<String, List<DatabaseReactor>> reactorsByCountry = service.getByCountries();
        loadFactors = service.getLoadFactors();

        for (String country : reactorsByCountry.keySet()) {
            countryConsumptionMap.put(country, calculateReactorConsumption(reactorsByCountry.get(country), service, reactorMap));
        }
        return generateConsumptionTable(countryConsumptionMap, "Страна");
    }

    public DefaultTableModel computeConsumptionByOperator(Service service, Map<String, List<Reactor>> reactorMap) {
        Map<String, Map<Integer, Float>> operatorConsumptionMap = new TreeMap<>();
        Map<String, List<DatabaseReactor>> reactorsByOperator = service.getByOperator();
        loadFactors = service.getLoadFactors();

        for (String operator : reactorsByOperator.keySet()) {
            operatorConsumptionMap.put(operator, calculateReactorConsumption(reactorsByOperator.get(operator), service, reactorMap));
        }
        return generateConsumptionTable(operatorConsumptionMap, "Оператор");
    }

    private Map<Integer, Float> calculateReactorConsumption(List<DatabaseReactor> reactors, Service service, Map<String, List<Reactor>> reactorMap) {
        Map<Integer, Float> annualConsumptionMap = new TreeMap<>();
        for (DatabaseReactor reactor : reactors) {
            Reactor reactorData = findReactorFileData(reactor, service, reactorMap);
            if (reactorData == null) {
                continue;
            }
            List<LoadFactor> loadFactorsReactor = loadFactors.stream().filter(l -> l.getReactorId().equals(reactor.getId())).toList();
            float annualConsumption = (float) (reactorData.getTermalCapacity() / reactorData.getBurnup());
            for (int year = 2014; year <= 2024; year++) {
                int currentYear = year;
                Optional<LoadFactor> loadFactorOpt = loadFactorsReactor.stream().filter(lf -> lf.getYear() == currentYear).findFirst();
                if (loadFactorOpt.isPresent() && loadFactorOpt.get().getLoadFactor() != null) {
                    LoadFactor loadFactor = loadFactorOpt.get();
                    annualConsumptionMap.merge(loadFactor.getYear(), annualConsumption * (loadFactor.getLoadFactor() / 100), Float::sum);
                } else {
                    LocalDate connectionDate = reactor.getConnectionDate().toLocalDate();
                    LocalDate shutdownDate = reactor.getShutdownDate() != null
                            ? reactor.getShutdownDate().toLocalDate() : null;

                    if (connectionDate.getYear() > year
                            || (shutdownDate != null && shutdownDate.getYear() < year)) {
                        continue;
                    } else {
                        annualConsumptionMap.merge(year, annualConsumption * 0.85f, Float::sum);
                    }
                }
            }
        }
        return annualConsumptionMap;
    }

    private Reactor findReactorFileData(DatabaseReactor reactor, Service service, Map<String, List<Reactor>> reactorMap) {
        Map<String, String> reactorTypes = Map.ofEntries(
                Map.entry("PHWR", "PHWR"),
                Map.entry("PWR", "PWR"),
                Map.entry("BWR", "BWR"),
                Map.entry("LWGR", "RBMK"),
                Map.entry("GCR", "MAGNOX"),
                Map.entry("FBR", "BN")
        );

        String reactorType = reactor.getType();
        String mappedType = reactorTypes.get(reactorType);

        if (mappedType != null) {
            List<Reactor> allReactorFileData = new ArrayList<>();
            reactorMap.values().forEach(allReactorFileData::addAll);

            for (Reactor reactorFileData : allReactorFileData) {
                if (mappedType.equals(reactorFileData.getName())) {
                    return reactorFileData;
                }
            }
        }
        return null;
    }

    private DefaultTableModel generateConsumptionTable(Map<String, Map<Integer, Float>> consumptionMap, String title) {
        int rowCount = consumptionMap.values().stream().mapToInt(Map::size).sum();
        Object[][] tableData = new Object[rowCount][3];
        Object[] columnNames = {title, "Год", "Объём ежегодного потребления"};

        int rowIndex = 0;
        for (Map.Entry<String, Map<Integer, Float>> entry : consumptionMap.entrySet()) {
            String key = entry.getKey();
            Map<Integer, Float> yearConsumptionMap = entry.getValue();

            for (Map.Entry<Integer, Float> yearEntry : yearConsumptionMap.entrySet()) {
                tableData[rowIndex][0] = key;
                tableData[rowIndex][1] = yearEntry.getKey();
                tableData[rowIndex][2] = yearEntry.getValue();
                rowIndex++;
            }
        }
        return new DefaultTableModel(tableData, columnNames);
    }
}
