/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package react;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author user
 */
public class Reactor {
    
    private String name;
    private double burnup;
    private double kpd;
    private double enrichment;
    private double termal_capacity;
    private double electrical_capacity;
    private double life_time;
    private double first_load;
    private ReactorSource source;

    public Reactor() {
    }

    public String getName() {
        return name;
    }

    public double getBurnup() {
        return burnup;
    }

    public double getKpd() {
        return kpd;
    }

    public double getEnrichment() {
        return enrichment;
    }

    public double getTermalCapacity() {
        return termal_capacity;
    }

    public double getElectricalCapacity() {
        return electrical_capacity;
    }

    public double getLifeTime() {
        return life_time;
    }

    public double getFirstLoad() {
        return first_load;
    }

    public ReactorSource getSource() {
        return source;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setBurnup(double burnup) {
        this.burnup = burnup;
    }

    public void setKpd(double kpd) {
        this.kpd = kpd;
    }

    public void setEnrichment(double enrichment) {
        this.enrichment = enrichment;
    }

    public void setTermalCapacity(double termalCapacity) {
        this.termal_capacity = termalCapacity;
    }

    public void setElectricalCapacity(double electricalCapacity) {
        this.electrical_capacity = electricalCapacity;
    }

    public void setLifeTime(double lifeTime) {
        this.life_time = lifeTime;
    }

    public void setFirstLoad(double firstLoad) {
        this.first_load = firstLoad;
    }

    public void setSource(ReactorSource source) {
        this.source = source;
    }
   
    public DefaultMutableTreeNode convertReactorToTreeNode() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(getName());

        DefaultMutableTreeNode propertiesNode = new DefaultMutableTreeNode("Properties");
        propertiesNode.add(new DefaultMutableTreeNode("Burnup: " + getBurnup()));
        propertiesNode.add(new DefaultMutableTreeNode("Kpd: " + getKpd()));
        propertiesNode.add(new DefaultMutableTreeNode("Enrichment: " + getEnrichment()));
        propertiesNode.add(new DefaultMutableTreeNode("Thermal Capacity: " + getTermalCapacity()));
        propertiesNode.add(new DefaultMutableTreeNode("Electrical Capacity: " + getElectricalCapacity()));
        propertiesNode.add(new DefaultMutableTreeNode("Lifetime: " + getLifeTime()));
        propertiesNode.add(new DefaultMutableTreeNode("First Load: " + getFirstLoad()));

        propertiesNode.add(new DefaultMutableTreeNode("Source: " + getSource().name()));

        rootNode.add(propertiesNode);

        return rootNode;
    }
}
