#!/bin/bash

produced_image_preview_name="image_"



source "./command/.env_cropper_one"

### java "${pathof_java_class}" \
java -cp "${pathof_jar}" \
  -verbose:class "${java_class}" \
  "${produced_image_ver}" \
  "${top}" \
  "${left}" \
  "${height}" \
  "${width}" \
  "${produced_image_mimetype}" \
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
cp -a "./command/.env_cropper_one" "./command/envs_cropper_one_shutterstock_2632283417/.env_cropper_one_${produced_image_ver}"

cp -a "${produced_image_parent}/${produced_image_name}${produced_image_ver}.${produced_image_mimetype}"   "./produced/preview/${produced_image_preview_name}${counter_n}.${produced_image_mimetype}"
echo -e "${counter_n}" > "${counter_path}"



echo -e "New image produced: \"${produced_image_parent}/${produced_image_name}${produced_image_ver}.${produced_image_mimetype}\" \n"
echo -e "Copy: \"./produced/preview/${produced_image_preview_name}${counter_n}.${produced_image_mimetype}\" \n"




