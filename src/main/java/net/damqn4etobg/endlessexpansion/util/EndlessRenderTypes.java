package net.damqn4etobg.endlessexpansion.util;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.damqn4etobg.endlessexpansion.EndlessExpansion;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;

public class EndlessRenderTypes {
    public static final RenderPipeline TEST_PIPELINE = RenderPipeline.builder(RenderPipelines.BLOCK_SNIPPET)
            .withLocation(Identifier.parse("endlessexpansion:pipeline/test"))
//            .withVertexShader(Identifier.parse("endlessexpansion:core/test"))
            .withFragmentShader(Identifier.parse("endlessexpansion:core/test"))
            .withSampler("Sampler0").build();

    public static final RenderType TEST = RenderType.create("endlessexpansion:test", RenderSetup.builder(TEST_PIPELINE)
            .withTexture("Sampler0", Identifier.fromNamespaceAndPath(EndlessExpansion.MODID, "textures/block/arbor_log.png")).createRenderSetup());

    public static void prints() {
        System.out.println("ENDLESS EXP RENDER: " + TEST_PIPELINE);
        System.out.println("ENDLESS EXP RENDER 2: " + TEST);
    }
}
