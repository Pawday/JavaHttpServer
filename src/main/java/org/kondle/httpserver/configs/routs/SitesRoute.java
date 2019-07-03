package org.kondle.httpserver.configs.routs;

import java.io.File;

public class SitesRoute
{
    private String siteAliace;
    private File baseDirectory;
    private String domainName;

    public SSLParameters sslParameters;




    class SSLParameters
    {
        private boolean use = false;
        private File pkcs12File;
        private String pkcs12FilePassword;
        private int redirectFromPort = 80;

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
