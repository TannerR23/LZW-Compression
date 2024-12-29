# LZW Compression
Converts the input to from bytes to hex, then performs LZW encoding, packs it down for further compression and then does the reverse to get the final exact output of the input.

## Requires
linux operating system

### Input
a text or image file

### Check correctness
can use md5sum

## How to use
Command to run full program piping each output as input to the next:
    cat **textfile/imagefile** | java ByteToHex | java LZWencode | java LZWpack | java LZWunpack | java LZWdecode | java HexToByte > **outputfilename**


### Notes
Does not contain commmit history as am in the process of adding pre-existing projects to my github.