/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mavenproject3;

import chain.JsonReaderHandler;
import chain.ReaderHandler;
import chain.XmlReaderHandler;
import chain.YamlReaderHandler;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import react.Reactor;

/**
 *
 * @author user
 */
public class ApplicationService {

    private ReaderHandler readerHandler;
    private Map<String, List<Reactor>> reactorMap;

    public ApplicationService() {
        this.readerHandler = new JsonReaderHandler();
        ReaderHandler secondHandler = new XmlReaderHandler();
        ReaderHandler thirdHandler = new YamlReaderHandler();
        readerHandler.setNext(secondHandler);
        secondHandler.setNext(thirdHandler);

        this.reactorMap = new HashMap<>();
    }

    public void handle(File file) throws IOException {
        List<Reactor> reactors = readerHandler.handle(file);
        reactorMap.put(file.getName(), reactors);

    }

    public DefaultMutableTreeNode getNodes() {
        return convertReactorMapToTreeNode(reactorMap);
    }

    private DefaultMutableTreeNode convertReactorToTreeNode(Reactor reactor) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(reactor.getName());

        DefaultMutableTreeNode propertiesNode = new DefaultMutableTreeNode("Properties");
        propertiesNode.add(new DefaultMutableTreeNode("Burnup: " + reactor.getBurnup()));
        propertiesNode.add(new DefaultMutableTreeNode("Kpd: " + reactor.getKpd()));
        propertiesNode.add(new DefaultMutableTreeNode("Enrichment: " + reactor.getEnrichment()));
        propertiesNode.add(new DefaultMutableTreeNode("Thermal Capacity: " + reactor.getTermalCapacity()));
        propertiesNode.add(new DefaultMutableTreeNode("Electrical Capacity: " + reactor.getElectricalCapacity()));
        propertiesNode.add(new DefaultMutableTreeNode("Lifetime: " + reactor.getLifeTime()));
        propertiesNode.add(new DefaultMutableTreeNode("First Load: " + reactor.getFirstLoad()));

        propertiesNode.add(new DefaultMutableTreeNode("Source: " + reactor.getSource().name()));

        rootNode.add(propertiesNode);

        return rootNode;
    }

    private DefaultMutableTreeNode convertReactorMapToTreeNode(Map<String, List<Reactor>> reactorMap) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Reactors");

        for (Map.Entry<String, List<Reactor>> entry : reactorMap.entrySet()) {
            String group = entry.getKey();
            List<Reactor> reactors = entry.getValue();
            DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group);
            for (Reactor reactor : reactors) {
                DefaultMutableTreeNode reactorNode = convertReactorToTreeNode(reactor);
                groupNode.add(reactorNode);
            }
            rootNode.add(groupNode);
        }

        return rootNode;
    }

}
