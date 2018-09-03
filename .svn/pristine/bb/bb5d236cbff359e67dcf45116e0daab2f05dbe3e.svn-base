package envir.web.app.filter;

import envir.web.app.data.TokenData;
import envir.web.app.data.TokenRepo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * describe: Created by IntelliJ IDEA.
 *
 * @author zzl
 * @version 2015/7/16
 */
public class SecurityFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.addHeader("P3P: CP", "\"CAO PSA OUR\"");

        Integer ownerId = null;
        String sOwnerId = request.getParameter("ownerId");
        if(sOwnerId == null) {
            sOwnerId = request.getParameter("userId");
        }
        if(sOwnerId != null) {
            ownerId = Integer.valueOf(sOwnerId);
        }

        boolean tokenVerified;
        try {
            tokenVerified = verifyToken(ownerId, request);
        }
        catch (Exception e) {
            //?
            e.printStackTrace();
            tokenVerified = false;
        }
        if(!tokenVerified) {
            String json = "{\"ok\":false,\"error\":true,\"msg\":\"ERR::BAD_TOKEN\"}";
            response.setContentType("application/json");
            response.getWriter().write(json);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private boolean verifyToken(Integer ownerId, HttpServletRequest request) {
        boolean skipVerify = false;
        skipVerify = true; //comment on release!
        if(skipVerify) {
            return true;//skip
        }
        String uri = request.getRequestURI();
        if(uri.endsWith("login.json")) {
            return true; //ignore
        }

        String xOwnerToken = null;
        String accept = request.getHeader("Accept");
        if(accept.startsWith("json-")) {
            xOwnerToken = accept.substring(5);
        }

//        String xOwnerToken = request.getHeader("x-owner-token");
//        String xOwnerToken = null;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("x-owner-token")) {
//                    xOwnerToken = cookie.getValue();
//                    break;
//                }
//            }
//        }
        if(ownerId == null && xOwnerToken == null) {
            return true; //skip
        }
        if(xOwnerToken == null) {
            return false;
        }
        String token = xOwnerToken.substring(0, 8);
        Integer ownerIdInHeader = Integer.parseInt(xOwnerToken.substring(8), 16);
        if(ownerId != null && !ownerId.equals(ownerIdInHeader)) {
            return false;
        }
        TokenData tokenData = TokenRepo.getToken(ownerIdInHeader);
        if(tokenData == null) {
            return false;
        }
        if(!token.equals(tokenData.getToken())) {
            return false;
        }
        //check issue time?
        return true;
    }

    public void destroy() {
        // do nothing
    }
}
