# WHAT IS THE VIRTUAL METHOD?
Virtual method is using for handle virtual fields for virtual machine.<br>
virtual field allocate virtual memory.<br>
# VIRTUAL METHOD LIST
createByte(byte dat): returns memory location<br>
this method set free memory location address's value to data.
<br><br>
memory.push(byte data)<br>
this method push stack pointer and save address.
<br><br>memory.pop()<br>
this method pop stack pointer.
<br><br>
free(int location)<br>
this method free memory on parameter address.
# VIRTUAL POINTER
in c:<br>
int *p = 12345;<br>
printf("%d", *p);<br>
in jobvm:<br>
Pointer p = Pointer.ref(12345);<br>
graphic.Println(p.getValue() + "");
<br><br>
in c:<br>
int i = 1;<br>
int *p = &i;<br>
in jobvm:<br>
Pointer p = Pointer.ref(createByte((byte)1));
