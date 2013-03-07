# This describes the Black Mesa East sarge SBC extended image, which includes everything
# that is in the standard sarge image, with a few extra drivers.

DESCRIPTION = "sarge-at91 extended image"

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
	file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c"

PR = "r2"

require sarge-image.inc


IMAGE_NAME = "${IMAGE_BASENAME}-${DISTRO_VERSION}-ext"
IMAGE_LINK_NAME = "${IMAGE_BASENAME}-${MACHINE}-ext"
DEPENDS += "console-image \
	task-sarge-ext \
	"
IMAGE_INSTALL += "task-sarge-ext"
TASK_BASIC_SSHDAEMON = "openssh-sshd openssh-sftp openssh-sftp-server"
IMAGE_FEATURES += "task-core-ssh-openssh"
export IMAGE_BASENAME="sarge-image-ext"
inherit image
