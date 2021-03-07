package com.damekai.herblore.common.capability.herbloreknowledge;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.item.ItemReagent;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerEntity;

public interface IHerbloreKnowledge
{
    ImmutableList<FlaskEffect> getKnownFlaskEffects(ItemReagent reagent);

    void setFlaskEffectKnown(PlayerEntity playerEntity, ItemReagent reagent, FlaskEffect flaskEffect);
}
