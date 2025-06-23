FILESEXTRAPATHS:prepend:system-controller := "${THISDIR}/${PN}:"

PACKAGE_ARCH:system-controller = "${MACHINE_ARCH}"
SRC_URI:append:system-controller = " file://mongod.conf "

# Ensure this package is newer then the base generic package (r0).
PR:system-controller = "r1"

do_install:append:system-controller() {
    install -d ${D}${sysconfdir}/
    install -m 0644 ${WORKDIR}/mongod.conf ${D}${sysconfdir}/
}
