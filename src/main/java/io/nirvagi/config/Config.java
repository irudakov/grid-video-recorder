package io.nirvagi.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
//import com.groupon.seleniumgridextras.config.driver.*;
//import com.groupon.seleniumgridextras.utilities.FileIOUtility;
//import com.groupon.seleniumgridextras.utilities.json.JsonParserWrapper;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;


public class Config {

    public static final String VIDEO_RECORDING_OPTIONS = "video_recording_options";
    public static final String ACTIVATE_MODULES = "active_modules";
    public static final String DISABLED_MODULES = "disabled_modules";

    private static Logger logger = Logger.getLogger(Config.class);


    protected Map theConfigMap;

    public Config() {
        theConfigMap = new HashMap();
        //gridNodeList = new LinkedList<GridNode>();
        //gridHubList = new LinkedList<GridHub>();
        //getConfigMap().put(NODE_CONFIG_FILES, new LinkedList<String>());
        //getConfigMap().put(HUB_CONFIG_FILES, new LinkedList<String>());
        initialize();
    }

    public Config(Boolean emptyConfig) {
        theConfigMap = new HashMap();
        //gridNodeList = new LinkedList<GridNode>();
        //gridHubList = new LinkedList<GridHub>();
        //getConfigMap().put(NODE_CONFIG_FILES, new LinkedList<String>());
        //getConfigMap().put(HUB_CONFIG_FILES, new LinkedList<String>());
        if (!emptyConfig) {
            initialize();
        }

    }

    private void initialize () {

        getConfigMap().put(ACTIVATE_MODULES, new ArrayList<String>());
        getConfigMap().put(DISABLED_MODULES, new ArrayList<String>());

        initializeVideoRecorder();
    }

    public void initializeVideoRecorder() {
        getConfigMap().put(VIDEO_RECORDING_OPTIONS, new VideoRecordingOptions());
    }

    public List<String> getActivatedModules() {
        return (List<String>) getConfigMap().get(ACTIVATE_MODULES);
    }

    public List<String> getDisabledModules() {
        return (List<String>) getConfigMap().get(DISABLED_MODULES);
    }


    private Map getConfigMap() {
        return theConfigMap;
    }

    public VideoRecordingOptions getVideoRecording() {
        return (VideoRecordingOptions) getConfigMap().get(VIDEO_RECORDING_OPTIONS);
    }

    public void addActivatedModules(String module) {
        getActivatedModules().add(module);
    }

    public void addDisabledModule(String module) {
        getDisabledModules().add(module);
    }
}
