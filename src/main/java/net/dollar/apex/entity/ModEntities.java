package net.dollar.apex.entity;

import net.dollar.apex.ModMain;
import net.dollar.apex.entity.custom.MysteriousSpecterEntity;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModEntities {
    private static final RegistryKey<EntityType<?>> obsidianGolemKey = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE, Identifier.of(ModMain.MOD_ID, "obsidian_golem"));
    private static final RegistryKey<EntityType<?>> mysteriousSpecterKey = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE, Identifier.of(ModMain.MOD_ID, "mysterious_specter"));

    public static final EntityType<ObsidianGolemEntity> OBSIDIAN_GOLEM = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(ModMain.MOD_ID, "obsidian_golem"),
            EntityType.Builder.create(ObsidianGolemEntity::new, SpawnGroup.MONSTER)
                    .dimensions(1.67f, 3.33f)
                    .build(obsidianGolemKey));

    public static final EntityType<MysteriousSpecterEntity> MYSTERIOUS_SPECTER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(ModMain.MOD_ID, "mysterious_specter"),
            EntityType.Builder.create(MysteriousSpecterEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6f, 1.8f)
                    .build(mysteriousSpecterKey));



    public static void register() {
        ModMain.LOGGER.info("Registering Entities for " + ModMain.MOD_ID);
    }
}
