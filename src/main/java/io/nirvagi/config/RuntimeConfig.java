package io.nirvagi.config;

import io.nirvagi.OS;
//import com.groupon.seleniumgridextras.SeleniumGridExtras;
//import com.groupon.seleniumgridextras.config.remote.ConfigPuller;
//import com.groupon.seleniumgridextras.downloader.webdriverreleasemanager.WebDriverReleaseManager;
import io.nirvagi.node.SessionTracker;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
//import org.dom4j.DocumentException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class RuntimeConfig {

    public static String configFile = "selenium_grid_extras_config.json";
    private static Config config = null;
    private static OS currentOS = new OS();
    private static Logger logger = Logger.getLogger(RuntimeConfig.class);
    private static SessionTracker sessionTracker;


    public RuntimeConfig() {
        config = new Config();
    }

    protected static void clearConfig() {
        //Use only for tests, don't use for any other reason
        config = null;
    }

    public static Config getConfig() {
        return config;
    }

    public static OS getOS() {
        return currentOS;
    }

    public static SessionTracker getTestSessionTracker() {
        if (sessionTracker == null) {
            sessionTracker = new SessionTracker();
        }

        return sessionTracker;
    }

    public static String getHostIp() {
        String ip = null;
        //if (config != null) {
        //    if (config.getHostIp() != null) {
        //        ip = config.getHostIp();
        //    } else if (config.getDefaultRole().equals("hub") && config.getHubs().size() > 0) {
        //        ip = config.getHubs().get(0).getConfiguration().getHost();
        //    } else if (config.getDefaultRole().equals("node") && config.getNodes().size() > 0) {
        //        ip = config.getNodes().get(0).getConfiguration() != null ? config.getNodes().get(0).getConfiguration().getHost() : config.getNodes().get(0).getHost();
        //    }
        //}
        if (ip == null) {
            ip = currentOS.getHostIp();
        }
        return ip;
    }

}
