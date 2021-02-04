package com.frank142857.fcc;

import com.frank142857.fcc.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = FCC.MODID, name = FCC.NAME, version = FCC.VERSION, acceptedMinecraftVersions = "[1.12,1.13)", dependencies = "")
public class FCC
{
    public static final String MODID = "fcc";
    public static final String NAME = "Fortune Character Collection";
    public static final String VERSION = "2.12.2021";

    private static Logger logger;

    @SidedProxy(clientSide = "com.frank142857.fcc.proxy.ClientProxy", serverSide = "com.frank142857.fcc.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("FCC mod added");
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    public void sop(Object obj){
        System.out.println(obj);
    }
}
