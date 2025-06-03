package servlet;


import dao.MedicoJpaController; 
import dto.Medico;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        // Inicializa una única vez el EntityManagerFactory desde persistence.xml
        emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Establecer tipo de contenido JSON
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String dni = request.getParameter("ndniMedi");
            String password = request.getParameter("passMedi");

            if (dni == null || password == null || dni.trim().isEmpty() || password.trim().isEmpty()) {
                out.print("{\"success\": false, \"message\": \"Faltan parámetros\"}");
                return;
            }

            dni = dni.trim();
            password = password.trim();

            MedicoJpaController controller = new MedicoJpaController(emf);

            boolean valido = controller.validarCredenciales(dni, password);

            if (valido) {
                // Crear sesión y guardar atributo
                HttpSession session = request.getSession(true);
                Medico medico = controller.findMedicoPorDni(dni);
                session.setAttribute("usuario", medico);

                out.print("{\"success\": true, \"redirect\": \"principal.html\"}");
            } else {
                out.print("{\"success\": false, \"message\": \"DNI o contraseña incorrectos\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"success\": false, \"message\": \"Error interno del servidor\"}");
        } finally {
            out.flush();
            out.close();
        }
    }

    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}