package business.models;

public class Condition {
    private String condition;
    private String filteringField;
    private String value;

    public Condition() {}
    public Condition(String condition, String filteringField, String value) {
        this.condition = condition;
        this.filteringField = filteringField;
        this.value = value;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFilteringField() {
        return filteringField;
    }

    public void setFilteringField(String filteringField) {
        this.filteringField = filteringField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}