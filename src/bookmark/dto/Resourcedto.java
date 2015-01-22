package bookmark.dto;

public class Resourcedto {
    private String resourceDescription;
    private String resourcePath;
    private String resourceGroupName;
    private String resourcePriority;
    private String resourceName;
    private String createdDate;
    
    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getResourceDescription() {
        return resourceDescription;
    }
    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }
    public String getResourcePath() {
        return resourcePath;
    }
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }
    public String getResourceGroupName() {
        return resourceGroupName;
    }
    public void setResourceGroupName(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName;
    }
    public String getResourcePriority() {
        return resourcePriority;
    }
    public void setResourcePriority(String resourcePriority) {
        this.resourcePriority = resourcePriority;
    }
    
}
