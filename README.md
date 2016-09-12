# JarvisAlarmClock
An Iron Man inspired alarm clock that reads the current time and the weather!

##Dependencies
Must be run on a Linux device with access to the `tmp` directory and the package `mpg123` installed and in the path.
Works on a RaspberryPi.

##Building
Requires Maven 3 to be installed. Run `createPackage.sh` to build all required JARs. 
Copy onto target device (such as a RaspberryPi), and run `install.sh`.

See the README in the `package` directory for more information.

##Running Manually
To manually fire the job, run `JarvisJob.jar`.
