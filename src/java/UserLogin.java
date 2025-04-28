import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.LoginDAO;
import model.Login;
import model.User;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Login login = new Login();
            login.setEmail(email);
            login.setPassword(password);

            LoginDAO loginDAO = new LoginDAO();
            boolean isLogin = loginDAO.loginUser(login, request);  // Ensure loginDAO validates user and sets session

            if (isLogin) {
                // Fetch the full user data and set it in the session
                User user = loginDAO.getUserByLogin(login);  // Get full user data

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);  // Store user object in session
                    session.setAttribute("username", user.getUsername());  // Set username
                    session.setAttribute("role", user.getRole());  // Set role

                    // Redirect based on role
                    String role = user.getRole();
                    if ("customer".equalsIgnoreCase(role)) {
                        // Redirect to customer page
                        response.sendRedirect("/JSP/CustomerHome.jsp");
                    } else if ("staff".equalsIgnoreCase(role)) {
                        // Redirect to staff page
                        response.sendRedirect("/JSP/StaffPanel.jsp");
                    } else {
                        // Default redirect (could be an error page or home page)
                        response.sendRedirect("/JSP/UserHome.jsp");
                    }
                } else {
                    out.println("Login failed! User not found.");
                }
            } else {
                out.println("Login Failed! Invalid email or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}