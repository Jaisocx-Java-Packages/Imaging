
![../node_cdn_installs/software_labels/softlabel_jaisocx.svg](../node_cdn_installs/software_labels/softlabel_jaisocx.svg)



# build via maven

  >  💡  the nice example of `pom.xml`, the Maven configuration file.
  >
  >  ❌  doesn't have for now variables block in example of `pom.xml` Maven configuration file.


| 🗓 **Updated**  | 🌼  Summer 2026 | `Sunday, 25th month July in year AD 2026 12:20:20 UTC 24h` |


---

**Notice**: I'm on seeing around for pom.xml variables ...



### Configured

a.   ✅  control inclusions to .jar

b.   ✅  Main Java configurations

c.   ✅  Current library configurations

d.   ✅  Paths of folders: compile, build .jar

e.   ✅  Charset encoding utf8

---



#### a. control inclusions to .jar

  >  💡  500.00 MB = 512_000.0 KB ( uncontrolled included logs, testing and development time resources ),
  >
  >  💡  2.40 MB = 2_457.6 KB ( of greate use might include documentation, command line shell helpers, resources ),
  >
  >  💡  0.05 MB = 51.0 KB ( just Java classes ) works, 50 times tinier.


- folders or files **stored to .jar**: `<include>readme/*.md</include>`

- **no copies of folders or files** won't be pasted to compiled project, neither to **.jar**: `<!-- <exclude>readme/*.md</exclude> -->`



####  💡  Main Java configurations

- **Main Java Class**: `<mainClass>com.jaisocx.app.CropperOneMultiple</mainClass>` by doubleclick on `jaisocx_imaging.jar`

- **Name of .jar package**: `<finalName>jaisocx_imaging</finalName>` -> `jaisocx_imaging.jar`

- **Folder for the .jar (outputDirectory)**: `<outputDirectory>${project.basedir}/build/</outputDirectory>` -> `build/jaisocx_imaging.jar`

- **Compiled to Java JRE bytecode version**: `<release>17</release>`

- **Packs to .jar**: `<packaging>jar</packaging>`



####  💡  Current library configurations

- **Main Java Class**: `<mainClass>com.jaisocx.app.CropperOneMultiple</mainClass>` by doubleclick on `jaisocx_imaging.jar`

- **Namespace**: `<groupId>com.jaisocx</groupId>`

- **Library name**: `<artifactId>jaisocx_imaging</artifactId>`

- **Name of .jar package**: `<finalName>jaisocx_imaging</finalName>` -> `jaisocx_imaging.jar`

- **Library ver. number**: `<version>2.3.8</version>`



####  💡  Paths of folders: compile, build .jar

- Folder for the **.jar** (outputDirectory): `<outputDirectory>${project.basedir}/build/</outputDirectory>` -> `build/jaisocx_imaging.jar`

- Folder for the **compiled project** 1: `<targetPath>${project.basedir}/target/jar/</targetPath>`

- Folder for the **compiled project** 2: `<classesDirectory>${project.basedir}/target/jar/</classesDirectory>`

- Folder for the **compiled project** 3 (outputDirectory, too): `<outputDirectory>${project.basedir}/target/jar/</outputDirectory>`



####  💡  Charset encoding utf8

- Text files saved with **charset encoding**: `<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>`



---

```xml 
  <mainClass>com.jaisocx.app.CropperOneMultiple</mainClass>
  <finalName>jaisocx_imaging</finalName>
  <release>17</release>
  <groupId>com.jaisocx</groupId>
  <artifactId>jaisocx_imaging</artifactId>
  <directory>${project.basedir}/</directory>
  <sourceDirectory>${project.basedir}/src/main/java/</sourceDirectory>
  <includes>
  <excludes>
  <outputDirectory>${project.basedir}/build/</outputDirectory>
  <targetPath>${project.basedir}/target/jar/</targetPath>
  <classesDirectory>${project.basedir}/target/jar/</classesDirectory>
  <outputDirectory>${project.basedir}/target/jar/</outputDirectory>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <packaging>jar</packaging>
  <version>2.3.8</version>
```



---

**pom.xml**

```xml 

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.jaisocx</groupId>
    <artifactId>jaisocx_imaging</artifactId>
    <version>2.3.8</version>
    <packaging>jar</packaging>



    <dependencies>

        <!-- Source: https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin -->
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.14.0</version>
            <scope>compile</scope>
        </dependency>

        <!-- Source: https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-jar-plugin -->
        <dependency>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-jar-plugin</artifactId>
            <version>3.4.2</version>
            <scope>compile</scope>
        </dependency>

    </dependencies>


    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <build>
        <finalName>jaisocx_imaging</finalName>
        <sourceDirectory>${project.basedir}/src/main/java/</sourceDirectory>
        <outputDirectory>${project.basedir}/target/jar/</outputDirectory>

        <resources>
            <!-- base -->
            <!-- <include> xml relative paths to <directory> are hardcopied to ${project.basedir}/target/jar/
                   and keep the paths like this:
                   <directory>${project.basedir}/</directory>
                   <include>readme/
                   ( ${project.basedir}/target/jar/ )readme/
                 example:
                   <include>readme/images/**</include>
                   ${project.basedir}/target/jar/readme/images/shutterstock_464833178_cropped_3_4.png
            -->
            <resource>
                <directory>${project.basedir}/</directory>
                <targetPath>${project.basedir}/target/jar/</targetPath>
                <includes>
                    <include>produced_images.html</include>

                    <include>README.md</include>
                    <include>readme/*.md</include>
                    <include>readme/**/*.md</include>
                    <include>readme/images/**</include>

                    <!-- <include>src/main/resources/templates/**</include> -->

                    <include>command/*.sh</include>
                    <include>command/example_env_*</include>
                    <include>command/**/*.sh</include>

                    <include>node_cdn_installs/favicons/**</include>
                    <include>node_cdn_installs/software_labels/**</include>
                    <include>node_cdn_installs/package.json</include>
                    <include>node_cdn_installs/package-lock.json</include>
                </includes>
                <excludes>

                    <exclude>build/**</exclude>
                    <exclude>target/**</exclude>

                    <exclude>produced/**</exclude>

                    <exclude>.mvn/**</exclude>
                    <exclude>.idea/**</exclude>
                    <exclude>.vscode/**</exclude>

                    <exclude>.env</exclude>
                    <exclude>.env*</exclude>
                    <exclude>**/.env</exclude>
                    <exclude>**/.env*</exclude>

                    <exclude>*.jar</exclude>
                    <exclude>**/*.jar</exclude>

                    <exclude>node_modules/**</exclude>
                    <exclude>**/node_modules/**</exclude>

                    <exclude>tmp/**</exclude>
                    <exclude>**/tmp/**</exclude>
                </excludes>
            </resource>

            <!-- resources -->
            <!-- <include> xml relative paths to <directory> are hardcopied to ${project.basedir}/target/jar/
                   and keep the paths like this:
                   <directory>${project.basedir}/src/main</directory>
                   <include>resources/templates/**</include>
                   ( ${project.basedir}/target/jar/ )resources/templates/
                 example:
                   <include>resources/templates/**</include>
                   ${project.basedir}/target/jar/resources/templates/produced_images.html
            -->
            <resource>
                <directory>${project.basedir}/src/main/</directory>
                <targetPath>${project.basedir}/target/jar/</targetPath>
                <includes>
                    <include>resources/templates/**</include>
                    <include>resources/images/**</include>
                </includes>
                <excludes>
                    <exclude>resources/tmp/**</exclude>
                </excludes>
            </resource>

        </resources>



        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.14.0</version>

                <configuration>
                    <goal>compiler:compile</goal>
                    <release>17</release>
                    <outputDirectory>${project.basedir}/target/jar/</outputDirectory>
                    <compilerArgs>
                        <arg>-verbose</arg>
                    </compilerArgs>
                </configuration>

                <executions>
                    <execution>
                        <phase>compile</phase>
                        <configuration>
                            <basedir>${project.basedir}/src/main/</basedir>

                            <includes>
                                <include>java/com/jaisocx/*.java</include>
                                <include>java/com/jaisocx/**/*.java</include>
                            </includes>
                            <excludes>
                                <exclude>resources/**</exclude>
                            </excludes>

                        </configuration>
                    </execution>
                </executions>
            </plugin>


            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.4.2</version>

                <configuration>
                    <goal>jar:jar</goal>
                    <classesDirectory>${project.basedir}/target/jar/</classesDirectory>
                    <outputDirectory>${project.basedir}/build/</outputDirectory>
                    <archive>
                        <manifest>
                            <addDefaultImplementationEntries>true</addDefaultImplementationEntries>
                            <addClasspath>true</addClasspath>
                            <mainClass>com.jaisocx.app.CropperOneMultiple</mainClass>
                        </manifest>
                    </archive>

                    <includes>
                        <include>com/jaisocx/*.class</include>
                        <include>com/jaisocx/**/*.class</include>

                        <!--<include>produced_images.html</include>-->

                        <!--<include>README.md</include>-->
                        <!--<include>readme/*.md</include>-->
                        <!--<include>readme/**/*.md</include>-->
                        <!--<include>readme/images/**</include>-->

                        <!--<include>command/*.sh</include>-->
                        <!--<include>command/example_env_*</include>-->
                        <!--<include>command/**/*.sh</include>-->

                        <!--<include>node_cdn_installs/favicons/**</include>-->
                        <!--<include>node_cdn_installs/software_labels/**</include>-->
                        <!--<include>node_cdn_installs/package.json</include>-->
                        <!--<include>node_cdn_installs/package-lock.json</include>-->
                    </includes>
                    <excludes>
                        <exclude>resources/**</exclude>

                        <exclude>produced_images.html</exclude>

                        <exclude>README.md</exclude>
                        <exclude>readme/*.md</exclude>
                        <exclude>readme/**/*.md</exclude>
                        <exclude>readme/images/**</exclude>

                        <exclude>command/*.sh</exclude>
                        <exclude>command/example_env_*</exclude>
                        <exclude>command/**/*.sh</exclude>

                        <exclude>node_cdn_installs/favicons/**</exclude>
                        <exclude>node_cdn_installs/software_labels/**</exclude>
                        <exclude>node_cdn_installs/package.json</exclude>
                        <exclude>node_cdn_installs/package-lock.json</exclude>
                    </excludes>
                </configuration>

                <executions>
                    <execution>
                        <configuration>
                            <phase>package</phase>
                            <finalName>jaisocx_imaging</finalName>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

        </plugins>
    </build>
</project>

```