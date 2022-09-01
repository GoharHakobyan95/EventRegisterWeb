package servlet;

import manager.EventManager;
import model.Event;
import model.EventType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/events/add")
public class AddEventServlet extends HttpServlet {

   private EventManager eventManager = new EventManager();
   private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addEvent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String place = req.getParameter("place");
        Boolean isOnline = Boolean.valueOf(req.getParameter("isOnline"));
        Double price = Double.valueOf(req.getParameter("price"));
        EventType eventType = EventType.valueOf(req.getParameter("eventType"));
        String eventDateStr = req.getParameter("eventDate");

        Event event = null;
        try {
            event = Event.builder()
                    .name(name)
                    .place(place)
                    .isOnline(isOnline)
                    .price(price)
                    .type(eventType)
                    .eventDate(sdf.parse(eventDateStr))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eventManager.addEvent(event);
        resp.sendRedirect("/events");
    }
}
