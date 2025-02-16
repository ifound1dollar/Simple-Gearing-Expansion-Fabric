package net.dollar.apex.util;

import net.dollar.apex.ModMain;
import net.minecraft.item.Item;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * Contains helper methods to generate Smithing Upgrade Template items, which have custom tooltips that require
 *  numerous Identifiers and styles.
 */
public class ModSmithingUpgradeItemHelper {
    //region COBALT-STEEL Texts
    //private static final Text COBALT_UPGRADE = Text.literal("Cobalt-Steel Upgrade").formatted(Formatting.GRAY);
    private static final Text COBALT_UPGRADE_APPLIES_TO = Text.literal("Diamond Equipment").formatted(Formatting.BLUE);
    private static final Text COBALT_UPGRADE_INGREDIENTS = Text.literal("Cobalt-Steel Ingot").formatted(Formatting.BLUE);
    private static final Text COBALT_UPGRADE_BASE_SLOT_DESCRIPTION = Text.literal(
            "Add diamond armor, weapon, or tool");
    private static final Text COBALT_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Text.literal(
            "Add Cobalt-Steel Ingot");
    //endregion

    //region INFUSED DIAMOND Texts
    //private static final Text INFUSION_UPGRADE = Text.literal("Infused Gemstone Upgrade").formatted(Formatting.GRAY);
    private static final Text INFUSION_UPGRADE_APPLIES_TO = Text.literal("Diamond Equipment").formatted(Formatting.BLUE);
    private static final Text INFUSION_UPGRADE_INGREDIENTS = Text.literal("Infused Gemstone").formatted(Formatting.BLUE);
    private static final Text INFUSION_UPGRADE_BASE_SLOT_DESCRIPTION = Text.literal(
            "Add diamond armor, weapon, or tool");
    private static final Text INFUSION_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Text.literal(
            "Add Infused Gemstone");
    //endregion

    //region TUNGSTEN-CARBIDE Texts
    //private static final Text CARBIDE_UPGRADE = Text.literal("Tungsten-Carbide Upgrade").formatted(Formatting.GRAY);
    private static final Text CARBIDE_UPGRADE_APPLIES_TO = Text.literal("Diamond Equipment").formatted(Formatting.BLUE);
    private static final Text CARBIDE_UPGRADE_INGREDIENTS = Text.literal("Tungsten-Carbide Ingot").formatted(Formatting.BLUE);
    private static final Text CARBIDE_UPGRADE_BASE_SLOT_DESCRIPTION = Text.literal(
            "Add diamond armor, weapon, or tool");
    private static final Text CARBIDE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Text.literal(
            "Add Tungsten-Carbide Ingot");
    //endregion

    //region EMPTY SLOT RESOURCE LOCATIONS
    private static final Identifier EMPTY_SLOT_HELMET = Identifier.of("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_SLOT_CHESTPLATE = Identifier.of("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_SLOT_LEGGINGS = Identifier.of("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_SLOT_BOOTS = Identifier.of("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_HOE = Identifier.of("item/empty_slot_hoe");
    private static final Identifier EMPTY_SLOT_AXE = Identifier.of("item/empty_slot_axe");
    private static final Identifier EMPTY_SLOT_SWORD = Identifier.of("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_SHOVEL = Identifier.of("item/empty_slot_shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE = Identifier.of("item/empty_slot_pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT = Identifier.of("item/empty_slot_ingot");
    private static final Identifier EMPTY_SLOT_DIAMOND = Identifier.of("item/empty_slot_diamond");
    //endregion



    //SmithingTemplateItem CLASS HAS EXAMPLE OF CORRECT Text.translatable() USAGE
    //appendHoverText() IN SAME CLASS HAS super() METHOD CALLED FIRST WHICH PLACES THOSE TextS BELOW ENCHANTMENTS???

    /**
     * Creates a Gilded Upgrade Smithing Template, which has special tooltips that are handled in this
     *  method and class.
     * @return New Gilded Bronze SmithingTemplateItem
     */
    public static SmithingTemplateItem createCobaltUpgradeTemplate() {
        return new SmithingTemplateItem(COBALT_UPGRADE_APPLIES_TO, COBALT_UPGRADE_INGREDIENTS,
                COBALT_UPGRADE_BASE_SLOT_DESCRIPTION, COBALT_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createUpgradeIconList(), createUpgradeMaterialList(true), new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM,
                        Identifier.of(ModMain.MOD_ID, "cobalt_upgrade_smithing_template"))));
    }

    /**
     * Creates an Infusion Upgrade Smithing Template, which has special tooltips that are handled in this
     *  method and class.
     * @return New Infused Diamond SmithingTemplateItem
     */
    public static SmithingTemplateItem createInfusionUpgradeTemplate() {
        return new SmithingTemplateItem(INFUSION_UPGRADE_APPLIES_TO, INFUSION_UPGRADE_INGREDIENTS,
                INFUSION_UPGRADE_BASE_SLOT_DESCRIPTION, INFUSION_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createUpgradeIconList(), createUpgradeMaterialList(false), new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM,
                Identifier.of(ModMain.MOD_ID, "infusion_upgrade_smithing_template"))));
    }

    /**
     * Creates a Carbide Upgrade Smithing Template, which has special tooltips that are handled in this
     *  method and class.
     * @return New Tungsten-Carbide SmithingTemplateItem
     */
    public static SmithingTemplateItem createCarbideUpgradeTemplate() {
        return new SmithingTemplateItem(CARBIDE_UPGRADE_APPLIES_TO, CARBIDE_UPGRADE_INGREDIENTS,
                CARBIDE_UPGRADE_BASE_SLOT_DESCRIPTION, CARBIDE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createUpgradeIconList(), createUpgradeMaterialList(true), new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM,
                        Identifier.of(ModMain.MOD_ID, "carbide_upgrade_smithing_template"))));
    }



    /**
     * Generates a List of Identifiers pointing to empty equipment icons.
     * @return List of empty equipment icon Identifiers
     */
    private static List<Identifier> createUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE,
                EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    /**
     * Generates a List of Identifiers pointing to empty upgrade ingredient icons.
     * @param isIngot Whether the upgrade material is shaped like a Diamond or an Ingot
     * @return List (single item) of empty upgrade ingredient Identifiers
     */
    private static List<Identifier> createUpgradeMaterialList(boolean isIngot) {
        if (isIngot) {
            return List.of(EMPTY_SLOT_INGOT);
        } else {
            return List.of(EMPTY_SLOT_DIAMOND);
        }
    }
}
