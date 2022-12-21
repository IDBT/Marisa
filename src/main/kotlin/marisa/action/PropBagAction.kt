package marisa.action

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.relics.*
import marisa.MarisaContinued
import marisa.powers.Marisa.PropBagPower
import marisa.relics.AmplifyWand

class PropBagAction : AbstractGameAction() {
    init {
        actionType = ActionType.CARD_MANIPULATION
        duration = Settings.ACTION_DUR_FAST
    }

    override fun update() {
        val p = AbstractDungeon.player
        MarisaContinued.logger.info("PropBagAction : Checking for relics")

        val relics = listOf(
            MeatOnTheBone(), MummifiedHand(), LetterOpener(), Shuriken(),
            GremlinHorn(), Sundial(), MercuryHourglass(), OrnamentalFan(),
            Kunai(), BlueCandle(), AmplifyWand()
        ).filterNot { p.hasRelic(it.relicId) }
        // TODO: refactor with Arrow

        when (relics.size) {
            0 -> {
                MarisaContinued.logger.info("PropBagAction : No relic to give")
            }

            else -> {
                val relic = relics.random()
                MarisaContinued.logger.info("PropBagAction : Giving relic : $relic")
                addToBot(ApplyPowerAction(p, p, PropBagPower(p, relic)))
            }
        }
        isDone = true
    }
}