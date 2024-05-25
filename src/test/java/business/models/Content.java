package business.models;

import java.util.List;

public class Content {
    private List<UserFilterResource> content;
    private Page page;

    // Getters and Setters
    public List<UserFilterResource> getContent() { return content; }
    public void setContent(List<UserFilterResource> content) { this.content = content; }

    public Page getPage() { return page; }
    public void setPage(Page page) { this.page = page; }
}
