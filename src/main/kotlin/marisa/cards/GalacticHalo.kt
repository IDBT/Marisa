package marisa.cards

import basemod.abstracts.CustomCard
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster
import marisa.MarisaContinued
import marisa.patches.AbstractCardEnum
import marisa.powers.Marisa.ChargeUpPower

class GalacticHalo : CustomCard(
    ID, NAME, IMG_PATH,
    COST, DESCRIPTION,
    CardType.SKILL,
    AbstractCardEnum.MARISA_COLOR,
    CardRarity.COMMON,
    CardTarget.SELF
) {
    init {
        baseMagicNumber = STC
        magicNumber = baseMagicNumber
        baseBlock = BLC
        block = baseBlock
    }

    override fun use(p: AbstractPlayer, unused: AbstractMonster?) {
        MarisaContinued.logger.info(
            """GalacticHalo : use : magicNumber : $magicNumber baseMagicNumber : $baseMagicNumber"""
        )
        addToBot(
            GainBlockAction(p, p, block)
        )
        addToBot(
            ApplyPowerAction(
                p, p,
                ChargeUpPower(p, magicNumber),
                magicNumber
            )
        )
    }

    override fun makeCopy(): AbstractCard = GalacticHalo()

    override fun upgrade() {
        if (upgraded) return
        upgradeName()
        upgradeMagicNumber(UPG_STC)
        upgradeBlock(UPG_BLC)
    }

    companion object {
        const val ID = "GalacticHalo"
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(ID)
        val NAME = cardStrings.NAME
        val DESCRIPTION = cardStrings.DESCRIPTION
        const val IMG_PATH = "img/cards/halo.png"
        private const val COST = 2
        private const val STC = 2
        private const val UPG_STC = 1
        private const val BLC = 12
        private const val UPG_BLC = 2
    }
}
