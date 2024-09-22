package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        totalParameter(request);
        oneParameter(request);
        multiParameter(request);
    }

    private static void multiParameter(HttpServletRequest request) {
        System.out.println("[이름이 같은 Parameter - start]");
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            System.out.println("username = " + username);
        }
        System.out.println("[이름이 같은 Parameter - end]");

    }

    private static void oneParameter(HttpServletRequest request) {
        System.out.println("[단일 Parameter - start]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 Parameter - end]");
    }

    private static void totalParameter(HttpServletRequest request) {
        System.out.println("[전체 Parameter - start]");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println("paramName = " + request.getParameter(paramName)));
        System.out.println("[전체 Parameter - end]");
        System.out.println();
    }
}
