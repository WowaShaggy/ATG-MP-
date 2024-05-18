package business.models;

import java.util.List;

public class Content {
    private List<CreateFilterRQ> content;
    private Page page;

    // Getters and Setters
    public List<CreateFilterRQ> getContent() { return content; }
    public void setContent(List<CreateFilterRQ> content) { this.content = content; }

    public Page getPage() { return page; }
    public void setPage(Page page) { this.page = page; }
}
