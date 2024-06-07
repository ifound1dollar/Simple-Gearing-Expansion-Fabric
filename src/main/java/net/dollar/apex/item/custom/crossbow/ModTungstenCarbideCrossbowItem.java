package net.dollar.apex.item.custom.crossbow;

import net.dollar.apex.item.custom.arrow.ArrowUtil;
import net.dollar.apex.util.ModUtils;
import net.minecraft.client.item.TooltipType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

/**
 * Corresponds specifically to the Tungsten-Carbide Crossbow item. Overrides and creates new methods to generate
 *  a custom arrow entity for special on-hit behavior.
 */
public class ModTungstenCarbideCrossbowItem extends CrossbowItem {
    public ModTungstenCarbideCrossbowItem(Settings settings) {
        super(settings);
    }



    //ONLY OVERRIDDEN METHODS ARE createArrowEntity() and appendTooltip(). Minecraft 1.20.5 largely fixed Crossbow
    //  implementation so the base methods do exactly what they need to. The customArrowEntity() method below
    //  is a new method that actually spawns the Entity by calling ArrowUtil.createCustomArrow().

    /**
     * Creates an arrow ProjectileEntity when the crossbow is fired. Is used to override vanilla functionality
     *  and spawn a custom ArrowEntity.
     * @param world Active world
     * @param shooter LivingEntity firing the crossbow
     * @param weaponStack ItemStack corresponding to this Crossbow
     * @param projectileStack ItemStack corresponding to the (now unused) Arrow stack
     * @param critical Whether the
     * @return The generated ProjectileEntity
     */
    @Override
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        if (projectileStack.isOf(Items.FIREWORK_ROCKET)) {
            return new FireworkRocketEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - (double)0.15f, shooter.getZ(), true);
        }

        //Vanilla functionality overridden only in next line.
        ProjectileEntity projectileEntity = customArrowEntity(world, shooter, weaponStack, projectileStack, critical);

        if (projectileEntity instanceof PersistentProjectileEntity persistentProjectileEntity) {
            persistentProjectileEntity.setShotFromCrossbow(true);
            persistentProjectileEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
        }
        return projectileEntity;
    }

    /**
     * Creates a custom arrow Entity using ArrowUtil that effectively replaces the method in RangedWeaponItem.
     * @param world Active world
     * @param entity LivingEntity firing the Crossbow
     * @param weaponStack ItemStack corresponding to this Crossbow
     * @param projectileStack ItemStack corresponding to the projectile being fired
     * @param critical Whether the arrow will be critical
     * @return The generated custom PersistentProjectileEntity
     */
    private static PersistentProjectileEntity customArrowEntity(World world, LivingEntity entity, ItemStack weaponStack,
                                                                ItemStack projectileStack, boolean critical) {
        //Replace vanilla functionality to get the ArrowItem from the found ItemStack with this function. Will
        //  automatically handle Spectral Arrow and Tipped Arrow functionality in-method.
        PersistentProjectileEntity persistentProjectileEntity = ArrowUtil.createCustomArrow(world, entity,
                projectileStack, ArrowUtil.ARROW_TYPE.CARBIDE);

        //Remainder of original function (with arrow creation omitted) is below.
        int k;
        int j;
        int i;
        if (critical) {
            persistentProjectileEntity.setCritical(true);
        }
        if ((i = EnchantmentHelper.getLevel(Enchantments.POWER, weaponStack)) > 0) {
            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)i * 0.5 + 0.5);
        }
        if ((j = EnchantmentHelper.getLevel(Enchantments.PUNCH, weaponStack)) > 0) {
            persistentProjectileEntity.setPunch(j);
        }
        if (EnchantmentHelper.getLevel(Enchantments.FLAME, weaponStack) > 0) {
            persistentProjectileEntity.setOnFireFor(100);
        }
        if ((k = EnchantmentHelper.getLevel(Enchantments.PIERCING, weaponStack)) > 0) {
            persistentProjectileEntity.setPierceLevel((byte)k);
        }
        return persistentProjectileEntity;
    }

    /**
     * Appends text to the Item's hover tooltip.
     * @param stack ItemStack corresponding to this item
     * @param context TooltipContext
     * @param tooltip List of tooltip texts to render
     * @param type TooltipType determining data like simple or advanced
     */
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ModUtils.appendTungstenCarbideEquipmentTooltip(tooltip, ModUtils.EquipmentType.RANGED);

        //Call super function because it has return statement if not charged.
        super.appendTooltip(stack, context, tooltip, type);
    }
}
