package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }

    private void decreaseSellIn(Item item) {
        item.sellIn --;
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            item.quality ++;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality --;
        }
    }

    private void updateAgedBrieQuality(Item item) {
        increaseQuality(item);
        if (isExpired(item)) {
            increaseQuality(item);
        }
    }

    private void updateBackstagePassQuality(Item item) {
        increaseQuality(item);

        if (item.sellIn < 10) {
            increaseQuality(item);
        }
        if (item.sellIn < 5) {
            increaseQuality(item);
        }

        if (isExpired(item)) {
            item.quality = 0;
        }
    }

    private void updateStandardItemQuality(Item item) {
        decreaseQuality(item);

        if (isExpired(item)) {
            decreaseQuality(item);
        }
    }
}

public void updateQuality() {
    for (Item item : items) {
        if (isSulfuras(item)) {
            continue; // Skip Sulfuras items as their quality does not change
        }

        decreaseSellIn(item);

        if (isAgedBrie(item)) {
            updateAgedBrieQuality(item);
            continue;
        }

        if (isBackstagePass(item)) {
            updateBackstagePassQuality(item);
            continue;
        }

        updateStandardItemQuality(item);
    }
}
