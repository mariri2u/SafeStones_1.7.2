package mariri.safestones;


import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SafeStones.MODID, version = SafeStones.VERSION)
public class SafeStones
{
    public static final String MODID = "SafeStones";
    public static final String VERSION = "1.1b";
    
//    private static boolean INVERT_SPAWNER_RULE;
    
    public static BlockSafeVanillaStone blockSafeStone;
    public static BlockSafeColorStone blockSafeColorStone;
    public static BlockEnderStone blockEnderStone;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        
        ModRegistry.INVERT_SPAWN_RULE = config.get(Configuration.CATEGORY_GENERAL, "invertSpawnRule", false).getBoolean(false);
//        INVERT_SPAWNER_RULE = config.get(Configuration.CATEGORY_GENERAL, "invertSpawnerRule", false).getBoolean(false);
       
        config.save();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event){
        blockSafeStone = new BlockSafeVanillaStone();
        blockSafeColorStone = new BlockSafeColorStone();
        blockEnderStone = new BlockEnderStone();
        
        OreDictionary.registerOre("stonebrick", new ItemStack(Blocks.stonebrick));
        
        if(ModRegistry.INVERT_SPAWN_RULE){
        	MinecraftForge.EVENT_BUS.register(CheckSpawnEventHandler.INSTANCE);
        }
        
//        if(INVERT_SPAWNER_RULE){
//        	MinecraftForge.EVENT_BUS.register(SpecialSpawnEventHandler.INSTANCE);
//        }
    }
}
