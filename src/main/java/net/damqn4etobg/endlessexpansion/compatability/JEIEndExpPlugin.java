package net.damqn4etobg.endlessexpansion.compatability;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.damqn4etobg.endlessexpansion.recipe.InfuserRecipe;
import net.damqn4etobg.endlessexpansion.screen.InfuserScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIEndExpPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(EndlessExpansion.MODID, "jei_plugin");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new InfuserCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<InfuserRecipe> infusingRecipes = recipeManager.getAllRecipesFor(InfuserRecipe.Type.INSTANCE);
        registration.addRecipes(InfuserCategory.INFUSING_TYPE, infusingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(InfuserScreen.class, 51, 47, 22, 7,
                InfuserCategory.INFUSING_TYPE);
        registration.addRecipeClickArea(InfuserScreen.class, 105, 47, 22, 7,
                InfuserCategory.INFUSING_TYPE);
    }
}
