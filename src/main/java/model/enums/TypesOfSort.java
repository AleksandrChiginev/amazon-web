package model.enums;

public enum TypesOfSort {
    LOW_PRICE("Price: Low to High"),
    HIGH_PRICE("Price: High to Low"),
    REVIEW("Avg. Customer Review"),
    ARRIVALS("Newest Arrivals");

    public final String value;

    TypesOfSort(String value) {
        this.value = value;
    }
}
