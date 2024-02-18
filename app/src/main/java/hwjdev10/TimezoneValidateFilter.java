package hwjdev10;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@WebFilter(value = "/time/*")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain chain) throws IOException, ServletException {

        String value = req.getParameter("timezone");

        String timeZoneName = TimeZone.getDefault().getDisplayName();//.getID();

        int offset =Integer.parseInt (value.substring(4, value.length()));
        String sign=value.substring(3, 4);
       // System.out.println(" sign ="+sign);
       // System.out.println(" offset "+offset);
        if( value==null |(offset<=18)& (timeZoneName!=null)&("+".equals(sign)|"-".equals(sign))) {
            chain.doFilter(req, resp);
        }
             else {
                resp.setStatus(400);

                resp.setContentType("application/json");
                resp.getWriter().write("{\"Error\": \"Invalid timezone\"}");
                resp.getWriter().close();
            }

    }
}