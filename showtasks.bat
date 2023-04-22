call runcrud.bat

if "%ERRORLEVEL%" == "0" goto runurl
echo.
echo RUNCRUD BUILD has errors – breaking work
goto fail

:runurl
start chrome http://localhost:8080/crud/v1/task/getTasks
