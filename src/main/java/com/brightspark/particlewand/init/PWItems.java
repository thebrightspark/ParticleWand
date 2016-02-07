package com.brightspark.particlewand.init;

import com.brightspark.particlewand.item.ItemParticleWand;
import com.brightspark.particlewand.util.LogHelper;
import com.brightspark.particlewand.util.Names;
import com.brightspark.particlewand.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.ItemModelMesherForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PWItems
{
    public static final ItemParticleWand wand = new ItemParticleWand();

    public static void regItems()
    {
        GameRegistry.registerItem(wand, Names.Items.PARTICLE_WAND);
    }

    public static void regModels()
    {
        m = (ItemModelMesherForge) Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        regModel(wand);
    }

    private static ItemModelMesherForge m;
    //Register a model
    private static void regModel(Item item)
    {
        regModel(item, 0);
    }
    //Register a model with meta
    private static void regModel(Item item, int meta)
    {
        String itemName = item.getUnlocalizedName();
        LogHelper.info("Registering texture " + Reference.ITEM_TEXTURE_DIR + itemName.substring(itemName.indexOf(".") + 1));
        m.register(item, meta, new ModelResourceLocation(Reference.ITEM_TEXTURE_DIR + itemName.substring(itemName.indexOf(".") + 1), "inventory"));
    }
}
