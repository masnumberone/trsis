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
@WebServlet(name = "DesignMagazineServlet", urlPatterns = {"/design/magazine"})
public class DesignMagazineServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n" +
                    "<head>" +
                    "<title>Оформить статью из журнала по ГОСТу</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Оформить статью из журнала для списка литературы по ГОСТу</h1>" +
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

                if (parameterMap.get("authorMagazine")[0].length() >= 1) {
                    literature += parameterMap.get("authorMagazine")[0];
                }
                if (parameterMap.get("articleMagazine")[0].length() >= 1) {
                    literature += " " + parameterMap.get("articleMagazine")[0] + "//";
                    dataEntered = true;
                }
                if (parameterMap.get("nameMagazine")[0].length() >= 1) {
                    literature += " " + parameterMap.get("nameMagazine")[0] + ". -";
                }
                if (parameterMap.get("yearMagazine")[0].length() >= 1) {
                    literature += " " + parameterMap.get("yearMagazine")[0] + ".";
                }
                if (parameterMap.get("numberMagazine")[0].length() >= 1) {
                    literature += "- №" + parameterMap.get("numberMagazine")[0] + ".";
                }
                if (parameterMap.get("pagesMagazine")[0].length() >= 1) {
                    literature += " С." + parameterMap.get("pagesMagazine")[0] + ".";
                }
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    System.out.println(parameterName + " " + values[0]);

                }

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
                    "<p><input name=\"authorMagazine\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Название статьи:</b></big><br>" +
                    "<i>Пример: Наука как искусство</i></p>" +
                    "<p><input name=\"articleMagazine\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Название журнала:</b></big><br>" +
                    "<i>Пример: Образование и наука</i></p>" +
                    "<p><input name=\"nameMagazine\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Год издания:</b></big><br>" +
                    "<i>Пример: 2020</i></p>" +
                    "<p><input name=\"yearMagazine\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Номер журнала:</b></big><br>" +
                    "<i>Пример: 10</i></p>" +
                    "<p><input name=\"numberMagazine\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Страницы статьи в журнале:</b></big><br>" +
                    "<i>Пример: 25-30</i></p>" +
                    "<p><input name=\"pagesMagazine\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<br><input type=\"submit\" value=\"Оформить статью\" style=\"font-size:24px; width:40%; display:block; margin:auto;\"></input>" +
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
