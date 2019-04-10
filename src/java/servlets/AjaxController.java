/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import securitylogic.RoleLogic;
import session.ReaderFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;
import utils.Encription;
import utils.PagePathLoader;

/**
 *
 * @author melnikov
 */
@WebServlet(name = "AjaxController", urlPatterns = {"/ajax/*"})
public class AjaxController extends HttpServlet {
    @EJB private ReaderFacade readerFacade;
    @EJB private UserFacade userFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;
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
        RoleLogic rl = new RoleLogic();
        Calendar c = new GregorianCalendar();
        
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Войдите!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        User regUser = (User) session.getAttribute("regUser");
        if(regUser == null){
            request.setAttribute("info", "Войдите!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        Boolean isRole = rl.isRole(RoleLogic.ROLE.MANAGER.toString(), regUser);
        if(!isRole){
            request.setAttribute("info", "Вы должны быть менеджером!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        String path = request.getRequestURI();
        String[] paths = path.split("/");
        path = paths[paths.length - 1];
        switch (path) {
            case "getListReaders":
                List<Reader> listReaders = readerFacade.findAll();
                String json = new Gson().toJson(listReaders);
                try (PrintWriter out = response.getWriter()) {
                    out.print(json);
                }
                break;
            case "registration":
                List<String> params = new Gson().fromJson(request.getReader(), ArrayList.class);
               
                String name = params.get(0);
                String surname = params.get(1);
                String email = params.get(2);
                String login = params.get(3);
                String password1 = params.get(4);
                String password2 = params.get(5);
                if(!password1.equals(password2)){
                    request.setAttribute("info", "Несовпадает пароль!");
                    request.getRequestDispatcher(PagePathLoader.getPagePath("showRegistration")).forward(request, response);
                }
                Reader reader = new Reader(email, name, surname);
                readerFacade.create(reader);
                Encription encription = new Encription();
                String encriptPassword = encription.getEncriptionPass(password1);
                User user = new User(login, encriptPassword, true, reader);
                userFacade.create(user);
                UserRoles ur = new UserRoles();
                ur.setUser(user);
                Role role = roleFacade.findByName(RoleLogic.ROLE.USER.toString());
                ur.setRole(role);
                userRolesFacade.create(ur);

                json = new Gson().toJson("Пользователь добавлен успешно");
                try (PrintWriter out = response.getWriter()) {
                    out.print(json);
                }
                break;    
            default:
                try (PrintWriter out = response.getWriter()) {
                    out.print("Error!");
                };
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
