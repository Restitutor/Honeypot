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

package org.reprogle.honeypot.common.utils.integrations;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.reprogle.honeypot.common.utils.HoneypotConfigManager;

/**
 * A small helper class for utilizing GriefPrevention
 */
public class GriefPreventionAdapter {

	private final HoneypotConfigManager configManager;

	protected GriefPreventionAdapter(HoneypotConfigManager configManager) {
		this.configManager = configManager;
	}

	/**
	 * Check if the player has permission
	 *
	 * @param player   The player placing the block
	 * @param location The location of the block being placed (It may be different
	 *                 from the player's location)
	 * @return True if allowed, false if not
	 */
	public boolean isAllowed(Player player, Location location) {
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(location, true, null);

		if (claim != null) {
			if (!configManager.getPluginConfig().getBoolean("respect-griefprevention"))
				return false;

			return claim.checkPermission(player, ClaimPermission.Build, null) == null;
		}

		return true;
	}
}
