



# Prawa dostępu do USB 
## utworzenie pliku
/etc/udev/rules.d/50-bdi.rules
## zawartość pliku
SUBSYSTEM=="usb", GROUP="plugdev", ATTRS{idVendor}=="10c4" 
## przeladowanie zasad
sudo udevadm control --reload-rules
