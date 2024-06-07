/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package react;

import java.sql.Date;

/**
 *
 * @author user
 */
public class DatabaseReactor {
    
    private Long id;
    private String type;
    private Integer thermalCapacity;
    private String reactorName;
    private Date connectionDate;
    private Date shutdownDate;
    private String aggregation;

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getThermalCapacity() {
        return thermalCapacity;
    }

    public void setThermalCapacity(Integer thermalCapacity) {
        this.thermalCapacity = thermalCapacity;
    }

    public String getReactorName() {
        return reactorName;
    }

    public void setReactorName(String reactorName) {
        this.reactorName = reactorName;
    }

    public Date getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(Date connectionDate) {
        this.connectionDate = connectionDate;
    }

    public Date getShutdownDate() {
        return shutdownDate;
    }

    public void setShutdownDate(Date shutdownDate) {
        this.shutdownDate = shutdownDate;
    }
}


