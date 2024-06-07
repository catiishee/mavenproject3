/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3;

import aggregation.Agregator;
import chain.JsonReaderHandler;
import chain.ReaderHandler;
import chain.XmlReaderHandler;
import chain.YamlReaderHandler;
import database.Service;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import react.Reactor;

/**
 *
 * @author user
 */
public class ApplicationService {

    private ReaderHandler readerHandler;
    private Map<String, List<Reactor>> reactorMap;
    private Service service;

    public ApplicationService() {
        this.readerHandler = new JsonReaderHandler();
        ReaderHandler secondHandler = new XmlReaderHandler();
        ReaderHandler thirdHandler = new YamlReaderHandler();
        readerHandler.setNext(secondHandler);
        secondHandler.setNext(thirdHandler);

        this.reactorMap = new HashMap<>();
        
        this.service = new Service();
    }

    public void handle(File file) throws IOException {
        List<Reactor> reactors = readerHandler.handle(file);
        reactorMap.put(file.getName(), reactors);

    }

    public DefaultMutableTreeNode getNodes() {
        return convertReactorMapToTreeNode(reactorMap);
    }
    
    public void createDatabase(){
        service.dropDatabase();
        service.createDatabase();
        service.insertDatabase();
    }
    
    public DefaultTableModel showConsumptionByRegion(){
        return new Agregator().computeConsumptionByRegion(service, reactorMap);
    }
    
    public DefaultTableModel showConsumptionByCountry(){
        return new Agregator().computeConsumptionByCountry(service, reactorMap);
    }
    
    public DefaultTableModel showConsumptionByOperator(){
        return new Agregator().computeConsumptionByOperator(service, reactorMap);
    }

    private DefaultMutableTreeNode convertReactorMapToTreeNode(Map<String, List<Reactor>> reactorMap) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Reactors");

        for (Map.Entry<String, List<Reactor>> entry : reactorMap.entrySet()) {
            String group = entry.getKey();
            List<Reactor> reactors = entry.getValue();
            DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group);
            for (Reactor reactor : reactors) {
                DefaultMutableTreeNode reactorNode = reactor.convertReactorToTreeNode();
                groupNode.add(reactorNode);
            }
            rootNode.add(groupNode);
        }

        return rootNode;
    }

}
