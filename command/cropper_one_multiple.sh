#!/bin/bash

#; Invokes Java class
#;    com.jaisocx.app.CropperOneMultiple
#;    via Console
#; ---------------------------------------


produced_image_preview_name="image_"

source "./command/.env_cropper_one_multiple"



#; -- INVOKES by .java Interpreter ( dev time ) --
#; [copy-paste sh snippet]: # java "${pathof_java_src}" \

#; -- INVOKES by .jar ( prod ) --
#;      java -cp "./build/jaisocx_imaging.jar"  "com.jaisocx.app.CropperOneMultiple" arg1 arg2 ...
#; [copy-paste sh snippet]: # java -cp "${pathof_jar}" "${java_class}" \
#; [copy-paste sh snippet]: # java -cp "${pathof_jar}" -verbose:class "${java_class}" \

#; -- INVOKES compiled --
#; [copy-paste sh snippet]: # java -cp "${pathof_java_class}" \



#; -- INVOKES Java class CropperOneMultiple --
java -cp "${pathof_jar}" "${java_class}" \
  "${produced_image_ver}" \
  "${top}" \
  "${left}" \
  "${height}" \
  "${width}" \
  "${produced_image_mimetype}" \
  "${step_next_try_pos_x}" \
  "${step_next_try_pos_y}" \
  "${step_next_try_size_h}" \
  "${step_next_try_size_w}" \
  "${trials_number_pos_x}" \
  "${trials_number_pos_y}" \
  "${trials_number_size_h}" \
  "${trials_number_size_w}" \
  "${produced_image_parent}" \
  "${produced_image_name}" \
  "${original_image_path}"



counter_path="./command/.counter"
if [ ! -e "${counter_path}" ]; then
  echo -e "0" > "${counter_path}"
fi

counter_t="$(cat "${counter_path}")"
counter_n="$(expr "${counter_t}" + 1)"



# .env of the image cropping settings saved
backup_envs="./command/envs_${produced_image_ver}"
if [ ! -e "${backup_envs}" ]; then
  mkdir -p "${backup_envs}"
fi

cp -a "./command/.env_cropper_one_multiple" "${backup_envs}/.env_cropper_one_multiple_${produced_image_ver}"
echo -e "${counter_n}" > "${counter_path}"

echo -e "New images produced \n"


