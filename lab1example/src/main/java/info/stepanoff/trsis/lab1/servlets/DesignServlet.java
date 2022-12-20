/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.lab1.servlets;

import info.stepanoff.trsis.lab1.model.DataMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

/**
 *
 * @author Pavel.Stepanov
 */
@WebServlet(name = "DesignServlet", urlPatterns = {"/design"})
public class DesignServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (!request.getParameterMap().values().isEmpty()) {
                // поправить условие (не работает)

                DataMap.getInstance().removeLiterature(request.getRemoteAddr(),Integer.parseInt(request.getParameter("indexDelete")) - 1);

            }

            out.println("<!DOCTYPE html>\n" +
                    "<head>" +
                    "<title>Оформить список литературы</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Оформить список литературы по ГОСТу</h1>" +
                    "<table width=\"100%\";><tbody><tr>" +
                    "<td width=\"15%\"><center><h3><a href=\"../design\">Список литературы</a></h3></center></td>" +
                    "<td width=\"15%\"><center><h3><a href=\"design/book\">Оформление книги</a></h3></center></td>" +
                    "<td width=\"15%\"><center><h3><a href=\"design/webpage\">Оформление веб-страницы (интернет-ресурс)</a></h3></center></td>" +
                    "<td width=\"15%\"><center><h3><a href=\"design/magazine\">Оформление статьи из журнала</a></h3></center></td>" +
                    "<td width=\"15%\"><center><h3><a href=\"design/law\">Оформление закона, нормативного акта и т.п.</a></h3></center></td>" +
                    "</tr></tbody></table>");

            if(DataMap.getInstance().containsKey(request.getRemoteAddr())) {
                out.println("<p style=\"font-size:20px; width:80%; margin:auto;\"><big>Список литературы:</big>" +
                        "<p style=\"font-size:20px; width:80%; margin:auto;\">");
                int count = 1;
                for(String literature : DataMap.getInstance().getData(request.getRemoteAddr())) {
                    out.println("[" + count + "] " + literature + "<br>");
                    count++;
                }
                out.println(
                        "<br><form name=\"deleteList\" method=\"post\" style=\"width:80%; margin:auto;\">" +
                        "<select name=\"indexDelete\" style=\"font-size:20px;\">");
                for (int i = 1; i < count; i++) {
                    out.println("<option>" + i + "</option>");
                }
                out.println(
                        "</select><input type=\"submit\" value=\"Удалить элемент списка\" style=\"font-size:20px;\"></input></form></p>"
                );

            } else {
                out.println("<p style=\"font-size:20px; width:80%; margin:auto;\"><big>Пример оформленного списка:</big>" +
                        "<p style=\"font-size:20px; width:80%; margin:auto;\">" +
                        "[1] Иванов И.М., Петров С.Н. Наука как искусство. - 3-е изд. - СПб: Просвещение, 2020. - 109 с.<br>" +
                        "[2] Наука как искусство // Ведомости URL: https://www.vedomosti.ru/ (дата обращения 01.01.2022).<br>" +
                        "[3] Закон Российской Федерации \"Жилищный кодекс Российской Федерации\" от 01.01.2000 № 1234-56 // Собрание законодательства Российской Федерации. - 2020 г. - № 5. - Ст. 15 с изм. и допол. в ред. от 11.09.2002.<br>" +
                        "</p>");
            }

            out.println("</body></html>");
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



}
