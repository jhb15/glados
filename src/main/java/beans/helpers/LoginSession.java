package beans.helpers;

import oauth.GatekeeperLogin;
import oauth.gatekeeper.GatekeeperInfo;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginSession {

    @EJB
    private GatekeeperLogin loginBean;

    private static final String userParam = "userId";
    private static final String ACCESS_STATE = "access";

    private String userId;

    public LoginSession() {
    }

    public void checkUserLogin()
            throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        String state = request.getParameter("state");
        if (state == null){
            loginBean.redirectToGatekeeper(response, request.getRequestURI(), ACCESS_STATE);
        }

        Map<String, String[]> paramMap = request.getParameterMap();
        String authorization = request.getHeader("Authorization");

        String accessToken = loginBean.getGatekeeperGetAccessToken(request);

        if (authorization != null && authorization.startsWith("Bearer")) {
            String[] authHead = authorization.split(" ", 2);

            if (authHead[1] == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No Access Token Parameter!");
                return;
            }

            if (!paramMap.containsKey(userParam)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No User ID Parameter!");
                return;
            }

            if (!loginBean.validateAccessToken(accessToken)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Access Token!");
                return;
            }
            // Logged in
            userId = loginBean.getUser_id();

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authorization Header Not Set or Not Bearer");
        }
    }

    public GatekeeperInfo getUserInfo(){
        return loginBean.getUserInfo();
    }

    protected String getUserId() {
        return userId;
    }
}