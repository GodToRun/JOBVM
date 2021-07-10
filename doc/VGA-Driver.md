# WHAT IS A VGA?
vga is a Video Graphics Array, jobvm use<br>
virtual vga screen. (320x200)
# METHODS
graphic.GraphicMode() - set mode to graphic(vga) mode.<br>
graphic.ConsoleMode() - set mode to console mode.<br>
graphic.isConsoleMode() : boolean - return true if current mode is console mode.<br>
graphic.setPixel(int x, int y, short color) - draw colorerd pixel on x, y.<br>
graphic.ConvertColor(short classic) : short - returns converted vga color.<br>
graphic.Draw(Graphics g) - draw vga screen.
