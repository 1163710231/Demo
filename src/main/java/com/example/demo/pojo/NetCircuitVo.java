package com.example.demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class NetCircuitVo {
    private Integer circuitId;

    private String circuitNo;

    private String circuitName;

    private String stationName;

    private Integer systemId;

    private Integer departId;

    private Integer stationId;

    private Integer validState;
    private String vState;

    private BigDecimal heatArea;

    private BigDecimal secondDesignFlow;

    private Integer secondSupplyFlow;

    private Integer secondTotalSupplyFlow;

    private Integer secondReturnFlow;

    private Integer secondTotalReturnFlow;

    private Integer secondHeatQuantity;

    private Integer secondTotalHeat;

    private Integer secondSupplyPress;

    private Integer secondReturnPress;

    private Integer secondDiffPress;

    private Integer secondSupplyTempr;

    private Integer secondReturnTempr;

    private BigDecimal designSupplyTempr;

    private BigDecimal designReturnTempr;

    private Integer fixedPress;

    private BigDecimal designHeatIndex;

    private BigDecimal actualHeatIndex;

    private Integer waterReplenish;

    private Integer totalReplenish;

    private Integer inverterFrequency;

    private Integer circulatePumpPower;

    private Integer replenishPumpPower;

    private Integer waterLevel;

    private Integer isDelete;
}
