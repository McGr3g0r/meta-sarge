# This describes a generic Black Mesa East sarge SBC image, even though the bb file is
# called 'sarge-image.bb' the distro specific configuration is
# done in conf/distro/${DISTRO}.conf (which should always include
# conf/distro/sarge.conf to get the standard settings).

HOMEPAGE = "http://www.blackmesaeast.com.pl"

DEPENDS = "task-sarge"
IMAGE_INSTALL = "task-sarge"

COMPATIBLE_MACHINE = "sarge-at91"

export IMAGE_BASENAME = "sarge-image"

IMAGE_NAME = "${IMAGE_BASENAME}-${DISTRO_VERSION}"
IMAGE_LINK_NAME = "${IMAGE_BASENAME}-${MACHINE}"
IMAGE_FSTYPES += "jffs2 tar.gz"
#IMAGE_DEVICE_TABLES = "files/device_table-sarge.txt"
IMAGE_LINGUAS = ""

# IMAGE_PREPROCESS_COMMAND is run before making the image.  In Sarge SBC image the
# kernel image is removed from the root file system to recover the space used -
# sarge-at91 SBC is assumed to boot from a separate kernel image in dataflash (not in the
# root file system), if this is not the case the following must not be done!
IMAGE_PREPROCESS_COMMAND += "rm -f ${IMAGE_ROOTFS}/boot/?Image*;"

IMAGE_PREPROCESS_COMMAND += "create_etc_timestamp"

#zap root password for release images
ROOTFS_POSTPROCESS_COMMAND += '${@base_conditional("DISTRO_TYPE", "release", "zap_root_password; ", "",d)}'
ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES}", d)}'

TASK_BASIC_SSHDAEMON = "openssh-sshd openssh-sftp openssh-sftp-server"

IMAGE_INSTALL += " \
	angstrom-task-boot \
	task-basic \
	${ROOTFS_PKGMANAGE_PKGS} \
	timestamp-service \
"

IMAGE_DEV_MANAGER   = "udev"

inherit image
