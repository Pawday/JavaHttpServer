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

        if (jsonRoute.has("ssl"))
            this.sslParameters = new SSLParameters((JSONObject) jsonRoute.get("ssl"));
	}

    private String siteAliace;
    private File baseDirectory;
    private String domainName;
    private int port;

    public SSLParameters sslParameters;


    public class SSLParameters
    {

        private SSLParameters(JSONObject object)
                throws IllegalArgumentException,FileNotFoundException
        {
            if (object.has("use"))
                this.use = object.getBoolean("use");
            else
                throw new IllegalArgumentException("In the json object there is no parameter ssl.use");

            if (this.use)
            {
                if (object.has("PKCS12File"))
                {
                    this.pkcs12File = new File(object.getString("PKCS12File"));
                    if (!this.pkcs12File.exists())
                        throw new FileNotFoundException(this.pkcs12File.getAbsolutePath() + " not found.");
                    if (!this.pkcs12File.isFile())
                        throw new IllegalArgumentException(this.pkcs12File.getAbsolutePath() + " is not a file");
                } else
                    throw new IllegalArgumentException("In the json object there is no parameter ssl.PKCS12File");

                if (object.has("PKCS12FilePassword"))
                {
                    this.pkcs12FilePassword = object.getString("PKCS12FilePassword");
                    if (this.pkcs12FilePassword.equals(""))
                    {
                        this.hasPKCS12FilePassword = false;
                        this.pkcs12FilePassword = null;
                    } else
                        this.hasPKCS12FilePassword = true;
                } else this.hasPKCS12FilePassword = false;

                if (object.has("RedirectFromPort"))
                {
                    this.redirectFromPort = object.getInt("RedirectFromPort");
                } else this.doRedirect = false;
            }
        }

        private boolean use = false;
        private File pkcs12File;
        private boolean hasPKCS12FilePassword;
        private String pkcs12FilePassword;
        private int redirectFromPort = 80;
        private boolean doRedirect = true;

        public boolean isUse()
        {
            return use;
        }
        private void setUse(boolean use)
        {
            this.use = use;
        }

        public File getPkcs12File()
        {
            return pkcs12File;
        }
        private void setPkcs12File(File pkcs12File)
        {
            this.pkcs12File = pkcs12File;
        }

        public String getPkcs12FilePassword()
        {
            return pkcs12FilePassword;
        }
        private void setPkcs12FilePassword(String pkcs12FilePassword)
        {
            this.pkcs12FilePassword = pkcs12FilePassword;
        }

        public int getRedirectFromPort()
        {
            return redirectFromPort;
        }
        private void setRedirectFromPort(int redirectFromPort)
        {
            this.redirectFromPort = redirectFromPort;
        }

        public boolean hasPKCS12FilePassword()
        {
            return hasPKCS12FilePassword;
        }

        public boolean isRedirect()
        {
            return doRedirect;
        }

        public void setDoRedirect(boolean doRedirect)
        {
            this.doRedirect = doRedirect;
        }
    }

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
