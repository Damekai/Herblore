package com.damekai.herblore.common.container;

import com.damekai.herblore.common.Herblore;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers
{
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Herblore.MOD_ID);

    public static final RegistryObject<ContainerType<ContainerFlaskStation>> FLASK_STATION = CONTAINERS.register("flask_station", () -> IForgeContainerType.create((id, playerInventory, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = playerInventory.player.level;
        return new ContainerFlaskStation(id, world, pos, playerInventory);
    }));
}
