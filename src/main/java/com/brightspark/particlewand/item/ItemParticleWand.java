package com.brightspark.particlewand.item;

import com.brightspark.particlewand.ParticleWand;
import com.brightspark.particlewand.entity.LinearFX;
import com.brightspark.particlewand.entity.TrailingFX;
import com.brightspark.particlewand.util.Names;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemParticleWand extends Item
{
    private EnumParticleModes currentMode = EnumParticleModes.FIRE;
    private Minecraft mc = Minecraft.getMinecraft();
    private int cooldown = 0;

    public ItemParticleWand()
    {
        setUnlocalizedName(Names.Items.PARTICLE_WAND);
        setCreativeTab(ParticleWand.CREATIVE_TAB);
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return false;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if(player.isSneaking())
        {
            //Change mode
            currentMode = currentMode.getNextMode();
        }
        else if(cooldown <= 0)
        {
            //Use current mode
            if(world.isRemote)
            {
                switch(currentMode.getMovementType())
                {
                    case SHOOT:
                        //Shoots particle
                        mc.effectRenderer.addEffect(new LinearFX(world, player, currentMode.getTextureIndex()));
                        cooldown = 2;
                        break;
                    case TWIRL:
                        //Twirls particle around player
                        //mc.effectRenderer.addEffect(new LinearFX(world, player, currentMode.getTextureIndex()));
                        mc.effectRenderer.addEffect(new TrailingFX(world, player, 10, currentMode.getTextureIndex(), currentMode.getChileTextureIndex()).setChildRGBColour(0.4f, 1f, 0.4f));
                        //mc.effectRenderer.addEffect(new TrailingFX(world, player, 5, currentMode.getTextureIndex(), currentMode.getChileTextureIndex()).setChildRGBColour(0.4f, 1f, 0.4f).setAngle(180));
                        cooldown = 40;
                        break;
                    case RISE:
                        //Rises multiple particles up from player's feet
                        mc.effectRenderer.addEffect(new LinearFX(world, player, currentMode.getTextureIndex()));
                        cooldown = 40;
                        break;
                }
            }
        }

        return super.onItemRightClick(stack, world, player);
    }

    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        //Cooldown
        if(cooldown > 0)
            cooldown--;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("Shift right click to change mode.");
        tooltip.add("Cooldown: " + Integer.toString(cooldown));
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
        return super.getItemStackDisplayName(stack) + " (" + currentMode.getName() + ")";
    }
}
