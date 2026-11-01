#!/bin/bash

javadoc \
  -private \
  -doctitle "Jaisocx Imaging" \
  -linksource \
  --syntax-highlight \
  -html5 \
  -charset utf8 \
  -d "./readme/javadoc/" \
  --source-path "./src/main/java/" \
    com.jaisocx.imaging \
    com.jaisocx.imaging.app \
    com.jaisocx.imaging.imaging_types \
    com.jaisocx.imaging.cropping_sizes \
    com.jaisocx.imaging.downgrader \
    com.jaisocx.imaging.downgrader_sizes \
    com.jaisocx.imaging.sizes.Constants \
    com.jaisocx.imaging.helpers \
    com.jaisocx.imaging.dbg \
    com.jaisocx.imaging.example_dbg \
    com.jaisocx.lib.array



#   --add-stylesheet "./readme/javadoc/bundle_css/clean_start_tiny__theme_base.css" \
#   --add-stylesheet "./readme/javadoc/bundle_css/clean_start_tiny__html_tags_n_styles.css" \
#   --add-stylesheet "./readme/javadoc/bundle_css/clean_start_tiny__sites_tools.css" \
#   --add-stylesheet "./readme/javadoc/bundle_css/clean_start_tiny__fonts.css" \

#    com.jaisocx.imaging \
#    com.jaisocx.imaging.app \
#    com.jaisocx.imaging.imaging_types \
#    com.jaisocx.imaging.cropping_sizes \
#    com.jaisocx.imaging.downgrader \
#    com.jaisocx.imaging.downgrader_sizes \
#    com.jaisocx.imaging.sizes.Constants \
#    com.jaisocx.imaging.helpers \
#    com.jaisocx.imaging.dbg \
#    com.jaisocx.imaging.example_dbg \
#    com.jaisocx.lib.array



