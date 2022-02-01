package com.amazon.ata.cost;

import com.amazon.ata.types.Material;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WeightedCostStrategy implements CostStrategy{
    public static final BigDecimal MONETARY_COST_STRATEGY_WEIGHT = BigDecimal.valueOf(0.8);//multiply monetary cost
    public static final BigDecimal CARBON_COST_STRATEGY_WEIGHT =  BigDecimal.valueOf(0.2);//multiply carbon cost
    private static final BigDecimal LABOR_COST = BigDecimal.valueOf(0.43);
    private final Map<BigDecimal, CostStrategy> weightedCost = new HashMap<>();
    private Map<Material, BigDecimal> materialCostPerGramCarbon;
    private Map<Material, BigDecimal> materialCostPerGramMonetary;
    private BigDecimal carbonCost;
    private BigDecimal monetaryCost;
    private BigDecimal FINAL_RESULT;





    public WeightedCostStrategy() {
//        materialCostPerGramCarbon = new HashMap<>();
//        materialCostPerGramMonetary = new HashMap<>();
//        //carbon
//        materialCostPerGramCarbon.put(Material.CORRUGATE, BigDecimal.valueOf(0.017));
//        materialCostPerGramCarbon.put(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(0.012));
//        //monetary
//        materialCostPerGramMonetary.put(Material.CORRUGATE, BigDecimal.valueOf(.005));
//        materialCostPerGramMonetary.put(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(.25));


        //multiple monetary cost x 0.8
        //multiply carbon cost by 0.2
    }


    public  WeightedCostStrategy(MonetaryCostStrategy monetaryCostStrategy, CarbonCostStrategy carbonCostStrategy) {
//        weightedCost.put(MONETARY_COST_STRATEGY_WEIGHT, monetaryCostStrategy);
//        weightedCost.put(CARBON_COST_STRATEGY_WEIGHT, carbonCostStrategy);
        carbonCost = carbonCostStrategy.getCosts().multiply(CARBON_COST_STRATEGY_WEIGHT);
        monetaryCost = monetaryCostStrategy.getCosts().multiply(MONETARY_COST_STRATEGY_WEIGHT);

    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        //monetary cost
//        Packaging packaging = shipmentOption.getPackaging();
//        BigDecimal materialCost = this.materialCostPerGramMonetary.get(packaging.getMaterial());
//        BigDecimal cost = packaging.getMass().multiply(materialCost)
//                .add(LABOR_COST);
//
//        //carbon
//        Packaging packaging1 = shipmentOption.getPackaging();
//        BigDecimal materialCostCarbon = this.materialCostPerGramCarbon.get(packaging1.getMaterial());
//
//        BigDecimal cost1 = packaging1.getMass().multiply(materialCostCarbon);
//
//        BigDecimal result = CARBON_COST_STRATEGY_WEIGHT.multiply(cost1).add(MONETARY_COST_STRATEGY_WEIGHT
//                .multiply(cost));

        return new ShipmentCost(shipmentOption, FINAL_RESULT = carbonCost.add(monetaryCost));

    }
}
