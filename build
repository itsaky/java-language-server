rm -rvf ./dist/classpath
mvn package -DskipTests \
&& mv dist/classpath/jls*.jar dist/classpath/jls.jar \
&& cp dist/classpath/jls.jar /sdcard/  \
&& cp dist/classpath/jls.jar $PROJECTS/android-ide/app/src/main/assets/data/  \
&& echo "\033[32mRENAMED AND COPIED!\033[0m"
