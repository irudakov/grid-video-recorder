FROM selenium/node-chrome
LABEL author=irudakov

USER seluser

COPY docker/node/start-selenium-node.sh \
     MonteScreenRecorder.jar \
     docker/node/generate_config \
/opt/bin/

COPY target/grid-video-recorder.jar \ 
/opt/selenium