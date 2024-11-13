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
        for (int i = 0; i < items.length; i++) {

            if (items[i].quality > 0 && !items[i].name.equals("Aged Brie") && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert" && !items[i].name.equals("Sulfuras, Hand of Ragnaros"))
            items[i].sellIn = items[i].sellIn - 1;
            if (items[i].sellIn < 0) {
                items[i].quality = items[i].quality - 2;
            }
            if (items[i].sellIn > 0){
                items[i].quality = items[i].quality - 1;
            }


            if ((items[i].name.equals("Aged Brie") && items[i].quality < 50) || (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert") && items[i].sellIn < 11 && items[i].quality < 50)){
                items[i].quality = items[i].quality + 1;
            }


            if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                items[i].quality = 0;
            }

            if (items[i].name.equals("Aged Brie") && (items[i].quality < 50)) {
                items[i].quality = items[i].quality + 1;

            }
        }
    }
}

