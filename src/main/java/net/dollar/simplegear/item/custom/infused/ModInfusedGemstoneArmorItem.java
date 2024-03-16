package net.dollar.simplegear.item.custom.infused;

import net.dollar.simplegear.item.ModItems;
import net.dollar.simplegear.util.IDamageHandlingArmor;
import net.dollar.simplegear.util.IInfusedGemstoneItem;
import net.dollar.simplegear.util.ModUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModInfusedGemstoneArmorItem extends ArmorItem implements IDamageHandlingArmor, IInfusedGemstoneItem {
    boolean isFullSet;

    public ModInfusedGemstoneArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }



    /**
     * Checks each tick if the player has a full set of Infused Gemstone armor, setting isFullSet if so.
     * @param stack The ItemStack associated with this Object
     * @param world Active world
     * @param entity The entity holding the item; usually a player
     * @param slot The slot this item is equipped in
     * @param selected Whether the item is in the selected hotbar slot
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        //Do nothing if on client side OR if not chestplate (isFullSet will never be true on clients).
        if (world.isClient() || slot != 38) { return; }   //Slot 38 corresponds to chestplate slot

        //TODO: COMPARE CORRECT INFUSED GEMSTONE ITEMS
        //Check for correct equipment, then set isFullSet accordingly
        if (entity instanceof PlayerEntity player) {
            boolean hasBoots = player.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.INFUSED_GEMSTONE.asItem();
            boolean hasLegs = player.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.INFUSED_GEMSTONE.asItem();
            boolean hasChest = player.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.INFUSED_GEMSTONE.asItem();
            boolean hasHelm = player.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.INFUSED_GEMSTONE.asItem();
            isFullSet = hasBoots && hasLegs && hasChest && hasHelm;
        } else {
            //If not player, always false.
            isFullSet = false;
        }
    }

    /**
     * Intercepts the damage taken operation to reduce Magic damage taken.
     * @param entity Attacked LivingEntity (wearer)
     * @param slot EquipmentSlot of this item
     * @param source Source of damage to be dealt
     * @param amount Initial damage amount
     * @return Updated damage amount
     */
    @Override
    public float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source, float amount) {
        //If not chestplate OR not full set, do not alter damage.
        if (slot != EquipmentSlot.CHEST || !isFullSet) { return amount; }

        //if taking damage from Magic source, reduce damage taken
        if (ModUtils.getDamageCategory(source) == ModUtils.DamageCategory.MAGIC) {
            //TODO: RE-IMPLEMENT CONFIGS
            //return amount * (1 - ((float)ModCommonConfigs.INFUSED_DIAMOND_MAGIC_DAMAGE_REDUCTION.get() / 100));
            return amount * 0.67f;
        }
        return amount;  //if reaches here, return original amount
    }

    /**
     * Appends text to the Item's hover tooltip (lore).
     * @param stack ItemStack corresponding to this Item
     * @param world Active world
     * @param tooltip List of tooltip texts to show
     * @param context TooltipContext denoting data like simple or advanced
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        ModUtils.appendInfusedDiamondEquipmentTooltip(tooltip, true);
    }
}
