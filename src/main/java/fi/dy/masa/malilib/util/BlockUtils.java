package fi.dy.masa.malilib.util;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.util.EnumFacing;

public class BlockUtils
{
    /**
     * Returns the EnumFacing value of the first found PropertyDirection
     * type blockstate property in the given state, if any.
     * If there are no PropertyDirection properties, then null is returned.
     * @param state
     * @return
     */
    @Nullable
    public static EnumFacing getFirstPropertyFacingValue(IBlockState state)
    {
        for (IProperty<?> prop : state.getProperties())
        {
            if (prop instanceof DirectionProperty)
            {
                return state.get((DirectionProperty) prop);
            }
        }

        return null;
    }
}
