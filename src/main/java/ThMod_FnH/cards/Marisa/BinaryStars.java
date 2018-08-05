package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.ThMod;
import ThMod_FnH.action.BinaryStarsAction;
import ThMod_FnH.cards.special.BlackFlareStar;
import ThMod_FnH.cards.special.WhiteDwarf;
import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

public class BinaryStars extends CustomCard {
	public static final String ID = "BinaryStars";
	public static final String IMG_PATH = "img/cards/Defend.png";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int AMP = 1;
	
	public BinaryStars() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.MARISA_COLOR, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (ThMod.Amplified(this.costForTurn+AMP, AMP)) {
			AbstractCard c = new BlackFlareStar();
			if (this.upgraded)
				c.upgrade();
			AbstractDungeon.actionManager.addToBottom(
					new MakeTempCardInHandAction(c,1));
			c = new WhiteDwarf();
			if (this.upgraded)
				c.upgrade();
			AbstractDungeon.actionManager.addToBottom(
					new MakeTempCardInHandAction(c,1));
		} else {
			AbstractDungeon.actionManager.addToBottom(
					new BinaryStarsAction( this.upgraded));
		}
		
	}

	public AbstractCard makeCopy() {
		return new BinaryStars();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = cardStrings.DESCRIPTION;
			initializeDescription();
		}
	}
}