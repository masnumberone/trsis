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
@WebServlet(name = "DesignWebpageServlet", urlPatterns = {"/design/webpage"})
public class DesignWebpageServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n" +
                    "<head>" +
                    "<title>Оформить веб-страницу по ГОСТу</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Оформить веб-страницу (интернет-ресурс) для списка литературы по ГОСТу</h1>" +
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

                if (parameterMap.get("titleWebpage")[0].length() >= 1) {
                    literature += parameterMap.get("titleWebpage")[0] + " // ";
                    dataEntered = true;
                }
                if (parameterMap.get("nameWebpage")[0].length() >= 1) {
                    literature += parameterMap.get("nameWebpage")[0] + " ";
                    dataEntered = true;
                }
                if (parameterMap.get("linkWebpage")[0].length() >= 1) {
                    literature += "URL: " + parameterMap.get("linkWebpage")[0] + " ";
                    dataEntered = true;
                }
                if (parameterMap.get("dateWebpage")[0].length() >= 1) {
                    literature += "(дата обращения " + parameterMap.get("dateWebpage")[0] + ").";
                    dataEntered = true;
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
                    "<p><big><b>Заголовок статьи или страницы:</b></big><br>" +
                    "<i>Пример: Наука как искусство</i></p>" +
                    "<p><input name=\"titleWebpage\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Название сайта:</b></big><br>" +
                    "<i>Пример: Ведомости</i></p>" +
                    "<p><input name=\"nameWebpage\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Гиперссылка:</b></big><br>" +
                    "<i>Пример: https://www.vedomosti.ru/</i></p>" +
                    "<p><input name=\"linkWebpage\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<p><big><b>Дата обращения на сайт:</b></big><br>" +
                    "<i>Пример: 01.01.2022</i></p>" +
                    "<p><input name=\"dateWebpage\" style=\"font-size:16px; width:100%;\" /></p>" +

                    "<br><input type=\"submit\" value=\"Оформить веб-страницу\" style=\"font-size:24px; width:40%; display:block; margin:auto;\"></input>" +
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
