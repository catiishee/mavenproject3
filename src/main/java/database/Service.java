/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import react.DatabaseReactor;
import react.LoadFactor;
import reactorReader.TextReader;

/**
 *
 * @author user
 */
public class Service {
    
    String filePathCreate = "createQueries.txt";
    String filePathInsert = "insertQueries.txt";
    String filePathDrop = "dropQueries.txt";
    ReactorRepository reactorRepository = new ReactorRepository();
    LoadFactorRepository loadFactorRepository = new LoadFactorRepository();
    
    public void createDatabase(){
        String createQuery = TextReader.readFileFromResources(filePathCreate);
        new Repository().executeQuery(createQuery);
    }
    
    public void insertDatabase(){
        String insertQuery = TextReader.readFileFromResources(filePathInsert);
        new Repository().executeQuery(insertQuery);
    }
    
    public void dropDatabase(){
        String dropQuery = TextReader.readFileFromResources(filePathDrop);
        new Repository().executeQuery(dropQuery);
    }

    public Map<String, List<DatabaseReactor>> getByRegion() {
        List<DatabaseReactor> reactors = reactorRepository.getAllReactorsByRegion();
        return reactors.stream().collect(Collectors.groupingBy(DatabaseReactor::getAggregation));
    }

    public Map<String, List<DatabaseReactor>> getByCountries() {
        List<DatabaseReactor> reactors = reactorRepository.getAllReactorsByCountry();
        return reactors.stream().collect(Collectors.groupingBy(DatabaseReactor::getAggregation));
    }

    public Map<String, List<DatabaseReactor>> getByOperator() {
        List<DatabaseReactor> reactors = reactorRepository.getAllReactorsByOperator();
        return reactors.stream().collect(Collectors.groupingBy(DatabaseReactor::getAggregation));
    }
    
    public List<LoadFactor> getLoadFactors(){
        return loadFactorRepository.getLoadFactors();
    }
    
}
