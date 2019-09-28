package io.nirvagi.node;

import java.util.LinkedList;
import java.util.List;


public class SessionTracker {

    List<String> sessionsRecorded = new LinkedList<String>();


    public void startSession(String session) {
        sessionsRecorded.add(session);
    }

    public List<String> getSessions() {
        return sessionsRecorded;
    }
}
