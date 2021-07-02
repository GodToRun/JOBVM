# Virtual Driver
JOBVM also support virtual driver.
# Driver list

CPU<br>
Memory<br>
Graphic Card<br>
Sound Card(WIP)<br>
VMDriver(WIP)<br>

# CPU

CPU have a eight registers.<br>
Numbers:
<br>
ia
is
id
ie
<br>
Strings:
sa
si
sd
se

and you can request cpu api.<br>
example:<br>
cpu.sa = "Hello, World!"; //set sa register
cpu.request(1) //1: print sa register

# Memory
Memory save resources.
<br>
Memory.setMaxAddress(int maxAddress); to set max memory size.
<br>
Memory.setAddressValue(int memory_location, byte value) method set location's value to byte parameter.
<br>
Memory.getMap() to returns Memory byte arrays.
<br>
Memory.getAddressValue(int memory_location) to returns byte value.
<br>
Memory.setBuffer(byte[] data) to set memory buffer.
<br>
Memory.getBuffer() returns memory buffer byte array.
# Graphic Card
Graphic Card is show virtual-console graphic or other.<br>
now, JOBVM doesn't support graphic, but there some commands.<br>

graphic.Println("Hello, VM!");
<br>
GraphicCard.Println(String msg) method write text in screen.
<br>
GraphicCard.Clear() method clear the screen.
<br>
GraphicCard.GetInput() method returns string. get input from user.
<br>
GraphicCard.readKey() method returns char, get any key from user.

# Sound Card
Work in process.
# VMDriver
Work in process.
