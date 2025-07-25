# For XSCT builds

DEPENDS += "sdt-artifacts"
COMPATIBLE_MACHINE:emb-plus-ve2302-sdt = "${MACHINE}"

PARTITION_METADATA_PATH ?= "${SYSTEM_DTFILE_DIR}"

do_compile:prepend() {
    if [ -e ${PARTITION_METADATA_PATH}/project/${PARTMETA_FILE} ]; then
        cp ${PARTITION_METADATA_PATH}/project/${PARTMETA_FILE} ${MACHINE}_${PARTMETA_FILE}
    else
        bbfatal "Unable to find SDT Artifacts path: ${PARTITION_METADATA_PATH}"
    fi

    xclbinutil --add-section PARTITION_METADATA:JSON:${MACHINE}_${PARTMETA_FILE} \
        -o ${WORKDIR}/${PN}.xsabin --force
}