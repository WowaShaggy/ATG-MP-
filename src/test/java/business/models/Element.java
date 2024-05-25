package business.models;

import java.util.List;

public class Element {
    private List<Condition> conditions;

    // Getters and Setters
    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
