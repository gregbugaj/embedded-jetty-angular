package com.gregbugaj.angularjetty;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class Application
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(final String[] args) throws InterruptedException
    {
        try
        {
            new Application().start();
        }
        catch (final Exception e)
        {
            LOGGER.error("Unable to completely initialize system", e.getCause());
        }
    }

    private void start() throws Exception
    {

        final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        // final Server server = new Server(new InetSocketAddress(sc.getBindingInterface(), sc.getServicePortNumber()));
        final ServletHolder jersey = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");

        jersey.setInitOrder(0);
        jersey.setAsyncSupported(true);

        // Tells the Jersey Servlet which REST service/class to load.
        jersey.setInitParameter("javax.ws.rs.Application", ServiceRegistrationApplication.class.getCanonicalName());
        jersey.setInitParameter("com.sun.jersey.config.property.packages",
                                ServiceRegistrationApplication.class.getPackage().getName());
        jersey.setInitParameter("jersey.config.server.provider.scanning.recursive", "true");

        // default config
        final ServiceConfig sc = new ServiceConfig();
        LOGGER.info("Initalizing  Service {} {} ", sc.getBindingInterface(), sc.getServicePortNumber());

        final Server server = new Server(new QueuedThreadPool(10));
        final ServerConnector connector = new ServerConnector(server);
        connector.setPort(sc.getServicePortNumber());
        connector.setHost(sc.getBindingInterface());

        // bootstrap webapp from resource folder
        final String extForm = this.getClass().getClassLoader().getResource("webapp").toExternalForm();
        LOGGER.info("Serving from : {}", extForm);

        final ResourceHandler resHandler = new ResourceHandler();
        resHandler.setResourceBase(extForm);
        final ContextHandler ctx = new ContextHandler("/");
        ctx.setHandler(resHandler);

        try
        {
            server.setConnectors(new Connector[] { connector });
            server.insertHandler(context); // Rest/Servlets
            server.insertHandler(resHandler); // HTML Resources
            server.start();
            server.join();
        }
        finally
        {
            server.destroy();
        }
    }
}
