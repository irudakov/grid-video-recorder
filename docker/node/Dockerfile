FROM selenium/node-chrome
LABEL author=irudakov

USER seluser

COPY docker/hub/start-selenium-node.sh \
     MonteScreenRecorder.jar \
/opt/bin/

COPY target/grid-video-recorder.jar \ 
/opt/selenium