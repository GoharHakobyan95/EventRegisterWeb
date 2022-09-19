package servlet;

import manager.EventManager;
import manager.UserManager;
import model.Event;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/events")
public class EventsServlet extends HttpServlet {
    private EventManager eventManager = new EventManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        double minPrice = Double.parseDouble(req.getParameter("minPrice") == null ? "0" : req.getParameter("minPrice"));
        double maxPrice = Double.parseDouble(req.getParameter("maxPrice") == null ? "0" : req.getParameter("maxPrice"));

        List<Event> eventList;
        if (keyword != null && !keyword.equals("")) {
            req.setAttribute("keyword", keyword);
            eventList = eventManager.search(keyword);
        } else if (minPrice > 0 || maxPrice > 0 && minPrice < maxPrice) {
            req.setAttribute("keyword", "");
            eventList = eventManager.filter(minPrice, maxPrice);
        } else {
            req.setAttribute("keyword", "");
            eventList = eventManager.getAll();
        }

        for (Event event : eventList) {
            List<User> users = userManager.getUsersByEventId(event.getId());
            event.setUsers(users);
        }

        req.setAttribute("events", eventList);
        req.getRequestDispatcher("/WEB-INF/events.jsp").forward(req, resp);
    }

}
