package org.intro.examen.model;

import java.util.List;

public class StatResponse {
    private int totalItems;
    private double averageRate;
    private List<Item> items;

    public StatResponse() {
    }

    public int getTotalItems() {
        return this.totalItems;
    }

    public double getAverageRate() {
        return this.averageRate;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof StatResponse)) return false;
        final StatResponse other = (StatResponse) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getTotalItems() != other.getTotalItems()) return false;
        if (Double.compare(this.getAverageRate(), other.getAverageRate()) != 0) return false;
        final Object this$items = this.getItems();
        final Object other$items = other.getItems();
        if (this$items == null ? other$items != null : !this$items.equals(other$items)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof StatResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getTotalItems();
        final long $averageRate = Double.doubleToLongBits(this.getAverageRate());
        result = result * PRIME + (int) ($averageRate >>> 32 ^ $averageRate);
        final Object $items = this.getItems();
        result = result * PRIME + ($items == null ? 43 : $items.hashCode());
        return result;
    }

    public String toString() {
        return "StatResponse(totalItems=" + this.getTotalItems() + ", averageRate=" + this.getAverageRate() + ", items=" + this.getItems() + ")";
    }
}
