package com.brightspark.particlewand.entity;

import com.brightspark.particlewand.util.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class DisappearingStaticFX extends StaticFX
{
    private float maxAlpha;

    public DisappearingStaticFX(World world, Entity entity, double relativeX, double relativeY, double relativeZ, int maxAge, int textureIndex)
    {
        super(world, entity, relativeX, relativeY, relativeZ, maxAge, textureIndex);
        //setAlphaF(0.5f);
        maxAlpha = particleAlpha;
    }

    public DisappearingStaticFX setInitialAlpha(float a)
    {
        if(particleAge > 0)
            new Exception("Tried to change initial alpha after particle has aged!");
        setAlphaF(a);
        maxAlpha = a;
        return this;
    }

    public void onUpdate()
    {
        float age = (float) particleAge;
        float max = (float) particleMaxAge;
        setAlphaF((max-age) / max);

        LogHelper.info(this.toString());
        super.onUpdate();
    }
}
