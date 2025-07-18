FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " file://bsp.cfg"
KERNEL_FEATURES:append = " bsp.cfg"
SRC_URI += "file://user_2025-06-30-00-48-00.cfg \
            file://user_2025-07-01-06-53-00.cfg \
            file://user_2025-07-17-00-30-00.cfg \
            "

