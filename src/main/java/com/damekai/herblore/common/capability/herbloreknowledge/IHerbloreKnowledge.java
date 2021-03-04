package com.damekai.herblore.common.capability.herbloreknowledge;

import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.item.ItemReagent;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerEntity;

public interface IHerbloreKnowledge
{
    ImmutableList<Flask> getKnownFlasks(ItemReagent reagent);

    void setFlaskKnown(PlayerEntity playerEntity, ItemReagent reagent, Flask flask);
}
