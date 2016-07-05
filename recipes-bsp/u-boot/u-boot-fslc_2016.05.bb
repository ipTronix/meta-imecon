require recipes-bsp/u-boot/u-boot.inc

# ensure the device tree compiler is installed for the host machine
DEPENDS = "dtc"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=22;md5=2687c5ebfd9cb284491c3204b726ea29"

SRCREV = "${AUTOREV}"

PV = "v2016.05"

SRC_URI = "git://github.com/Freescale/u-boot-fslc.git;branch=2016.05+fslc"

SRC_URI_append_imeconVKY = " \
   file://0000-Add_Imecon_VKY.patch \
"

S = "${WORKDIR}/git"

do_compile () {
	if [ "${@base_contains('DISTRO_FEATURES', 'ld-is-gold', 'ld-is-gold', '', d)}" = "ld-is-gold" ] ; then
		sed -i 's/$(CROSS_COMPILE)ld$/$(CROSS_COMPILE)ld.bfd/g' config.mk
	fi

	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS

	cd ${S}; git status; cd -

	oe_runmake ${UBOOT_MACHINE}
	oe_runmake ${UBOOT_MAKE_TARGET}
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

