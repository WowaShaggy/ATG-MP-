package business.models;

public class Order {
    private boolean isAsc;
    private String sortingColumn;

    public Order(boolean isAsc, String sortingColumn) {
        this.isAsc = isAsc;
        this.sortingColumn = sortingColumn;
    }


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