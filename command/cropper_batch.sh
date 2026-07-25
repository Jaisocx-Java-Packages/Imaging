#!/bin/bash


#; Invokes Java class
#;    com.jaisocx.app.CropperBatch
#;    via Console
#; ---------------------------------------

source "./command/.env_cropper_batch"


#; -- INVOKES by .java Interpreter ( dev time ) --
#; [copy-paste sh snippet]: # java "${pathof_java_src}" \

#; -- INVOKES by .jar ( prod ) --
#;      java -cp "./build/jaisocx_imaging.jar"  "com.jaisocx.app.CropperBatch" arg1 arg2 ...
#; [copy-paste sh snippet]: # java -cp "${pathof_jar}" "${java_class}" \
#; [copy-paste sh snippet]: # java -cp "${pathof_jar}" -verbose:class "${java_class}" \

#; -- INVOKES compiled --
#; [copy-paste sh snippet]: # java -cp "${pathof_java_class}" \



#; -- INVOKES Java class CropperBatch --
java "${pathof_java_src}" \
  "${produced_image_ver}" \
  "${left}" \
  "${top}" \
  "${width}" \
  "${height}" \
  "${offset_x}" \
  "${offset_y}" \
  "${offset_next_x}" \
  "${offset_next_y}" \
  "${ci_first_line}" \
  "${ci_first_item}" \
  "${ci_lines_number}" \
  "${ci_items_number}" \
  "${produced_image_parent}" \
  "${produced_image_name}" \
  "${original_image_path}" \
  "${produced_image_mimetype}" \

backup_envs="./command/envs_${produced_image_ver}"
if [ ! -e "${backup_envs}" ]; then
  mkdir -p "${backup_envs}"
fi

cp -a "./command/.env_cropper_batch" "${backup_envs}/.env_cropper_batch_${produced_image_ver}"


