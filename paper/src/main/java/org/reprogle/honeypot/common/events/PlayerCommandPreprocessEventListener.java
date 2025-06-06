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

package org.reprogle.honeypot.common.events;

import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.reprogle.honeypot.Honeypot;
import org.reprogle.honeypot.common.commands.CommandFeedback;

public class PlayerCommandPreprocessEventListener implements Listener {

    private final Honeypot plugin;
    private final CommandFeedback commandFeedback;

    /**
     * Create private constructor to hide the implicit one
     */
    @Inject
    PlayerCommandPreprocessEventListener(Honeypot plugin, CommandFeedback commandFeedback) {
        this.plugin = plugin;
        this.commandFeedback = commandFeedback;
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void playerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().startsWith("/hpteleport")) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission("honeypot.teleport")) {
                String rawCommand = event.getMessage();
                String processedCommand = rawCommand.replace("/hpteleport",
                        "minecraft:tp " + event.getPlayer().getName());

                Bukkit.getGlobalRegionScheduler().run(plugin, scheduledTask ->
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), processedCommand));
            } else {
                event.getPlayer().sendMessage(commandFeedback.sendCommandFeedback("nopermission"));
            }

        }
    }

}
