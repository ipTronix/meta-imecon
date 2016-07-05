# Imecon VKY

DESCRIPTION = "kernel for Imecon VKY platform"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_imeconVKY = " \
  file://0000-imeconVKY-dts.patch \
"

KERNEL_IMAGE_BASE_NAME ?= "${KERNEL_IMAGETYPE}-${PKGE}-${PKGV}-${PKGR}"
