package org.kondle.httpserver.configs.routs;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;

public class SitesRoute
{
	public SitesRoute(JSONObject jsonRoute) throws IllegalArgumentException, FileNotFoundException
    {
		if (jsonRoute.has("SiteAliace"))
			this.siteAliace = jsonRoute.getString("SiteAliace");
		else throw new IllegalArgumentException("In the json object there is no parameter SiteAliace");

        if (jsonRoute.has("BaseDirectory"))
        {
            this.baseDirectory = new File(jsonRoute.getString("BaseDirectory"));
            if (!this.baseDirectory.isAbsolute())
                throw new IllegalArgumentException("The base directory must be specified as an absolute path.");
            if (this.baseDirectory.exists() && this.baseDirectory.isFile())
                throw new IllegalArgumentException(this.baseDirectory.getAbsolutePath() + " " + this.baseDirectory.getName() + " is not a directory");
        }
        else throw new IllegalArgumentException("In the json object there is no parameter BaseDirectory");

        if (jsonRoute.has("DomainName"))
            this.domainName = jsonRoute.getString("DomainName");
        else throw new IllegalArgumentException("In the json object there is no parameter DomainName");

        if (jsonRoute.has("port"))
            this.port = jsonRoute.getInt("port");
        else throw new IllegalArgumentException("In the json object there is no parameter port");
	}

    private String siteAliace;
    private File baseDirectory;
    private String domainName;
    private int port;

    public String getSiteAliace()
    {
        return siteAliace;
    }
    public File getBaseDirectory()
    {
        return baseDirectory;
    }
    public int getPort()
    {
        return port;
    }
    public String getDomainName()
    {
        return domainName;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof SitesRoute)
            return ((SitesRoute) obj).domainName.equals(this.domainName);
        else return false;
    }

    @Override
    public int hashCode()
    {
        return this.siteAliace.hashCode();
    }
}
