#!/bin/bash

source "./command/.env_cropper_batch"

### java "${pathof_java_class}" \
java -cp "${pathof_jar}" \
  -verbose:class "${java_class}" \
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


