package me.asdanjer.firstitemv2;

import org.bukkit.scheduler.BukkitRunnable;

class Save extends BukkitRunnable {
    @Override
    public void run() {

        MaterialListWriter.writeMaterialListToFile(Firstitemv2.gottenmatierals,Firstitemv2.OUTPUTFILE);
    }
}

