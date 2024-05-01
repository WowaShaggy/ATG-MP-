package business.models;

import java.util.ArrayList;
import java.util.List;

public class CreateFilterRQ {
    private List<Condition> conditions;
    private String description;
    private String name;
    private List<Order> orders;
    private String type;

    public CreateFilterRQ() {
        this.conditions = new ArrayList<>();
        this.orders = new ArrayList<>();
    }


    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}