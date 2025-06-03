package servlet;

import dao.MedicoJpaController;
import dto.Medico;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "cambiarclave", urlPatterns = {"/cambiarclave"})
public class cambiarclave extends HttpServlet {

    private MedicoJpaController medicoJpaController;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");
        medicoJpaController = new MedicoJpaController(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtenemos el usuario desde la sesión
        HttpSession session = request.getSession();
        Medico medico = (Medico) session.getAttribute("usuario");

        response.setContentType("application/json;charset=UTF-8");

        if (medico == null) {
            response.getWriter().write("{\"success\":false,\"message\":\"Sesión no válida. Inicie sesión de nuevo.\"}");
            return;
        }

        String claveActual = request.getParameter("viejaClave");
        String nuevaClave = request.getParameter("nuevaClave");
        String confirmarClave = request.getParameter("confirmarClave");

        if (claveActual == null || nuevaClave == null || confirmarClave == null ||
            claveActual.isEmpty() || nuevaClave.isEmpty() || confirmarClave.isEmpty()) {
            response.getWriter().write("{\"success\":false,\"message\":\"Todos los campos son requeridos.\"}");
            return;
        }

        if (!nuevaClave.equals(confirmarClave)) {
            response.getWriter().write("{\"success\":false,\"message\":\"La nueva clave y la confirmación no coinciden.\"}");
            return;
        }

        // Verificar clave actual
        if (!BCrypt.checkpw(claveActual, medico.getPassMedi())) {
            response.getWriter().write("{\"success\":false,\"message\":\"La clave actual es incorrecta.\"}");
            return;
        }

        try {
            // Hashear nueva clave
            String nuevaClaveHasheada = BCrypt.hashpw(nuevaClave, BCrypt.gensalt());

            // Actualizar y guardar
            medico.setPassMedi(nuevaClaveHasheada);
            medicoJpaController.edit(medico);

            response.getWriter().write("{\"success\":true,\"message\":\"Contraseña actualizada correctamente.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"success\":false,\"message\":\"Error interno del servidor.\"}");
        }
    }
}
