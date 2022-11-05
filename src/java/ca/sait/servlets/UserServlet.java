package ca.sait.servlets;

import ca.sait.models.Role;
import ca.sait.models.User;
import ca.sait.services.RoleService;
import ca.sait.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alexa
 */
public class UserServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        
        try {
            UserService userService = new UserService();
            List<User> users = userService.getAll();
            request.setAttribute("users", users);
            
            RoleService roleService = new RoleService();
            List<Role> roles = roleService.getAll();
            request.setAttribute("roles", roles);
            
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try{
            UserService us = new UserService();
            List<User> users = us.getAll();
            RoleService rs = new RoleService();
            List<Role> roles = rs.getAll();
            
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
        } catch (Exception ex){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         if (action != null && action.equals("add")) {
             try {
                 String email = request.getParameter("email");
                 boolean active = request.getParameter("active") != null;
                 String firstName = request.getParameter("firstName");
                 String lastName = request.getParameter("lastName");
                 String password = request.getParameter("password");
                 String roleName = request.getParameter("role");
                 int roleId = 0;
                 
                 RoleService rs = new RoleService();
                 List<Role> roleList = rs.getAll();
                 
                 for(Role role : roleList){
                    if(role.getName().equals(roleName)){
                        roleId = role.getId();
                    }
                }
                
                if(roleId == 0){
                    throw new Exception("Invalid role");
                }
                
                Role role = new Role(roleId, roleName);
                UserService us = new UserService();
                us.insert(email, active, firstName, lastName, password, role);
                request.setAttribute("message", "User has been updated");
                 
             } catch (Exception ex) {
                 Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("message", "User was not updated");
             }
         }
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}