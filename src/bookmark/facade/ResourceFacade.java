package bookmark.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookmark.service.ResourceService;
import bookmark.service.UserService;

@Service("resourceFacade")
public class ResourceFacade {
    @Autowired
    ResourceService resourceService;

    public boolean addResource(String resource) throws Exception {
        return resourceService.createresource(resource);

    }

    public boolean addResourceGroup(String resourcegroup) throws Exception {
        return resourceService.createResourceGroup(resourcegroup);
        
    }

    public boolean addActivity(String activity) throws Exception {
        return resourceService.addActivity(activity);
        
    }

    public String getResourceGroup(String resourcegroup) throws Exception {
        return resourceService.getresourcegroup(resourcegroup);   
    }

    public String getResourceGroupdetails(String resourcegroup) throws Exception {
        return resourceService.getResourceGroupdetails(resourcegroup);   
    }

    public boolean updateResourceGroup(String resourcegroup) throws Exception {
        return resourceService.updateResourceGroup(resourcegroup);
        
    }

    public String getResourceDetails(String resourcegroup) throws Exception {
        return resourceService.getResourceDetails(resourcegroup);  
    }

    public boolean resourceGroupdelete(String bookmark_resource_groupName) throws Exception {
        return  resourceService.resourceGroupdelete(bookmark_resource_groupName);
    }

    public boolean updateResource(String resource) throws Exception {
        return resourceService.updateResource(resource);
        
    }

    public boolean addNote(String note) throws Exception {
        return resourceService.addNote(note);
        
    }

    public Boolean resourcedelete(String resource) throws Exception {
        return  resourceService.resourcedelete(resource);
    }

    public String getActivityDetails(String activity) throws Exception {
        return resourceService.getActivityDetails(activity);
    }

}
