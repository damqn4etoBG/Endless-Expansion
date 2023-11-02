package net.damqn4etobg.endlessexpansion.compatability;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.block.ModBlocks;
import net.damqn4etobg.endlessexpansion.recipe.InfuserRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class InfuserCategory implements IRecipeCategory<InfuserRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(EndlessExpansion.MODID, "infusing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(EndlessExpansion.MODID,
            "textures/gui/infuser_gui.png");

    public static final RecipeType<InfuserRecipe> INFUSING_TYPE =
            new RecipeType<>(UID, InfuserRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public InfuserCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.INFUSER.get()));
    }

    @Override
    public RecipeType<InfuserRecipe> getRecipeType() {
        return INFUSING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.endlessexpansion.infuser");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InfuserRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 26, 42).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 134, 42).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 42).addItemStack(recipe.getResultItem(null));
    }
}
