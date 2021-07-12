# CREATE BOOT SECTOR
you need to write a simple boot sector.
create 'data' directory on virtual os path, then create file 'BOOT.SYS' in 'data' directory<br>
and open 'BOOT.SYS' and write this:<br><br>
126 6 9 8 11 17
<br><br>
then save and close file.<br>
and write java codes. (see the example/SimpleOS.java)<br>
# PRINT ON THE SCREEN
then run, you can see os successfuly booted. next is the print on screen.<br>
write bootsector file this:<br><br>
126 6 9 8 11 17 72 17 101 17 108 17 108 17 111 17 33
<br><br>
'17' command mean print next ascii code.<br>
then save and run java file, you can see the screen printed 'Hello!'<br>
now you see other docs and write code right now!
# HOW IT WORKS?
first, os loader load all resources and virtual cpu read boot record.<br>
