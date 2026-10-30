package com.bqsqwad.datareceipt.tile

import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

/**
 * Quick Settings tile for toggling the Live Cost Bubble.
 * Stub implementation so the project builds cleanly.
 */
class LiveCostTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        qsTile?.apply {
            state = Tile.STATE_INACTIVE
            label = "Live Cost"
            updateTile()
        }
    }

    override fun onClick() {
        super.onClick()
        // TODO: toggle LiveCostBubbleService
        qsTile?.apply {
            state = if (state == Tile.STATE_ACTIVE) Tile.STATE_INACTIVE else Tile.STATE_ACTIVE
            updateTile()
        }
    }
}
