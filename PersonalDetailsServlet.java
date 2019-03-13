package com.mycompany.myproject.julyapp.core.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.mycompany.myproject.julyapp.core.beans.Employee;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Personal Details Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/services/julypersonaldetails",
        "sling.servlet.extensions=" + "json"
})
public class PersonalDetailsServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException, IOException {

		Gson gson = new Gson();
		Employee employee = new Employee();
		employee.setFname("AEM Trainer");
		employee.setLname("Consultant");
		employee.setTechnology("Consultant");
		String jsonString = gson.toJson(employee);
		logger.info("jsonString:" + jsonString);
		resp.getWriter().println(jsonString);
	}

	@Activate
	protected void activate(Map<String, Object> props) {
		logger.info("PersonalDetailsServlet invoked !!!");
	}
}
