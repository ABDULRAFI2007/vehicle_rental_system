@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF)
@REM Maven Wrapper startup script for Windows
@REM ----------------------------------------------------------------------------

@IF "%DEBUG%"=="" @ECHO OFF
@setlocal

set WRAPPER_JAR="%~dp0\.mvn\wrapper\maven-wrapper.jar"
set WRAPPER_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar"

@REM Download maven-wrapper.jar if it doesn't exist
if not exist %WRAPPER_JAR% (
    echo Downloading Maven Wrapper...
    mkdir "%~dp0\.mvn\wrapper" 2>NUL
    powershell -Command "(New-Object Net.WebClient).DownloadFile('%WRAPPER_URL:"=%', '%WRAPPER_JAR:"=%')"
)

set MAVEN_PROJECTBASEDIR=%~dp0
set WRAPPER_PROPERTIES="%~dp0\.mvn\wrapper\maven-wrapper.properties"

@REM Use the wrapper jar
"%JAVA_HOME%\bin\java.exe" -jar %WRAPPER_JAR% %* 2>NUL
if %ERRORLEVEL% NEQ 0 (
    java -jar %WRAPPER_JAR% %*
)
