package io.nirvagi;


import com.sun.jna.platform.win32.Kernel32;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class OS {

    private static final String WINDOWS = "Windows";
    private static final String MAC = "Mac";
    private static final String OS_NAME = "os.name";
    private static final String USER_HOME = "user.home";
    private static final String USER_NAME = "user.name";
    private static final String FILE_SEPARATOR = "file.separator";
    private static final String PATH_SEPARATOR = "path.separator";
    //private static Logger logger = Logger.getLogger(OS.class);


    public boolean isWindows() {
        return getOSName().startsWith(WINDOWS);
    }

    public boolean isMac() {
        return getOSName().startsWith(MAC);
    }

    public String getOSName() {
        return System.getProperty(OS_NAME);
    }

    public String getUserHome() {
        return System.getProperty(USER_HOME);
    }

    public String getUserName() {
        return System.getProperty(USER_NAME);
    }

    public String getFileSeparator() {
        return System.getProperty(FILE_SEPARATOR);
    }

    public String getPathSeparator() {
        return System.getProperty(PATH_SEPARATOR);
    }

    public String getCurrentPid() {
        if (isWindows()) {
            return getWindowsPid();
        } else {
            return getUnixPid();
        }

    }

    public String getHostName() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (UnknownHostException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public String getHostIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public boolean hasGUI() {
        return !GraphicsEnvironment.isHeadless();
    }

    public String getWindowsRealArchitecture() {
        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
        String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");

        String realArch = arch.endsWith("64")
                || wow64Arch != null && wow64Arch.endsWith("64")
                ? "64" : "32";

        return realArch;
    }


    private String getUnixPid() {
        return ManagementFactory.getRuntimeMXBean().getName().replaceAll("@.*", "");
    }

    private String getWindowsPid() {
        int pid = Kernel32.INSTANCE.GetCurrentProcessId();
        return String.valueOf(pid);
    }
}
