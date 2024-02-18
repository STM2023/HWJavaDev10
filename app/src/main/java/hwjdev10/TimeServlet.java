package hwjdev10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

@WebServlet(value = "/time")
public class TimeServlet  extends HttpServlet{
    private static final String DATE_FORMAT = "yyyy-MM-dd   HH:mm:ss  zzz";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // resp.setIntHeader("Refresh", 5);

        resp.setContentType("text/html");


//time?timezone=UTC%2B2
        String value = req.getParameter("timezone");

        Date dateNow = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat(" yyyy-MM-dd   HH:mm:ss   zzz");

        if (value == null) {
            resp.getWriter().write(format1.format(dateNow));

        } else {
            ZoneId zone = ZoneId.of(value);
            ZonedDateTime time = ZonedDateTime.now(zone);


            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
            resp.getWriter().write(format.format(time));

            resp.getWriter().close();

        }
    }
}