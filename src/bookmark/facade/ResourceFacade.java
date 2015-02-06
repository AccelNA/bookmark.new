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

    public String getResourceGroup(String resourcegroup) throws Exception {
        return resourceService.getresourcegroup(resourcegroup);   
    }

    public String getResourceGroupdetails(String resourcegroup) throws Exception {
        return resourceService.getResourceGroupdetails(resourcegroup);   
    }

    public boolean updateResourceGroup(String resourcegroup) throws Exception {
        return resourceService.updateResourceGroup(resourcegroup);
        
    }

    public String getResourceDetails(String email) throws Exception {
        return resourceService.getResourceDetails(email);  
    }

    public boolean resourceGroupdelete(String Resourcegroup_id) throws Exception {
        return  resourceService.resourceGroupdelete(Resourcegroup_id);
    }

    public boolean updateResource(String resource) throws Exception {
        return resourceService.updateResource(resource);
        
    }

    public boolean addNote(String note) throws Exception {
        return resourceService.addNote(note);
        
    }

    public Boolean resourcedelete(String Resource_id) throws Exception {
        return  resourceService.resourcedelete(Resource_id);
    }

    public String getActivityDetails(String activity) throws Exception {
        return resourceService.getActivityDetails(activity);
    }

}
