DESCRIPTION = "Task packages for the sarge SBC extended image"
PR = "r0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"
#SRC_URI += "file://COPYING.GPL"

inherit task

SARGE_SENSORS_PACKAGES = "\
	lmsensors \
	i2c-tools \
	"

SARGE_ALSA_EXTRA_PACKAGES = "\
	alsa-plugins \
	"

SARGE_EXTRA_PACKAGES = "\
	alsa-lib \
	alsa-utils \
	alsa-oss \
	bash \
	portmap \
	kernel-module-isofs \
	kernel-module-udf \
	wireless-tools \
	wpa-supplicant \
	kernel-module-loop \
	kexec-tools \
	task-base-bluetooth \
	${SARGE_SENSORS_PACKAGES} \
	"

RDEPENDS_${PN} = "\
	${SARGE_EXTRA_PACKAGES} \
	"

# some additional modules
RRECOMMENDS_${PN} = "\
	${SARGE_EXTRA_PACKAGES} \
	"

SARGE_STANDARD_RDEPENDS += "${SARGE_EXTRA_PACKAGES}"
SARGE_STANDARD_RRECOMMENDS += "${SARGE_EXTRA_PACKAGES}"


IMAGE_INSTALL += "${SARGE_EXTRA_PACKAGES}"

