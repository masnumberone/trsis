/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.lab1.servlets;

import info.stepanoff.trsis.lab1.model.DataMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 *
 * @author Pavel.Stepanov
 */
@WebServlet(name = "DesignBookServlet", urlPatterns = {"/design/book"})
public class DesignBookServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n" +
                    "<head>" +
                    "<title>Оформить книгу по ГОСТу</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Оформить книгу для списка литературы по ГОСТу</h1>" +
                    "<table width=\"100%\";><tbody><tr>" +
                    "<td width=\"15%\"><center><h3><a href=\"../design\">Список литературы</a></h3></center></td>" +
                    "<td width=\"15%\"><center><h3><a href=\"../design/book\">Оформление книги</a></h3></center></td>" +
                    "<td width=\"15%\"><center><h3><a href=\"../design/webpage\">Оформление веб-страницы (интернет-ресурс)</a></h3></center></td>" +
                    "<td width=\"15%\"><center><h3><a href=\"../design/magazine\">Оформление статьи из журнала</a></h3></center></td>" +
                    "<td width=\"15%\"><center><h3><a href=\"../design/law\">Оформление закона, нормативного акта и т.п.</a></h3></center></td>" +
                    "</tr></tbody></table>");

            if (!request.getParameterMap().values().isEmpty()) {
                // поправить условие (не работает)

                Map<String, String[]> parameterMap = request.getParameterMap();
                String literature = new String();
                boolean dataEntered = false;

                if (parameterMap.get("authorBook")[0].length() >= 1) {
                    literature += parameterMap.get("authorBook")[0] + " ";
                    dataEntered = true;
                }
                if (parameterMap.get("nameBook")[0].length() >= 1) {
                    literature += parameterMap.get("nameBook")[0] + ". - ";
                    dataEntered = true;
                }
                if (parameterMap.get("numberBook")[0].length() >= 1) {
                    literature += parameterMap.get("numberBook")[0] + " изд. - ";
                }
                if (parameterMap.get("cityBook")[0].length() >= 1) {
                    literature += parameterMap.get("cityBook")[0] + ": ";
                }
                if (parameterMap.get("publishBook")[0].length() >= 1) {
                    literature += parameterMap.get("publishBook")[0] + ", ";
                    dataEntered = true;
                }
                if (parameterMap.get("yearBook")[0].length() >= 1) {
                    literature += parameterMap.get("yearBook")[0] + ". - ";
                }
                if (parameterMap.get("pageBook")[0].length() >= 1) {
                    literature += parameterMap.get("pageBook")[0] + " с.";
                }
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    System.out.println(parameterName + " " + values[0]);

                }

//                out.println("<h2>Кто прочитает этот текст, должен Масяну сырную шаверму</h2><br>");

                // output to console
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    System.out.println(parameterName + " " + values[0]);

                }

                if(dataEntered) {
                    out.println("<p style=\"width:80%; margin:auto;\"><big>Источник оформлен:</big><br>" + literature + "</p><br>");
                    DataMap.getInstance().addData(request.getRemoteAddr(),literature);
                }
            }




            out.println(
                    "<form name=\"data\" method=\"post\" style=\"width:80%; margin:auto;\">" +
                    "<p><big><b>Фамилии и инициалы авторов:</b></big><br>" +
                    "<i>Пример: Иванов И.М., Петров С.Н.</i></p>" +
                    "<p><input name=\"authorBook\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Название книги:</b></big><br>" +
                    "<i>Пример: Наука как искусство</i></p>" +
                    "<p><input name=\"nameBook\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Номер издания:</b></big><br>" +
                    "<i>Пример: 3-е</i></p>" +
                    "<p><input name=\"numberBook\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Город издательства:</b></big><br>" +
                    "<i>Пример: М., СПб. - сокращёно с точкой, другие города - полностью без точки</i></p>" +
                    "<p><input name=\"cityBook\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Название издательства:</b></big><br>" +
                    "<i>Пример: Просвещение</i></p>" +
                    "<p><input name=\"publishBook\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Год издания:</b></big><br>" +
                    "<i>Пример: 2020</i></p>" +
                    "<p><input name=\"yearBook\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Количество страниц:</b></big><br>" +
                    "<i>Пример: 999</i></p>" +
                    "<p><input name=\"pageBook\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<br><input type=\"submit\" value=\"Оформить книгу\" style=\"font-size:24px; width:40%; display:block; margin:auto;\"></input>" +
                    "</form>" +
                    "</body></html>");
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
