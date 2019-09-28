package io.nirvagi.node.task;

import com.google.gson.JsonObject;

import io.nirvagi.ExecuteCommand;
import io.nirvagi.EndPoint;
import io.nirvagi.config.RuntimeConfig;
import io.nirvagi.utils.json.JsonCodec;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class ExecuteOSTask extends EndPoint{

    final private
    String
            notImplementedError =
            "This task was not implemented on " + RuntimeConfig.getOS().getOSName();
    public boolean waitToFinishTask = true;

    private static Logger logger = Logger.getLogger(ExecuteOSTask.class);

    public JsonObject execute() {
        return execute("");
    }

    public JsonObject execute(Map<String, String> parameter) {
        if (!parameter.isEmpty() && parameter.containsKey(JsonCodec.PARAMETER)) {
            return execute(parameter.get(JsonCodec.PARAMETER).toString());
        } else {
            return execute();
        }
    }


    public JsonObject execute(String parameter) {
        String command;

        if (RuntimeConfig.getOS().isWindows()) {
            command = getWindowsCommand(parameter);
        } else if (RuntimeConfig.getOS().isMac()) {
            command = getMacCommand(parameter);
        } else {
            command = getLinuxCommand(parameter);
        }

        return ExecuteCommand.execRuntime(command + parameter, waitToFinishTask);
    }

    public String getWindowsCommand(String parameter) {

        getJsonResponse().addKeyValues(JsonCodec.ERROR,
                notImplementedError + " " + this.getClass().getCanonicalName());

        return getJsonResponse().toString();

    }

    public String getWindowsCommand() {
        return getWindowsCommand("");
    }

    public String getLinuxCommand(String parameter) {
        getJsonResponse().addKeyValues(JsonCodec.ERROR,
                notImplementedError + " " + this.getClass().getCanonicalName());

        return getJsonResponse().toString();

    }

    public String getLinuxCommand() {
        return getLinuxCommand("");
    }

    public String getMacCommand(String parameter) {
        return getLinuxCommand(parameter);
    }

    public String getMacCommand() {
        return getLinuxCommand();
    }

    public boolean initialize() {

        if (allDependenciesLoaded()) {
            printInitilizedSuccessAndRegisterWithAPI();
            return true;
        } else {
            printInitilizedFailure();
            return false;
        }

    }

    public void printInitilizedSuccessAndRegisterWithAPI() {

        final
        String
                message =
                "Y " + this.getClass().getSimpleName() + " - " + this.getEndpoint() + " - " + this
                        .getDescription();

        System.out.println(message);
        logger.info(message);
        //Switching api registration off for a while 'cos it's not relevant at thye moment
        //registerApi();
    }

    public void printInitilizedFailure() {
        final String message = "N " + this.getClass().getSimpleName();
        System.out.println(message);
        logger.info(message);
    }

    public Boolean allDependenciesLoaded() {
        Boolean returnValue = true;

        //Switch off this check for a while

        //for (String module : getDependencies()) {
        //    if (RuntimeConfig.getConfig().checkIfModuleEnabled(module) && returnValue) {
        //        logger.info(this.getClass().getSimpleName() + " is enabled");
        //    } else {
        //        logger.info("  " + this.getClass().getSimpleName() + " depends on " + module
        //                + " but it is not activated");
        //        returnValue = false;
        //    }
        //}

        return returnValue;
    }

    public List<String> getDependencies() {
        List<String> dependencies = new LinkedList();
        return dependencies;
    }

    protected void systemAndLog(String output) {
        System.out.println(output);
        logger.info(output);
    }
}
