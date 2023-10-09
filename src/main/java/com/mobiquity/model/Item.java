package com.mobiquity.model;

/**
 * This class represents an item that can be put into the package.
 * It includes the index, weight, and cost of the item.
 * It implements Comparable interface to sort items based on their cost-to-weight ratio in descending order.
 * Overriding the compareTo method allows for a more efficient solution when sorting items based on their cost-to-weight ratio.
 */
public record Item(int index, double weight, double cost) implements Comparable<Item> {

    /**
     * @param index The index of the item.
     * @param weight The weight of the item.
     * @param cost The cost of the item.
     */

    /**
     * Compares this item with another item based on their cost-to-weight ratio.
     *
     * @param other The other item to be compared with.
     * @return A negative integer, zero, or a positive integer as this item's cost-to-weight ratio
     *         is less than, equal to, or greater than the other item's cost-to-weight ratio.
     */
    @Override
    public int compareTo(Item other) {
        double ratio1 = cost / weight;
        double ratio2 = other.cost / other.weight;
        return Double.compare(ratio2, ratio1);
    }

    /**
     * Checks if this item is equal to another object.
     *
     * @param obj The object to be compared with this item.
     * @return true if this item is equal to the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item item = (Item) obj;
        return index == item.index &&
                Double.compare(item.weight, weight) == 0 &&
                Double.compare(item.cost, cost) == 0;
    }

    /**
     * Returns a hash code value for this item. This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        result = prime * result + index;
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
