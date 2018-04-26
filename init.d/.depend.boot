TARGETS = console-setup mountkernfs.sh resolvconf ufw x11-common hostname.sh lm-sensors udev cryptdisks cryptdisks-early networking urandom hwclock.sh checkroot.sh lvm2 checkfs.sh mountdevsubfs.sh rpcbind bootmisc.sh checkroot-bootclean.sh mountall-bootclean.sh mountall.sh udev-finish kmod procps mountnfs-bootclean.sh mountnfs.sh
INTERACTIVE = console-setup udev cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: mountkernfs.sh urandom resolvconf procps
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountdevsubfs.sh: mountkernfs.sh udev
rpcbind: networking
bootmisc.sh: udev checkroot-bootclean.sh mountall-bootclean.sh mountnfs-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
udev-finish: udev
kmod: checkroot.sh
procps: mountkernfs.sh udev
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking rpcbind
