package eastonium.nuicraft.client.model;

import eastonium.nuicraft.NuiCraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NuiCraftModelLayers {
    public static final ModelLayerLocation MAHI = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mahi"), "main");
}
