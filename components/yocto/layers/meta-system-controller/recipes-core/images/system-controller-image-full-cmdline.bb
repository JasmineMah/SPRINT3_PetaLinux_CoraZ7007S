DESCRIPTION = "A full featured console-only image for System Controller."

inherit core-image

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:system-controller = "${MACHINE}"

IMAGE_FEATURES += "splash ssh-server-openssh hwcodecs package-management"

IMAGE_INSTALL = " \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    dfx-mgr \
    image-update \
    kernel-modules \
    libubootenv \
    libubootenv-bin \
    linux-xlnx-udev-rules \
    lmsensors-config-libsensors \
    lmsensors-fancontrol \
    packagegroup-core-boot \
    packagegroup-scweb \
    packagegroup-syscontroller \
    python3-pip \
    python3-psutil \
    raft \
    repart-resize \
    systemcontroller-licenses-manual \
    u-boot-tools \
    ser2net \
"

IMAGE_INSTALL:append:eval-brd-sc-zynqmp = " uboot-device-tree"
IMAGE_INSTALL:remove:eval-brd-sc-zynqmp-sdt = "uboot-device-tree"
