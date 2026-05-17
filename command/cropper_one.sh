#!/bin/bash

source "./command/.env_cropper_one"

java "${pathof_java_class_invoke}" \
  "${produced_image_ver}" \
  "${left}" \
  "${top}" \
  "${width}" \
  "${height}" \
  "${produced_image_mimetype}" \
  "${cropped_image_path}"

cp -a "./command/.env_cropper_one" "./command/envs_cropper_one_shutterstock_2632283417/.env_cropper_one_${produced_image_ver}"


