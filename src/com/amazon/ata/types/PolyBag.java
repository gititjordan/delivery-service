package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

public class PolyBag extends Packaging  {
     BigDecimal volume;
//    private BigDecimal volume = Math.ceil(Math.sqrt(volume.doubleValue()) * 0.6);
    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;

    }

    public BigDecimal getVolume() {
        return volume;
    }

    @Override
    public boolean canFitItem(Item item) {
        BigDecimal itemVolume = item.getLength().multiply(item.getWidth().multiply(item.getHeight()));
        return this.volume.compareTo(itemVolume) > 0;
    }

    @Override
    public BigDecimal getMass() {
        double mass = Math.ceil(Math.sqrt(volume.doubleValue()) * 0.6);
        return BigDecimal.valueOf(mass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMass());

    }
    //    BigDecimal mass = Math.ceil(Math.sqrt(volume) * 0.6);
}
