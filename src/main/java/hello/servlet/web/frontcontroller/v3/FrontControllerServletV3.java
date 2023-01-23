package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.Controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.Controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.Controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllermap = new HashMap<>();

    public FrontControllerServletV3(){
        controllermap.put("/front-controller/v3/members/new-form",new MemberFormControllerV3());
        controllermap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllermap.put("/front-controller/v3/members/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException {
        System.out.println("FrontControllerServletv3.service");

        String requestURI = req.getRequestURI();
        ControllerV3 controller = controllermap.get(requestURI);
        if(controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //process param into model
        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap);


        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), req, resp);
    }

    private MyView viewResolver(String viewName) {
        MyView view = new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return view;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
