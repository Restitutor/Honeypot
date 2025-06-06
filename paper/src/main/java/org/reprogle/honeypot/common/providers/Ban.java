/*
 * Honeypot is a plugin written for Paper which assists with griefing auto-moderation
 *
 * Copyright (c) TerrorByte and Honeypot Contributors 2022 - 2025.
 *
 * This program is free software: You can redistribute it and/or modify it under
 *  the terms of the Mozilla Public License 2.0 as published by the Mozilla under the Mozilla Foundation.
 *
 * This program is distributed in the hope that it will be useful, but provided on an "as is" basis,
 * without warranty of any kind, either expressed, implied, or statutory, including,
 * without limitation, warranties that the Covered Software is free of defects, merchantable,
 * fit for a particular purpose or non-infringing. See the MPL 2.0 license for more details.
 *
 * For a full copy of the license in its entirety, please visit <https://www.mozilla.org/en-US/MPL/2.0/>
 */

package org.reprogle.honeypot.common.providers;

import com.google.inject.Inject;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.reprogle.honeypot.common.commands.CommandFeedback;

@Behavior(type = BehaviorType.BAN, name = "ban", icon = Material.BARRIER)
@SuppressWarnings("deprecation")
public class Ban extends BehaviorProvider {

    @Inject
    private CommandFeedback commandFeedback;

    @Override
    public boolean process(Player p, Block block) {
        String banReason = PlainTextComponentSerializer.plainText().serialize(commandFeedback.sendCommandFeedback("ban-reason"));
        String chatPrefix = PlainTextComponentSerializer.plainText().serialize(commandFeedback.getChatPrefix());

        Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), banReason, null,
                chatPrefix);
        p.kickPlayer(banReason);

        return true;
    }
}
