LICENSE = "Proprietary & GPL-2.0-only"
LIC_FILES_CHKSUM = " \
	file://${WORKDIR}/git/LICENSE-BINARIES;md5=fa9b03ada9ffccf51dd60899bf28c702 \
	file://${WORKDIR}/git/LICENSE-GPLv2;md5=9afdcd1be3f71bd3791fa5961075d776 \
	"
BRANCH = "xlnx_rel_v2023.2"
SRC_URI = "git://github.com/Xilinx/versal-sc-firmware.git;protocol=https;branch=${BRANCH}"
SRCREV ?= "43bd0335da8f9368135805a496877509afc0b1f7"

inherit dfx_user_dts
COMPATIBLE_MACHINE = "^$"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[prefuncs] =+ "move_fw_files"
python move_fw_files () {
    import shutil
    import os

    s = d.getVar('S')
    fw_filename = d.getVar('FW_FILENAME')
    pn = d.getVar('PN')
    shutil.move(os.path.join(s, fw_filename + '.bit'), os.path.join(s, pn + '.bit'))
    shutil.move(os.path.join(s, fw_filename + '.dtsi'), os.path.join(s, pn + '.dtsi'))
}
