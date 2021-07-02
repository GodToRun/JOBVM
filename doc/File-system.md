# VFS
Virtual File System.
JOBVM Support own VFS.
but, JOBVM does not support directory system now.
# How to use
First, OSLoader already defined default disk (A:)

import jobvm.fs.*;<br>
disk.AddFile(new VirtualFile(disk,"filename.txt"));

then, a file created on disk (A:).

# LOAD AND SAVE File-System
Save: 
VirtualDisk.Save(vp);

insert valid VirtualPC instance on 'vp'.
all files save on data\$DriveLetter(ex. A)\

Load:
VirtualDisk.Load(vp);

insert valid VirtualPC instance on 'vp'.
this method load all disk (data\).
