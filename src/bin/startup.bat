@echo off
rem ======================================================================
rem windows startup script
rem
rem author: liyuan
rem date: 2020-04-18
rem ======================================================================

rem Open in a browser
start "" "http://localhost:8080/example/hello?name=123"

rem startup jar
java -jar ../boot/@project.build.finalName@.jar --spring.config.location=../config/

pause
