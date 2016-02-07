package com.brightspark.particlewand.item;

import com.google.common.collect.Maps;

import java.util.Map;

public enum EnumParticleModes
{
    FIRE("Fire", 0, MOVEMENTS.SHOOT, 48), //48 = Flame
    RESISTANCE("Resistance", 1, MOVEMENTS.TWIRL, 82, 160), //82 = Happy Villager, 160 = Smallest Spark
    HEAL("Heal", 2, MOVEMENTS.RISE, 80); //80 = Heart

    public enum MOVEMENTS
    {
        SHOOT,
        TWIRL,
        RISE
    }

    private final String modeName;
    private final int modeID;
    private final MOVEMENTS modeMovement;
    private final int modeTextureIndex;
    private final int modeChildTextureIndex;
    private static final Map<Integer, EnumParticleModes> MODES = Maps.newHashMap();

    @SuppressWarnings("all")
    private EnumParticleModes(String name, int id, MOVEMENTS movement, int textureIndex)
    {
        this(name, id, movement, textureIndex, -1);
    }

    @SuppressWarnings("all")
    private EnumParticleModes(String name, int id, MOVEMENTS movement, int textureIndex, int childTextureIndex)
    {
        modeName = name;
        modeID = id;
        modeMovement = movement;
        modeTextureIndex = textureIndex;
        modeChildTextureIndex = childTextureIndex;
    }

    /**
     * Gets the name of this mode.
     * @return Mode Name
     */
    public String getName()
    {
        return modeName;
    }

    /**
     * Gets the ID of this mode.
     * @return Mode ID
     */
    public int getID()
    {
        return modeID;
    }

    /**
     * Gets the type of movement for this mode.
     * @return Movement Type
     */
    public MOVEMENTS getMovementType()
    {
        return modeMovement;
    }

    /**
     * Gets the vanilla particle texture index for this mode.
     * @return Particle Texture Index
     */
    public int getTextureIndex()
    {
        return modeTextureIndex;
    }

    /**
     * Gets the child particles (if any) of this one's particle texture index.
     * If this doesn't have child particles, then this is -1.
     * @return Child Particle Texture Index
     */
    public int getChileTextureIndex()
    {
        return modeChildTextureIndex;
    }

    /**
     * Gets the mode using it's ID.
     * @param id ID of the mode wanted
     * @return The mode
     */
    public EnumParticleModes getModeByID(int id)
    {
        return MODES.get(id);
    }

    /**
     * Returns the next mode using their ID.
     * @return Next mode
     */
    public EnumParticleModes getNextMode()
    {
        EnumParticleModes next = getModeByID(modeID + 1);
        return next != null ? next : getModeByID(0);
    }

    //Creates map of all modes
    static
    {
        for (EnumParticleModes enumModes : values())
        {
            MODES.put(enumModes.getID(), enumModes);
        }
    }
}
