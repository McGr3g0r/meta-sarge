require linux.inc

PR = "${INC_PR}.5"
COMPATIBLE_MACHINE = "(sarge-at91)"

SRC_URI += "${KERNELORG_MIRROR}/linux/kernel/v3.x/linux-${PV}.tar.bz2;name=kernel \
           ${KERNELORG_MIRROR}/linux/kernel/v3.x/patch-${PV}.12.bz2;apply=yes;name=stablepatch \
           file://defconfig \
           file://${LOGO_SIZE}/logo_linux_clut224.ppm.bz2 \
           "

SRC_URI_append_sarge-at91 = " \
                         file://board-sarge.patch \
                         file://ste100p-sarge.patch \
                         "


# update machine types list for ARM architecture, only for machines that need it

SRC_URI[kernel.md5sum] = "7ceb61f87c097fc17509844b71268935"
SRC_URI[kernel.sha256sum] = "c881fc2b53cf0da7ca4538aa44623a7de043a41f76fd5d0f51a31f6ed699d463"
SRC_URI[stablepatch.md5sum] = "377a6d731cd246aaa0c0f6a432b7aece"
SRC_URI[stablepatch.sha256sum] = "c6991964901ef7797cca09ff9d1b4ed7abda27e2796ad55cad09a266eaaebb5d"
