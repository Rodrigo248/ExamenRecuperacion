package Servlet;

import dto.Medico;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "listarmedico", urlPatterns = {"/listarMedico"})
public class listarmedico extends HttpServlet {

    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();
        response.setContentType("application/json;charset=UTF-8");

        // Definimos formato de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (PrintWriter out = response.getWriter()) {
            List<Medico> medicos = em.createNamedQuery("Medico.findAll", Medico.class).getResultList();
            System.out.println("Medicos encontrados: " + medicos.size());

            JSONArray jsonArray = new JSONArray();

            for (Medico m : medicos) {
                JSONObject obj = new JSONObject();
                obj.put("codiMedi", m.getCodiMedi());
                obj.put("ndniMedi", m.getNdniMedi());
                obj.put("appaMedi", m.getAppaMedi());
                obj.put("apmaMedi", m.getApmaMedi());
                obj.put("nombMedi", m.getNombMedi());
                if (m.getFechNaciMedi() != null) {
                    obj.put("fechNaciMedi", sdf.format(m.getFechNaciMedi()));
                } else {
                    obj.put("fechNaciMedi", JSONObject.NULL);
                }
                obj.put("logiMedi", m.getLogiMedi());
                jsonArray.put(obj);
            }

            out.print(jsonArray.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("{\"error\":\"Error interno del servidor\"}");
        } finally {
            em.close();
        }
    }

    @Override
    public void destroy() {
        if (emf != null) {
            emf.close();
        }
    }
}
