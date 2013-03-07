# This describes a generic Black Mesa East sarge SBC image, even though the bb file is
# called 'sarge-image.bb' the distro specific configuration is
# done in conf/distro/${DISTRO}.conf (which should always include
# conf/distro/sarge.conf to get the standard settings).
#
DESCRIPTION = "Task packages for the sarge-at91 distribution"
HOMEPAGE = "http://www.blackmesaeast.com.pl"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
#SRC_URI += "file://COPYING.GPL"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe"

PR = "r24"
PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "sarge-at91"
ALLOW_EMPTY = "1"

#----------------------------------------------------------------------------------
# FIRMWARE CONFIGURATION
#----------------------------------------------------------------------------------
# EXTRA PACKAGES
# --------------
# The standard firmware contents and additional packages built as requirements
# of the firmware are defined here in SARGE_STANDARD_RDEPENDS.  This represents
# the standard set of software for the sarge-at91 device.
SARGE_STANDARD_RDEPENDS ?= ""
SARGE_STANDARD_RRECOMMENDS ?= ""
SARGE_MACHINE_RDEPENDS ?= ""
SARGE_MACHINE_RRECOMMENDS ?= ""


# These lines add support for formatting ext2 and ext3 file systems
# on a hard disk attached to the sarge-at91.  ext3 is the standard Linux
# file system.
SARGE_STANDARD_RRECOMMENDS += "\
e2fsprogs-mke2fs \
e2fsprogs-fsck \
e2fsprogs-e2fsck \
e2fsprogs-badblocks \
e2fsprogs-blkid \
"

# Filesystem selection.  Adding entries here adds the module to the
# image.  The module must be built as part of sarge-kernel (i.e. it
# must be specified as a module in the defconfig file).  The NLS
# support charset modules must be given explicitly and must match
# the codepage/iocharset and NLS handling for the file systems which
# require them.  The installed lanugage set is minimal but sufficient
# for any file system (since it uses utf8).  
#
# KERNEL LEVEL FILE SYSTEM SUPPORT
# --------------------------------
# NOTE: removing kernel-module-nfs from this list will prevent NFS
# boot (however you can do a simple flash file system boot - no
# attached disk - and install the nfs modules from ssh.)
# The altboot mechanism requires kernel-module-loop.
SARGE_STANDARD_RRECOMMENDS += "\
kernel-module-ext2 \
kernel-module-jbd \
kernel-module-ext3 \
kernel-module-vfat \
kernel-module-nls-cp437 \
kernel-module-nls-utf8 \
kernel-module-nfs \
kernel-module-btusb \
kernel-module-hci-usb \
kernel-module-ipwireless \
wpa-supplicant \
wpa-supplicant-cli \
wpa-supplicant-passphrase \
bluez4 \
"

# Add modules required for usb support
# starndard sarge-at91 kernal has this modlue compiled in, uncomment if not
#kernel-module-ohci-hcd \
SARGE_STANDARD_RRECOMMENDS += "\
"

# Add packages and modules required for RAID-1 support
SARGE_STANDARD_RRECOMMENDS += "\
mdadm \
kernel-module-md-mod \
kernel-module-raid1 \
"

# Add the machine-specific RRECOMMENDS stuff -- kernel modules required for
# network support.
SARGE_MACHINE_RRECOMMENDS_sarge-at91 = "\
"

# Add machine-specific RDEPENDS stuff
SLUGOS_MACHINE_RDEPENDS_sarge-at91 = "\
"

DISTRO_EXTRA_DEPENDS ?= ""
DEPENDS += "${DISTRO_EXTRA_DEPENDS}"

DISTRO_EXTRA_RDEPENDS ?= ""

RDEPENDS += "\
	kernel \
	base-files \
	base-passwd \
	netbase \
	busybox \
	bash \
	portmap \
	initscripts \
	update-modules \
	sysvinit \
	udev \
	wireless-tools \
	bluez4 \
	wpa-supplicant \
	module-init-tools \
	modutils-initscripts \
	opkg-collateral ${IPKG_VARIANT} \
	libgcc \
	util-linux-mount \
	task-base-bluetooth \
	${SARGE_STANDARD_RDEPENDS} \
	${SARGE_MACHINE_RDEPENDS} \
	${DISTRO_EXTRA_RDEPENDS}"

DISTRO_EXTRA_RRECOMMENDS ?= ""
RRECOMMENDS += "\
	openssh \
	${SARGE_STANDARD_RRECOMMENDS} \
	${SARGE_MACHINE_RRECOMMENDS} \
	${DISTRO_EXTRA_RRECOMMENDS} \
	"

IMAGE_INSTALL += "\
	openssh \
	${SARGE_STANDARD_RRECOMMENDS} \
	${SARGE_MACHINE_RRECOMMENDS} \
	${DISTRO_EXTRA_RRECOMMENDS} \
	"
