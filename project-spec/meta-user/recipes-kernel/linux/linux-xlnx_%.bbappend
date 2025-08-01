FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://bsp.cfg"
KERNEL_FEATURES:append = " bsp.cfg"SRC_URI += "file://user_2025-07-28-23-21-00.cfg"

SRC_URI += "file://user_2025-07-31-22-14-00.cfg"

