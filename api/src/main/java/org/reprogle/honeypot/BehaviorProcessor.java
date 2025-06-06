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

package org.reprogle.honeypot;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.reprogle.honeypot.common.providers.BehaviorProvider;

// Return value can be used for other plugins that utilize Behavior Providers
@SuppressWarnings("UnusedReturnValue")
public class BehaviorProcessor {
    /**
     * This method calls the correct processor function, depending on if the type of
     * the behavior provider is <code>BehaviorTypes.CUSTOM</code> or not
     *
     * @param behavior The behavior provider to process
     * @param p        The player to process against
     * @param block    The block that was involved in the event, may be null in some
     *                 rare instances
     * @return True if successful, false if not
     */
    public static boolean process(@NotNull BehaviorProvider behavior, Player p, Block block) {
        if (Registry.getBehaviorRegistry().isInitialized()
                && Registry.getBehaviorRegistry().getBehaviorProvider(behavior.getProviderName()) != null) {
            return behavior.process(p, block);
        }

        return false;
    }

}
