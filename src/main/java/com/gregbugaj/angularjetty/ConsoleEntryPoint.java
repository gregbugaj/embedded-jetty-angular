package com.gregbugaj.angularjetty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/console")
public class ConsoleEntryPoint
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleEntryPoint.class);

    @GET
    @Path("")
    public String index()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(anchor("Self Check", "/console/selfcheck")).append("</br>")
            .append(anchor("Config", "/console/config")).append("</br>").append("</br>");
        return sb.toString();
    }

    @GET
    @Path("/selfcheck")
    public String selfcheck()
    {
        return "SelfCheck";
    }

    @GET
    @Path("/config")
    public String config()
    {
        return "Config";
    }

    private String anchor(final String label, final String link)
    {
        return "<a href='" + link + "'>" + label + "</a>";
    }
}
