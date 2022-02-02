package com.amazon.ata.cost;

import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class WeightedCostStrategy implements CostStrategy {
    private static final BigDecimal LABOR_COST = BigDecimal.valueOf(0.43);

    private Map<Material, BigDecimal> materialCostPerGramCarbon;
    private Map<Material, BigDecimal> materialCostPerGramMonetary;

    /**
     * Default ctor.
     */
    public WeightedCostStrategy() {


    }

    /**
     * WeightedCostStrategy constructor with params.
     * @param monetaryCostStrategy - cost strategy.
     * @param carbonCostStrategy - cost strategy.
     */
    public WeightedCostStrategy(MonetaryCostStrategy monetaryCostStrategy, CarbonCostStrategy carbonCostStrategy) {

    }




    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {

        Packaging packaging = shipmentOption.getPackaging();
        materialCostPerGramCarbon = new HashMap<>();
        materialCostPerGramMonetary = new HashMap<>();

        materialCostPerGramCarbon.put(Material.CORRUGATE, BigDecimal.valueOf(0.017));
        materialCostPerGramCarbon.put(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(0.012));

        materialCostPerGramMonetary.put(Material.CORRUGATE, BigDecimal.valueOf(.005));
        materialCostPerGramMonetary.put(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(.25));

        BigDecimal cost1 = packaging.getMass().multiply(materialCostPerGramCarbon.get(packaging.getMaterial())
                .multiply(BigDecimal.valueOf(0.2)));
        BigDecimal monetaryMaterialCost = this.materialCostPerGramMonetary.get(packaging.getMaterial());
        BigDecimal cost2 = packaging.getMass().multiply(monetaryMaterialCost).add(LABOR_COST)
                .multiply(BigDecimal.valueOf(0.8));
        BigDecimal result = cost1.add(cost2);

        return new ShipmentCost(shipmentOption, result);

    }
}
