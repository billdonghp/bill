package com.ait.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author  ait
 */
public class LogoutCommand implements Command {

        /** Creates a new instance of LogoutCommand */
        public LogoutCommand() {
        }

        public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession(false);
                if (session != null) {
                        session.invalidate();
                        session = null;
                }
                return "/index.jsp";
        }

}
