package business.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    @JsonProperty("isAsc")
    private boolean isAsc;
    private String sortingColumn;

    public Order(boolean isAsc, String sortingColumn) {
        this.isAsc = isAsc;
        this.sortingColumn = sortingColumn;
    }
    public Order() {}

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean asc) {
        isAsc = asc;
    }

    public String getSortingColumn() {
        return sortingColumn;
    }

    public void setSortingColumn(String sortingColumn) {
        this.sortingColumn = sortingColumn;
    }
}