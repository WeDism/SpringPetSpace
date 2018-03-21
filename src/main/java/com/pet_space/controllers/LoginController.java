package com.pet_space.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletConfigAware;

import javax.servlet.ServletConfig;

@Controller
@RequestMapping(value = "/login")
public class LoginController implements ServletConfigAware {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Override
    @SuppressWarnings("NullableProblems")
    public void setServletConfig(ServletConfig config) {
        String contextPath = config.getServletContext().getContextPath();
        if (!contextPath.equals("")) {
            config.getServletContext().setAttribute("bootstrap.css", contextPath + "/web_resources/css/bootstrap.css");
            config.getServletContext().setAttribute("bootstrap-grid.css", contextPath + "/web_resources/css/bootstrap-grid.css");
            config.getServletContext().setAttribute("bootstrap-reboot.css", contextPath + "/web_resources/css/bootstrap-reboot.css");
            config.getServletContext().setAttribute("jquery", contextPath + "/web_resources/js/jquery-3.3.1.js");
            config.getServletContext().setAttribute("popper", contextPath + "/web_resources/js/popper.js");
            config.getServletContext().setAttribute("bootstrap.js", contextPath + "/web_resources/js/bootstrap.js");
        } else {
            config.getServletContext().setAttribute("bootstrap.css", contextPath + "/web_resources/css/bootstrap.min.css");
            config.getServletContext().setAttribute("bootstrap-grid.css", contextPath + "/web_resources/css/bootstrap-grid.min.css");
            config.getServletContext().setAttribute("bootstrap-reboot.css", contextPath + "/web_resources/css/bootstrap-reboot.min.css");
            config.getServletContext().setAttribute("jquery", contextPath + "/web_resources/js/jquery-3.3.1.min.js");
            config.getServletContext().setAttribute("popper", contextPath + "/web_resources/js/popper.min.js");
            config.getServletContext().setAttribute("bootstrap.js", contextPath + "/web_resources/js/bootstrap.min.js");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginView() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String redirect() {
        return "redirect:/login";
    }

}
