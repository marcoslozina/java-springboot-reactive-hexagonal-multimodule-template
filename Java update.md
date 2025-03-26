📌 Guide: Changing Java Version with SDKMAN! and System Configuration

This guide explains how to change the Java version using SDKMAN!, update system environment files to make the change
permanent, and ensure that Gradle uses the correct version.

✅ 1. Check the Current Java Version

Before switching Java versions, check which one is currently active:

java -version

If it's not the desired version, follow the steps below.

🔄 2. List Available Java Versions in SDKMAN!

To see all installed and available Java versions:

sdk list java

Find the version you want to use.

🔹 3. Install a New Java Version (if needed)

If the desired version is not installed, install it with:

sdk install java 23.0.2-amzn # Adjust based on the available version

To verify installation:

sdk list java

🔄 4. Temporarily Change the Java Version

To change the Java version only for the current session, use:

sdk use java 23.0.2-amzn # Adjust based on the installed version

Verify the change with:

java -version

🔄 5. Set Java as the Default Version

To make Java 23 the default for all sessions, run:

sdk default java 23.0.2-amzn

Verify that the change is applied:

java -version

If an older version still appears, follow the next steps to manually update environment variables.

🔧 6. Update ** and ** Globally

If java -version still shows an old version after switching in SDKMAN!, update the environment variables manually.

➤ **Manually Update **``

Run the following commands:

export JAVA_HOME=$HOME/.sdkman/candidates/java/23.0.2-amzn
export PATH=$JAVA_HOME/bin:$PATH

To make this permanent, add these lines to your shell configuration file:

For Bash:

echo 'export JAVA_HOME=$HOME/.sdkman/candidates/java/23.0.2-amzn' >> ~/.bashrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bashrc
source ~/.bashrc

For Zsh:

echo 'export JAVA_HOME=$HOME/.sdkman/candidates/java/23.0.2-amzn' >> ~/.zshrc
echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.zshrc
source ~/.zshrc

Verify again:

java -version
echo $JAVA_HOME
which java

If the old version still appears, restart the computer.

🔹 7. Force Gradle to Use Java 23

If Gradle still uses an older Java version, update its configuration.

➤ **Edit **``

Open the gradle.properties file and add this line:

org.gradle.java.home=$HOME/.sdkman/candidates/java/23.0.2-amzn

➤ Restart Gradle

Run these commands to ensure Gradle picks up the new version:

./gradlew --stop
./gradlew clean build --warning-mode all

Check which Java version Gradle is using:

./gradlew --version

If it still points to an old version, try restarting the computer.

🔹 8. Update Java Version in IntelliJ IDEA

If you're using IntelliJ IDEA, follow these steps to update the Java version for your project:

➤ Update Project Structure (Project SDK)

Open IntelliJ IDEA.

Go to File → Project Structure → Project.

Under Project SDK, select Java 23.

Click Apply and OK.

➤ **Update Java in **``

If you're using Gradle, ensure your build.gradle file is set to use Java 23:

java {
toolchain {
languageVersion.set(JavaLanguageVersion.of(23))
}
}

After making these changes, reload Gradle in IntelliJ:

Open the Gradle Tool Window.

Click the Refresh button (or right-click the project and select Reload Gradle Project).

🎯 Done! Java 23 is Now Set Up Globally 🚀

If you ever want to switch versions again, use:

sdk use java 21.*  # To switch to Java 21
sdk use java 23.*  # To switch back to Java 23

Now, your system, Gradle, and IntelliJ IDEA are correctly configured to use Java 23. 🚀
