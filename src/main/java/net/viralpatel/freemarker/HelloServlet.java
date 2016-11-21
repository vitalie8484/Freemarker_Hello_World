package net.viralpatel.freemarker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HelloServlet")
public class HelloServlet extends HttpServlet {
    private static List<User> userList = new ArrayList<>();

    //Just prepare static data to display on screen
    static {
        userList.add(new User("Bill", "Gates"));
        userList.add(new User("Steve", "Jobs"));
        userList.add(new User("Larry", "Page"));
        userList.add(new User("Sergey", "Brin"));
        userList.add(new User("Larry", "Ellison"));
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Put the user list in request and
        //let freemarker paint it.
        request.setAttribute("users", userList);

        request.getRequestDispatcher("/index.ftl").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        if (firstname != null &&
                lastname != null &&
                !firstname.isEmpty() &&
                !lastname.isEmpty())
            userList.add(new User(firstname, lastname));

        doGet(request, response);
    }
}
