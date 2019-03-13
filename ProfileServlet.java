package com.mycompany.myproject.sepapp.core.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.myproject.mayapp.core.models.UserInfoModel;

@Component (service=Servlet.class,enabled=true, immediate=true,configurationPid="com.mycompany.myproject.sepapp.core.servlets.ProfileServlet",
property={
		Constants.SERVICE_DESCRIPTION + "= Profile Details Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths="+ "/services/sepretrieveprofiledetails",
		"sling.servlet.extensions=" + "json"
})
public class ProfileServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Reference
	ResourceResolverFactory resourceResolverFactory;
	
	@Override
	protected void doGet(final SlingHttpServletRequest req,
			final SlingHttpServletResponse resp) throws ServletException, IOException {

		ResourceResolver resourceResolver;
		try {
			resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
			Resource resource = resourceResolver.getResource("/content/sepapp/en/employee");
			UserInfoModel userInfoModel = resource.adaptTo(UserInfoModel.class);
			String firstName = userInfoModel.getFirstName();
			String lastName = userInfoModel.getLastName();
			String technology = userInfoModel.getTechnology();
			
			resp.getWriter().println("firstName: "+firstName+" lastName:"+lastName+" technology:"+technology);
			logger.info("firstName: "+firstName+" lastName:"+lastName+" technology:"+technology);
			
		} catch (LoginException e) {
			logger.error("LoginException:" + e);
		}
		
	}

	@Activate
	protected void activate(Map<String, Object> props) {
		logger.info("ProfileServlet invoked !!!");
	}
}
