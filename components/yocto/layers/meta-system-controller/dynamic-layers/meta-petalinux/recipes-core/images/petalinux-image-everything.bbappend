require petalinux-image-common-sc.inc

IMAGE_INSTALL:append:system-controller = " \
    packagegroup-syscontroller \
    packagegroup-scweb \
    packagegroup-systemcontroller-boards \
    libubootenv \
    libubootenv-bin \
    mmc-utils \
    resize-partition \
    u-boot-tools \
    udev-extraconf \
    "

IMAGE_INSTALL:append:eval-brd-sc-zynqmp = " uboot-device-tree"
IMAGE_INSTALL:remove:eval-brd-sc-zynqmp-sdt = "uboot-device-tree"
