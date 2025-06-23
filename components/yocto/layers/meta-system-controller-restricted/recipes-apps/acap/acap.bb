#
# This file is the acap recipe.
#

SUMMARY = "Simple acap application"
SECTION = "PETALINUX/apps"
LICENSE = "MIT & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1249d07defc2f21c0bc10e688d82f037"

SRC_URI = "git://gitenterprise.xilinx.com/IPSP/ACAP_Cockpit.git;protocol=https;branch=release"
SRC_URI:append = " file://acaprun.service"

SRCREV = "f629df178d531411050d3121f8e47441593cae70"
S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:system-controller = "${MACHINE}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit update-rc.d systemd

INITSCRIPT_NAME = "acaprun.sh"
INITSCRIPT_PARAMS = "start 98 5 ."

SYSTEMD_PACKAGES="${PN}"
SYSTEMD_SERVICE:${PN}="acaprun.service"
SYSTEMD_AUTO_ENABLE:${PN}="enable"

do_install() {
    install -d ${D}${bindir}/src/ACAP_Cockpit/
    install -d ${D}${sysconfdir}/apache2/conf.d/

    cp -r ${S}/* ${D}${bindir}/src/ACAP_Cockpit/
    install -m 0644 ${S}/acap.conf   ${D}${sysconfdir}/apache2/conf.d/


    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
         install -d ${D}${sysconfdir}/init.d/
         install -m 0755 ${S}/acaprun.sh ${D}${sysconfdir}/init.d/
    fi

    install -d ${D}${systemd_system_unitdir} 
    install -d ${D}${bindir} 

    install -m 0644 ${WORKDIR}/acaprun.service ${D}${systemd_system_unitdir}
    install -m 0755 ${S}/acaprun.sh ${D}${bindir}
}

pkg_postinst_ontarget:${PN}() {
    chown -R mongodb.mongodb /usr/bin/src/ACAP_Cockpit/data/db
}

RDEPENDS:${PN} = " \
    bash \
    python3-certifi \
    python3-chardet \
    python3-markupsafe \
    python3-pluggy \
    python3-pytest \
    python3-jinja2 \
    python3-flask \
    python3-flask-cors \
    python3-flask-pymongo \
    python3-pymongo \
    python3-tornado \
    python3-bson \
    python3-flask-classy \
    python3-flask-httpauth \
    python3-asgiref \
    mongodb \
    apache2 \
    "

FILES:${PN} += "\
    ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','${sysconfdir}/init.d/acaprun.sh', '', d)} \
    ${systemd_system_unitdir} \
    "

SKIP_RECIPE[acap] = "mongodb is not available with python 3.12 or newer"
