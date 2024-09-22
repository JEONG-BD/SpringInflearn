package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name="requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        prindHeader(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private static void printStartLine(HttpServletRequest request) {
        String method = request.getMethod();
        System.out.println("request.getMethod() = " + request.getMethod());
        System.out.println("request.getProtocol() = " + request.getProtocol());
        System.out.println("request.getScheme() = " + request.getScheme());
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure());
    }

    private static void prindHeader(HttpServletRequest request){
        System.out.println("----Header - Start  ---");
        Enumeration<String> headerNames = request.getHeaderNames();

//        while (headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            System.out.println("headerName = " + headerName);
//        }
//

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println(headerName + " : " + headerName));
        System.out.println("----Header - End    ---");
        System.out.println();

    }

    private static void printHeaderUtils(HttpServletRequest request){

        System.out.println("Header");
        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServerPort() = " + request.getServerPort());
        System.out.println();

        System.out.println("[Accept Language]");
        request.getLocales().asIterator().forEachRemaining(locale -> System.out.println("locals = " + locale ));
        System.out.println("request.getLocale() = " + request.getLocale());

        System.out.println("[Cookie]");
        if (request.getCookies() != null){
            for (Cookie cookie :request.getCookies())
                System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
        System.out.println();
    }

    private void printEtc(HttpServletRequest request) {

        System.out.println("ETC");
        System.out.println("[Remote Information]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());

        System.out.println();

        System.out.println("[Local Information]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
    }

}
