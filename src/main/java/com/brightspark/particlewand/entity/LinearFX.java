package com.brightspark.particlewand.entity;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class LinearFX extends EntityFX
{
    /**
     * Spawn at entity and move in direction it's looking
     * @param world World
     * @param entity Entity to attach to
     */
    public LinearFX(World world, Entity entity, int textureIndex)
    {
        this(world, entity.posX, entity.posY + 1.2, entity.posZ, entity.getLookVec().xCoord, entity.getLookVec().yCoord, entity.getLookVec().zCoord, textureIndex);
    }

    public LinearFX(World world, double posX, double posY, double posZ, double speedX, double speedY, double speedZ, int textureIndex)
    {
        super(world, posX, posY, posZ);
        motionX = speedX;
        motionY = speedY;
        motionZ = speedZ;
        setParticleTextureIndex(textureIndex);
        noClip = false;
        particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
    }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
