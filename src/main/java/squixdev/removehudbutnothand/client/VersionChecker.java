package squixdev.removehudbutnothand.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import squixdev.removehudbutnothand.common.RemoveHUDbutNotHand;

/**
 * @author jabelar
 *
 */
public class VersionChecker implements Runnable
{
    private static boolean isLatestVersion = false;
    private static String latestVersion = "";

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() 
    {
        InputStream in = null;
        try 
        {
            in = new URL("http://squixdev.appspot.com/ModsMinecraft/RemoveHUDbutNotHand/rhbnh.version").openStream();
        } 
        catch 
        (MalformedURLException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("[RHBNH-VersionChecker] Error: Url Malformed !!!");
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("[RHBNH-VersionChecker] Error: IOException !!!");
        }

        try 
        {
            latestVersion = IOUtils.readLines(in).get(0);
        } 
        catch (IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        finally 
        {
            IOUtils.closeQuietly(in);
        }
        System.out.println("Latest mod version = "+latestVersion);
        isLatestVersion = RemoveHUDbutNotHand.VERSION.equals(latestVersion);
        System.out.println("Are you running latest version = "+isLatestVersion);
    }
    
    public boolean isLatestVersion()
    {
     return isLatestVersion;
    }
    
    public String getLatestVersion()
    {
     return latestVersion;
    }
}