package net.damqn4etobg.endlessexpansion.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.phys.Vec3;

public class FlatTextureSheetParticle extends TextureSheetParticle {
    public float scale;
    protected FlatTextureSheetParticle(ClientLevel pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
    }

    protected FlatTextureSheetParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ModParticleRenderTypes.FLAT;
    }

    @Override
    public void render(VertexConsumer pBuffer, Camera pRenderInfo, float pPartialTicks) {
        Vec3 cameraPos = pRenderInfo.getPosition();
        float x = (float)(this.xo + (this.x - this.xo) * pPartialTicks - cameraPos.x);
        float y = (float)(this.yo + (this.y - this.yo) * pPartialTicks - cameraPos.y);
        float z = (float)(this.zo + (this.z - this.zo) * pPartialTicks - cameraPos.z);

        float scale = this.quadSize * this.scale;
        float half = scale / 2.0F;

        // Flat quad on the XZ plane (horizontal facing up)
        Vec3[] corners = new Vec3[] {
                new Vec3(-half, 0, -half),
                new Vec3(-half, 0, half),
                new Vec3(half, 0, half),
                new Vec3(half, 0, -half)
        };

        int light = this.getLightColor(pPartialTicks);
        TextureAtlasSprite sprite = this.sprite;
        float u0 = sprite.getU0();
        float u1 = sprite.getU1();
        float v0 = sprite.getV0();
        float v1 = sprite.getV1();

        // Top face
        pBuffer.vertex(x + (float)corners[0].x, y + (float)corners[0].y, z + (float)corners[0].z).uv(u0, v1).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        pBuffer.vertex(x + (float)corners[1].x, y + (float)corners[1].y, z + (float)corners[1].z).uv(u0, v0).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        pBuffer.vertex(x + (float)corners[2].x, y + (float)corners[2].y, z + (float)corners[2].z).uv(u1, v0).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        pBuffer.vertex(x + (float)corners[3].x, y + (float)corners[3].y, z + (float)corners[3].z).uv(u1, v1).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();

        // Bottom face
        pBuffer.vertex(x + (float)corners[3].x, y + (float)corners[3].y, z + (float)corners[3].z).uv(u1, v1).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        pBuffer.vertex(x + (float)corners[2].x, y + (float)corners[2].y, z + (float)corners[2].z).uv(u1, v0).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        pBuffer.vertex(x + (float)corners[1].x, y + (float)corners[1].y, z + (float)corners[1].z).uv(u0, v0).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
        pBuffer.vertex(x + (float)corners[0].x, y + (float)corners[0].y, z + (float)corners[0].z).uv(u0, v1).color(rCol, gCol, bCol, alpha).uv2(light).endVertex();
    }
}
