package business.models;

import java.util.List;

public class FilterRequestBody {
    private List<UserFilterResource> elements;

    public FilterRequestBody(List<UserFilterResource> elements) {
        this.elements = elements;
    }

    public List<UserFilterResource> getElements() {
        return elements;
    }

    public void setElements(List<UserFilterResource> elements) {
        this.elements = elements;
    }
}
