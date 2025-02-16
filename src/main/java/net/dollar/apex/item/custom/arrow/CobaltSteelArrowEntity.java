package net.dollar.apex.item.custom.arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpectralArrowItem;
import net.minecraft.world.World;

public class CobaltSteelArrowEntity extends ArrowEntity {
    private boolean isSpectral;

    public CobaltSteelArrowEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack, null);
    }



    /**
     * Checks whether the passed-in ItemStack's corresponding Item is a SpectralArrowItem, setting
     *  the local isSpectral variable if so (affects onHit() behavior).
     * @param arrow ItemStack of the ArrowItem used to spawn this ArrowEntity
     */
    public void checkIsSpectral(ItemStack arrow) {
        if (arrow.getItem() instanceof SpectralArrowItem) { isSpectral = true; }
    }

    /**
     * Sets the base damage value of this ArrowEntity (set to 3.0 from 2.0).
     * @param damage New base damage (default 2.0)
     */
    @Override
    public void setDamage(double damage) {
        super.setDamage(3.0);
    }

    /**
     * Performs operations as the arrow hits a target LivingEntity.
     * @param target The collided LivingEntity
     */
    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);

        //If the arrow is spectral, make the target glowing (same functionality as actual Spectral Arrow).
        if (isSpectral) {
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(
                    StatusEffects.GLOWING, 200, 0); //10 seconds
            target.addStatusEffect(statusEffectInstance, this.getEffectCause());
        }

        //If the owner (who fired the arrow) is a LivingEntity.
        if (getOwner() instanceof LivingEntity livingEntity) {
            //Apply Slowness effect to target (attackedEntity) for configurable duration in seconds.
            //TODO: RE-IMPLEMENT CONFIGS
            //Level 2 slow (third argument) for 30% reduction, 15%/level.
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20, 1));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
                    4 * 20, 1));
        }
    }
}
