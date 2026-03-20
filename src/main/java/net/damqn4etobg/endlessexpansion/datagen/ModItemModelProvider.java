package net.damqn4etobg.endlessexpansion.datagen;

import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EndlessExpansion.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // old stuff
        evenSimplerBlockItem(ModBlocks.URANIUM_BLOCK);
        simpleItem(ModItems.URANIUM_INGOT);
        simpleItem(ModItems.NUCLEAR_WASTE_BUCKET);

        simpleItem(ModItems.LUMINITE);
        handheldItem(ModItems.LUMINITE_STAFF);
        simpleItem(ModItems.RAW_COBALT);
        simpleItem(ModItems.COBALT_INGOT);
        simpleItem(ModItems.CELESTIAL_INGOT);
        evenSimplerBlockItem(ModBlocks.TITANUM_SOIL);
        evenSimplerBlockItem(ModBlocks.PACKED_SNOW_BLOCK);
        evenSimplerBlockItem(ModBlocks.GLACIER_BRICKS);
        evenSimplerBlockItem(ModBlocks.DEEPSLATE_LUMINITE_ORE);
        evenSimplerBlockItem(ModBlocks.DEEPSLATE_COBALT_ORE);
        evenSimplerBlockItem(ModBlocks.COBALT_ORE);

        handheldItem(ModItems.COBALT_SWORD);
        handheldItem(ModItems.COBALT_PICKAXE);
        handheldItem(ModItems.COBALT_SHOVEL);
        handheldItem(ModItems.COBALT_AXE);
        handheldItem(ModItems.COBALT_HOE);
        handheldItem(ModItems.COBALT_PAXEL);

        handheldItem(ModItems.CELESTIAL_SWORD);
        handheldItem(ModItems.CELESTIAL_PICKAXE);
        handheldItem(ModItems.CELESTIAL_SHOVEL);
        handheldItem(ModItems.CELESTIAL_AXE);
        handheldItem(ModItems.CELESTIAL_HOE);
        handheldItem(ModItems.CELESTIAL_PAXEL);

        simpleBlockItemBlockTexture(ModBlocks.MYSTICAL_EVERBLUE_OCRHID);
        simpleItem(ModItems.MYSTICAL_EVERBLUE_POWDER);

        trimmedArmorItem(ModItems.COBALT_HELMET);
        trimmedArmorItem(ModItems.COBALT_CHESTPLATE);
        trimmedArmorItem(ModItems.COBALT_LEGGINGS);
        trimmedArmorItem(ModItems.COBALT_BOOTS);
        trimmedArmorItem(ModItems.CELESTIAL_HELMET);
        trimmedArmorItem(ModItems.CELESTIAL_CHESTPLATE);
        trimmedArmorItem(ModItems.CELESTIAL_LEGGINGS);
        trimmedArmorItem(ModItems.CELESTIAL_BOOTS);

        simpleItem(ModItems.ARBOR_STICK);
        simpleBlockItemBlockTexture(ModBlocks.ARBOR_SAPLING);
        fenceItem(ModBlocks.ARBOR_FENCE, ModBlocks.ARBOR_PLANKS);
        buttonItem(ModBlocks.ARBOR_BUTTON, ModBlocks.ARBOR_PLANKS);
        evenSimplerBlockItem(ModBlocks.ARBOR_STAIRS);
        evenSimplerBlockItem(ModBlocks.ARBOR_SLAB);
        evenSimplerBlockItem(ModBlocks.ARBOR_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.ARBOR_FENCE_GATE);
        trapdoorItem(ModBlocks.ARBOR_TRAPDOOR);
        simpleBlockItem(ModBlocks.ARBOR_DOOR);
        simpleItem(ModItems.ARBOR_BOAT);
        simpleItem(ModItems.ARBOR_CHEST_BOAT);
        simpleItem(ModItems.ARBOR_SIGN);
        simpleItem(ModItems.ARBOR_HANGING_SIGN);

        simpleItem(ModItems.PYRONIUM);
        evenSimplerBlockItem(ModBlocks.PYRONIUM_ORE);
        evenSimplerBlockItem(ModBlocks.BLACKSTONE_PYRONIUM_ORE);
        simpleItem(ModItems.PYRONIUM_INFUSED_COAL);
        //evenSimplerBlockItem(ModBlocks.INFUSER);

        //evenSimplerBlockItem(ModBlocks.SMALL_RED_MUSHROOM);
        //evenSimplerBlockItem(ModBlocks.SMALL_BROWN_MUSHROOM);

        evenSimplerBlockItem(ModBlocks.DEEPSLATE_BLACK_OPAL_ORE);
        simpleItem(ModItems.BLACK_OPAL);
        simpleItem(ModItems.SHADOW_ESSENCE);
        simpleItem(ModItems.SHADOWSTEEL_INGOT);

        handheldItem(ModItems.SHADOWSTEEL_SWORD);
        handheldItem(ModItems.SHADOWSTEEL_PICKAXE);
        handheldItem(ModItems.SHADOWSTEEL_SHOVEL);
        handheldItem(ModItems.SHADOWSTEEL_AXE);
        handheldItem(ModItems.SHADOWSTEEL_HOE);
        handheldItem(ModItems.SHADOWSTEEL_PAXEL);

        trimmedArmorItem(ModItems.SHADOWSTEEL_HELMET);
        trimmedArmorItem(ModItems.SHADOWSTEEL_CHESTPLATE);
        trimmedArmorItem(ModItems.SHADOWSTEEL_LEGGINGS);
        trimmedArmorItem(ModItems.SHADOWSTEEL_BOOTS);

        simpleItem(ModItems.LUMINITE_ESSENCE_BUCKET);
        evenSimplerBlockItem(ModBlocks.LUMINITE_BLOCK);
        //simpleItem(ModItems.FIRE_CORE);
        //simpleItem(ModItems.GLACIAL_CRYSTAL);
        simpleItem(ModItems.RAW_SAPPHIRE);
        simpleItem(ModItems.REFINED_SAPPHIRE);
        simpleBlockItemBlockTexture(ModBlocks.SAPPHIRE_CLUSTER);
        simpleItem(ModItems.GLASS_VIAL);
        simpleItem(ModItems.MYSTICAL_COOKIE);
        //evenSimplerBlockItem(ModBlocks.MYSTICAL_COOKIE_JAR);
        evenSimplerBlockItem(ModBlocks.MYSTICAL_GLASS);

        evenSimplerBlockItem(ModBlocks.BLISSWOOD_LOG);
        evenSimplerBlockItem(ModBlocks.STRIPPED_BLISSWOOD_LOG);
        evenSimplerBlockItem(ModBlocks.BLISSWOOD_WOOD);
        evenSimplerBlockItem(ModBlocks.STRIPPED_BLISSWOOD_WOOD);
        evenSimplerBlockItem(ModBlocks.BLISSWOOD_LEAVES);
        fenceItem(ModBlocks.BLISSWOOD_FENCE, ModBlocks.BLISSWOOD_PLANKS);
        buttonItem(ModBlocks.BLISSWOOD_BUTTON, ModBlocks.BLISSWOOD_PLANKS);
        evenSimplerBlockItem(ModBlocks.BLISSWOOD_STAIRS);
        evenSimplerBlockItem(ModBlocks.BLISSWOOD_SLAB);
        evenSimplerBlockItem(ModBlocks.BLISSWOOD_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.BLISSWOOD_FENCE_GATE);
        simpleItem(ModItems.BLISSWOOD_SIGN);
        simpleItem(ModItems.BLISSWOOD_HANGING_SIGN);
        simpleItem(ModItems.BLISSWOOD_BOAT);
        simpleItem(ModItems.BLISSWOOD_CHEST_BOAT);
        trapdoorItem(ModBlocks.BLISSWOOD_TRAPDOOR);
        simpleBlockItem(ModBlocks.BLISSWOOD_DOOR);
        simpleItem(ModItems.BLISSWOOD_STICK);
        simpleBlockItemBlockTexture(ModBlocks.BLISSWOOD_SAPLING);

        simpleItem(ModItems.COBALT_ROD);
        simpleItem(ModItems.COBALT_BOLT);
        simpleItem(ModItems.MYSTICAL_COBALT_BOLT);

        evenSimplerBlockItem(ModBlocks.ABYSSAL_SILT);
        simpleItem(ModItems.SAPPHIRE_DIVING_HELMET);
        simpleItem(ModItems.IMPACT_EXPLODE_ENCHANTED_BOOK);
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID,"item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(EndlessExpansion.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID,"item/" + item.getId().getPath()));
    }

    // Shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = EndlessExpansion.MODID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.fromNamespaceAndPath(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(EndlessExpansion.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
}
