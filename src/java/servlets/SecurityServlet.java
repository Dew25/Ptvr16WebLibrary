/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
import entity.secure.Permision;
import entity.secure.PermisionName;
import entity.secure.RolePermision;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.PermisionFacade;
import session.PermisionNameFacade;
import session.ReaderFacade;
import session.RoleFacade;
import session.RolePermisionFacade;
import session.UserFacade;
import session.UserRolesFacade;
import utils.Encription;

/**
 *
 * @author Melnikov
 */
@WebServlet(name = "SecutityServlet",loadOnStartup = 1, urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    "/showRegistration",
    "/registration",
    "/showChangePassword",
    "/changePassword",
    "/showPermisionName",
    "/createPermisionName",
    "/showPermisions",
    "/createPermisions",

})
public class SecurityServlet extends HttpServlet {
    @EJB private UserFacade userFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private PermisionNameFacade permisionNameFacade;
    @EJB private PermisionFacade permisionFacade;
    @EJB private RolePermisionFacade rolePermisionFacade;

    @Override
    public void init() throws ServletException {
        List<User> listUsers = userFacade.findAll();
        if(!listUsers.isEmpty()){return;}
        Reader reader = new Reader("juri.melnikov@ivkhk.ee", "Juri", "Melnikov");
        readerFacade.create(reader);
        Encription encription = new Encription();
        String password = encription.getEncriptionPass("admin");
        User user = new User("admin", password, true, reader);
        userFacade.create(user);
        Role role = new Role("ADMINSTRATOR");
        roleFacade.create(role);
        UserRoles ur = new UserRoles();
        ur.setRole(role);
        ur.setUser(user);
        userRolesFacade.create(ur);
        role.setName("USER");
        roleFacade.create(role);
        ur.setRole(role);
        ur.setUser(user);
        userRolesFacade.create(ur);
    }
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = null;
        String path = request.getServletPath();
        if(path != null)
            switch (path) {
                case "/showLogin":
                    request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                    break;
                case "/login":
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    User regUser = userFacade.findUserByLogin(login);
                    if(regUser == null){
                        request.setAttribute("info", "Неправильный логин или пароль!");
                        request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                    }
                    Encription encription = new Encription();
                    String encriptPassword = encription.getEncriptionPass(password);
                    if(!encriptPassword.equals(regUser.getPassword())){
                        request.setAttribute("info", "Неправильный логин или пароль!");
                        request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                    }
                    session = request.getSession(true);
                    session.setAttribute("regUser", regUser);
                    request.setAttribute("info", "Вы вошли!");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                case "/logout":
                    session = request.getSession(false);
                    if(session != null){
                        session.invalidate();
                        request.setAttribute("info", "Вы вышли!");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                    break;
                case "/showRegistration":
                    request.getRequestDispatcher("/showRegistration.jsp").forward(request, response);
                    break;
                case "/registration":
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");
                    String email = request.getParameter("email");
                    login = request.getParameter("login");
                    String password1 = request.getParameter("password1");
                    String password2 = request.getParameter("password2");
                    if(!password1.equals(password2)){
                        request.setAttribute("info", "Несовпадает пароль!");
                        request.getRequestDispatcher("/showRegistration.jsp").forward(request, response);
                    }
                    Reader reader = new Reader(email, name, surname);
                    readerFacade.create(reader);
                    encription = new Encription();
                    encriptPassword = encription.getEncriptionPass(password1);
                    User user = new User(login, encriptPassword, true, reader);
                    userFacade.create(user);
                    UserRoles ur = new UserRoles();
                    ur.setUser(user);
                    Role role = roleFacade.findByName("USER");
                    ur.setRole(role);
                    userRolesFacade.create(ur);
                    request.setAttribute("info", "Регистрация успешна!");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                case "/showChangePassword":
                    session = request.getSession(false);
                    if(session == null){
                        request.setAttribute("info", "Вы должны войти");
                        request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                        break;
                    }
                    regUser = (User) session.getAttribute("regUser");
                    if(regUser == null){
                        request.setAttribute("info", "Вы должны войти");
                        request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                        break;
                    }
                    String username = regUser.getReader().getName()+" "+regUser.getReader().getSurname();
                    request.setAttribute("username", username);
                    login = regUser.getLogin();
                    request.setAttribute("login", login);
                    request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
                    break;
                case "/changePassword":
                    session = request.getSession();
                    regUser = (User) session.getAttribute("regUser");
                    String oldPassword = request.getParameter("oldPassword");
                    encription = new Encription();
                    String encriptOldPassword = encription.getEncriptionPass(oldPassword);
                    if(!encriptOldPassword.equals(regUser.getPassword())){
                        request.setAttribute("info", "Вы должны войти");
                        request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                        break;
                    }
                    String newPassword1 = request.getParameter("newPassword1");
                    String newPassword2 = request.getParameter("newPassword2");
                    if(newPassword1.equals(newPassword2)){
                        regUser.setPassword(encription.getEncriptionPass(newPassword1));
                        userFacade.edit(regUser);
                    }
                    request.setAttribute("info", "Вы успешно изменили пароль");
                    request.getRequestDispatcher("/logout");
                    request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                    break;    
                    
                case "/showPermisionName":
                    List<PermisionName> listPermisionNames=new ArrayList<>();
                    List<Role> listRoles=new ArrayList<>();
                    List<Permision> listPermisions=new ArrayList<>();
                    try {
                        listPermisionNames = permisionNameFacade.findAll();
                        listRoles = roleFacade.findAll();
                        listPermisions = permisionFacade.findAll();
                    } catch (Exception e) {
                        
                    }
                    request.setAttribute("listPermisions", listPermisions);
                    request.setAttribute("listRoles", listRoles);
                    request.setAttribute("listPermisionNames", listPermisionNames);
                    request.getRequestDispatcher("/WEB-INF/showPermisionName.jsp").forward(request, response);
                    break;
                    
                case "/createPermisionName":
                    String pName = request.getParameter("newPermisionName");
                    PermisionName pn = new PermisionName(pName);
                    permisionNameFacade.create(pn);
                    
                    listPermisionNames = permisionNameFacade.findAll();
                    request.setAttribute("listPermisionNames", listPermisionNames);
                    request.getRequestDispatcher("/WEB-INF/showPermisionName.jsp").forward(request, response);
                    break;
                case "/createPermisions":
                    String permisionNameId = request.getParameter("permisionNameId"); 
                    String roleId = request.getParameter("roleId");
                    PermisionName permisionName = permisionNameFacade.find(Long.parseLong(permisionNameId));
                    role = roleFacade.find(Long.parseLong(roleId));
                    Permision permision = new Permision(permisionName, role);
                    RolePermision rp = new RolePermision(permision, role);
                    rolePermisionFacade.create(rp);
                    request.getRequestDispatcher("/WEB-INF/showPermisionName.jsp").forward(request, response);
                    break;
                
            }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
