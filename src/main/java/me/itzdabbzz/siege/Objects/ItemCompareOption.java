package me.itzdabbzz.siege.Objects;


public class ItemCompareOption {
    private boolean material;
    private boolean amount;
    private boolean damageValue;
    private boolean displayName;
    private boolean lore;
    private boolean enchantments;
    private boolean unbreakable;
    private boolean color;
    private boolean skullOwner;

    public ItemCompareOption() {
        material = true;
        amount = false;
        damageValue = false;
        displayName = false;
        lore = false;
        enchantments = false;
        unbreakable = false;
        color = false;
        skullOwner = false;
    }

    public ItemCompareOption(Builder builder) {
        this.material = builder.material;
        this.amount = builder.amount;
        this.damageValue = builder.damageValue;
        this.displayName = builder.displayName;
        this.lore = builder.lore;
        this.enchantments = builder.enchantments;
        this.unbreakable = builder.unbreakable;
        this.color = builder.color;
        this.skullOwner = builder.skullOwner;
    }

    public static class Builder{
        private boolean material;
        private boolean amount;
        private boolean damageValue;
        private boolean displayName;
        private boolean lore;
        private boolean enchantments;
        private boolean unbreakable;
        private boolean color;
        private boolean skullOwner;

        public Builder(){
            material = true;
            amount = false;
            damageValue = false;
            displayName = false;
            lore = false;
            enchantments = false;
            unbreakable = false;
            color = false;
            skullOwner = false;
        }

        public Builder withMaterial(boolean material){ this.material = material; return this; }
        public Builder withAmount(boolean amount){ this.amount = amount; return this; }
        public Builder withDamageValue(boolean damageValue){ this.damageValue = damageValue; return this; }
        public Builder withDisplayName(boolean displayName){ this.displayName = displayName; return this; }
        public Builder withLore(boolean lore){ this.lore = lore; return this; }
        public Builder withEnchantments(boolean enchantments){ this.enchantments = enchantments; return this; }
        public Builder withUnbreakable(boolean unbreakable){ this.unbreakable = unbreakable; return this; }
        public Builder withColor(boolean color){ this.color = color; return this; }
        public Builder withSkullOwner(boolean skullOwner){ this.skullOwner = skullOwner; return this; }

        public ItemCompareOption build(){
            return new ItemCompareOption(this);
        }
    }

    public boolean isMaterial() {
        return material;
    }
    public void setMaterial(boolean material) {
        this.material = material;
    }
    public boolean isAmount() {
        return amount;
    }
    public void setAmount(boolean amount) {
        this.amount = amount;
    }
    public boolean isDamageValue() {
        return damageValue;
    }
    public void setDamageValue(boolean damageValue) {
        this.damageValue = damageValue;
    }
    public boolean isDisplayName() {
        return displayName;
    }
    public void setDisplayName(boolean displayName) {
        this.displayName = displayName;
    }
    public boolean isLore() {
        return lore;
    }
    public void setLore(boolean lore) {
        this.lore = lore;
    }
    public boolean isEnchantments() {
        return enchantments;
    }
    public void setEnchantments(boolean enchantments) {
        this.enchantments = enchantments;
    }
    public boolean isUnbreakable() {
        return unbreakable;
    }
    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }
    public boolean isColor() {
        return color;
    }
    public void setColor(boolean color) {
        this.color = color;
    }
    public boolean isSkullOwner() {
        return skullOwner;
    }
    public void setSkullOwner(boolean skullOwner) {
        this.skullOwner = skullOwner;
    }
}