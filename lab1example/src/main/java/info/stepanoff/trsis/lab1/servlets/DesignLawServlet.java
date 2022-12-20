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
@WebServlet(name = "DesignLawServlet", urlPatterns = {"/design/law"})
public class DesignLawServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>\n" +
                    "<head>" +
                    "<title>Оформить закон, нормативный акт и т.п. по ГОСТу</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Оформить закон, нормативный акт и т.п. для списка литературы по ГОСТу</h1>" +
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

                if (parameterMap.get("typeLaw")[0].length() >= 1) {
                    literature += parameterMap.get("typeLaw")[0];
                    dataEntered = true;
                }
                if (parameterMap.get("nameLaw")[0].length() >= 1) {
                    literature += " \"" + parameterMap.get("nameLaw")[0] + "\"";
                    dataEntered = true;
                }
                if (parameterMap.get("dateLaw")[0].length() >= 1) {
                    literature += " от " + parameterMap.get("dateLaw")[0];
                }
                if (parameterMap.get("numberLaw")[0].length() >= 1) {
                    literature += " № " + parameterMap.get("numberLaw")[0];
                }
                if (parameterMap.get("sourceLaw")[0].length() >= 1) {
                    literature += " // " + parameterMap.get("sourceLaw")[0] + ".";
                }
                if (parameterMap.get("dateSourceLaw")[0].length() >= 1) {
                    literature += " - " + parameterMap.get("dateSourceLaw")[0] + " г.";
                }
                if (parameterMap.get("numberSourceLaw")[0].length() >= 1) {
                    literature += " - № " + parameterMap.get("numberSourceLaw")[0] + ".";
                }
                if (parameterMap.get("numberPublicationLaw")[0].length() >= 1) {
                    literature += " - Ст. " + parameterMap.get("numberPublicationLaw")[0];
                }
                if (parameterMap.get("publicationLaw")[0].length() >= 1) {
                    literature += " с изм. и допол. в ред. от " + parameterMap.get("publicationLaw")[0] + ".";
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
                            "<p><big><b>Тип нормативного акта:</b></big><br>" +
                            "<i>Пример: Закон Российской Федерации</i></p>" +
                            "<p><input name=\"typeLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<p><big><b>Полное название нормативного акта:</b></big><br>" +
                            "<i>Пример: Жилищный кодекс Российской Федерации</i></p>" +
                            "<p><input name=\"nameLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<p><big><b>Дата принятия:</b></big><br>" +
                            "<i>Пример: 01.01.2000</i></p>" +
                            "<p><input name=\"dateLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<p><big><b>Номер нормативного акта:</b></big><br>" +
                            "<i>Пример: 1234-56</i></p>" +
                            "<p><input name=\"numberLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<p><big><b>Оффициальный источник опубликования:</b></big><br>" +
                            "<i>Пример: Собрание законодательства Российской Федерации</i></p>" +
                            "<p><input name=\"sourceLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<p><big><b>Год публикации источника:</b></big><br>" +
                            "<i>Пример: 2020</i></p>" +
                            "<p><input name=\"dateSourceLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<p><big><b>Номер выхода источника:</b></big><br>" +
                            "<i>Пример: 5</i></p>" +
                            "<p><input name=\"numberSourceLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<p><big><b>Номер статьи:</b></big><br>" +
                            "<i>Пример: 15</i></p>" +
                            "<p><input name=\"numberPublicationLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<p><big><b>В редакции от:</b></big><br>" +
                            "<i>Пример: 11.09.2002</i></p>" +
                            "<p><input name=\"publicationLaw\" style=\"font-size:16px; width:100%;\" /></p>" +

                            "<br><input type=\"submit\" value=\"Оформить закон\" style=\"font-size:24px; width:40%; display:block; margin:auto;\"></input>" +
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

