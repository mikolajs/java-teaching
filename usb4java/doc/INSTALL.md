



# Prawa dostępu do USB 
## utworzenie pliku
/etc/udev/rules.d/92-cypherlab.rules
## zawartość pliku
SUBSYSTEM=="usb", GROUP="plugdev", ATTRS{idVendor}=="10c4", MODE="0660",
## przeladowanie zasad
sudo udevadm control --reload-rules
