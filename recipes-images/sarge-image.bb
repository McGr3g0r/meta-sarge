# This describes a generic Black Mesa East sarge SBC image, even though the bb file is
# called 'sarge-image.bb' the distro specific configuration is
# done in conf/distro/${DISTRO}.conf (which should always include
# conf/distro/sarge.conf to get the standard settings).

DESCRIPTION = "Generic sarge SBC image"

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe \
	file://COPYING.LGPL;md5=4fbd65380cdd255951079008b364516c"

PR = "r1"

require sarge-image.inc


DEPENDS += "console-image \
	"

TASK_BASIC_SSHDAEMON = "openssh-sshd openssh-sftp openssh-sftp-server"
IMAGE_FEATURES += "task-core-ssh-openssh"

inherit image
