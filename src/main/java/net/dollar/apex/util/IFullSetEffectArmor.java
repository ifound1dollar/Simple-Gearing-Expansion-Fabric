package net.dollar.apex.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * Implemented by end-game armor tiers to determine whether certain effects can be applied to the wearer.
 */
public interface IFullSetEffectArmor {
    boolean canReceiveEffect(RegistryEntry<StatusEffect> effect, LivingEntity wearer);
}
