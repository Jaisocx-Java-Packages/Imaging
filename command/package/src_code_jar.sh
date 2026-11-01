#!/bin/bash

package_name="jaisocx_imaging"
javadoc_jar_name="${package_name}_src.jar"

mkdir "src/main/java/META-INF"
cp -a "./src/main/resources/manifest/MANIFEST.MF" "./src/main/java/META-INF/MANIFEST.MF"


jar --create -f "./build/${javadoc_jar_name}" -C "./src/main/java" "./"


rm -r "./src/main/java/META-INF"




