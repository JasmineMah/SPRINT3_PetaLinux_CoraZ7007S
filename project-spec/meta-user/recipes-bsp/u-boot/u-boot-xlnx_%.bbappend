FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://platform-top.h file://bsp.cfg"
SRC_URI += "file://user_2025-07-18-07-02-00.cfg \
            file://user_2025-07-18-09-29-00.cfg \
            "

