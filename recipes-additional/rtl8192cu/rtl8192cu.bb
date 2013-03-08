DESCRIPTION = "Realtek 8192cu USB wifi driver Install" 
PR = "r0" 

LICENSE = "GPLv2+ & LGPLv2.1+"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/LGPL-2.0;md5=9427b8ccf5cf3df47c29110424c9641a"

SRC_DRIVER="RTL8188C_8192C"
SRC_VER="v3.4.4_4749.20121105"
SRC_URI="ftp://WebUser:r3iZ6vJI@95.130.192.218/cn/wlan/RTL8192xC_USB_linux_${SRC_VER}.zip" 
SRC_URI[md5sum] = "791bde2cd1a13dfbbf2338c799dd8aaf" 
SRC_URI[sha256sum] = "e74d6ad1451eebc2cbfb6456c81b6f8278f36f1f366fcd4fd7efd84754e51a3b" 

S = "${WORKDIR}/RTL8188C_8192C_USB_linux_${SRC_VER}/driver"

inherit module 

do_compile() { 
        cd ${S}
        tar -zxvf rtl8188C_8192C_usb_linux_${SRC_VER}.tar.gz
        cd rtl8188C_8192C_usb_linux_${SRC_VER}
        ls -l 
        unset LDFLAGS 
        oe_runmake KSRC=${STAGING_KERNEL_DIR} CFLAGS='${CFLAGS}' 
        ${TARGET_SYS}-strip --strip-debug 8192cu.ko 
} 

do_install() { 
  install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless 
  install -m 0644 ${S}/rtl8188C_8192C_usb_linux_v3.4.4_4749.20121105/8192cu.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless 
} 
