package ca.sait.services;

import ca.sait.dataaccess.RoleDB;
import ca.sait.dataaccess.UserDB;
import ca.sait.models.Role;
import ca.sait.models.User;
import java.util.List;

public class RoleService {
    private RoleDB roleDB = new RoleDB();
    
    public List<Role> getAll() throws Exception {
        List<Role> roles = this.roleDB.getAll();
        return roles;
    }
    
}
