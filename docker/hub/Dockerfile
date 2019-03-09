FROM selenium/hub
LABEL author=irudakov

USER seluser

COPY docker/hub/start-selenium-hub.sh \
/opt/bin/

COPY target/grid-video-recorder.jar \ 
/opt/selenium